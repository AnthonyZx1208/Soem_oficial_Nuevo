package Controlador;

import Modelo.ConfiguracionSitio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfiguracionDAO {

    private Conexion conect = new Conexion();

    public ConfiguracionSitio obtener() {
        ConfiguracionSitio c = valoresPredeterminados();
        String sql = "SELECT nombre_tienda, logo_url, favicon_url, color_primario, color_secundario, color_acento, "
                + "contacto_whatsapp, contacto_email, descripcion_tienda FROM Configuracion_Sitio WHERE id_config = 1";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return c;
            try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c.setNombreTienda(rs.getString("nombre_tienda"));
                    c.setLogoUrl(rs.getString("logo_url"));
                    c.setFaviconUrl(rs.getString("favicon_url"));
                    c.setColorPrimario(rs.getString("color_primario"));
                    c.setColorSecundario(rs.getString("color_secundario"));
                    c.setColorAcento(rs.getString("color_acento"));
                    c.setContactoWhatsapp(rs.getString("contacto_whatsapp"));
                    c.setContactoEmail(rs.getString("contacto_email"));
                    c.setDescripcionTienda(rs.getString("descripcion_tienda"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener configuración del sitio: " + e.getMessage());
        }
        return c;
    }

    public boolean actualizar(ConfiguracionSitio c) {
        String sql = "UPDATE Configuracion_Sitio SET nombre_tienda=?, logo_url=?, favicon_url=?, color_primario=?, "
                + "color_secundario=?, color_acento=?, contacto_whatsapp=?, contacto_email=?, descripcion_tienda=? "
                + "WHERE id_config = 1";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getNombreTienda());
                ps.setString(2, c.getLogoUrl());
                ps.setString(3, c.getFaviconUrl());
                ps.setString(4, c.getColorPrimario());
                ps.setString(5, c.getColorSecundario());
                ps.setString(6, c.getColorAcento());
                ps.setString(7, c.getContactoWhatsapp());
                ps.setString(8, c.getContactoEmail());
                ps.setString(9, c.getDescripcionTienda());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar configuración del sitio: " + e.getMessage());
            return false;
        }
    }

    private ConfiguracionSitio valoresPredeterminados() {
        ConfiguracionSitio c = new ConfiguracionSitio();
        c.setNombreTienda("SOEM Oficial");
        c.setColorPrimario("#000000");
        c.setColorSecundario("#FFFFFF");
        c.setColorAcento("#C9A24D");
        c.setContactoWhatsapp("3150846431");
        return c;
    }
}
