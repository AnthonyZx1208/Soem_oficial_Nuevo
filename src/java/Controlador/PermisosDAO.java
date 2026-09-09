package Controlador;

import Modelo.Permisos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermisosDAO {

    private Conexion conect = new Conexion();

    public boolean insertarPermiso(Permisos miPermiso) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Permisos (nombre_permiso, descripcion) VALUES (?, ?)")) {
                ps.setString(1, miPermiso.getNombrePermiso());
                ps.setString(2, miPermiso.getDescripcion());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar permiso: " + e.getMessage());
            return false;
        }
    }

    public Permisos consultarPermiso(int idPermiso) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return null;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_permiso, nombre_permiso, descripcion FROM Permisos WHERE id_permiso = ?")) {
                ps.setInt(1, idPermiso);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next() ? mapear(rs) : null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar permiso: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarPermiso(Permisos miPermiso) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Permisos SET nombre_permiso = ?, descripcion = ? WHERE id_permiso = ?")) {
                ps.setString(1, miPermiso.getNombrePermiso());
                ps.setString(2, miPermiso.getDescripcion());
                ps.setInt(3, miPermiso.getIdPermiso());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar permiso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPermiso(int idPermiso) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Permisos WHERE id_permiso = ?")) {
                ps.setInt(1, idPermiso);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar permiso: " + e.getMessage()
                    + "\nNota: si el permiso está asociado a roles, primero elimina la relación.");
            return false;
        }
    }

    public List<Permisos> listarPermisos() {
        List<Permisos> lista = new ArrayList<>();
        try (Connection conn = conect.getConn()) {
            if (conn == null) return lista;
            try (PreparedStatement ps = conn.prepareStatement("SELECT id_permiso, nombre_permiso, descripcion FROM Permisos ORDER BY id_permiso");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar permisos: " + e.getMessage());
        }
        return lista;
    }

    /** true si el rol dado tiene el permiso indicado (por nombre_permiso), vía Roles_Has_Permisos. */
    public boolean rolTienePermiso(int rolId, String nombrePermiso) {
        String sql = "SELECT 1 FROM Roles_Has_Permisos rp "
                + "JOIN Permisos p ON p.id_permiso = rp.Permisos_id_permiso "
                + "WHERE rp.Roles_id_rol = ? AND p.nombre_permiso = ?";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, rolId);
                ps.setString(2, nombrePermiso);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar permiso: " + e.getMessage());
            return false;
        }
    }

    /** Todos los nombre_permiso de un rol en una sola consulta (para no repetir rolTienePermiso por cada chequeo). */
    public Set<String> permisosDeRol(int rolId) {
        Set<String> nombres = new HashSet<>();
        String sql = "SELECT p.nombre_permiso FROM Roles_Has_Permisos rp "
                + "JOIN Permisos p ON p.id_permiso = rp.Permisos_id_permiso WHERE rp.Roles_id_rol = ?";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return nombres;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, rolId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) nombres.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar permisos del rol: " + e.getMessage());
        }
        return nombres;
    }

    private Permisos mapear(ResultSet rs) throws SQLException {
        Permisos miPermiso = new Permisos();
        miPermiso.setIdPermiso(rs.getInt("id_permiso"));
        miPermiso.setNombrePermiso(rs.getString("nombre_permiso"));
        miPermiso.setDescripcion(rs.getString("descripcion"));
        return miPermiso;
    }
}
