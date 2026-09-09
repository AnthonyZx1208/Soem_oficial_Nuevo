package Controlador;

import Modelo.Colores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColoresDAO {

    private Conexion conect = new Conexion();

    public boolean insertarColor(Colores miColor) {
        boolean resultado = false;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Colores (nombre_color, codigo_hexadecimal) VALUES (?, ?)")) {
                ps.setString(1, miColor.getNombreColor());
                ps.setString(2, miColor.getCodigoHexadecimal());
                resultado = ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar color: " + e.getMessage());
        }
        return resultado;
    }

    public Colores consultarColor(int idColor) {
        Colores miColor = null;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return null;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_color, nombre_color, codigo_hexadecimal FROM Colores WHERE id_color = ?")) {
                ps.setInt(1, idColor);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) miColor = mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar color: " + e.getMessage());
        }
        return miColor;
    }

    public boolean actualizarColor(Colores miColor) {
        boolean resultado = false;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Colores SET nombre_color = ?, codigo_hexadecimal = ? WHERE id_color = ?")) {
                ps.setString(1, miColor.getNombreColor());
                ps.setString(2, miColor.getCodigoHexadecimal());
                ps.setInt(3, miColor.getIdColor());
                resultado = ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar color: " + e.getMessage());
        }
        return resultado;
    }

    public boolean eliminarColor(int idColor) {
        boolean resultado = false;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Colores WHERE id_color = ?")) {
                ps.setInt(1, idColor);
                resultado = ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar color: " + e.getMessage()
                    + "\nNota: si el color tiene variantes de producto asociadas, primero elimínalas.");
        }
        return resultado;
    }

    public List<Colores> listarColores() {
        List<Colores> listaColores = new ArrayList<>();
        try (Connection conn = conect.getConn()) {
            if (conn == null) return listaColores;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_color, nombre_color, codigo_hexadecimal FROM Colores ORDER BY id_color");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) listaColores.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar colores: " + e.getMessage());
        }
        return listaColores;
    }

    private Colores mapear(ResultSet rs) throws SQLException {
        Colores miColor = new Colores();
        miColor.setIdColor(rs.getInt("id_color"));
        miColor.setNombreColor(rs.getString("nombre_color"));
        miColor.setCodigoHexadecimal(rs.getString("codigo_hexadecimal"));
        return miColor;
    }
}
