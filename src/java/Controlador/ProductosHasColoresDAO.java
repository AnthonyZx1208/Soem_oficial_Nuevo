/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ProductosHasColores;
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
public class ProductosHasColoresDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR RELACION PRODUCTO-COLOR ===================== */
    public boolean insertarProductosHasColores(ProductosHasColores miRelacion) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Producto_has_Colores (Producto_id_producto, Colores_id_nombre_color) "
                    + "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miRelacion.getProductoIdProducto());
            ps.setInt(2, miRelacion.getColoresIdNombreColor());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar relacion producto-color: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR RELACION PRODUCTO-COLOR ===================== */
    public ProductosHasColores consultarProductosHasColores(int idProducto, int idColor) {
        ProductosHasColores miRelacion = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Producto_id_producto, Colores_id_nombre_color "
                    + "FROM Producto_has_Colores WHERE Producto_id_producto = ? AND Colores_id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idProducto);
            ps.setInt(2, idColor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miRelacion = new ProductosHasColores();
                miRelacion.setProductoIdProducto(rs.getInt("Producto_id_producto"));
                miRelacion.setColoresIdNombreColor(rs.getInt("Colores_id_nombre_color"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar relacion producto-color: " + e.getMessage());
        }
        return miRelacion;
    }

    /* ===================== ACTUALIZAR RELACION PRODUCTO-COLOR ===================== */
    public boolean actualizarProductosHasColores(ProductosHasColores miRelacion, int idProductoAntiguo, int idColorAntiguo) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Producto_has_Colores SET Producto_id_producto = ?, Colores_id_nombre_color = ? "
                    + "WHERE Producto_id_producto = ? AND Colores_id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miRelacion.getProductoIdProducto());
            ps.setInt(2, miRelacion.getColoresIdNombreColor());
            ps.setInt(3, idProductoAntiguo);
            ps.setInt(4, idColorAntiguo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar relacion producto-color: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR RELACION PRODUCTO-COLOR ===================== */
    public boolean modificarProductosHasColores(ProductosHasColores miRelacion, int idProductoAntiguo, int idColorAntiguo) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Producto_has_Colores SET Producto_id_producto = ?, Colores_id_nombre_color = ? "
                    + "WHERE Producto_id_producto = ? AND Colores_id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miRelacion.getProductoIdProducto());
            ps.setInt(2, miRelacion.getColoresIdNombreColor());
            ps.setInt(3, idProductoAntiguo);
            ps.setInt(4, idColorAntiguo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar relacion producto-color: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR RELACION PRODUCTO-COLOR ===================== */
    public boolean eliminarProductosHasColores(int idProducto, int idColor) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Producto_has_Colores WHERE Producto_id_producto = ? AND Colores_id_nombre_color = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idProducto);
            ps.setInt(2, idColor);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar relacion producto-color: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== LISTAR TODAS LAS RELACIONES PRODUCTO-COLOR ===================== */
    public List<ProductosHasColores> listarProductosHasColores() {
        List<ProductosHasColores> listaRelaciones = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT Producto_id_producto, Colores_id_nombre_color FROM Producto_has_Colores";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductosHasColores miRelacion = new ProductosHasColores();
                miRelacion.setProductoIdProducto(rs.getInt("Producto_id_producto"));
                miRelacion.setColoresIdNombreColor(rs.getInt("Colores_id_nombre_color"));
                listaRelaciones.add(miRelacion);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar relaciones producto-color: " + e.getMessage());
        }
        return listaRelaciones;
    }
}
