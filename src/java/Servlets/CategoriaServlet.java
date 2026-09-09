/*
 * SOEM Oficial - Servlet de Categorías
 * Muestra productos por categoría
 */
package Servlets;

import Controlador.ProductoDAO;
import Controlador.CategoriaDAO;
import Modelo.Producto;
import Modelo.Categoria;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet de Categoría
 * Muestra productos filtrados por categoría
 */
@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idCategoriaStr = request.getParameter("id");
        
        if (idCategoriaStr == null || idCategoriaStr.isEmpty()) {
            response.sendRedirect("home");
            return;
        }
        
        try {
            int idCategoria = Integer.parseInt(idCategoriaStr);
            
            // Obtener información de la categoría
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categoria = categoriaDAO.consultarCategoria(idCategoria);
            
            if (categoria != null) {
                request.setAttribute("categoria", categoria);
                
                // Obtener productos de esta categoría
                ProductoDAO productoDAO = new ProductoDAO();
                List<Producto> productos = productoDAO.consultarProductosPorCategoria(idCategoria);
                request.setAttribute("productos", productos);
                
                request.getRequestDispatcher("/vistas/categoria.jsp").forward(request, response);
            } else {
                response.sendRedirect("home");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
        }
    }
}
