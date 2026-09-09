/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Unidad;
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
public class UnidadDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR UNIDAD ===================== */
    public boolean insertarUnidad(Unidad miUnidad) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Unidad (Descripcion_unidad) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miUnidad.getDescripcionUnidad());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar unidad: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR UNIDAD ===================== */
    public Unidad consultarUnidad(int idUnidad) {
        Unidad miUnidad = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Id_unidad, Descripcion_unidad FROM Unidad WHERE Id_unidad = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idUnidad);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miUnidad = new Unidad();
                miUnidad.setIdUnidad(rs.getInt("Id_unidad"));
                miUnidad.setDescripcionUnidad(rs.getString("Descripcion_unidad"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar unidad: " + e.getMessage());
        }
        return miUnidad;
    }

    /* ===================== ACTUALIZAR UNIDAD ===================== */
    public boolean actualizarUnidad(Unidad miUnidad) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Unidad SET Descripcion_unidad = ? WHERE Id_unidad = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miUnidad.getDescripcionUnidad());
            ps.setInt(2, miUnidad.getIdUnidad());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar unidad: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR UNIDAD ===================== */
    public boolean modificarUnidad(Unidad miUnidad) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Unidad SET Descripcion_unidad = ? WHERE Id_unidad = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miUnidad.getDescripcionUnidad());
            ps.setInt(2, miUnidad.getIdUnidad());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar unidad: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR UNIDAD ===================== */
    public boolean eliminarUnidad(int idUnidad) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Unidad WHERE Id_unidad = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idUnidad);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar unidad: " + e.getMessage()
                    + "\nNota: Si la unidad tiene productos asociados, primero debes eliminarlos.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODAS LAS UNIDADES ===================== */
    public List<Unidad> listarUnidades() {
        List<Unidad> listaUnidades = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Id_unidad, Descripcion_unidad FROM Unidad";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Unidad miUnidad = new Unidad();
                miUnidad.setIdUnidad(rs.getInt("Id_unidad"));
                miUnidad.setDescripcionUnidad(rs.getString("Descripcion_unidad"));
                listaUnidades.add(miUnidad);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar unidades: " + e.getMessage());
        }
        return listaUnidades;
    }
}
