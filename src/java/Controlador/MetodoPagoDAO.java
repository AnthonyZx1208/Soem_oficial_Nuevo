/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.MetodoPago;
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
public class MetodoPagoDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR METODO DE PAGO ===================== */
    public boolean insertarMetodoPago(MetodoPago miMetodoPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Metododepago (Descripcion_metodoPago) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miMetodoPago.getDescripcionMetodoPago());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar metodo de pago: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR METODO DE PAGO ===================== */
    public MetodoPago consultarMetodoPago(int idMetodoPago) {
        MetodoPago miMetodoPago = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_metodoPago, Descripcion_metodoPago FROM Metododepago WHERE id_metodoPago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idMetodoPago);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miMetodoPago = new MetodoPago();
                miMetodoPago.setIdMetodoPago(rs.getInt("id_metodoPago"));
                miMetodoPago.setDescripcionMetodoPago(rs.getString("Descripcion_metodoPago"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar metodo de pago: " + e.getMessage());
        }
        return miMetodoPago;
    }

    /* ===================== ACTUALIZAR METODO DE PAGO ===================== */
    public boolean actualizarMetodoPago(MetodoPago miMetodoPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Metododepago SET Descripcion_metodoPago = ? WHERE id_metodoPago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miMetodoPago.getDescripcionMetodoPago());
            ps.setInt(2, miMetodoPago.getIdMetodoPago());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar metodo de pago: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR METODO DE PAGO ===================== */
    public boolean modificarMetodoPago(MetodoPago miMetodoPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Metododepago SET Descripcion_metodoPago = ? WHERE id_metodoPago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miMetodoPago.getDescripcionMetodoPago());
            ps.setInt(2, miMetodoPago.getIdMetodoPago());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar metodo de pago: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR METODO DE PAGO ===================== */
    public boolean eliminarMetodoPago(int idMetodoPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Metododepago WHERE id_metodoPago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idMetodoPago);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar metodo de pago: " + e.getMessage()
                    + "\nNota: Si el metodo de pago tiene pagos asociados, primero debes eliminarlos.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS METODOS DE PAGO ===================== */
    public List<MetodoPago> listarMetodosPago() {
        List<MetodoPago> listaMetodosPago = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_metodoPago, Descripcion_metodoPago FROM Metododepago";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MetodoPago miMetodoPago = new MetodoPago();
                miMetodoPago.setIdMetodoPago(rs.getInt("id_metodoPago"));
                miMetodoPago.setDescripcionMetodoPago(rs.getString("Descripcion_metodoPago"));
                listaMetodosPago.add(miMetodoPago);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar metodos de pago: " + e.getMessage());
        }
        return listaMetodosPago;
    }
}
