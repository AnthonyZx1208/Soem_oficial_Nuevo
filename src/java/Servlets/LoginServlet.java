package Servlets;
import Controlador.UsuarioDAO;
import Modelo.Usuario;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        SeguridadAplicacion.csrf(req.getSession());
        req.getRequestDispatcher("/vistas/login.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403,"Solicitud no válida");
            return;
        }
        String correo=req.getParameter("correo"), clave=req.getParameter("contrasena");
        Usuario u=new UsuarioDAO().consultarUsuarioPorCorreo(correo==null?"":correo.trim().toLowerCase());
        if(u==null||!SeguridadAplicacion.verificarPassword(clave,u.getContrasena())) {
            req.setAttribute("error","Credenciales inválidas.");
            req.getRequestDispatcher("/vistas/login.jsp").forward(req,res);
            return;
        }
        // Conservar el carrito del visitante al regenerar la sesión (protección
        // contra fijación de sesión) para que no se pierda al iniciar sesión.
        HttpSession antigua=req.getSession(false);
        Object carritoInvitado=antigua==null?null:antigua.getAttribute("carrito");
        if(antigua!=null)antigua.invalidate();
        HttpSession s=req.getSession(true);
        if(carritoInvitado!=null)s.setAttribute("carrito",carritoInvitado);
        s.setAttribute("usuarioId",u.getIdUsuario());
        s.setAttribute("usuarioNombre",u.getNombreUsuario());
        s.setAttribute("usuarioRol",u.getRolesIdRol());
        SeguridadAplicacion.csrf(s);
        res.sendRedirect(req.getContextPath()+"/home");
    }
}
