/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.CarritoCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aprendiz
 */
public class CarritoCompraDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR CARRITO ===================== */
    public boolean insertarCarrito(CarritoCompra miCarrito) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Carrito_Compra (Usuario_id_usuario, Producto_id_producto, "
                    + "Colores_id_color, Talla_id_talla, cantidad, fecha_agregado) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            if (miCarrito.getUsuarioIdUsuario() != null) {
                ps.setInt(1, miCarrito.getUsuarioIdUsuario());
            } else {
                ps.setNull(1, Types.INTEGER);
            }
            ps.setInt(2, miCarrito.getProductoIdProducto());
            if (miCarrito.getColores_id_color() != null) {
                ps.setInt(3, miCarrito.getColores_id_color());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            if (miCarrito.getTalla_id_talla() != null) {
                ps.setInt(4, miCarrito.getTalla_id_talla());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            ps.setInt(5, miCarrito.getCantidad());
            if (miCarrito.getFechaAgregado() != null) {
                ps.setTimestamp(6, new Timestamp(miCarrito.getFechaAgregado().getTime()));
            } else {
                ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            }

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar carrito: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR CARRITO ===================== */
    public CarritoCompra consultarCarrito(int idCarrito) {
        CarritoCompra miCarrito = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_carrito, Usuario_id_usuario, Producto_id_producto, Colores_id_color, "
                    + "Talla_id_talla, cantidad, fecha_agregado FROM Carrito_Compra WHERE id_carrito = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idCarrito);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miCarrito = mapearCarrito(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar carrito: " + e.getMessage());
        }
        return miCarrito;
    }

    /* ===================== ACTUALIZAR CARRITO ===================== */
    public boolean actualizarCarrito(CarritoCompra miCarrito) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Carrito_Compra SET Usuario_id_usuario = ?, Producto_id_producto = ?, "
                    + "Colores_id_color = ?, Talla_id_talla = ?, cantidad = ? WHERE id_carrito = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            if (miCarrito.getUsuarioIdUsuario() != null) {
                ps.setInt(1, miCarrito.getUsuarioIdUsuario());
            } else {
                ps.setNull(1, Types.INTEGER);
            }
            ps.setInt(2, miCarrito.getProductoIdProducto());
            if (miCarrito.getColores_id_color() != null) {
                ps.setInt(3, miCarrito.getColores_id_color());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            if (miCarrito.getTalla_id_talla() != null) {
                ps.setInt(4, miCarrito.getTalla_id_talla());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            ps.setInt(5, miCarrito.getCantidad());
            ps.setInt(6, miCarrito.getIdCarrito());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar carrito: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR CARRITO ===================== */
    public boolean modificarCarrito(CarritoCompra miCarrito) {
        return actualizarCarrito(miCarrito);
    }

    /* ===================== ELIMINAR CARRITO ===================== */
    public boolean eliminarCarrito(int idCarrito) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Carrito_Compra WHERE id_carrito = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idCarrito);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar carrito: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS CARRITOS ===================== */
    public List<CarritoCompra> listarCarritos() {
        List<CarritoCompra> listaCarritos = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_carrito, Usuario_id_usuario, Producto_id_producto, Colores_id_color, "
                    + "Talla_id_talla, cantidad, fecha_agregado FROM Carrito_Compra";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaCarritos.add(mapearCarrito(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar carritos: " + e.getMessage());
        }
        return listaCarritos;
    }

    /* ===================== HELPER: MAPEAR RESULTSET -> CARRITOCOMPRA ===================== */
    private CarritoCompra mapearCarrito(ResultSet rs) throws SQLException {
        CarritoCompra miCarrito = new CarritoCompra();
        miCarrito.setIdCarrito(rs.getInt("id_carrito"));
        int usuarioId = rs.getInt("Usuario_id_usuario");
        miCarrito.setUsuarioIdUsuario(rs.wasNull() ? null : usuarioId);
        miCarrito.setProductoIdProducto(rs.getInt("Producto_id_producto"));
        int colorId = rs.getInt("Colores_id_color");
        miCarrito.setColores_id_color(rs.wasNull() ? null : colorId);
        int tallaId = rs.getInt("Talla_id_talla");
        miCarrito.setTalla_id_talla(rs.wasNull() ? null : tallaId);
        miCarrito.setCantidad(rs.getInt("cantidad"));
        miCarrito.setFechaAgregado(rs.getTimestamp("fecha_agregado"));
        return miCarrito;
    }
}
