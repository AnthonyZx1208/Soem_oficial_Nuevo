package Servlets;
import Controlador.ConfiguracionDAO;
import Modelo.ConfiguracionSitio;
import Seguridad.SeguridadAplicacion;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.UUID;

@WebServlet("/admin/identidad")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, maxRequestSize = 4 * 1024 * 1024)
public class AdminIdentidadServlet extends HttpServlet {

    private static final String CARPETA = "/assets/identidad/";

    private boolean admin(HttpServletRequest r) {
        return Integer.valueOf(1).equals(r.getSession().getAttribute("usuarioRol"));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!admin(req)) {
            res.sendError(403);
            return;
        }
        req.setAttribute("configuracion", new ConfiguracionDAO().obtener());
        req.getRequestDispatcher("/vistas/admin_identidad.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!admin(req)) {
            res.sendError(403);
            return;
        }
        if (!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403);
            return;
        }
        ConfiguracionDAO dao = new ConfiguracionDAO();
        try {
            ConfiguracionSitio actual = dao.obtener();
            ConfiguracionSitio c = new ConfiguracionSitio();
            String nombre = limpiar(req.getParameter("nombreTienda"));
            if (nombre.isBlank()) throw new IllegalArgumentException("El nombre de la tienda es obligatorio.");
            c.setNombreTienda(nombre);
            c.setColorPrimario(validarHex(req.getParameter("colorPrimario"), "primario"));
            c.setColorSecundario(validarHex(req.getParameter("colorSecundario"), "secundario"));
            c.setColorAcento(validarHex(req.getParameter("colorAcento"), "de acento"));
            c.setContactoWhatsapp(limpiar(req.getParameter("contactoWhatsapp")));
            String email = limpiar(req.getParameter("contactoEmail"));
            c.setContactoEmail(email.isBlank() ? null : email);
            String descripcion = limpiar(req.getParameter("descripcionTienda"));
            c.setDescripcionTienda(descripcion.isBlank() ? null : descripcion);
            c.setLogoUrl(subirImagenSiViene(req, "logo", actual.getLogoUrl()));
            c.setFaviconUrl(subirImagenSiViene(req, "favicon", actual.getFaviconUrl()));
            boolean ok = dao.actualizar(c);
            req.setAttribute(ok ? "exito" : "error", ok ? "Identidad actualizada." : "No fue posible actualizar la identidad.");
        } catch (IllegalArgumentException ex) {
            req.setAttribute("error", ex.getMessage());
        }
        doGet(req, res);
    }

    private String validarHex(String valor, String etiqueta) {
        if (valor == null || !valor.matches("^#[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("El color " + etiqueta + " debe ser un hexadecimal válido, ej: #C9A24D.");
        }
        return valor.toUpperCase();
    }

    private String subirImagenSiViene(HttpServletRequest req, String campo, String actual) throws IOException, ServletException {
        Part parte = req.getPart(campo);
        if (parte == null || parte.getSize() == 0) return actual;
        String nombreOriginal = parte.getSubmittedFileName();
        String extension = "";
        if (nombreOriginal != null && nombreOriginal.contains(".")) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.')).toLowerCase();
        }
        if (!extension.matches("\\.(jpg|jpeg|png|webp|svg|ico)")) {
            throw new IllegalArgumentException("Formato de imagen no permitido para " + campo + ".");
        }
        String nombreArchivo = campo + "-" + UUID.randomUUID() + extension;
        String rutaReal = getServletContext().getRealPath(CARPETA);
        File carpeta = new File(rutaReal);
        if (!carpeta.exists()) carpeta.mkdirs();
        try (var entrada = parte.getInputStream()) {
            Files.copy(entrada, new File(carpeta, nombreArchivo).toPath());
        }
        return "assets/identidad/" + nombreArchivo;
    }

    private String limpiar(String v) {
        return v == null ? "" : v.trim();
    }
}
