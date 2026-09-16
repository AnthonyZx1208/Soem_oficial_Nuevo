/*
 * SOEM Oficial - Servlet de Home
 * Gestiona la página de inicio y catálogo de productos
 */
package Servlets;

import Controlador.ProductoDAO;
import Controlador.CategoriaDAO;
import Modelo.Producto;
import Modelo.Categoria;
import Seguridad.Util;
import java.io.IOException;
import java.util.ArrayList;
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

        String termino = Util.parametroUtf8(request, "q");
        if (termino != null && !termino.isBlank()) {
            String buscado = termino.trim().toLowerCase();
            List<Producto> resultados = new ArrayList<>();
            for (Producto p : productosDestacados) {
                if (p.getNombre_producto() != null && p.getNombre_producto().toLowerCase().contains(buscado)) {
                    resultados.add(p);
                }
            }
            request.setAttribute("resultadosBusqueda", resultados);
        } else {
            List<Producto> ofertas = new ArrayList<>();
            int maxDescuento = 0;
            for (Producto p : productosDestacados) {
                if (p.getPrecio_oferta() != null) {
                    ofertas.add(p);
                    int descuento = (int) ((p.getPrecio_producto() - p.getPrecio_oferta()) * 100 / p.getPrecio_producto());
                    if (descuento > maxDescuento) maxDescuento = descuento;
                }
            }
            request.setAttribute("ofertas", ofertas);
            request.setAttribute("maxDescuento", maxDescuento);
        }

        // Redirigir a la vista
        request.getRequestDispatcher("/vistas/home.jsp").forward(request, response);
    }
}
