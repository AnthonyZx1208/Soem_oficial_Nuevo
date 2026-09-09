package Servlets;
import Controlador.TiendaDAO;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        Integer id=(Integer)req.getSession().getAttribute("usuarioId");
        if(id==null) {
            res.sendRedirect(req.getContextPath()+"/login");
            return;
        }req.getRequestDispatcher("/vistas/checkout.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        Integer id=(Integer)req.getSession().getAttribute("usuarioId");
        if(id==null) {
            res.sendError(401);
            return;
        }if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403);
            return;
        }List<Modelo.CarritoCompra> carrito=(List<Modelo.CarritoCompra>)req.getSession().getAttribute("carrito");
        try {
            String orden=new TiendaDAO().crearOrden(id,carrito);
            req.getSession().removeAttribute("carrito");
            req.setAttribute("numeroOrden",orden);
            req.getRequestDispatcher("/vistas/confirmation.jsp").forward(req,res);
        }catch(SQLException ex) {
            req.setAttribute("error",ex.getMessage());
            req.getRequestDispatcher("/vistas/checkout.jsp").forward(req,res);
        }
    }
}
