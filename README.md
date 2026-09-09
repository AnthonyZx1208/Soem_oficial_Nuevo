# 🛍️ SOEM OFICIAL - Tienda en Línea de Ropa

Plataforma e-commerce desarrollada en Java con JSP para la venta de ropa para hombres, mujeres, niños y niñas con colores blanco, negro y dorado.

---

## 📋 Contenidos del Proyecto

```
SOEM_Oficial_Completa/
├── src/
│   ├── java/
│   │   ├── Controlador/          # DAOs y clases de conexión
│   │   ├── Modelo/               # Clases de entidades
│   │   ├── Pruebas/              # Clases de prueba
│   │   └── Servlets/             # Servlets de la aplicación
│   └── ...
├── web/
│   ├── vistas/                   # Archivos JSP
│   ├── css/                      # Estilos CSS
│   ├── js/                       # Scripts JavaScript
│   ├── img/                      # Imágenes
│   └── WEB-INF/
├── database.sql                  # Script SQL para la base de datos
├── build.xml                     # Archivo de construcción Ant
└── nbproject/                    # Configuración NetBeans

```

---

## 🚀 Requisitos de Instalación

- **Java JDK 8 o superior**
- **Apache Tomcat 9 o superior**
- **MariaDB 10.4.32 o MySQL 5.7+**
- **NetBeans IDE (opcional)**
- **Navegador web moderno (Chrome, Firefox, Safari, Edge)**

---

## 📦 Instalación Paso a Paso

### 1. Configurar la Base de Datos

```bash
# Abre MySQL/MariaDB
mysql -u root -p

# Ejecuta el script SQL
source database.sql;

# Verifica que la base de datos fue creada
USE soem_oficial;
SHOW TABLES;
```

**Usuarios de prueba ya incluidos:**
- **Admin:** admin@soem.com / admin123
- **Cliente:** juan@example.com / cliente123

### 2. Descargar e Instalar Apache Tomcat

```bash
# Descarga Tomcat desde https://tomcat.apache.org/
# Extrae en tu carpeta de preferencia
# Variables de entorno: configura CATALINA_HOME apuntando a la carpeta de Tomcat

# En Windows
set CATALINA_HOME=C:\apache-tomcat-9.x
set PATH=%PATH%;%CATALINA_HOME%\bin

# En Linux/Mac
export CATALINA_HOME=/opt/apache-tomcat-9.x
export PATH=$PATH:$CATALINA_HOME/bin
```

### 3. Descarga del Driver de MariaDB

Necesitas agregar el JAR de MariaDB Connector en Tomcat:

```bash
# Descarga el driver desde:
# https://downloads.mariadb.com/Connectors/java/

# Copia el JAR a:
# TOMCAT_HOME/lib/mariadb-java-client-3.1.4.jar
# (o la versión más reciente disponible)
```

### 4. Compilar y Desplegar la Aplicación

**Opción A: Usando NetBeans**
1. Abre NetBeans
2. File → Open Project
3. Selecciona la carpeta `SOEM_Oficial_Completa`
4. Click derecho en el proyecto → Build
5. Click derecho → Run (o presiona F6)

**Opción B: Usando Ant**
```bash
cd SOEM_Oficial_Completa
ant build
ant deploy
```

**Opción C: Compilación Manual**
```bash
# Crear carpeta de compilación
mkdir -p build

# Compilar Java
javac -d build -cp ".:lib/*" src/java/Modelo/*.java
javac -d build -cp ".:lib/*" src/java/Controlador/*.java
javac -d build -cp ".:lib/*" src/java/Servlets/*.java

# Crear WAR
jar cvf SOEM_Oficial.war -C build .
```

### 5. Iniciar la Aplicación

```bash
# Iniciar Tomcat
cd $CATALINA_HOME/bin
./startup.sh    # En Linux/Mac
startup.bat     # En Windows

# Acceder a la aplicación
http://localhost:8080/SOEM_Oficial
```

---

## 🔧 Configuración de Conexión a Base de Datos

Si necesitas cambiar los parámetros de conexión, edita:

**Archivo:** `src/java/Controlador/Conexion.java`

