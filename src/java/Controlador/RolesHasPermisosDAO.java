package Controlador;

import Modelo.RolesHasPermisos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesHasPermisosDAO {

    private Conexion conect = new Conexion();

    public boolean insertarRolesHasPermisos(RolesHasPermisos miRelacion) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO Roles_Has_Permisos (Roles_id_rol, Permisos_id_permiso) VALUES (?, ?)")) {
                ps.setInt(1, miRelacion.getRolesIdRol());
                ps.setInt(2, miRelacion.getPermisosIdPermisos());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar relación rol-permiso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarRolesHasPermisos(int idRol, int idPermiso) {
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Roles_Has_Permisos WHERE Roles_id_rol = ? AND Permisos_id_permiso = ?")) {
                ps.setInt(1, idRol);
                ps.setInt(2, idPermiso);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar relación rol-permiso: " + e.getMessage());
            return false;
        }
    }

    public List<RolesHasPermisos> listarPorRol(int idRol) {
        List<RolesHasPermisos> lista = new ArrayList<>();
        try (Connection conn = conect.getConn()) {
            if (conn == null) return lista;
            try (PreparedStatement ps = conn.prepareStatement("SELECT Roles_id_rol, Permisos_id_permiso FROM Roles_Has_Permisos WHERE Roles_id_rol = ?")) {
                ps.setInt(1, idRol);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar permisos del rol: " + e.getMessage());
        }
        return lista;
    }

    public List<RolesHasPermisos> listarRolesHasPermisos() {
        List<RolesHasPermisos> lista = new ArrayList<>();
        try (Connection conn = conect.getConn()) {
            if (conn == null) return lista;
            try (PreparedStatement ps = conn.prepareStatement("SELECT Roles_id_rol, Permisos_id_permiso FROM Roles_Has_Permisos");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar relaciones rol-permiso: " + e.getMessage());
        }
        return lista;
    }

    private RolesHasPermisos mapear(ResultSet rs) throws SQLException {
        RolesHasPermisos r = new RolesHasPermisos();
        r.setRolesIdRol(rs.getInt("Roles_id_rol"));
        r.setPermisosIdPermisos(rs.getInt("Permisos_id_permiso"));
        return r;
    }
}
