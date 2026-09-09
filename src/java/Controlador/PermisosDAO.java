/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Permisos;
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
public class PermisosDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR PERMISO ===================== */
    public boolean insertarPermiso(Permisos miPermiso) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Permisos (Descrip_permisos) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miPermiso.getDescripPermisos());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar permiso: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR PERMISO ===================== */
    public Permisos consultarPermiso(int idPermiso) {
        Permisos miPermiso = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_Permisos, Descrip_permisos FROM Permisos WHERE id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idPermiso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miPermiso = new Permisos();
                miPermiso.setIdPermisos(rs.getInt("id_Permisos"));
                miPermiso.setDescripPermisos(rs.getString("Descrip_permisos"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar permiso: " + e.getMessage());
        }
        return miPermiso;
    }

    /* ===================== ACTUALIZAR PERMISO ===================== */
    public boolean actualizarPermiso(Permisos miPermiso) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Permisos SET Descrip_permisos = ? WHERE id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miPermiso.getDescripPermisos());
            ps.setInt(2, miPermiso.getIdPermisos());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar permiso: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR PERMISO ===================== */
    public boolean modificarPermiso(Permisos miPermiso) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Permisos SET Descrip_permisos = ? WHERE id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miPermiso.getDescripPermisos());
            ps.setInt(2, miPermiso.getIdPermisos());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar permiso: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR PERMISO ===================== */
    public boolean eliminarPermiso(int idPermiso) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Permisos WHERE id_Permisos = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idPermiso);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar permiso: " + e.getMessage()
                    + "\nNota: Si el permiso esta asociado a roles, primero elimina la relacion.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS PERMISOS ===================== */
    public List<Permisos> listarPermisos() {
        List<Permisos> listaPermisos = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_Permisos, Descrip_permisos FROM Permisos";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Permisos miPermiso = new Permisos();
                miPermiso.setIdPermisos(rs.getInt("id_Permisos"));
                miPermiso.setDescripPermisos(rs.getString("Descrip_permisos"));
                listaPermisos.add(miPermiso);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar permisos: " + e.getMessage());
        }
        return listaPermisos;
    }
}
