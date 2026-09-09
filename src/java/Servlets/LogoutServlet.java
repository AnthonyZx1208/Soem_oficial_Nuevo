/*
 * SOEM Oficial - Servlet de Logout
 * Cierra la sesión del usuario
 */
package Servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet de Logout
 * Cierra la sesión del usuario actual
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión actual
        HttpSession sesion = request.getSession(false);
        
        if (sesion != null) {
            // Invalidar la sesión
            sesion.invalidate();
        }
        
        // Redirigir al home
        response.sendRedirect("home");
    }
}
