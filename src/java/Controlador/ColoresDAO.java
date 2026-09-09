/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Colores;
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
public class ColoresDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR COLOR ===================== */
    public boolean insertarColor(Colores miColor) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Colores (codigoRGB) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miColor.getCodigoRGB());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar color: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR COLOR ===================== */
    public Colores consultarColor(int idColor) {
        Colores miColor = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_nombre_color, codigoRGB FROM Colores WHERE id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idColor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miColor = new Colores();
                miColor.setIdNombreColor(rs.getInt("id_nombre_color"));
                miColor.setCodigoRGB(rs.getString("codigoRGB"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar color: " + e.getMessage());
        }
        return miColor;
    }

    /* ===================== ACTUALIZAR COLOR ===================== */
    public boolean actualizarColor(Colores miColor) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Colores SET codigoRGB = ? WHERE id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miColor.getCodigoRGB());
            ps.setInt(2, miColor.getIdNombreColor());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar color: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR COLOR ===================== */
    public boolean modificarColor(Colores miColor) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Colores SET codigoRGB = ? WHERE id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miColor.getCodigoRGB());
            ps.setInt(2, miColor.getIdNombreColor());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar color: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR COLOR ===================== */
    public boolean eliminarColor(int idColor) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Colores WHERE id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idColor);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar color: " + e.getMessage()
                    + "\nNota: Si el color esta asociado a productos, primero elimina la relacion.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS COLORES ===================== */
    public List<Colores> listarColores() {
        List<Colores> listaColores = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_nombre_color, codigoRGB FROM Colores";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Colores miColor = new Colores();
                miColor.setIdNombreColor(rs.getInt("id_nombre_color"));
                miColor.setCodigoRGB(rs.getString("codigoRGB"));
                listaColores.add(miColor);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar colores: " + e.getMessage());
        }
        return listaColores;
    }
}
