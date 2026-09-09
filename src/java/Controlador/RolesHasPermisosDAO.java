/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RolesHasPermisos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aprendiz
 */
public class RolesHasPermisosDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR RELACION ROL-PERMISO ===================== */
    public boolean insertarRolesHasPermisos(RolesHasPermisos miRelacion) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Roles_has_Permisos (Roles_id_rol, Permisos_id_Permisos) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miRelacion.getRolesIdRol());
            ps.setInt(2, miRelacion.getPermisosIdPermisos());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar relacion rol-permiso: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR RELACION ROL-PERMISO ===================== */
    public RolesHasPermisos consultarRolesHasPermisos(int idRol, int idPermiso) {
        RolesHasPermisos miRelacion = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Roles_id_rol, Permisos_id_Permisos "
                    + "FROM Roles_has_Permisos WHERE Roles_id_rol = ? AND Permisos_id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idRol);
            ps.setInt(2, idPermiso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miRelacion = new RolesHasPermisos();
                miRelacion.setRolesIdRol(rs.getInt("Roles_id_rol"));
                miRelacion.setPermisosIdPermisos(rs.getInt("Permisos_id_Permisos"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar relacion rol-permiso: " + e.getMessage());
        }
        return miRelacion;
    }

    /* ===================== ACTUALIZAR RELACION ROL-PERMISO ===================== */
    public boolean actualizarRolesHasPermisos(RolesHasPermisos miRelacion, int idRolAntiguo, int idPermisoAntiguo) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Roles_has_Permisos SET Roles_id_rol = ?, Permisos_id_Permisos = ? "
                    + "WHERE Roles_id_rol = ? AND Permisos_id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miRelacion.getRolesIdRol());
            ps.setInt(2, miRelacion.getPermisosIdPermisos());
            ps.setInt(3, idRolAntiguo);
            ps.setInt(4, idPermisoAntiguo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar relacion rol-permiso: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR RELACION ROL-PERMISO ===================== */
    public boolean modificarRolesHasPermisos(RolesHasPermisos miRelacion, int idRolAntiguo, int idPermisoAntiguo) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Roles_has_Permisos SET Roles_id_rol = ?, Permisos_id_Permisos = ? "
                    + "WHERE Roles_id_rol = ? AND Permisos_id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miRelacion.getRolesIdRol());
            ps.setInt(2, miRelacion.getPermisosIdPermisos());
            ps.setInt(3, idRolAntiguo);
            ps.setInt(4, idPermisoAntiguo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar relacion rol-permiso: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR RELACION ROL-PERMISO ===================== */
    public boolean eliminarRolesHasPermisos(int idRol, int idPermiso) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Roles_has_Permisos WHERE Roles_id_rol = ? AND Permisos_id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idRol);
            ps.setInt(2, idPermiso);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar relacion rol-permiso: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== LISTAR TODAS LAS RELACIONES ROL-PERMISO ===================== */
    public List<RolesHasPermisos> listarRolesHasPermisos() {
        List<RolesHasPermisos> listaRelaciones = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Roles_id_rol, Permisos_id_Permisos FROM Roles_has_Permisos";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RolesHasPermisos miRelacion = new RolesHasPermisos();
                miRelacion.setRolesIdRol(rs.getInt("Roles_id_rol"));
                miRelacion.setPermisosIdPermisos(rs.getInt("Permisos_id_Permisos"));
                listaRelaciones.add(miRelacion);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar relaciones rol-permiso: " + e.getMessage());
        }
        return listaRelaciones;
    }
}
