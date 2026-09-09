# SPEC TÉCNICO — Proyecto SOEM Oficial
### Documento de handoff para continuar desarrollo con Claude Code

> Este documento es autocontenido: no depende de conversaciones previas. Contiene el contexto de negocio, el stack técnico exacto, el estado actual del código, y las tareas pendientes con criterios de aceptación. Léelo completo antes de tocar código.

---

## 1. Contexto del proyecto

**SOEM Oficial** es una tienda en línea de ropa (hombres, mujeres, niños, niñas) que ya tiene una base de código funcional construida en Java/Jakarta EE. El objetivo de este documento es guiar la **continuación** del desarrollo, no un inicio desde cero.

- **Moneda:** peso colombiano (COP).
- **Identidad visual:** blanco, negro, dorado. Diseño moderno y juvenil.
- **Modelo de pago:** manual vía Nequi (QR + comprobante por WhatsApp), sin pasarela de pago automatizada. Todo pedido queda pendiente hasta que un administrador aprueba el pago manualmente.

---

## 2. Stack técnico — OBLIGATORIO, no desviarse

| Aspecto | Valor exacto | Notas |
|---|---|---|
| Tipo de proyecto | **NetBeans Java Web Application (Ant)** | **NO es Maven.** No agregar `pom.xml` ni convertir el proyecto. |
| IDE | Apache NetBeans IDE 20 | |
| JDK | 17 | |
| Servidor de aplicaciones | **GlassFish Server 7.0** | Implica Jakarta EE 10 |
| Namespace de Servlets/Filtros | `jakarta.servlet.*` | **NUNCA** `javax.servlet.*` — no compila en GlassFish 7.0 |
| Base de datos | MariaDB gestionada por **XAMPP** (módulo "MySQL" del panel de XAMPP) | Debe estar encendida antes de correr el proyecto |
| Administración de BD | MySQL Workbench (diseño de esquema) + phpMyAdmin de XAMPP (consultas rápidas) | |
| Driver JDBC | `mysql-connector-java-8.0.12.jar` (ya está en `web/WEB-INF/lib/`) | Compatible con MariaDB sin cambios |
| Gestión de dependencias | JARs agregados manualmente en **Libraries** de NetBeans | No usar Maven/Gradle para agregar librerías |
| Frontend | Bootstrap 5 (CDN) + CSS propio | Paleta: blanco `#FFFFFF`, negro `#0A0A0A`, dorado `#C9A227` (ajustar si el proyecto ya define otros valores en `web/css/estilo.css`) |
| Vistas | JSP con scriptlets (`<% %>`) — así está el proyecto actualmente | Ver sección 5 sobre escape de salida — es obligatorio al imprimir datos de usuario |
| Patrón de paquetes Java | `Controlador` (DAOs), `Modelo` (POJOs), `Servlets` (controladores web), `Seguridad` (auth/CSRF/filtros) | Mantener esta convención para todo código nuevo — no crear paquetes paralelos tipo `controller`/`dao`/`model` en inglés |
| Hash de contraseñas | PBKDF2-HMAC-SHA256 con salt vía `Seguridad.SeguridadAplicacion` (ya implementado) | No reemplazar ni duplicar con otro método |
| Empaquetado final | `.war` generado por NetBeans (Clean and Build) | |

---

## 3. Estado actual del código (ya construido, no reconstruir)

Ubicación de referencia en el proyecto: `src/java/` con subpaquetes `Controlador`, `Modelo`, `Servlets`, `Seguridad`, y `Pruebas` (clases de test manual, ver sección 6).

