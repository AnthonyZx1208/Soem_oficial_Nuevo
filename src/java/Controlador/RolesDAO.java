package Controlador;

import Modelo.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesDAO {

    private Conexion conect = new Conexion();

    public boolean insertarRol(Roles miRol) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Roles (nombre_rol, descripcion) VALUES (?, ?)")) {
                ps.setString(1, miRol.getNombreRol());
                ps.setString(2, miRol.getDescripcion());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar rol: " + e.getMessage());
            return false;
        }
    }

    public Roles consultarRol(int idRol) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return null;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_rol, nombre_rol, descripcion FROM Roles WHERE id_rol = ?")) {
                ps.setInt(1, idRol);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next() ? mapear(rs) : null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar rol: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarRol(Roles miRol) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Roles SET nombre_rol = ?, descripcion = ? WHERE id_rol = ?")) {
                ps.setString(1, miRol.getNombreRol());
                ps.setString(2, miRol.getDescripcion());
                ps.setInt(3, miRol.getIdRol());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarRol(int idRol) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Roles WHERE id_rol = ?")) {
                ps.setInt(1, idRol);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar rol: " + e.getMessage()
                    + "\nNota: si el rol tiene usuarios asociados, primero reasígnalos.");
            return false;
        }
    }

    public List<Roles> listarRoles() {
        List<Roles> lista = new ArrayList<>();
        try (Connection conn = conect.getConn()) {
            if (conn == null) return lista;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_rol, nombre_rol, descripcion FROM Roles ORDER BY id_rol");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar roles: " + e.getMessage());
        }
        return lista;
    }

    private Roles mapear(ResultSet rs) throws SQLException {
        Roles miRol = new Roles();
        miRol.setIdRol(rs.getInt("id_rol"));
        miRol.setNombreRol(rs.getString("nombre_rol"));
        miRol.setDescripcion(rs.getString("descripcion"));
        return miRol;
    }
}
