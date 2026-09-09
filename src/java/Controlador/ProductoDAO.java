/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
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
public class ProductoDAO {

    private Conexion conect = new Conexion();

    private static final String COLUMNAS =
            "id_producto, nombre_producto, descripcion, precio_producto, precio_oferta, "
            + "cantidad_stock, fecha_creacion, fecha_actualizacion, imagen_principal, "
            + "Categoria_id_categoria, SubCategoria_id_subcategoria, estado, "
            + "fecha_inicio_oferta, fecha_fin_oferta";

    /* ===================== INSERTAR PRODUCTO ===================== */
    public boolean insertarProducto(Producto miProducto) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Producto (nombre_producto, descripcion, precio_producto, precio_oferta, "
                    + "cantidad_stock, imagen_principal, Categoria_id_categoria, SubCategoria_id_subcategoria, estado, "
                    + "fecha_inicio_oferta, fecha_fin_oferta) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miProducto.getNombre_producto());
            ps.setString(2, miProducto.getDescripcion());
            ps.setFloat(3, miProducto.getPrecio_producto());
            if (miProducto.getPrecio_oferta() != null) {
                ps.setDouble(4, miProducto.getPrecio_oferta());
            } else {
                ps.setNull(4, java.sql.Types.DECIMAL);
            }
            ps.setInt(5, miProducto.getCantidad_stock());
            ps.setString(6, miProducto.getImagen_principal());
            ps.setInt(7, miProducto.getCategoria_id_categoria());
            if (miProducto.getSubCategoria_id_subcategoria() != null) {
                ps.setInt(8, miProducto.getSubCategoria_id_subcategoria());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }
            ps.setString(9, miProducto.getEstado() != null ? miProducto.getEstado() : "Activo");
            if (miProducto.getFecha_inicio_oferta() != null) {
                ps.setTimestamp(10, miProducto.getFecha_inicio_oferta());
            } else {
                ps.setNull(10, java.sql.Types.TIMESTAMP);
            }
            if (miProducto.getFecha_fin_oferta() != null) {
                ps.setTimestamp(11, miProducto.getFecha_fin_oferta());
            } else {
                ps.setNull(11, java.sql.Types.TIMESTAMP);
            }

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR PRODUCTO ===================== */
    public Producto consultarProducto(int idProducto) {
        return consultarProducto(idProducto, true);
    }

    /**
     * Igual que consultarProducto(id), pero sin anular precio_oferta cuando
     * la oferta está fuera de su ventana de vigencia. La usa el formulario
     * de edición del admin (AdminProductoServlet), que necesita ver y
     * conservar el valor guardado aunque la oferta todavía no empiece o ya
     * haya terminado.
     */
    public Producto consultarProductoCrudo(int idProducto) {
        return consultarProducto(idProducto, false);
    }

    private Producto consultarProducto(int idProducto, boolean aplicarVigencia) {
        Producto miProducto = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT " + COLUMNAS + " FROM Producto WHERE id_producto = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miProducto = mapearProducto(rs, aplicarVigencia);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e.getMessage());
        }
        return miProducto;
    }

    /* ===================== ACTUALIZAR PRODUCTO ===================== */
    public boolean actualizarProducto(Producto miProducto) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Producto SET nombre_producto = ?, descripcion = ?, precio_producto = ?, "
                    + "precio_oferta = ?, cantidad_stock = ?, imagen_principal = ?, Categoria_id_categoria = ?, "
                    + "SubCategoria_id_subcategoria = ?, estado = ?, fecha_inicio_oferta = ?, fecha_fin_oferta = ? "
                    + "WHERE id_producto = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miProducto.getNombre_producto());
            ps.setString(2, miProducto.getDescripcion());
            ps.setFloat(3, miProducto.getPrecio_producto());
            if (miProducto.getPrecio_oferta() != null) {
                ps.setDouble(4, miProducto.getPrecio_oferta());
            } else {
                ps.setNull(4, java.sql.Types.DECIMAL);
            }
            ps.setInt(5, miProducto.getCantidad_stock());
            ps.setString(6, miProducto.getImagen_principal());
            ps.setInt(7, miProducto.getCategoria_id_categoria());
            if (miProducto.getSubCategoria_id_subcategoria() != null) {
                ps.setInt(8, miProducto.getSubCategoria_id_subcategoria());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }
            ps.setString(9, miProducto.getEstado());
            if (miProducto.getFecha_inicio_oferta() != null) {
                ps.setTimestamp(10, miProducto.getFecha_inicio_oferta());
            } else {
                ps.setNull(10, java.sql.Types.TIMESTAMP);
            }
            if (miProducto.getFecha_fin_oferta() != null) {
                ps.setTimestamp(11, miProducto.getFecha_fin_oferta());
            } else {
                ps.setNull(11, java.sql.Types.TIMESTAMP);
            }
            ps.setInt(12, miProducto.getId_producto());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR PRODUCTO ===================== */
    public boolean modificarProducto(Producto miProducto) {
        return actualizarProducto(miProducto);
    }

    /* ===================== ELIMINAR PRODUCTO ===================== */
    public boolean eliminarProducto(int idProducto) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Producto WHERE id_producto = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idProducto);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage()
                    + "\nNota: Si el producto tiene relaciones (Detalle_Factura, Carrito, Colores) primero debes eliminarlas.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS PRODUCTOS ===================== */
    public List<Producto> listarProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT " + COLUMNAS + " FROM Producto";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaProductos.add(mapearProducto(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return listaProductos;
    }

    /* ===================== LISTAR PRODUCTOS POR CATEGORIA ===================== */
    public List<Producto> consultarProductosPorCategoria(int idCategoria) {
        List<Producto> listaProductos = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT " + COLUMNAS + " FROM Producto WHERE Categoria_id_categoria = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaProductos.add(mapearProducto(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar productos por categoria: " + e.getMessage());
        }
        return listaProductos;
    }

    /* ===================== LISTAR PRODUCTOS POR ESTADO ===================== */
    public List<Producto> listarProductosPorEstado(String estado) {
        List<Producto> listaProductos = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT " + COLUMNAS + " FROM Producto WHERE estado = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaProductos.add(mapearProducto(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar productos por estado: " + e.getMessage());
        }
        return listaProductos;
    }

    /* ===================== HELPER: MAPEAR RESULTSET -> PRODUCTO ===================== */
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        return mapearProducto(rs, true);
    }

    private Producto mapearProducto(ResultSet rs, boolean aplicarVigencia) throws SQLException {
        Producto miProducto = new Producto();
        miProducto.setId_producto(rs.getInt("id_producto"));
        miProducto.setNombre_producto(rs.getString("nombre_producto"));
        miProducto.setDescripcion(rs.getString("descripcion"));
        miProducto.setPrecio_producto(rs.getFloat("precio_producto"));
        double precioOferta = rs.getDouble("precio_oferta");
        miProducto.setPrecio_oferta(rs.wasNull() ? null : precioOferta);
        miProducto.setCantidad_stock(rs.getInt("cantidad_stock"));
        miProducto.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
        miProducto.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
        miProducto.setImagen_principal(rs.getString("imagen_principal"));
        miProducto.setCategoria_id_categoria(rs.getInt("Categoria_id_categoria"));
        int subCategoria = rs.getInt("SubCategoria_id_subcategoria");
        miProducto.setSubCategoria_id_subcategoria(rs.wasNull() ? null : subCategoria);
        miProducto.setEstado(rs.getString("estado"));
        miProducto.setFecha_inicio_oferta(rs.getTimestamp("fecha_inicio_oferta"));
        miProducto.setFecha_fin_oferta(rs.getTimestamp("fecha_fin_oferta"));
        if (aplicarVigencia && miProducto.getPrecio_oferta() != null
                && !ofertaVigente(miProducto.getFecha_inicio_oferta(), miProducto.getFecha_fin_oferta())) {
            miProducto.setPrecio_oferta(null);
        }
        return miProducto;
    }

    /**
     * Una oferta sin fechas es siempre vigente; con fechas, solo lo es dentro
     * de la ventana [inicio, fin]. La usan también TiendaDAO (bloqueo de fila
     * en checkout) para que el precio cobrado coincida con el mostrado.
     */
    public static boolean ofertaVigente(Timestamp inicio, Timestamp fin) {
        long ahora = System.currentTimeMillis();
        if (inicio != null && ahora < inicio.getTime()) return false;
        if (fin != null && ahora > fin.getTime()) return false;
        return true;
    }
}