### Ya funcional y probado:
- **Autenticación:** `LoginServlet`, `RegisterServlet`, `LogoutServlet` con hash PBKDF2 y protección CSRF.
- **Seguridad transversal:** `CabecerasSeguridadFilter` (headers HTTP: `X-Frame-Options`, `X-Content-Type-Options`, HSTS condicional), `SeguridadAplicacion` (CSRF, hash de contraseñas).
- **Catálogo público:** `HomeServlet`, `CategoriaServlet`, `ProductoServlet` — breadcrumb, etiqueta "SIN STOCK", cálculo de precio de oferta (precio anterior, ahorro en $ y %), formato de moneda `es-CO`.
- **Carrito:** `CartServlet`, funciona en sesión sin requerir login.
- **Usuario logueado:** `WishlistServlet`, `ProfileServlet` — correctamente protegidos verificando `usuarioId` en sesión.
- **Checkout:** `CheckoutServlet` + `TiendaDAO.crearOrden()` — usa `SELECT ... FOR UPDATE` (bloqueo de fila), transacción con commit/rollback, tabla `Reserva_Stock` con expiración a 7 días. Esta lógica es correcta y **no debe modificarse sin entender el flujo completo primero**.
- **Admin (parcial):** `AdminServlet` — dashboard con contadores (usuarios, productos, compras, monto vendido) y aprobación/rechazo de pedidos vía `TiendaDAO.actualizarEstado()` (libera stock reservado si se rechaza).
- **Esquema de base de datos:** `database.sql`, 21 tablas, incluyendo `Producto`, `Categoria`, `Usuario`, `Cabeza_Factura`, `Detalle_Factura`, `Reserva_Stock`, `Roles`, `Permisos`.
- **DAOs:** 17 clases en `Controlador/*DAO.java`, todas usando `PreparedStatement` (sin inyección SQL). Ya tienen métodos de inserción/actualización/eliminación para todas las entidades, aunque no todos están conectados a un Servlet todavía (ver sección 4).

---

## 4. Tareas pendientes — en orden de ejecución

### TAREA 1 (prioridad máxima) — Cerrar XSS almacenado
**Problema:** los JSP imprimen datos de usuario sin escapar (`<%=usuarioNombre%>` en `header.jsp`, `<%=p.get("Nombre_Usuario")%>` en `profile.jsp`, etc.). `RegisterServlet.limpiar()` solo hace `trim()`, no sanitiza HTML/JS. Un usuario puede registrarse con nombre `<script>...</script>` y ese script se ejecuta en el navegador de cualquiera que vea su nombre.

**Qué hacer:**
- Crear `Util.escapeHtml(String)` (o usar una librería como `org.apache.commons.text.StringEscapeUtils` si se agrega como JAR) que escape `< > & " '`.
- Aplicarlo en **todo** `<%= %>` que imprima un dato que provenga, directa o indirectamente, de un input de usuario (nombre, apellido, dirección, teléfono, mensajes de error que reflejen el input).
- Revisar especialmente: `header.jsp` (usuarioNombre), `profile.jsp` (todos los campos del formulario), `login.jsp`/`register.jsp` (mensajes de error, aunque estos son generados por el servidor y no directamente por el usuario, revisar igual).

**Criterio de aceptación:** registrar un usuario con nombre `<script>alert(1)</script>` no debe ejecutar el script en ninguna vista donde se muestre el nombre.

### TAREA 2 — Eliminar código muerto e inseguro
- Borrar `Controlador/TokenSeguridad.java` (no se usa en ningún Servlet, tiene clave secreta hardcodeada `SoEmOficialClave256BitSegura2024` y usa AES en modo ECB, inseguro).
- Confirmar con `grep -r "TokenSeguridad"` que no queda ninguna referencia antes de borrar.

### TAREA 3 — CRUD de Categorías (admin)
- Crear `Servlets/AdminCategoriaServlet.java`, protegido con el mismo patrón `admin()` que usa `AdminServlet` (verificar `usuarioRol == 1` en sesión).
- Usar `CategoriaDAO` existente; agregar métodos de escritura si faltan (crear/actualizar/eliminar).
- Vista `web/vistas/admin_categorias.jsp`: tabla de categorías + formulario de alta/edición + eliminación con confirmación.
- Validar que no se elimine una categoría con productos asociados (o definir comportamiento `ON DELETE` en el esquema).
- Aplicar CSRF (`SeguridadAplicacion.csrfValido()`) en el `doPost`, igual que el resto de Servlets de escritura.