```java
private static final String driver = "org.mariadb.jdbc.Driver";
private static final String url = "jdbc:mariadb://localhost:3306/soem_oficial";
private static final String usuario = "root";
private static final String contrasena = "";  // Cambiar si MariaDB tiene contraseña
```

---

## 🎨 Características Principales

### ✅ Funcionalidades Implementadas

1. **Sistema de Autenticación**
   - Registro de usuarios
   - Login seguro con sesiones
   - Logout
   - Tokens JWT encriptados

2. **Catálogo de Productos**
   - Visualización de productos por categoría
   - Detalles de producto con imágenes
   - Filtros de búsqueda
   - Etiquetas de stock y ofertas

3. **Carrito de Compras**
   - Agregar/eliminar productos
   - Actualizar cantidades
   - Resumen de compra
   - Persistencia en sesión

4. **Sistema de Pago**
   - Modal de pago con QR (NEqui)
   - Disclaimer informativo
   - Validación de monto exacto
   - Confirmación de pago

5. **Seguridad**
   - Encriptación AES 256 bits de datos sensibles
   - Tokens de sesión seguros
   - Validaciones en formularios
   - Control de acceso por rol

6. **Diseño Responsivo**
   - Interfaz optimizada para mobile
   - Paleta de colores: Blanco, Negro, Dorado
   - Carrusel de productos
   - Navegación intuitiva

### 📋 Vistas Principales

| Página | Ruta | Descripción |
|--------|------|------------|
| Home | `/home` | Página principal con carrusel y productos |
| Producto | `/producto?id=X` | Detalles de un producto |
| Login | `/login` | Formulario de inicio de sesión |
| Registro | `/register` | Formulario de registro de usuarios |
| Carrito | `/cart?action=view` | Visualización del carrito de compras |
| Logout | `/logout` | Cierre de sesión |

---

## 💳 Sistema de Pago

### Flujo de Pago

1. **Agregar productos al carrito**
2. **Ver carrito y proceder al pago**
3. **Se muestra modal con:**
   - QR de NEqui
   - Número de transferencia
   - Disclaimer con pasos
   - Recomendaciones

4. **Usuario debe:**
   - Transferir el monto exacto
   - Enviar comprobante a WhatsApp: +57 315 084 6431
   - Indicar número de orden

5. **Estados del Pago:**
   - Pendiente
   - Verificación de Pago
   - Pago Aprobado
   - Preparando Producto
   - Producto Enviado
   - Producto Entregado

---

## 🛡️ Seguridad y Encriptación

### Token de Seguridad
- **Algoritmo:** AES 256 bits
- **Ubicación:** `/src/java/Controlador/TokenSeguridad.java`
- **Funciones:**
  - Encriptación/Desencriptación de datos
  - Generación de tokens de sesión
  - Validación de tokens

### Datos Protegidos
- Números de tarjeta/cuenta
- Información de pago
- Datos de usuario en tránsito
- Sessiones de usuario

---

## 📊 Base de Datos

### Tablas Principales

| Tabla | Descripción |
|-------|------------|
| `Usuario` | Información de clientes y administradores |
| `Producto` | Catálogo de productos |
| `Categoria` | Categorías de productos |
| `Carrito_Compra` | Items en carrito temporal |
| `Cabeza_Factura` | Encabezado de órdenes/facturas |
| `Detalle_Factura` | Items de órdenes |
| `Pago` | Registros de pagos |
| `Colores` | Colores disponibles de productos |
| `Talla` | Tallas disponibles |
| `Lista_Deseos` | Lista de deseos de usuarios |

### Relaciones y Claves Foráneas
- Usuario → Roles
- Producto → Categoria
- Carrito → Usuario, Producto
- Factura → Usuario, Productos, Pago

---

## 🧪 Pruebas

### Credenciales de Prueba

**Admin:**
```
Correo: admin@soem.com
Contraseña: admin123
```

**Cliente:**
```
Correo: juan@example.com
Contraseña: cliente123
```

### Pasos de Prueba

1. **Registro:** `/register` - Crear nuevo usuario
2. **Login:** `/login` - Inicia sesión
3. **Explorar:** `/home` - Ver catálogo
4. **Producto:** `/producto?id=1` - Ver detalles
5. **Carrito:** Agregar productos y ver carrito
6. **Pago:** Proceder a pago (simulado)

