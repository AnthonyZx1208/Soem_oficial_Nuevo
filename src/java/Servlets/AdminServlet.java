package Servlets;
import Controlador.TiendaDAO;
import Controlador.ProductoDAO;
import Controlador.CategoriaDAO;
import Modelo.Producto;
import Modelo.Categoria;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private boolean admin(HttpServletRequest r) {
        return Integer.valueOf(1).equals(r.getSession().getAttribute("usuarioRol"));
    }
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        if(!admin(req)) {
            res.sendError(403);
            return;
        }TiendaDAO d=new TiendaDAO();
        req.setAttribute("usuarios",d.contarUsuarios());
        req.setAttribute("productos",d.contarProductos());
        req.setAttribute("compras",d.contarOrdenes(""));
        req.setAttribute("pendientes",d.contarOrdenes("WHERE estado_compra IN ('Pendiente','Verificacion de Pago')"));
        req.setAttribute("ordenes",d.ordenesPendientes());
        req.setAttribute("listaProductos",new ProductoDAO().listarProductos());
        req.setAttribute("categorias",new CategoriaDAO().listarCategorias());
        req.getRequestDispatcher("/vistas/admin.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        if(!admin(req)) {
            res.sendError(403);
            return;
        }if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403);
            return;
        }try {
            new TiendaDAO().actualizarEstado(Integer.parseInt(req.getParameter("ordenId")),req.getParameter("estado"),req.getParameter("motivo"));
            req.setAttribute("exito","Orden actualizada.");
        }catch(SQLException|NumberFormatException ex) {
            req.setAttribute("error","No fue posible actualizar la orden.");
        }doGet(req,res);
    }
}
