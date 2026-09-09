-- SOEM Oficial | Migración para bases de datos ya existentes (creadas antes de la Tarea 4)
-- database.sql ya incluye estas columnas en el CREATE TABLE para instalaciones nuevas;
-- este script es solo para aplicar el mismo cambio sobre una base de datos que ya tenía
-- datos (por ejemplo, la de XAMPP en desarrollo o una base ya desplegada en Railway).
-- Es un cambio aditivo: no borra ni modifica datos existentes.
USE soem_oficial;

ALTER TABLE producto
    ADD COLUMN fecha_inicio_oferta DATETIME NULL,
    ADD COLUMN fecha_fin_oferta DATETIME NULL;
