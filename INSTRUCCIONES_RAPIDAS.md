# SOEM OFICIAL - GUÍA RÁPIDA DE INSTALACIÓN

## ⚡ Instalación Rápida en 5 Pasos

### 1️⃣ BASE DE DATOS
```bash
# Conecta a MariaDB/MySQL
mysql -u root -p

# Ejecuta en MySQL:
source database.sql;
```

### 2️⃣ DRIVER DE MARIADB
- Descarga: https://downloads.mariadb.com/Connectors/java/
- Copia el JAR a: `$TOMCAT_HOME/lib/mariadb-java-client-3.1.4.jar`

### 3️⃣ COMPILAR CON NETBEANS (Recomendado)
1. Abre NetBeans
2. File → Open Project → Selecciona carpeta SOEM_Oficial_Completa
3. Click derecho en proyecto → Clean and Build
4. Click derecho → Run (F6)

### 4️⃣ COMPILAR CON LÍNEA DE COMANDOS
```bash
cd SOEM_Oficial_Completa
ant build
ant deploy
```

### 5️⃣ ACCEDER
- URL: http://localhost:8080/SOEM_Oficial
- Usuario Admin: admin@soem.com / admin123
- Usuario Cliente: juan@example.com / cliente123

---

## 🔧 ARCHIVOS PRINCIPALES

| Archivo | Función |
|---------|---------|
| `database.sql` | Script de base de datos |
| `README.md` | Documentación completa |
| `src/java/Servlets/` | Controladores de la app |
| `src/java/Modelo/` | Entidades de datos |
| `src/java/Controlador/` | DAOs y conexión |
| `web/vistas/` | Páginas JSP |
| `web/css/estilo.css` | Estilos CSS |

---

## 🆘 PROBLEMAS COMUNES

### ❌ "Driver de MariaDB no encontrado"
✅ Solución: Copia el JAR a `$TOMCAT_HOME/lib/`

### ❌ "No se conecta a la BD"
✅ Solución: Verifica que MariaDB esté corriendo
```bash
mysql --version
mysql -u root -p
```

### ❌ "Error 404 Not Found"
✅ Solución: Verifica URL: http://localhost:8080/SOEM_Oficial

### ❌ "Sesión no funciona"
✅ Solución: Limpia caché: Ctrl+Shift+Delete

---

## 📱 CARACTERÍSTICAS

✅ Catálogo de productos  
✅ Sistema de login/registro  
✅ Carrito de compras  
✅ Modal de pago con QR  
✅ Encriptación AES 256  
✅ Diseño responsivo  
✅ Colores: Blanco, Negro, Dorado  

---

## 📞 CONTACTO

- Email: info@soemoficial.com
- WhatsApp: +57 315 084 6431
- Desarrollador: NinjaTech AI

---

¡Listo para usar! 🎉
