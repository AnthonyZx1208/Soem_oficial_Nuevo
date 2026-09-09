package Servlets;
import Controlador.UsuarioDAO;
import Modelo.Usuario;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        SeguridadAplicacion.csrf(req.getSession());
        req.getRequestDispatcher("/vistas/register.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403,"Solicitud no válida");
            return;
        } String n=limpiar(req.getParameter("nombre")),a=limpiar(req.getParameter("apellido")),d=limpiar(req.getParameter("numeroDocumento")),t=limpiar(req.getParameter("telefono")),c=limpiar(req.getParameter("correo")).toLowerCase(),p=req.getParameter("contrasena"),pc=req.getParameter("confirmarContrasena");
        if(n.isBlank()||a.isBlank()||d.isBlank()||!c.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")||p==null||p.length()<10||!p.equals(pc)) {
            req.setAttribute("error","Revisa los datos: usa un correo válido y una contraseña de al menos 10 caracteres.");
            req.getRequestDispatcher("/vistas/register.jsp").forward(req,res);
            return;
        }
        UsuarioDAO dao=new UsuarioDAO();
        if(dao.consultarUsuarioPorCorreo(c)!=null) {
            req.setAttribute("error","El correo ya está registrado.");
            req.getRequestDispatcher("/vistas/register.jsp").forward(req,res);
            return;
        }
        Usuario u=new Usuario();
        u.setNombreUsuario(n);
        u.setApellidoUsuario(a);
        u.setNumeroDocumento(d);
        u.setTelefono(t);
        u.setCorreo(c);
        u.setContrasena(SeguridadAplicacion.hashPassword(p));
        u.setDireccion(limpiar(req.getParameter("direccion")));
        u.setAutorizacionDatos("Si");
        u.setRolesIdRol(2);
        u.setTipoDocumentoIdTipoDocumento(1);
        if(dao.insertarUsuario(u)) {
            req.setAttribute("exito","Registro completado. Ya puedes iniciar sesión.");
        }else req.setAttribute("error","No fue posible registrar tu cuenta. Verifica documento y correo.");
        req.getRequestDispatcher("/vistas/register.jsp").forward(req,res);
    }
    private String limpiar(String v) {
        return v==null?"":v.trim();
    }
}
