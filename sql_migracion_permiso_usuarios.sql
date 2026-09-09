-- SOEM Oficial | Migración para bases de datos ya existentes (Fase 1 del panel admin ampliado)
-- Agrega el permiso 'Gestionar usuarios' (solo para el rol Admin) que necesita
-- AdminUsuariosServlet. database.sql ya lo incluye en su seed para instalaciones
-- nuevas; este script es para aplicar el mismo cambio sobre una base existente.
-- Es aditivo: no borra ni modifica permisos existentes.
USE soem_oficial;

INSERT IGNORE INTO Permisos (nombre_permiso, descripcion)
VALUES ('Gestionar usuarios', 'Ver clientes y crear cuentas de personal');

INSERT IGNORE INTO Roles_Has_Permisos (Roles_id_rol, Permisos_id_permiso)
SELECT 1, id_permiso FROM Permisos WHERE nombre_permiso = 'Gestionar usuarios';
