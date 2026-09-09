/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aprendiz
 */
public class PagoDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR PAGO ===================== */
    public boolean insertarPago(Pago miPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Pagos (fecha_pago, montoPago, Metododepago_id_metodoPago, "
                    + "Cabeza_Factura_id_factura) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setTimestamp(1, new Timestamp(miPago.getFechaPago().getTime()));
            ps.setFloat(2, miPago.getMontoPago());
            ps.setInt(3, miPago.getMetodoDePagoIdMetodoPago());
            ps.setInt(4, miPago.getCabezaFacturaIdFactura());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar pago: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR PAGO ===================== */
    public Pago consultarPago(int idPago) {
        Pago miPago = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_pago, fecha_pago, montoPago, Metododepago_id_metodoPago, "
                    + "Cabeza_Factura_id_factura FROM Pagos WHERE id_pago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idPago);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miPago = new Pago();
                miPago.setIdPago(rs.getInt("id_pago"));
                miPago.setFechaPago(rs.getTimestamp("fecha_pago"));
                miPago.setMontoPago(rs.getFloat("montoPago"));
                miPago.setMetodoDePagoIdMetodoPago(rs.getInt("Metododepago_id_metodoPago"));
                miPago.setCabezaFacturaIdFactura(rs.getInt("Cabeza_Factura_id_factura"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar pago: " + e.getMessage());
        }
        return miPago;
    }

    /* ===================== ACTUALIZAR PAGO ===================== */
    public boolean actualizarPago(Pago miPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Pagos SET fecha_pago = ?, montoPago = ?, "
                    + "Metododepago_id_metodoPago = ?, Cabeza_Factura_id_factura = ? "
                    + "WHERE id_pago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setTimestamp(1, new Timestamp(miPago.getFechaPago().getTime()));
            ps.setFloat(2, miPago.getMontoPago());
            ps.setInt(3, miPago.getMetodoDePagoIdMetodoPago());
            ps.setInt(4, miPago.getCabezaFacturaIdFactura());
            ps.setInt(5, miPago.getIdPago());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR PAGO ===================== */
    public boolean modificarPago(Pago miPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Pagos SET fecha_pago = ?, montoPago = ?, "
                    + "Metododepago_id_metodoPago = ?, Cabeza_Factura_id_factura = ? "
                    + "WHERE id_pago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setTimestamp(1, new Timestamp(miPago.getFechaPago().getTime()));
            ps.setFloat(2, miPago.getMontoPago());
            ps.setInt(3, miPago.getMetodoDePagoIdMetodoPago());
            ps.setInt(4, miPago.getCabezaFacturaIdFactura());
            ps.setInt(5, miPago.getIdPago());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar pago: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR PAGO ===================== */
    public boolean eliminarPago(int idPago) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Pagos WHERE id_pago = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idPago);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar pago: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS PAGOS ===================== */
    public List<Pago> listarPagos() {
        List<Pago> listaPagos = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_pago, fecha_pago, montoPago, Metododepago_id_metodoPago, "
                    + "Cabeza_Factura_id_factura FROM Pagos";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago miPago = new Pago();
                miPago.setIdPago(rs.getInt("id_pago"));
                miPago.setFechaPago(rs.getTimestamp("fecha_pago"));
                miPago.setMontoPago(rs.getFloat("montoPago"));
                miPago.setMetodoDePagoIdMetodoPago(rs.getInt("Metododepago_id_metodoPago"));
                miPago.setCabezaFacturaIdFactura(rs.getInt("Cabeza_Factura_id_factura"));
                listaPagos.add(miPago);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar pagos: " + e.getMessage());
        }
        return listaPagos;
    }
}
