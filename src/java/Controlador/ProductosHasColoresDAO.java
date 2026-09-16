package Controlador;

import Modelo.ProductosHasColores;
import Modelo.Talla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosHasColoresDAO {

    private Conexion conect = new Conexion();

    /** Inserta la variante o, si ya existe (mismo producto+color+talla), actualiza su cantidad. */
    public boolean guardarVariante(ProductosHasColores v) {
        String sql = "INSERT INTO Productos_Has_Colores (Producto_id_producto, Colores_id_color, Talla_id_talla, cantidad_disponible) "
                + "VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE cantidad_disponible = VALUES(cantidad_disponible)";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, v.getProductoIdProducto());
                ps.setInt(2, v.getColoresIdColor());
                ps.setInt(3, v.getTallaIdTalla());
                ps.setInt(4, v.getCantidadDisponible());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar variante producto-color-talla: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarVariante(int idProducto, int idColor, int idTalla) {
        String sql = "DELETE FROM Productos_Has_Colores WHERE Producto_id_producto = ? AND Colores_id_color = ? AND Talla_id_talla = ?";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return false;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idProducto);
                ps.setInt(2, idColor);
                ps.setInt(3, idTalla);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar variante producto-color-talla: " + e.getMessage());
            return false;
        }
    }

    public List<ProductosHasColores> listarPorProducto(int idProducto) {
        List<ProductosHasColores> lista = new ArrayList<>();
        String sql = "SELECT Producto_id_producto, Colores_id_color, Talla_id_talla, cantidad_disponible "
                + "FROM Productos_Has_Colores WHERE Producto_id_producto = ? ORDER BY Colores_id_color, Talla_id_talla";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return lista;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idProducto);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar variantes del producto: " + e.getMessage());
        }
        return lista;
    }

    /** Tallas con stock configurado para este producto, para mostrar solo las suyas en la ficha. */
    public List<Talla> tallasDisponibles(int idProducto) {
        List<Talla> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT t.id_talla, t.nombre_talla FROM Productos_Has_Colores v "
                + "JOIN Talla t ON t.id_talla = v.Talla_id_talla "
                + "WHERE v.Producto_id_producto = ? AND v.cantidad_disponible > 0 ORDER BY t.id_talla";
        try (Connection conn = conect.getConn()) {
            if (conn == null) return lista;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idProducto);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Talla t = new Talla();
                        t.setIdTalla(rs.getInt("id_talla"));
                        t.setNombreTalla(rs.getString("nombre_talla"));
                        lista.add(t);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar tallas disponibles del producto: " + e.getMessage());
        }
        return lista;
    }

    private ProductosHasColores mapear(ResultSet rs) throws SQLException {
        ProductosHasColores v = new ProductosHasColores();
        v.setProductoIdProducto(rs.getInt("Producto_id_producto"));
        v.setColoresIdColor(rs.getInt("Colores_id_color"));
        v.setTallaIdTalla(rs.getInt("Talla_id_talla"));
        v.setCantidadDisponible(rs.getInt("cantidad_disponible"));
        return v;
    }
}
