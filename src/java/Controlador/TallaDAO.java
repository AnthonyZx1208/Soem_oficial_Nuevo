package Controlador;

import Modelo.Talla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TallaDAO {

    private Conexion conect = new Conexion();

    public boolean insertarTalla(Talla miTalla) {
        boolean resultado = false;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Talla (nombre_talla) VALUES (?)")) {
                ps.setString(1, miTalla.getNombreTalla());
                resultado = ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar talla: " + e.getMessage());
        }
        return resultado;
    }

    public Talla consultarTalla(int idTalla) {
        Talla miTalla = null;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return null;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_talla, nombre_talla FROM Talla WHERE id_talla = ?")) {
                ps.setInt(1, idTalla);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        miTalla = new Talla();
                        miTalla.setIdTalla(rs.getInt("id_talla"));
                        miTalla.setNombreTalla(rs.getString("nombre_talla"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar talla: " + e.getMessage());
        }
        return miTalla;
    }

    public boolean actualizarTalla(Talla miTalla) {
        boolean resultado = false;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Talla SET nombre_talla = ? WHERE id_talla = ?")) {
                ps.setString(1, miTalla.getNombreTalla());
                ps.setInt(2, miTalla.getIdTalla());
                resultado = ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar talla: " + e.getMessage());
        }
        return resultado;
    }

    public boolean eliminarTalla(int idTalla) {
        boolean resultado = false;
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Talla WHERE id_talla = ?")) {
                ps.setInt(1, idTalla);
                resultado = ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar talla: " + e.getMessage()
                    + "\nNota: si la talla tiene variantes de producto asociadas, primero elimínalas.");
        }
        return resultado;
    }

    public List<Talla> listarTallas() {
        List<Talla> listaTallas = new ArrayList<>();
        try (Connection conn = conect.getConn()) {
            if (conn == null) return listaTallas;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_talla, nombre_talla FROM Talla ORDER BY id_talla");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Talla miTalla = new Talla();
                    miTalla.setIdTalla(rs.getInt("id_talla"));
                    miTalla.setNombreTalla(rs.getString("nombre_talla"));
                    listaTallas.add(miTalla);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar tallas: " + e.getMessage());
        }
        return listaTallas;
    }
}
