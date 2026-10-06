# Railway / Linux deployment

The project is case-sensitive safe: the database schema and SQL access use consistent identifiers. Do not rename database tables or source folders only by changing upper/lower case.

## Railway variables

Configure these variables in Railway (all uppercase):

- `SOEM_DB_HOST`
- `SOEM_DB_PORT`
- `SOEM_DB_NAME`
- `SOEM_DB_USER`
- `SOEM_DB_PASSWORD`

Optionally set `SOEM_DB_URL` with a complete JDBC URL. It takes precedence over the individual values.

`Dockerfile` is a two-stage build: it compiles `src/java` and packages `web/` into a WAR itself (no local `dist/` needed, since that folder is gitignored and never reaches Railway), then runs it on Payara Micro. Pushing to the connected GitHub branch is enough to trigger a build — no local `Clean and Build` step required before pushing.

If Railway already has a MySQL database service in the same project, add the web service's env vars as references to that service (`${{MySQL.MYSQLHOST}}`, `${{MySQL.MYSQLPORT}}`, `${{MySQL.MYSQLDATABASE}}`, `${{MySQL.MYSQLUSER}}`, `${{MySQL.MYSQLPASSWORD}}`) instead of retyping the `SOEM_DB_*` ones — `Conexion.java` already falls back to Railway's own `MYSQLHOST`/`MYSQLPORT`/`MYSQLDATABASE`/`MYSQLUSER`/`MYSQLPASSWORD`/`MYSQL_URL` variable names when the `SOEM_DB_*` ones aren't set.
