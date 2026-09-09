-- SOEM Oficial | MariaDB 10.4.32
-- ADVERTENCIA: este script reinicia únicamente la base de datos soem_oficial.
-- Haz una copia de seguridad antes de ejecutarlo si conservas datos reales.

SET NAMES utf8mb4;
SET time_zone = '-05:00';
SET sql_mode = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS soem_oficial;
CREATE DATABASE soem_oficial CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE soem_oficial;

CREATE TABLE TipoDocumento (
    idTipoDocumento INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NULL,
    UNIQUE KEY uq_tipo_documento (nombre_tipo)
) ENGINE=InnoDB;

CREATE TABLE Roles (
    id_rol INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(60) NOT NULL,
    descripcion VARCHAR(255) NULL,
    UNIQUE KEY uq_rol (nombre_rol)
) ENGINE=InnoDB;

CREATE TABLE Permisos (
    id_permiso INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_permiso VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NULL,
    UNIQUE KEY uq_permiso (nombre_permiso)
) ENGINE=InnoDB;

CREATE TABLE Roles_Has_Permisos (
    Roles_id_rol INT UNSIGNED NOT NULL,
    Permisos_id_permiso INT UNSIGNED NOT NULL,
    PRIMARY KEY (Roles_id_rol, Permisos_id_permiso),
    CONSTRAINT fk_rol_permiso_rol FOREIGN KEY (Roles_id_rol) REFERENCES Roles(id_rol) ON DELETE CASCADE,
    CONSTRAINT fk_rol_permiso_permiso FOREIGN KEY (Permisos_id_permiso) REFERENCES Permisos(id_permiso) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Usuario (
    id_Usuario INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nombre_Usuario VARCHAR(100) NOT NULL,
    Apellido_Usuario VARCHAR(100) NOT NULL,
    Numero_Documento VARCHAR(20) NOT NULL,
    Telefono VARCHAR(20) NULL,
    correo VARCHAR(150) NOT NULL,
    Contrasena VARCHAR(255) NOT NULL COMMENT 'Hash PBKDF2, nunca texto plano',
    Direccion VARCHAR(255) NULL,
    Fecha_nacimiento DATE NULL,
    Fecha_vencimiento DATE NULL,
    Autorizacion_datos ENUM('Si','No') NOT NULL DEFAULT 'No',
    Roles_id_rol INT UNSIGNED NOT NULL DEFAULT 2,
    TipoDocumento_idTipoDocumento INT UNSIGNED NOT NULL DEFAULT 1,
    fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_usuario_documento (Numero_Documento),
    UNIQUE KEY uq_usuario_correo (correo),
    KEY ix_usuario_rol (Roles_id_rol),
    CONSTRAINT chk_usuario_correo CHECK (correo REGEXP '^[^[:space:]@]+@[^[:space:]@]+\\.[^[:space:]@]+$'),
    CONSTRAINT chk_usuario_nombres CHECK (CHAR_LENGTH(TRIM(Nombre_Usuario)) >= 2 AND CHAR_LENGTH(TRIM(Apellido_Usuario)) >= 2),
    CONSTRAINT fk_usuario_rol FOREIGN KEY (Roles_id_rol) REFERENCES Roles(id_rol),
    CONSTRAINT fk_usuario_documento FOREIGN KEY (TipoDocumento_idTipoDocumento) REFERENCES TipoDocumento(idTipoDocumento)
) ENGINE=InnoDB;

CREATE TABLE Categoria (
    id_categoria INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL,
    descripcion TEXT NULL,
    imagen_url VARCHAR(500) NULL,
    estado ENUM('Activo','Inactivo') NOT NULL DEFAULT 'Activo',
    UNIQUE KEY uq_categoria (nombre_categoria)
) ENGINE=InnoDB;

CREATE TABLE SubCategoria (
    id_subcategoria INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_subcategoria VARCHAR(100) NOT NULL,
    descripcion TEXT NULL,
    Categoria_id_categoria INT UNSIGNED NOT NULL,
    estado ENUM('Activo','Inactivo') NOT NULL DEFAULT 'Activo',
    UNIQUE KEY uq_subcategoria_categoria (nombre_subcategoria, Categoria_id_categoria),
    CONSTRAINT fk_subcategoria_categoria FOREIGN KEY (Categoria_id_categoria) REFERENCES Categoria(id_categoria) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Talla (
    id_talla INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_talla VARCHAR(20) NOT NULL,
    UNIQUE KEY uq_talla (nombre_talla)
) ENGINE=InnoDB;

CREATE TABLE Colores (
    id_color INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_color VARCHAR(50) NOT NULL,
    codigo_hexadecimal CHAR(7) NOT NULL,
    UNIQUE KEY uq_color_nombre (nombre_color),
    UNIQUE KEY uq_color_hex (codigo_hexadecimal),
    CONSTRAINT chk_color_hex CHECK (codigo_hexadecimal REGEXP '^#[0-9A-Fa-f]{6}$')
) ENGINE=InnoDB;

CREATE TABLE Unidad (
    id_unidad INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_unidad VARCHAR(50) NOT NULL,
    UNIQUE KEY uq_unidad (nombre_unidad)
) ENGINE=InnoDB;

CREATE TABLE Producto (
    id_producto INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    precio_producto DECIMAL(12,2) NOT NULL,
    precio_oferta DECIMAL(12,2) NULL,
    cantidad_stock INT NOT NULL DEFAULT 0,
    imagen_principal VARCHAR(500) NULL,
    Categoria_id_categoria INT UNSIGNED NOT NULL,
    SubCategoria_id_subcategoria INT UNSIGNED NULL,
    estado ENUM('Activo','Inactivo') NOT NULL DEFAULT 'Activo',
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    KEY ix_producto_categoria (Categoria_id_categoria),
    KEY ix_producto_estado (estado),
    CONSTRAINT chk_producto_nombre CHECK (CHAR_LENGTH(TRIM(nombre_producto)) >= 3),
    CONSTRAINT chk_producto_precio CHECK (precio_producto > 0),
    CONSTRAINT chk_producto_oferta CHECK (precio_oferta IS NULL OR (precio_oferta > 0 AND precio_oferta < precio_producto)),
    CONSTRAINT chk_producto_stock CHECK (cantidad_stock >= 0),
    CONSTRAINT fk_producto_categoria FOREIGN KEY (Categoria_id_categoria) REFERENCES Categoria(id_categoria),
    CONSTRAINT fk_producto_subcategoria FOREIGN KEY (SubCategoria_id_subcategoria) REFERENCES SubCategoria(id_subcategoria) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE Productos_Has_Colores (
    Producto_id_producto INT UNSIGNED NOT NULL,
    Colores_id_color INT UNSIGNED NOT NULL,
    Talla_id_talla INT UNSIGNED NOT NULL,
    cantidad_disponible INT NOT NULL DEFAULT 0,
    PRIMARY KEY (Producto_id_producto, Colores_id_color, Talla_id_talla),
    CONSTRAINT chk_variante_stock CHECK (cantidad_disponible >= 0),
    CONSTRAINT fk_variante_producto FOREIGN KEY (Producto_id_producto) REFERENCES Producto(id_producto) ON DELETE CASCADE,
    CONSTRAINT fk_variante_color FOREIGN KEY (Colores_id_color) REFERENCES Colores(id_color),
    CONSTRAINT fk_variante_talla FOREIGN KEY (Talla_id_talla) REFERENCES Talla(id_talla)
) ENGINE=InnoDB;

CREATE TABLE Metodo_Pago (
    id_metodo_pago INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_metodo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NULL,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    UNIQUE KEY uq_metodo_pago (nombre_metodo)
) ENGINE=InnoDB;

CREATE TABLE Carrito_Compra (
    id_carrito INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Usuario_id_usuario INT UNSIGNED NULL,
    sesion_token CHAR(64) NULL,
    Producto_id_producto INT UNSIGNED NOT NULL,
    Colores_id_color INT UNSIGNED NULL,
    Talla_id_talla INT UNSIGNED NULL,
    cantidad INT NOT NULL DEFAULT 1,
    fecha_agregado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    KEY ix_carrito_usuario (Usuario_id_usuario),
    CONSTRAINT chk_carrito_cantidad CHECK (cantidad > 0),
    CONSTRAINT chk_carrito_propietario CHECK (Usuario_id_usuario IS NOT NULL OR sesion_token IS NOT NULL),
    CONSTRAINT fk_carrito_usuario FOREIGN KEY (Usuario_id_usuario) REFERENCES Usuario(id_Usuario) ON DELETE CASCADE,
    CONSTRAINT fk_carrito_producto FOREIGN KEY (Producto_id_producto) REFERENCES Producto(id_producto) ON DELETE CASCADE,
    CONSTRAINT fk_carrito_color FOREIGN KEY (Colores_id_color) REFERENCES Colores(id_color) ON DELETE SET NULL,
    CONSTRAINT fk_carrito_talla FOREIGN KEY (Talla_id_talla) REFERENCES Talla(id_talla) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE Lista_Deseos (
    id_deseo INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Usuario_id_usuario INT UNSIGNED NOT NULL,
    Producto_id_producto INT UNSIGNED NOT NULL,
    fecha_agregado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uq_deseo_usuario_producto (Usuario_id_usuario, Producto_id_producto),
    CONSTRAINT fk_deseo_usuario FOREIGN KEY (Usuario_id_usuario) REFERENCES Usuario(id_Usuario) ON DELETE CASCADE,
    CONSTRAINT fk_deseo_producto FOREIGN KEY (Producto_id_producto) REFERENCES Producto(id_producto) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Cabeza_Factura (
    id_cabeza_factura INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    numero_orden VARCHAR(50) NOT NULL,
    Usuario_id_usuario INT UNSIGNED NOT NULL,
    total_compra DECIMAL(12,2) NOT NULL,
    estado_compra ENUM('Pendiente','Verificacion de Pago','Pago Aprobado','Preparando Producto','Producto Enviado','Producto Entregado','Rechazado','Cancelado') NOT NULL DEFAULT 'Pendiente',
    motivo_rechazo VARCHAR(255) NULL,
    fecha_compra TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_pago DATETIME NULL,
    Metodo_Pago_id_metodo_pago INT UNSIGNED NULL,
    UNIQUE KEY uq_orden (numero_orden),
    KEY ix_orden_usuario (Usuario_id_usuario),
    KEY ix_orden_estado (estado_compra),
    CONSTRAINT chk_orden_total CHECK (total_compra > 0),
    CONSTRAINT fk_orden_usuario FOREIGN KEY (Usuario_id_usuario) REFERENCES Usuario(id_Usuario),
    CONSTRAINT fk_orden_pago FOREIGN KEY (Metodo_Pago_id_metodo_pago) REFERENCES Metodo_Pago(id_metodo_pago)
) ENGINE=InnoDB;

CREATE TABLE Detalle_Factura (
    id_detalle_factura INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Cabeza_Factura_id_cabeza_factura INT UNSIGNED NOT NULL,
    Producto_id_producto INT UNSIGNED NOT NULL,
    Colores_id_color INT UNSIGNED NULL,
    Talla_id_talla INT UNSIGNED NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(12,2) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    CONSTRAINT chk_detalle_cantidad CHECK (cantidad > 0),
    CONSTRAINT chk_detalle_precio CHECK (precio_unitario > 0 AND subtotal > 0),
    CONSTRAINT fk_detalle_orden FOREIGN KEY (Cabeza_Factura_id_cabeza_factura) REFERENCES Cabeza_Factura(id_cabeza_factura) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (Producto_id_producto) REFERENCES Producto(id_producto),
    CONSTRAINT fk_detalle_color FOREIGN KEY (Colores_id_color) REFERENCES Colores(id_color) ON DELETE SET NULL,
    CONSTRAINT fk_detalle_talla FOREIGN KEY (Talla_id_talla) REFERENCES Talla(id_talla) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE Pago (
    id_pago INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Cabeza_Factura_id_cabeza_factura INT UNSIGNED NOT NULL,
    monto_pagado DECIMAL(12,2) NOT NULL,
    fecha_pago TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    comprobante_url VARCHAR(500) NULL,
    estado_pago ENUM('Pendiente','Verificado','Rechazado') NOT NULL DEFAULT 'Pendiente',
    CONSTRAINT chk_pago_monto CHECK (monto_pagado > 0),
    CONSTRAINT fk_pago_orden FOREIGN KEY (Cabeza_Factura_id_cabeza_factura) REFERENCES Cabeza_Factura(id_cabeza_factura) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Reserva_Stock (
    id_reserva INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Cabeza_Factura_id_cabeza_factura INT UNSIGNED NOT NULL,
    Producto_id_producto INT UNSIGNED NOT NULL,
    cantidad INT NOT NULL,
    estado ENUM('Reservado','Confirmado','Liberado') NOT NULL DEFAULT 'Reservado',
    fecha_reserva TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_expiracion DATETIME NOT NULL,
    CONSTRAINT chk_reserva_cantidad CHECK (cantidad > 0),
    CONSTRAINT fk_reserva_orden FOREIGN KEY (Cabeza_Factura_id_cabeza_factura) REFERENCES Cabeza_Factura(id_cabeza_factura) ON DELETE CASCADE,
    CONSTRAINT fk_reserva_producto FOREIGN KEY (Producto_id_producto) REFERENCES Producto(id_producto)
) ENGINE=InnoDB;

CREATE TABLE Estado_Orden_Historial (
    id_historial INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Cabeza_Factura_id_cabeza_factura INT UNSIGNED NOT NULL,
    estado VARCHAR(50) NOT NULL,
    detalle VARCHAR(255) NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historial_orden FOREIGN KEY (Cabeza_Factura_id_cabeza_factura) REFERENCES Cabeza_Factura(id_cabeza_factura) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Configuracion_Sitio (
    id_config TINYINT UNSIGNED PRIMARY KEY DEFAULT 1,
    nombre_tienda VARCHAR(255) NOT NULL DEFAULT 'SOEM Oficial',
    logo_url VARCHAR(500) NULL,
    favicon_url VARCHAR(500) NULL,
    color_primario CHAR(7) NOT NULL DEFAULT '#000000',
    color_secundario CHAR(7) NOT NULL DEFAULT '#FFFFFF',
    color_acento CHAR(7) NOT NULL DEFAULT '#C9A24D',
    contacto_whatsapp VARCHAR(20) NOT NULL DEFAULT '3150846431',
    contacto_email VARCHAR(150) NULL,
    descripcion_tienda TEXT NULL,
    CONSTRAINT chk_config_unica CHECK (id_config = 1)
) ENGINE=InnoDB;

INSERT INTO TipoDocumento (nombre_tipo, descripcion) VALUES
('Cédula de Ciudadanía','CC'),('Tarjeta de Identidad','TI'),('Cédula de Extranjería','CE'),('Pasaporte','PP');
INSERT INTO Roles (nombre_rol, descripcion) VALUES ('Admin','Control total'),('Cliente','Comprador de la tienda'),('Vendedor','Gestión comercial');
INSERT INTO Permisos (nombre_permiso, descripcion) VALUES ('Gestionar productos','Crear y editar productos'),('Gestionar categorías','Crear y editar categorías'),('Procesar compras','Actualizar estados'),('Gestionar configuración','Identidad visual');
INSERT INTO Roles_Has_Permisos VALUES (1,1),(1,2),(1,3),(1,4),(3,1),(3,3);
INSERT INTO Metodo_Pago (nombre_metodo, descripcion) VALUES ('NEqui','Transferencia a NEqui'),('Transferencia','Transferencia bancaria');
INSERT INTO Talla (nombre_talla) VALUES ('XS'),('S'),('M'),('L'),('XL'),('XXL'),('Única');
INSERT INTO Colores (nombre_color,codigo_hexadecimal) VALUES ('Negro','#000000'),('Blanco','#FFFFFF'),('Dorado','#C9A24D'),('Azul','#1D4ED8'),('Rosado','#EC4899');
INSERT INTO Unidad (nombre_unidad) VALUES ('Unidad'),('Par'),('Conjunto');
INSERT INTO Categoria (nombre_categoria,descripcion,estado) VALUES ('Hombres','Moda masculina','Activo'),('Mujeres','Moda femenina','Activo'),('Niñas','Moda para niñas','Activo'),('Niños','Moda para niños','Activo');
INSERT INTO SubCategoria (nombre_subcategoria,Categoria_id_categoria) VALUES
('Pantalones',1),('Camisas',1),('Blusas',1),('Tops',1),('Busos',1),('Chaquetas',1),
('Pantalones',2),('Camisas',2),('Blusas',2),('Tops',2),('Busos',2),('Chaquetas',2),
('Pantalones',3),('Camisas',3),('Blusas',3),('Tops',3),('Busos',3),('Chaquetas',3),
('Pantalones',4),('Camisas',4),('Blusas',4),('Tops',4),('Busos',4),('Chaquetas',4);
-- Catálogo de prueba: 10 referencias por categoría. Las fotos se entregan dentro
-- del proyecto en web/assets/productos, por lo que no dependen de servicios externos.
INSERT INTO Producto (nombre_producto,descripcion,precio_producto,precio_oferta,cantidad_stock,imagen_principal,Categoria_id_categoria,SubCategoria_id_subcategoria) VALUES
('Camiseta Eclipse Blanca','Camiseta oversize blanca con estampado gráfico.',89900,69900,18,'assets/productos/camiseta-blanca-grafica.png',1,2),
('Camiseta Eclipse Negra','Camiseta oversize negra de algodón.',89900,NULL,24,'assets/productos/camiseta-negra-grafica.png',1,2),
('Camiseta Shadow Print','Camiseta negra de corte relajado y estampado frontal.',109900,89900,15,'assets/productos/camiseta-negra-print.png',1,2),
('Camiseta Tribal Blanca','Camiseta blanca unisex con diseño tribal.',99900,79900,12,'assets/productos/camiseta-blanca-tribal.png',1,2),
('Chaqueta Track Blanca','Chaqueta deportiva con cierre y bolsillos.',189900,159900,10,'assets/productos/chaqueta-blanca-track.png',1,6),
('Buso Tribal Blanco','Buso con capota y estampado de alto contraste.',169900,NULL,14,'assets/productos/buso-blanco-tribal.png',1,5),
('Buso Anime Gris','Buso de dos capas con ilustración frontal.',179900,149900,9,'assets/productos/buso-gris-anime.png',1,5),
('Jean Bordado Rojo','Jean negro baggy con bordado posterior.',179900,NULL,11,'assets/productos/jean-negro-rojo.png',1,1),
('Jean Araña Gris','Jean baggy gris con gráficos urbanos.',169900,139900,7,'assets/productos/jean-gris-arana.png',1,1),
('Jean Dragón Negro','Jean recto negro con arte de dragón.',189900,NULL,13,'assets/productos/jean-negro-dragon.png',1,1),
('Blusa Eclipse Blanca','Blusa oversize blanca con estampado gráfico.',89900,69900,16,'assets/productos/camiseta-blanca-grafica.png',2,9),
('Blusa Eclipse Negra','Blusa negra de algodón de corte relajado.',89900,NULL,20,'assets/productos/camiseta-negra-grafica.png',2,9),
('Top Shadow Print','Top negro de diseño urbano.',94900,74900,14,'assets/productos/camiseta-negra-print.png',2,10),
('Blusa Tribal Blanca','Blusa blanca con diseño tribal.',99900,NULL,12,'assets/productos/camiseta-blanca-tribal.png',2,9),
('Chaqueta Track Mujer','Chaqueta deportiva blanca con cierre.',189900,159900,9,'assets/productos/chaqueta-blanca-track.png',2,12),
('Buso Tribal Mujer','Buso con capota y detalles gráficos.',169900,NULL,13,'assets/productos/buso-blanco-tribal.png',2,11),
('Buso Anime Mujer','Buso gris de dos capas con ilustración.',179900,149900,8,'assets/productos/buso-gris-anime.png',2,11),
('Jean Bordado Rojo Mujer','Jean baggy negro con bordado rojo.',179900,NULL,10,'assets/productos/jean-negro-rojo.png',2,7),
('Jean Araña Mujer','Jean gris de tiro alto con gráficos.',169900,139900,11,'assets/productos/jean-gris-arana.png',2,7),
('Jean Ángel Negro','Jean negro con ilustración blanca.',189900,NULL,6,'assets/productos/jean-negro-angel.png',2,7),
('Camiseta Mini Eclipse Blanca','Camiseta infantil blanca, suave y cómoda.',69900,54900,22,'assets/productos/camiseta-blanca-grafica.png',3,14),
('Camiseta Mini Eclipse Negra','Camiseta infantil negra de algodón.',69900,NULL,21,'assets/productos/camiseta-negra-grafica.png',3,14),
('Camiseta Mini Shadow','Camiseta infantil negra de corte amplio.',74900,59900,17,'assets/productos/camiseta-negra-print.png',3,14),
('Blusa Mini Tribal','Blusa infantil blanca con diseño gráfico.',74900,NULL,19,'assets/productos/camiseta-blanca-tribal.png',3,15),
('Chaqueta Mini Track','Chaqueta infantil con cierre frontal.',149900,124900,8,'assets/productos/chaqueta-blanca-track.png',3,18),
('Buso Mini Tribal','Buso infantil con capota.',129900,NULL,14,'assets/productos/buso-blanco-tribal.png',3,17),
('Buso Mini Anime','Buso infantil gris de dos capas.',139900,109900,10,'assets/productos/buso-gris-anime.png',3,17),
('Jean Mini Bordado','Jean infantil negro con bordado.',139900,NULL,12,'assets/productos/jean-negro-rojo.png',3,13),
('Jean Mini Araña','Jean infantil gris con gráficos.',129900,104900,10,'assets/productos/jean-gris-arana.png',3,13),
('Jean Mini Dragón','Jean infantil negro de pierna recta.',139900,NULL,9,'assets/productos/jean-negro-dragon.png',3,13),
('Camiseta Urban Blanca','Camiseta para niño blanca de algodón.',69900,54900,23,'assets/productos/camiseta-blanca-grafica.png',4,20),
('Camiseta Urban Negra','Camiseta para niño negra de algodón.',69900,NULL,20,'assets/productos/camiseta-negra-grafica.png',4,20),
('Camiseta Urban Shadow','Camiseta negra juvenil con estampado.',74900,59900,16,'assets/productos/camiseta-negra-print.png',4,20),
('Camiseta Urban Tribal','Camiseta blanca con gráfico frontal.',74900,NULL,18,'assets/productos/camiseta-blanca-tribal.png',4,20),
('Chaqueta Urban Track','Chaqueta blanca infantil con cierre.',149900,124900,9,'assets/productos/chaqueta-blanca-track.png',4,24),
('Buso Urban Tribal','Buso para niño con capota.',129900,NULL,14,'assets/productos/buso-blanco-tribal.png',4,23),
('Buso Urban Anime','Buso gris infantil de dos capas.',139900,109900,10,'assets/productos/buso-gris-anime.png',4,23),
('Jean Urban Bordado','Jean negro juvenil con bordado.',139900,NULL,13,'assets/productos/jean-negro-rojo.png',4,19),
('Jean Urban Araña','Jean gris juvenil con gráfico.',129900,104900,11,'assets/productos/jean-gris-arana.png',4,19),
('Jean Urban Ángel','Jean negro juvenil con ilustración.',139900,NULL,8,'assets/productos/jean-negro-angel.png',4,19);
INSERT INTO Productos_Has_Colores (Producto_id_producto,Colores_id_color,Talla_id_talla,cantidad_disponible)
SELECT id_producto, CASE WHEN imagen_principal LIKE '%blanca%' THEN 2 ELSE 1 END, 3, cantidad_stock FROM Producto;
INSERT INTO Configuracion_Sitio (id_config,nombre_tienda,contacto_email,descripcion_tienda) VALUES (1,'SOEM Oficial','info@soemoficial.com','Moda para toda la familia.');

DELIMITER $$
CREATE TRIGGER trg_detalle_factura_validar
BEFORE INSERT ON Detalle_Factura FOR EACH ROW
BEGIN
    IF NEW.subtotal <> ROUND(NEW.cantidad * NEW.precio_unitario, 2) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El subtotal no coincide con cantidad por precio unitario';
    END IF;
END$$
CREATE TRIGGER trg_reserva_fecha
BEFORE INSERT ON Reserva_Stock FOR EACH ROW
BEGIN
    IF NEW.fecha_expiracion <= NOW() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La fecha de expiración de la reserva debe ser futura';
    END IF;
END$$
DELIMITER ;

-- Después de registrar el primer usuario desde la aplicación, promuévelo así:
-- UPDATE Usuario SET Roles_id_rol = 1 WHERE correo = 'tu-correo@dominio.com';
-- La aplicación guarda las contraseñas nuevas como hash PBKDF2; nunca insertes contraseñas en texto plano.
