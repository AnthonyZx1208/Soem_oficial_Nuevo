package Controlador;
import java.sql.*;
import java.util.*;
public class TiendaDAO {
    private final Conexion conexion = new Conexion();
    public int contarDeseos(int usuarioId) {
        return contar("SELECT COUNT(*) FROM Lista_Deseos WHERE Usuario_id_usuario=?", usuarioId);
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
}
