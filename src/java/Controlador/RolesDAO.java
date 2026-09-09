/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Roles;
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
public class RolesDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR ROL ===================== */
    public boolean insertarRol(Roles miRol) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Roles (nombre_rol, Descrip_rol) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miRol.getNombreRol());
            ps.setString(2, miRol.getDescripRol());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar rol: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR ROL ===================== */
    public Roles consultarRol(int idRol) {
        Roles miRol = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_rol, nombre_rol, Descrip_rol FROM Roles WHERE id_rol = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idRol);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miRol = new Roles();
                miRol.setIdRol(rs.getInt("id_rol"));
                miRol.setNombreRol(rs.getString("nombre_rol"));
                miRol.setDescripRol(rs.getString("Descrip_rol"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar rol: " + e.getMessage());
        }
        return miRol;
    }

    /* ===================== ACTUALIZAR ROL ===================== */
    public boolean actualizarRol(Roles miRol) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Roles SET nombre_rol = ?, Descrip_rol = ? WHERE id_rol = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miRol.getNombreRol());
            ps.setString(2, miRol.getDescripRol());
            ps.setInt(3, miRol.getIdRol());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR ROL ===================== */
    public boolean modificarRol(Roles miRol) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Roles SET nombre_rol = ?, Descrip_rol = ? WHERE id_rol = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miRol.getNombreRol());
            ps.setString(2, miRol.getDescripRol());
            ps.setInt(3, miRol.getIdRol());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar rol: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR ROL ===================== */
    public boolean eliminarRol(int idRol) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Roles WHERE id_rol = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idRol);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar rol: " + e.getMessage()
                    + "\nNota: Si el rol tiene usuarios asociados, primero debes eliminarlos.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS ROLES ===================== */
    public List<Roles> listarRoles() {
        List<Roles> listaRoles = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_rol, nombre_rol, Descrip_rol FROM Roles";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Roles miRol = new Roles();
                miRol.setIdRol(rs.getInt("id_rol"));
                miRol.setNombreRol(rs.getString("nombre_rol"));
                miRol.setDescripRol(rs.getString("Descrip_rol"));
                listaRoles.add(miRol);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar roles: " + e.getMessage());
        }
        return listaRoles;
    }
}
