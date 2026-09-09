/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Talla;
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
public class TallaDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR TALLA ===================== */
    public boolean insertarTalla(Talla miTalla) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Talla (Descripcion_talla) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miTalla.getDescripcionTalla());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar talla: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR TALLA ===================== */
    public Talla consultarTalla(int idTalla) {
        Talla miTalla = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Id_Talla, Descripcion_talla FROM Talla WHERE Id_Talla = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idTalla);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miTalla = new Talla();
                miTalla.setIdTalla(rs.getInt("Id_Talla"));
                miTalla.setDescripcionTalla(rs.getString("Descripcion_talla"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar talla: " + e.getMessage());
        }
        return miTalla;
    }

    /* ===================== ACTUALIZAR TALLA ===================== */
    public boolean actualizarTalla(Talla miTalla) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Talla SET Descripcion_talla = ? WHERE Id_Talla = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miTalla.getDescripcionTalla());
            ps.setInt(2, miTalla.getIdTalla());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar talla: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR TALLA ===================== */
    public boolean modificarTalla(Talla miTalla) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Talla SET Descripcion_talla = ? WHERE Id_Talla = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miTalla.getDescripcionTalla());
            ps.setInt(2, miTalla.getIdTalla());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar talla: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR TALLA ===================== */
    public boolean eliminarTalla(int idTalla) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Talla WHERE Id_Talla = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idTalla);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar talla: " + e.getMessage()
                    + "\nNota: Si la talla tiene productos asociados, primero debes eliminarlos.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODAS LAS TALLAS ===================== */
    public List<Talla> listarTallas() {
        List<Talla> listaTallas = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Id_Talla, Descripcion_talla FROM Talla";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Talla miTalla = new Talla();
                miTalla.setIdTalla(rs.getInt("Id_Talla"));
                miTalla.setDescripcionTalla(rs.getString("Descripcion_talla"));
                listaTallas.add(miTalla);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar tallas: " + e.getMessage());
        }
        return listaTallas;
    }
}
