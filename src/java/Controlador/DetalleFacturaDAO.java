/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DetalleFactura;
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
public class DetalleFacturaDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR DETALLE FACTURA ===================== */
    public boolean insertarDetalleFactura(DetalleFactura miDetalle) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Detalle_Factura (cantidad, subtotal_fac, Cabeza_Factura_id_factura, "
                    + "Producto_id_producto) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miDetalle.getCantidad());
            ps.setFloat(2, miDetalle.getSubtotalFac());
            ps.setInt(3, miDetalle.getCabezaFacturaIdFactura());
            ps.setInt(4, miDetalle.getProductoIdProducto());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar detalle factura: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR DETALLE FACTURA ===================== */
    public DetalleFactura consultarDetalleFactura(int idDetalleFactura) {
        DetalleFactura miDetalle = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_Detalle_Factura, cantidad, subtotal_fac, Cabeza_Factura_id_factura, "
                    + "Producto_id_producto FROM Detalle_Factura WHERE id_Detalle_Factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idDetalleFactura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miDetalle = new DetalleFactura();
                miDetalle.setIdDetalleFactura(rs.getInt("id_Detalle_Factura"));
                miDetalle.setCantidad(rs.getInt("cantidad"));
                miDetalle.setSubtotalFac(rs.getFloat("subtotal_fac"));
                miDetalle.setCabezaFacturaIdFactura(rs.getInt("Cabeza_Factura_id_factura"));
                miDetalle.setProductoIdProducto(rs.getInt("Producto_id_producto"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar detalle factura: " + e.getMessage());
        }
        return miDetalle;
    }

    /* ===================== ACTUALIZAR DETALLE FACTURA ===================== */
    public boolean actualizarDetalleFactura(DetalleFactura miDetalle) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Detalle_Factura SET cantidad = ?, subtotal_fac = ?, "
                    + "Cabeza_Factura_id_factura = ?, Producto_id_producto = ? WHERE id_Detalle_Factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miDetalle.getCantidad());
            ps.setFloat(2, miDetalle.getSubtotalFac());
            ps.setInt(3, miDetalle.getCabezaFacturaIdFactura());
            ps.setInt(4, miDetalle.getProductoIdProducto());
            ps.setInt(5, miDetalle.getIdDetalleFactura());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle factura: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR DETALLE FACTURA ===================== */
    public boolean modificarDetalleFactura(DetalleFactura miDetalle) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Detalle_Factura SET cantidad = ?, subtotal_fac = ?, "
                    + "Cabeza_Factura_id_factura = ?, Producto_id_producto = ? WHERE id_Detalle_Factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miDetalle.getCantidad());
            ps.setFloat(2, miDetalle.getSubtotalFac());
            ps.setInt(3, miDetalle.getCabezaFacturaIdFactura());
            ps.setInt(4, miDetalle.getProductoIdProducto());
            ps.setInt(5, miDetalle.getIdDetalleFactura());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar detalle factura: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR DETALLE FACTURA ===================== */
    public boolean eliminarDetalleFactura(int idDetalleFactura) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Detalle_Factura WHERE id_Detalle_Factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idDetalleFactura);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle factura: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS DETALLES DE FACTURA ===================== */
    public List<DetalleFactura> listarDetallesFactura() {
        List<DetalleFactura> listaDetalles = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_Detalle_Factura, cantidad, subtotal_fac, Cabeza_Factura_id_factura, "
                    + "Producto_id_producto FROM Detalle_Factura";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetalleFactura miDetalle = new DetalleFactura();
                miDetalle.setIdDetalleFactura(rs.getInt("id_Detalle_Factura"));
                miDetalle.setCantidad(rs.getInt("cantidad"));
                miDetalle.setSubtotalFac(rs.getFloat("subtotal_fac"));
                miDetalle.setCabezaFacturaIdFactura(rs.getInt("Cabeza_Factura_id_factura"));
                miDetalle.setProductoIdProducto(rs.getInt("Producto_id_producto"));
                listaDetalles.add(miDetalle);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar detalles de factura: " + e.getMessage());
        }
        return listaDetalles;
    }
}
