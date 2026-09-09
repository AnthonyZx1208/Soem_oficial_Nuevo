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

`Dockerfile` deploys the compiled WAR. After changing Java or JSP files, run `Clean and Build` before pushing the project to Railway.
