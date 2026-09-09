# SOEM Oficial

## Ejecución local

1. Instala MariaDB 10.4 y ejecuta `database.sql` desde una instancia vacía.
2. Configura, antes de iniciar NetBeans, las variables `SOEM_DB_HOST`, `SOEM_DB_PORT`, `SOEM_DB_NAME`, `SOEM_DB_USER` y `SOEM_DB_PASSWORD`.
3. Abre el proyecto en NetBeans 20, selecciona GlassFish 7 y ejecuta **Clean and Build**.
4. Inicia la aplicación. La URL de desarrollo aparece en la salida de GlassFish.
5. Registra el primer usuario. Si debe administrar la tienda, asígnale el rol Admin ejecutando el `UPDATE` documentado al final de `database.sql`.

## Seguridad

- Contraseñas nuevas: PBKDF2-HMAC-SHA256 con salt e iteraciones.
- Formularios sensibles: token CSRF de sesión.
- Consultas: sentencias parametrizadas.
- Cabeceras: anti clickjacking, anti sniffing y política de referencias.
- Producción: configura HTTPS en GlassFish; ningún token reemplaza TLS para proteger el tráfico.

## Flujo de pedidos

El checkout genera una orden en verificación, reserva el stock y registra una línea de tiempo. El administrador puede aprobar, preparar, enviar, entregar o rechazar. Al rechazar, el stock reservado se libera.
