package Servlets;
import Controlador.TiendaDAO;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        Integer id=(Integer)req.getSession().getAttribute("usuarioId");
        if(id==null) {
            res.sendRedirect(req.getContextPath()+"/login");
            return;
        }req.setAttribute("perfil",new TiendaDAO().perfil(id));
        req.getRequestDispatcher("/vistas/profile.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        Integer id=(Integer)req.getSession().getAttribute("usuarioId");
        if(id==null) {
            res.sendError(401);
            return;
        }if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403);
            return;
        }boolean ok=new TiendaDAO().actualizarPerfil(id,limpiar(req.getParameter("nombre")),limpiar(req.getParameter("apellido")),limpiar(req.getParameter("telefono")),limpiar(req.getParameter("direccion")));
        req.getSession().setAttribute("usuarioNombre",limpiar(req.getParameter("nombre")));
        req.setAttribute(ok?"exito":"error",ok?"Perfil actualizado.":"No se pudo actualizar el perfil.");
        doGet(req,res);
    }
    private String limpiar(String v) {
        return v==null?"":v.trim();
    }
}
