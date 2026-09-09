package Controlador;
import Modelo.Producto;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class TiendaDAO {
    private final Conexion conexion = new Conexion();
    public int contarDeseos(int usuarioId) {
        return contar("SELECT COUNT(*) FROM Lista_Deseos WHERE Usuario_id_usuario=?", usuarioId);
    }
    public int contarUsuarios() {
        return contar("SELECT COUNT(*) FROM Usuario", null);
    }
    public int contarProductos() {
        return contar("SELECT COUNT(*) FROM Producto WHERE estado='Activo'", null);
    }
    public int contarOrdenes(String condicion) {
        return contar("SELECT COUNT(*) FROM Cabeza_Factura " + condicion, null);
    }
    private int contar(String sql, Integer parametro) {
        try (Connection c=conexion.getConn(); PreparedStatement ps=c.prepareStatement(sql)) {
            if (c == null) return 0;
            if (parametro != null) ps.setInt(1,parametro);
            try(ResultSet rs=ps.executeQuery()) {
                return rs.next()?rs.getInt(1):0;
            }
        } catch(SQLException ex) {
            return 0;
        }
    }
    public boolean agregarDeseo(int usuarioId, int productoId) {
        String sql="INSERT IGNORE INTO Lista_Deseos (Usuario_id_usuario,Producto_id_producto) VALUES (?,?)";
        try(Connection c=conexion.getConn(); PreparedStatement ps=c.prepareStatement(sql)) {
            if(c==null)return false;
            ps.setInt(1,usuarioId);
            ps.setInt(2,productoId);
            ps.executeUpdate();
            return true;
        } catch(SQLException ex) {
            return false;
        }
    }
    public boolean eliminarDeseo(int usuarioId, int productoId) {
        try(Connection c=conexion.getConn(); PreparedStatement ps=c.prepareStatement("DELETE FROM Lista_Deseos WHERE Usuario_id_usuario=? AND Producto_id_producto=?")) {
            if(c==null)return false;
            ps.setInt(1,usuarioId);
            ps.setInt(2,productoId);
            return ps.executeUpdate()>0;
        }catch(SQLException ex) {
            return false;
        }
    }
    public List<Map<String,Object>> deseos(int usuarioId) {
        return filas("SELECT p.id_producto,p.nombre_producto,p.descripcion,p.precio_producto,p.precio_oferta,p.cantidad_stock,p.imagen_principal FROM Lista_Deseos d JOIN Producto p ON p.id_producto=d.Producto_id_producto WHERE d.Usuario_id_usuario=? ORDER BY d.fecha_agregado DESC", usuarioId);
    }
    public List<Map<String,Object>> ordenesUsuario(int usuarioId) {
        return filas("SELECT id_cabeza_factura,numero_orden,total_compra,estado_compra,motivo_rechazo,fecha_compra FROM Cabeza_Factura WHERE Usuario_id_usuario=? ORDER BY fecha_compra DESC", usuarioId);
    }
    public List<Map<String,Object>> ordenesPendientes() {
        return filas("SELECT f.id_cabeza_factura,f.numero_orden,f.total_compra,f.estado_compra,f.fecha_compra,u.Nombre_Usuario,u.Apellido_Usuario,u.correo FROM Cabeza_Factura f JOIN Usuario u ON u.id_Usuario=f.Usuario_id_usuario ORDER BY f.fecha_compra DESC", null);
    }
    public List<Map<String,Object>> detalleOrden(int ordenId) {
        return filas("SELECT d.cantidad,d.precio_unitario,d.subtotal,p.nombre_producto,p.imagen_principal FROM Detalle_Factura d JOIN Producto p ON p.id_producto=d.Producto_id_producto WHERE d.Cabeza_Factura_id_cabeza_factura=?", ordenId);
    }
    public List<Map<String,Object>> historialOrden(int ordenId) {
        return filas("SELECT estado,detalle,fecha FROM Estado_Orden_Historial WHERE Cabeza_Factura_id_cabeza_factura=? ORDER BY fecha", ordenId);
    }
    private List<Map<String,Object>> filas(String sql, Integer parametro) {
        List<Map<String,Object>> lista=new ArrayList<>();
        try(Connection c=conexion.getConn(); PreparedStatement ps=c.prepareStatement(sql)) {
            if(c==null)return lista;
            if(parametro!=null)ps.setInt(1,parametro);
            try(ResultSet rs=ps.executeQuery()) {
                ResultSetMetaData md=rs.getMetaData();
                while(rs.next()) {
                    Map<String,Object> f=new LinkedHashMap<>();
                    for(int i=1;i<=md.getColumnCount();i++)f.put(md.getColumnLabel(i),rs.getObject(i));
                    lista.add(f);
                }
            }
        }catch(SQLException ex) {
        }
        return lista;
    }
    public Map<String,Object> perfil(int usuarioId) {
        List<Map<String,Object>> lista=filas("SELECT id_Usuario,Nombre_Usuario,Apellido_Usuario,Numero_Documento,Telefono,correo,Direccion,Fecha_nacimiento FROM Usuario WHERE id_Usuario=?",usuarioId);
        return lista.isEmpty()?null:lista.get(0);
    }
    public boolean actualizarPerfil(int id,String nombre,String apellido,String telefono,String direccion) {
        try(Connection c=conexion.getConn();PreparedStatement ps=c.prepareStatement("UPDATE Usuario SET Nombre_Usuario=?,Apellido_Usuario=?,Telefono=?,Direccion=? WHERE id_Usuario=?")) {
            if(c==null)return false;
            ps.setString(1,nombre);
            ps.setString(2,apellido);
            ps.setString(3,telefono);
            ps.setString(4,direccion);
            ps.setInt(5,id);
            return ps.executeUpdate()>0;
        }catch(SQLException ex) {
            return false;
        }
    }
    public String crearOrden(int usuarioId, List<Modelo.CarritoCompra> carrito) throws SQLException {
        if(carrito==null||carrito.isEmpty()) throw new SQLException("El carrito está vacío");
        try(Connection c=conexion.getConn()) {
            if(c==null)throw new SQLException("No hay conexión con la base de datos");
            c.setAutoCommit(false);
            try {
                BigDecimal total=BigDecimal.ZERO;
                List<Producto> productos=new ArrayList<>();
                for(Modelo.CarritoCompra item:carrito) {
                    Producto p=productoBloqueado(c,item.getProductoIdProducto());
                    if(p==null||p.getCantidad_stock()<item.getCantidad())throw new SQLException("Stock insuficiente para uno de los productos");
                    productos.add(p);
                    total=total.add(BigDecimal.valueOf(item.getPrecioUnitario()).multiply(BigDecimal.valueOf(item.getCantidad())));
                }
                String numero="SOEM-"+DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now())+"-"+usuarioId;
                int orden;
                try(PreparedStatement ps=c.prepareStatement("INSERT INTO Cabeza_Factura(numero_orden,Usuario_id_usuario,total_compra,estado_compra,Metodo_Pago_id_metodo_pago) VALUES(?,?,?,'Verificacion de Pago',1)",Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1,numero);
                    ps.setInt(2,usuarioId);
                    ps.setBigDecimal(3,total);
                    ps.executeUpdate();
                    try(ResultSet k=ps.getGeneratedKeys()) {
                        k.next();
                        orden=k.getInt(1);
                    }
                }
                for(int i=0;i<carrito.size();i++) {
                    Modelo.CarritoCompra item=carrito.get(i);
                    Producto p=productos.get(i);
                    BigDecimal precio=BigDecimal.valueOf(item.getPrecioUnitario());
                    try(PreparedStatement d=c.prepareStatement("INSERT INTO Detalle_Factura(Cabeza_Factura_id_cabeza_factura,Producto_id_producto,Colores_id_color,Talla_id_talla,cantidad,precio_unitario,subtotal) VALUES(?,?,?,?,?,?,?)")) {
                        d.setInt(1,orden);
                        d.setInt(2,item.getProductoIdProducto());
                        if(item.getColores_id_color()==null)d.setNull(3,Types.INTEGER);
                        else d.setInt(3,item.getColores_id_color());
                        if(item.getTalla_id_talla()==null)d.setNull(4,Types.INTEGER);
                        else d.setInt(4,item.getTalla_id_talla());
                        d.setInt(5,item.getCantidad());
                        d.setBigDecimal(6,precio);
                        d.setBigDecimal(7,precio.multiply(BigDecimal.valueOf(item.getCantidad())));
                        d.executeUpdate();
                    }
                    try(PreparedStatement u=c.prepareStatement("UPDATE Producto SET cantidad_stock=cantidad_stock-? WHERE id_producto=?")) {
                        u.setInt(1,item.getCantidad());
                        u.setInt(2,p.getId_producto());
                        u.executeUpdate();
                    }
                    try(PreparedStatement r=c.prepareStatement("INSERT INTO Reserva_Stock(Cabeza_Factura_id_cabeza_factura,Producto_id_producto,cantidad,estado,fecha_expiracion) VALUES(?,?,?,'Reservado',DATE_ADD(NOW(), INTERVAL 7 DAY))")) {
                        r.setInt(1,orden);
                        r.setInt(2,p.getId_producto());
                        r.setInt(3,item.getCantidad());
                        r.executeUpdate();
                    }
                }
                registrarEstado(c,orden,"Verificacion de Pago","Orden creada; stock reservado durante siete días.");
                c.commit();
                return numero;
            }catch(SQLException ex) {
                c.rollback();
                throw ex;
            }finally {
                c.setAutoCommit(true);
            }
        }
    }
    private Producto productoBloqueado(Connection c,int id)throws SQLException {
        try(PreparedStatement ps=c.prepareStatement("SELECT id_producto,nombre_producto,precio_producto,precio_oferta,cantidad_stock FROM Producto WHERE id_producto=? FOR UPDATE")) {
            ps.setInt(1,id);
            try(ResultSet r=ps.executeQuery()) {
                if(!r.next())return null;
                Producto p=new Producto();
                p.setId_producto(r.getInt(1));
                p.setNombre_producto(r.getString(2));
                p.setPrecio_producto(r.getFloat(3));
                double oferta=r.getDouble(4);
                p.setPrecio_oferta(r.wasNull()?null:oferta);
                p.setCantidad_stock(r.getInt(5));
                return p;
            }
        }
    }
    public boolean actualizarEstado(int ordenId,String estado,String motivo)throws SQLException {
        Set<String> validos=Set.of("Verificacion de Pago","Pago Aprobado","Preparando Producto","Producto Enviado","Producto Entregado","Rechazado");
        if(!validos.contains(estado))return false;
        try(Connection c=conexion.getConn()) {
            if(c==null)return false;
            c.setAutoCommit(false);
            try {
                try(PreparedStatement ps=c.prepareStatement("UPDATE Cabeza_Factura SET estado_compra=?,motivo_rechazo=? WHERE id_cabeza_factura=?")) {
                    ps.setString(1,estado);
                    ps.setString(2,"Rechazado".equals(estado)?motivo:null);
                    ps.setInt(3,ordenId);
                    if(ps.executeUpdate()==0)throw new SQLException("Orden no encontrada");
                }
                if("Rechazado".equals(estado)) {
                    try(PreparedStatement q=c.prepareStatement("SELECT Producto_id_producto,cantidad FROM Reserva_Stock WHERE Cabeza_Factura_id_cabeza_factura=? AND estado='Reservado'")) {
                        q.setInt(1,ordenId);
                        try(ResultSet rs=q.executeQuery()) {
                            while(rs.next())try(PreparedStatement u=c.prepareStatement("UPDATE Producto SET cantidad_stock=cantidad_stock+? WHERE id_producto=?")) {
                                u.setInt(1,rs.getInt(2));
                                u.setInt(2,rs.getInt(1));
                                u.executeUpdate();
                            }
                        }
                    }try(PreparedStatement u=c.prepareStatement("UPDATE Reserva_Stock SET estado='Liberado' WHERE Cabeza_Factura_id_cabeza_factura=?")) {
                        u.setInt(1,ordenId);
                        u.executeUpdate();
                    }
                }
                registrarEstado(c,ordenId,estado,motivo==null?"Estado actualizado por administración.":motivo);
                c.commit();
                return true;
            }catch(SQLException ex) {
                c.rollback();
                throw ex;
            }finally {
                c.setAutoCommit(true);
            }
        }
    }
    private void registrarEstado(Connection c,int orden,String estado,String detalle)throws SQLException {
        try(PreparedStatement ps=c.prepareStatement("INSERT INTO Estado_Orden_Historial(Cabeza_Factura_id_cabeza_factura,estado,detalle) VALUES(?,?,?)")) {
            ps.setInt(1,orden);
            ps.setString(2,estado);
            ps.setString(3,detalle);
            ps.executeUpdate();
        }
    }
}