### TAREA 4 — CRUD de Productos + Ofertas (admin)
- Crear `Servlets/AdminProductoServlet.java` (mismo patrón de protección admin + CSRF).
- Crear, editar, eliminar productos y variantes (talla/color/stock) usando `ProductoDAO`, `TallaDAO`, `ColoresDAO`, `ProductosHasColoresDAO` (ya existen).
- Subida de imágenes: usar `@MultipartConfig` de `jakarta.servlet.annotation`, guardar en `web/img/productos/` y la ruta en BD.
- Agregar a la tabla `Producto` (si no existen ya) columnas para oferta: precio anterior, fecha inicio, fecha fin.
- Vista `web/vistas/admin_productos.jsp`: tabla filtrable por categoría y estado de stock, formulario de alta/edición.
- **Reutilizar la lógica ya escrita en `Pruebas/PruebaInsertarProducto.java`, `PruebaActualizarProducto.java`, `PruebaEliminarProducto.java`** — es más rápido adaptar ese código probado a un Servlet que escribirlo de cero.

### TAREA 5 — Identidad de marca configurable
- Nueva tabla `Configuracion_Sitio` (o similar): ruta de logo, ruta de favicon, colores personalizables (defaults: blanco/negro/dorado).
- `Servlets/AdminIdentidadServlet.java`: formulario para subir logo/favicon y definir colores.
- `header.jsp`/`footer.jsp` deben leer estos valores dinámicamente (variables CSS `:root { --color-dorado: ... }` inyectadas desde JSP) en vez de tenerlos fijos en `estilo.css`.

### TAREA 6 — Liberación automática de stock a los 7 días
**Problema:** hoy el stock reservado solo se libera si un admin rechaza manualmente el pedido (`TiendaDAO.actualizarEstado()`). Si nadie lo rechaza, el stock queda reservado indefinidamente aunque `Reserva_Stock.fecha_expiracion` ya haya pasado.

**Qué hacer:**
- Crear un `@WebListener` (`ServletContextListener`) que al iniciar la aplicación registre un `ScheduledExecutorService` corriendo cada hora.
- La tarea programada debe: buscar en `Reserva_Stock` filas con `estado='Reservado'` y `fecha_expiracion < NOW()`, y por cada una llamar a `TiendaDAO.actualizarEstado(ordenId, "Rechazado", "Pedido liberado automáticamente por vencimiento del plazo de pago")` — esto reutiliza toda la lógica ya existente de liberación de stock, no duplicarla.

### TAREA 7 — Limpieza final y empaquetado
- Sacar las 42 clases de `Pruebas/*.java` del build (moverlas fuera de `Source Packages` o a una carpeta de referencia externa al proyecto NetBeans) para que no se compilen dentro del `.war` final.
- Revisar `web.xml`: hay comentarios listando rutas que ya están mapeadas por `@WebServlet` — dejar las anotaciones como única fuente de verdad y limpiar los comentarios redundantes.
- Cambiar `<cookie-config><secure>false</secure></cookie-config>` en `web.xml` a `true` **solo** si el despliegue final corre bajo HTTPS (dejar `false` para pruebas locales en `http://localhost`).
- Generar el `.war` (Clean and Build en NetBeans) y confirmar que corre limpio en GlassFish 7.0 con la base de datos de XAMPP encendida.

---

## 5. Errores que NO se deben cometer (checklist obligatorio antes de cada commit)

