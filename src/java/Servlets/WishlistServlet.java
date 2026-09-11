package Servlets;
import Controlador.TiendaDAO;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        Integer id=(Integer)req.getSession().getAttribute("usuarioId");
        if(id==null) {
            res.sendRedirect(req.getContextPath()+"/login");
            return;
        }req.setAttribute("deseos",new TiendaDAO().deseos(id));
        req.getRequestDispatcher("/vistas/wishlist.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        Integer id=(Integer)req.getSession().getAttribute("usuarioId");
        if(id==null) {
            res.sendRedirect(req.getContextPath()+"/login");
            return;
        }if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403);
            return;
        }int p=parse(req.getParameter("productoId"));
        TiendaDAO dao=new TiendaDAO();
        if("remove".equals(req.getParameter("action")))dao.eliminarDeseo(id,p);
        else dao.agregarDeseo(id,p);
        res.sendRedirect(req.getContextPath()+"/wishlist");
    }
    private int parse(String v) {
        try {
            return Integer.parseInt(v);
        }catch(Exception e) {
            return 0;
        }
    }
}