---

## 🚨 Troubleshooting

### Error: "No se encontró el driver de MariaDB"
```bash
# Solución: Verifica que el JAR esté en:
# $TOMCAT_HOME/lib/mariadb-java-client-*.jar

# O añádelo al classpath en build.xml
```

### Error: "No se pudo conectar a la base de datos"
```bash
# Verifica:
1. Que MariaDB está corriendo: mysql --version
2. La URL de conexión en Conexion.java
3. El usuario y contraseña sean correctos
4. La base de datos exista: SHOW DATABASES;
```

### Error: 404 Not Found
```bash
# Verifica:
1. El nombre de la aplicación es correcto
2. Los servlets están mapeados en web.xml
3. Las rutas en los formularios son correctas
```

### Error: Sesión no persiste
```bash
# Asegúrate que las cookies están habilitadas en el navegador
# Limpia la caché: Ctrl+Shift+Delete (o Cmd+Shift+Delete en Mac)
```

---

## 📝 Estructura de Carpetas de Desarrollo

```
src/
├── java/
│   ├── Modelo/
│   │   ├── Usuario.java
│   │   ├── Producto.java
│   │   ├── Categoria.java
│   │   ├── CarritoCompra.java
│   │   ├── Pago.java
│   │   └── ...
│   ├── Controlador/
│   │   ├── Conexion.java          ← Conexión a BD
│   │   ├── TokenSeguridad.java    ← Encriptación
│   │   ├── UsuarioDAO.java
│   │   ├── ProductoDAO.java
│   │   ├── CarritoCompraDAO.java
│   │   └── ...
│   └── Servlets/
│       ├── LoginServlet.java
│       ├── RegisterServlet.java
│       ├── HomeServlet.java
│       ├── ProductoServlet.java
│       ├── CartServlet.java
│       └── LogoutServlet.java

web/
├── vistas/
│   ├── header.jsp
│   ├── footer.jsp
│   ├── home.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── producto.jsp
│   ├── cart.jsp
│   └── ...
├── css/
│   └── estilo.css         ← Estilos principales
├── js/
│   └── main.js           ← Scripts
└── WEB-INF/
    ├── web.xml           ← Configuración
    └── glassfish-web.xml
```

---

## 🎓 Extensiones Futuras

1. **Panel Administrativo Completo**
   - Gestión de productos
   - Gestión de usuarios
   - Reporte de ventas
   - Control de inventario

2. **Funcionalidades Adicionales**
   - Sistema de calificaciones
   - Chat de soporte
   - Programa de lealtad
   - Cupones y promociones

3. **Integraciones**
   - Gateway de pago real (Stripe, PayPal)
   - API REST
   - Notificaciones por email
   - SMS de confirmación

4. **Optimizaciones**
   - Caché de productos
   - Búsqueda avanzada
   - Recomendaciones ML
   - CDN para imágenes

---

## 📱 Contacto y Soporte

- **Email:** info@soemoficial.com
- **WhatsApp:** +57 315 084 6431
- **Desarrollador:** NinjaTech AI
- **Última Actualización:** 2024

---

## 📄 Licencia

© 2024 SOEM Oficial. Todos los derechos reservados.

Desarrollado por NinjaTech AI - Tu solución integral en tecnología.

---

## ✨ Notas Importantes

- **Seguridad:** En producción, implementa HTTPS obligatorio
- **Contraseñas:** Usa bcrypt en lugar de plain text
- **Imagenes:** Reemplaza las URLs placeholder con imágenes reales
- **Correos:** Implementa notificaciones por correo
- **Backup:** Realiza backups regulares de la base de datos
- **Logs:** Implementa un sistema de logging completo

---

## 🔗 Links Útiles

- [Documentación Apache Tomcat](https://tomcat.apache.org/tomcat-9.0-doc/)
- [MariaDB Documentation](https://mariadb.com/kb/en/)
- [Java Servlets Tutorial](https://www.oracle.com/java/technologies/java-servlet-tech.html)
- [JSP Documentation](https://projects.eclipse.org/projects/ee4j.jsp)

---

**¡Gracias por usar SOEM Oficial! 🎉**
