package Controlador;
import Modelo.Usuario;
import java.sql.*;
import java.util.*;
public class UsuarioDAO {
    private final Conexion conect = new Conexion();
    private static final String CAMPOS="id_Usuario,Nombre_Usuario,Apellido_Usuario,Numero_Documento,Telefono,correo,Contrasena,Direccion,Fecha_nacimiento,Fecha_vencimiento,Autorizacion_datos,Roles_id_rol,TipoDocumento_idTipoDocumento";
    public boolean insertarUsuario(Usuario u) {
        String sql="INSERT INTO Usuario(Nombre_Usuario,Apellido_Usuario,Numero_Documento,Telefono,correo,Contrasena,Direccion,Fecha_nacimiento,Autorizacion_datos,Roles_id_rol,TipoDocumento_idTipoDocumento) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection c=conect.getConn();PreparedStatement p=c.prepareStatement(sql)) {
            if(c==null)return false;
            p.setString(1,u.getNombreUsuario());
            p.setString(2,u.getApellidoUsuario());
            p.setString(3,u.getNumeroDocumento());
            p.setString(4,u.getTelefono());
            p.setString(5,u.getCorreo());
            p.setString(6,u.getContrasena());
            p.setString(7,u.getDireccion());
            if(u.getFechaNacimiento()==null)p.setNull(8,Types.DATE);
            else p.setDate(8,new java.sql.Date(u.getFechaNacimiento().getTime()));
            p.setString(9,u.getAutorizacionDatos());
            p.setInt(10,u.getRolesIdRol()==0?2:u.getRolesIdRol());
            p.setInt(11,u.getTipoDocumentoIdTipoDocumento()==0?1:u.getTipoDocumentoIdTipoDocumento());
            return p.executeUpdate()>0;
        }catch(SQLException e) {
            return false;
        }
    }
    public Usuario consultarUsuario(String documento) {
        return uno("SELECT "+CAMPOS+" FROM Usuario WHERE Numero_Documento=?",documento);
    }
    public Usuario consultarUsuarioPorCorreo(String correo) {
        return uno("SELECT "+CAMPOS+" FROM Usuario WHERE correo=?",correo);
    }
    private Usuario uno(String sql,String valor) {
        try(Connection c=conect.getConn();PreparedStatement p=c.prepareStatement(sql)) {
            if(c==null)return null;
            p.setString(1,valor);
            try(ResultSet r=p.executeQuery()) {
                return r.next()?mapear(r):null;
            }
        }catch(SQLException e) {
            return null;
        }
    }
    public List<Usuario> listarUsuarios() {
        List<Usuario> salida=new ArrayList<>();
        try(Connection c=conect.getConn();PreparedStatement p=c.prepareStatement("SELECT "+CAMPOS+" FROM Usuario");ResultSet r=p.executeQuery()) {
            if(c==null)return salida;
            while(r.next())salida.add(mapear(r));
        }catch(SQLException e) {
        }return salida;
    }
    public boolean actualizarUsuario(Usuario u) {
        return guardar(u);
    } public boolean modificarUsuario(Usuario u) {
        return guardar(u);
    }
    private boolean guardar(Usuario u) {
        String sql="UPDATE Usuario SET Nombre_Usuario=?,Apellido_Usuario=?,Numero_Documento=?,Telefono=?,correo=?,Direccion=?,Fecha_nacimiento=?,Autorizacion_datos=?,TipoDocumento_idTipoDocumento=? WHERE id_Usuario=?";
        try(Connection c=conect.getConn();PreparedStatement p=c.prepareStatement(sql)) {
            if(c==null)return false;
            p.setString(1,u.getNombreUsuario());
            p.setString(2,u.getApellidoUsuario());
            p.setString(3,u.getNumeroDocumento());
            p.setString(4,u.getTelefono());
            p.setString(5,u.getCorreo());
            p.setString(6,u.getDireccion());
            if(u.getFechaNacimiento()==null)p.setNull(7,Types.DATE);
            else p.setDate(7,new java.sql.Date(u.getFechaNacimiento().getTime()));
            p.setString(8,u.getAutorizacionDatos());
            p.setInt(9,u.getTipoDocumentoIdTipoDocumento());
            p.setInt(10,u.getIdUsuario());
            return p.executeUpdate()>0;
        }catch(SQLException e) {
            return false;
        }
    }
    public boolean eliminarUsuario(int id) {
        try(Connection c=conect.getConn();PreparedStatement p=c.prepareStatement("DELETE FROM Usuario WHERE id_Usuario=?")) {
            if(c==null)return false;
            p.setInt(1,id);
            return p.executeUpdate()>0;
        }catch(SQLException e) {
            return false;
        }
    }
    private Usuario mapear(ResultSet r)throws SQLException {
        Usuario u=new Usuario();
        u.setIdUsuario(r.getInt("id_Usuario"));
        u.setNombreUsuario(r.getString("Nombre_Usuario"));
        u.setApellidoUsuario(r.getString("Apellido_Usuario"));
        u.setNumeroDocumento(r.getString("Numero_Documento"));
        u.setTelefono(r.getString("Telefono"));
        u.setCorreo(r.getString("correo"));
        u.setContrasena(r.getString("Contrasena"));
        u.setDireccion(r.getString("Direccion"));
        u.setFechaNacimiento(r.getDate("Fecha_nacimiento"));
        u.setFechaVencimiento(r.getDate("Fecha_vencimiento"));
        u.setAutorizacionDatos(r.getString("Autorizacion_datos"));
        u.setRolesIdRol(r.getInt("Roles_id_rol"));
        u.setTipoDocumentoIdTipoDocumento(r.getInt("TipoDocumento_idTipoDocumento"));
        return u;
    }
}
