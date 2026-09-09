package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexion compatible con desarrollo local y Railway.
 * Railway puede proporcionar variables SOEM_DB_* o MYSQL*.
 */
public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private final String url = obtenerUrl();
    private final String user = obtenerValor("root", "SOEM_DB_USER", "MYSQLUSER");
    private final String password = obtenerValor("", "SOEM_DB_PASSWORD", "MYSQLPASSWORD");

    public Connection getConn() {
        try {
            Class.forName(DRIVER);
            if (urlIncluyeCredenciales()) {
                return DriverManager.getConnection(url);
            }
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException exception) {
            System.err.println("No fue posible conectar con la base de datos: " + exception.getMessage());
            return null;
        }
    }

    private String obtenerUrl() {
        String urlConfigurada = obtenerValor("", "SOEM_DB_URL", "MYSQL_URL", "DATABASE_URL");
        if (!urlConfigurada.isBlank()) {
            return normalizarUrlJdbc(urlConfigurada);
        }

        String host = obtenerValor("localhost", "SOEM_DB_HOST", "MYSQLHOST");
        String puerto = obtenerValor("3306", "SOEM_DB_PORT", "MYSQLPORT");
        String baseDatos = obtenerValor("soem_oficial", "SOEM_DB_NAME", "MYSQLDATABASE");

        return "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos
                + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false"
                + "&serverTimezone=America/Bogota";
    }

    private String obtenerValor(String valorPredeterminado, String... nombres) {
        for (String nombre : nombres) {
            String valor = System.getenv(nombre);
            if (valor != null && !valor.isBlank()) {
                return valor;
            }
        }
        return valorPredeterminado;
    }

    private boolean urlIncluyeCredenciales() {
        int inicioAutoridad = url.indexOf("://");
        if (inicioAutoridad < 0) {
            return false;
        }
        int finAutoridad = url.indexOf('/', inicioAutoridad + 3);
        String autoridad = finAutoridad < 0 ? url.substring(inicioAutoridad + 3)
                : url.substring(inicioAutoridad + 3, finAutoridad);
        return autoridad.contains("@");
    }
    private String normalizarUrlJdbc(String url) {
        if (url.startsWith("jdbc:")) {
            return url;
        }
        if (url.startsWith("mysql://")) {
            return "jdbc:" + url;
        }
        if (url.startsWith("mariadb://")) {
            return "jdbc:mysql:" + url.substring("mariadb:".length());
        }
        throw new IllegalArgumentException("La URL de base de datos debe iniciar con jdbc:mysql://, mysql:// o mariadb://");
    }
}