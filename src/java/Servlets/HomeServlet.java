/*
 * SOEM Oficial - Servlet de Home
 * Gestiona la página de inicio y catálogo de productos
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
 * Servlet de Home
 * Muestra la página de inicio con productos destacados
 */
@WebServlet({"/home", "/index"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener categorías
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listarCategorias();
        request.setAttribute("categorias", categorias);

        // Obtener productos destacados
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productosDestacados = productoDAO.listarProductosPorEstado("Activo");
        request.setAttribute("productosDestacados", productosDestacados);

        // Redirigir a la vista
        request.getRequestDispatcher("/vistas/home.jsp").forward(request, response);
    }
}
