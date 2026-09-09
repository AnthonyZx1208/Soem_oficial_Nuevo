package Servlets;
import Controlador.TiendaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        Integer id=(Integer)req.getSession().getAttribute("usuarioId");
        if(id==null) {
            res.sendRedirect(req.getContextPath()+"/login");
            return;
        }TiendaDAO dao=new TiendaDAO();
        String orden=req.getParameter("id");
        if(orden!=null) {
            try {
                int oid=Integer.parseInt(orden);
                // La orden solicitada debe pertenecer al cliente autenticado.
                boolean propia=dao.ordenesUsuario(id).stream().anyMatch(o -> oid==((Number)o.get("id_cabeza_factura")).intValue());
                if(propia) {
                    req.setAttribute("detalle",dao.detalleOrden(oid));
                    req.setAttribute("historial",dao.historialOrden(oid));
                }
            }catch(NumberFormatException e) {
            }
        }req.setAttribute("ordenes",dao.ordenesUsuario(id));
        req.getRequestDispatcher("/vistas/orders.jsp").forward(req,res);
    }
}