### Seguridad
- [ ] Ningún `<%= %>` imprime un dato de usuario sin pasar por escape HTML.
- [ ] Ninguna clave secreta o credencial está hardcodeada en el código (usar variables de entorno como ya hace `Conexion.java` con `SOEM_DB_*`).
- [ ] Ningún cifrado simétrico nuevo usa modo ECB (usar `AES/GCM/NoPadding` con IV aleatorio si se necesita cifrado).
- [ ] No se introduce un segundo método de hash de contraseñas (usar siempre `SeguridadAplicacion.hashPassword()`).
- [ ] Todo `doPost` que escribe datos valida `SeguridadAplicacion.csrfValido(req)` antes de procesar.
- [ ] Ninguna consulta SQL se arma por concatenación de strings — siempre `PreparedStatement`.
- [ ] `cookie secure` está en `true` si y solo si el despliegue es HTTPS.

### Arquitectura
- [ ] No se crea un Servlet nuevo por cada acción — seguir el patrón `action=agregar/eliminar/actualizar` dentro de un único Servlet por módulo.
- [ ] Ninguna clase de `Pruebas/` queda incluida en el build del `.war` final.
- [ ] No queda código sin usar en el proyecto (verificar con `grep` antes de dar una clase por terminada).
- [ ] Las rutas de Servlets se definen en un solo lugar (`@WebServlet`), no duplicadas ni contradichas en `web.xml`.
- [ ] Ningún cambio de esquema de BD se aplica directamente sin probarlo antes contra una copia local de `database.sql`.

### Stock e inventario
- [ ] Ninguna aprobación/rechazo de pedido toca `Producto.cantidad_stock` o `Reserva_Stock` fuera de `TiendaDAO.actualizarEstado()`.
- [ ] La liberación automática por vencimiento (Tarea 6) reutiliza `actualizarEstado()`, no duplica la lógica de restaurar stock.

### Entorno
- [ ] No se agregan dependencias vía Maven/Gradle — todo JAR nuevo se agrega manualmente a Libraries en NetBeans.
- [ ] No se usa `javax.servlet.*` en ningún archivo nuevo — siempre `jakarta.servlet.*` (GlassFish 7.0 = Jakarta EE 10).
- [ ] Antes de correr el proyecto, el módulo MySQL de XAMPP está encendido (si no, `Conexion.getConn()` devuelve `null` silenciosamente — revisar siempre ese caso en código nuevo).
- [ ] Si se descomprime nuevamente algún respaldo del proyecto, verificar que no haya una carpeta duplicada anidada antes de sobrescribir código — confirmar cuál copia tiene `nbproject/`, `build.xml` y `dist/` en su raíz.

---

## 6. Convenciones de código a mantener

- Nombres de clases y paquetes en español, siguiendo el estilo ya usado (`Controlador`, `Modelo`, `Servlets`, `Seguridad`, `ProductoDAO`, `CategoriaDAO`).
- Cada Servlet nuevo debe seguir el mismo esqueleto que los existentes: `doGet` para mostrar/forward, `doPost` para procesar con validación CSRF y de sesión al inicio.
- Los Servlets de escritura deben verificar sesión/rol **antes** de cualquier otra lógica (patrón ya usado: `if(!admin(req)){res.sendError(403);return;}`).
- Formato de moneda: siempre `NumberFormat.getCurrencyInstance(new Locale("es","CO"))`, igual que en `producto.jsp`.
- Los estados de pedido son exactamente estos, en este orden, y no deben cambiarse los nombres (ya están usados en BD y en el frontend): `Verificacion de Pago`, `Pago Aprobado`, `Preparando Producto`, `Producto Enviado`, `Producto Entregado`, `Rechazado`.

---

## 7. Cómo verificar que todo sigue funcionando

Después de cada tarea, en NetBeans:
1. Encender el módulo MySQL de XAMPP.
2. Clean and Build del proyecto.
3. Run en GlassFish 7.0 (Deploy).
4. Probar manualmente: login, agregar producto al carrito, wishlist, checkout completo, y la funcionalidad nueva agregada en esa tarea.
5. Confirmar en la consola de GlassFish que no hay excepciones nuevas al iniciar ni al navegar.
