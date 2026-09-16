/*
 * SOEM Oficial - Servlet de Detalle de Producto
 * Muestra información detallada de un producto
 */
package Servlets;

import Controlador.ProductoDAO;
import Controlador.ProductosHasColoresDAO;
import Controlador.TiendaDAO;
import Modelo.Producto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet de Producto
 * Muestra detalle de un producto específico
 */
@WebServlet("/producto")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idProductoStr = request.getParameter("id");
        
        if (idProductoStr == null || idProductoStr.isEmpty()) {
            response.sendRedirect("home");
            return;
        }
        
        try {
            int idProducto = Integer.parseInt(idProductoStr);
            ProductoDAO productoDAO = new ProductoDAO();
            Producto producto = productoDAO.consultarProducto(idProducto);
            
            if (producto != null) {
                request.setAttribute("producto", producto);
                request.setAttribute("tallasProducto", new ProductosHasColoresDAO().tallasDisponibles(idProducto));
                Integer usuarioId = (Integer) request.getSession().getAttribute("usuarioId");
                if (usuarioId != null) {
                    request.setAttribute("enFavoritos", new TiendaDAO().enDeseos(usuarioId, idProducto));
                }

                // Calcular descuento si existe
                if (producto.getPrecio_oferta() != null && producto.getPrecio_oferta() > 0) {
                    double descuento = ((producto.getPrecio_producto() - producto.getPrecio_oferta()) / producto.getPrecio_producto()) * 100;
                    double ahorrosPesos = producto.getPrecio_producto() - producto.getPrecio_oferta();
                    request.setAttribute("descuento", (int) descuento);
                    request.setAttribute("ahorros", ahorrosPesos);
                }
                
                request.getRequestDispatcher("/vistas/producto.jsp").forward(request, response);
            } else {
                response.sendRedirect("home");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
        }
    }
}
