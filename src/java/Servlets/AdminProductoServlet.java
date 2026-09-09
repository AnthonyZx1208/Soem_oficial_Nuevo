package Servlets;
import Controlador.CategoriaDAO;
import Controlador.ColoresDAO;
import Controlador.Conexion;
import Controlador.ProductoDAO;
import Controlador.ProductosHasColoresDAO;
import Controlador.TallaDAO;
import Modelo.Producto;
import Modelo.ProductosHasColores;
import Seguridad.SeguridadAplicacion;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Part;

@WebServlet("/admin/productos")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class AdminProductoServlet extends HttpServlet {

    private boolean admin(HttpServletRequest r) {
        return SeguridadAplicacion.tienePermiso(r, "Gestionar productos");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!admin(req)) {
            res.sendError(403);
            return;
        }
        Integer idEditar = null;
        String editarId = req.getParameter("editar");
        if (editarId != null) {
            try {
                idEditar = Integer.parseInt(editarId);
            } catch (NumberFormatException ignorado) { }
        }
        mostrarFormulario(req, res, idEditar);
    }

    /** Recarga la lista y, si corresponde, el producto en edición con sus variantes. Común a doGet y a las acciones de doPost, así los mensajes de éxito/error (atributos del request) sobreviven al forward. */
    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse res, Integer idEditar) throws ServletException, IOException {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.listarProductos();
        String filtroCategoria = req.getParameter("categoria");
        String filtroEstado = req.getParameter("estado");
        if (filtroCategoria != null && !filtroCategoria.isBlank()) {
            try {
                int idCategoria = Integer.parseInt(filtroCategoria);
                productos.removeIf(p -> p.getCategoria_id_categoria() != idCategoria);
            } catch (NumberFormatException ignorado) { }
        }
        if (filtroEstado != null && !filtroEstado.isBlank()) {
            productos.removeIf(p -> !filtroEstado.equals(p.getEstado()));
        }
        req.setAttribute("productos", productos);
        req.setAttribute("categorias", new CategoriaDAO().listarCategorias());
        req.setAttribute("subcategorias", listarSubCategorias());
        req.setAttribute("tallas", new TallaDAO().listarTallas());
        req.setAttribute("colores", new ColoresDAO().listarColores());
        req.setAttribute("filtroCategoria", filtroCategoria);
        req.setAttribute("filtroEstado", filtroEstado);
        if (idEditar != null) {
            Producto editando = productoDAO.consultarProductoCrudo(idEditar);
            req.setAttribute("editando", editando);
            if (editando != null) {
                req.setAttribute("variantes", new ProductosHasColoresDAO().listarPorProducto(idEditar));
            }
        }
        req.getRequestDispatcher("/vistas/admin_productos.jsp").forward(req, res);
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
        String accion = req.getParameter("action");
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            switch (accion == null ? "" : accion) {
                case "crear": {
                    Producto p = leerFormulario(req, new Producto());
                    p.setImagen_principal(subirImagenSiViene(req, null));
                    boolean ok = productoDAO.insertarProducto(p);
                    req.setAttribute(ok ? "exito" : "error", ok ? "Producto creado." : "No fue posible crear el producto.");
                    break;
                }
                case "actualizar": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Producto existente = productoDAO.consultarProducto(id);
                    if (existente == null) throw new IllegalArgumentException("El producto no existe.");
                    Producto p = leerFormulario(req, new Producto());
                    p.setId_producto(id);
                    p.setImagen_principal(subirImagenSiViene(req, existente.getImagen_principal()));
                    boolean ok = productoDAO.actualizarProducto(p);
                    req.setAttribute(ok ? "exito" : "error", ok ? "Producto actualizado." : "No fue posible actualizar el producto.");
                    break;
                }
                case "eliminar": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    boolean ok = productoDAO.eliminarProducto(id);
                    req.setAttribute(ok ? "exito" : "error", ok ? "Producto eliminado."
                            : "No fue posible eliminar: probablemente tiene compras o variantes asociadas.");
                    break;
                }
                case "guardarVariante": {
                    ProductosHasColores v = new ProductosHasColores();
                    v.setProductoIdProducto(Integer.parseInt(req.getParameter("productoId")));
                    v.setColoresIdColor(Integer.parseInt(req.getParameter("colorId")));
                    v.setTallaIdTalla(Integer.parseInt(req.getParameter("tallaId")));
                    v.setCantidadDisponible(Math.max(0, Integer.parseInt(req.getParameter("cantidad"))));
                    boolean ok = new ProductosHasColoresDAO().guardarVariante(v);
                    req.setAttribute(ok ? "exito" : "error", ok ? "Variante guardada." : "No fue posible guardar la variante.");
                    req.setAttribute("volverEditar", v.getProductoIdProducto());
                    break;
                }
                case "eliminarVariante": {
                    int productoId = Integer.parseInt(req.getParameter("productoId"));
                    int colorId = Integer.parseInt(req.getParameter("colorId"));
                    int tallaId = Integer.parseInt(req.getParameter("tallaId"));
                    boolean ok = new ProductosHasColoresDAO().eliminarVariante(productoId, colorId, tallaId);
                    req.setAttribute(ok ? "exito" : "error", ok ? "Variante eliminada." : "No fue posible eliminar la variante.");
                    req.setAttribute("volverEditar", productoId);
                    break;
                }
                default:
                    req.setAttribute("error", "Acción no reconocida.");
            }
        } catch (IllegalArgumentException ex) {
            req.setAttribute("error", ex.getMessage() != null ? ex.getMessage() : "Datos inválidos.");
        }
        mostrarFormulario(req, res, (Integer) req.getAttribute("volverEditar"));
    }

    private Producto leerFormulario(HttpServletRequest req, Producto p) {
        String nombre = limpiar(req.getParameter("nombre"));
        if (nombre.length() < 3) throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres.");
        p.setNombre_producto(nombre);
        p.setDescripcion(limpiar(req.getParameter("descripcion")));
        p.setPrecio_producto(parseFloat(req.getParameter("precioProducto"), "El precio es obligatorio y debe ser mayor a 0."));
        if (p.getPrecio_producto() <= 0) throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        String precioOferta = req.getParameter("precioOferta");
        if (precioOferta != null && !precioOferta.isBlank()) {
            double oferta = parseFloat(precioOferta, "El precio de oferta no es válido.");
            if (oferta <= 0 || oferta >= p.getPrecio_producto())
                throw new IllegalArgumentException("El precio de oferta debe ser mayor a 0 y menor al precio normal.");
            p.setPrecio_oferta(oferta);
        } else {
            p.setPrecio_oferta(null);
        }
        p.setCantidad_stock(Math.max(0, parseInt(req.getParameter("cantidadStock"), "El stock no es válido.")));
        p.setCategoria_id_categoria(parseInt(req.getParameter("categoriaId"), "Selecciona una categoría."));
        String subcategoria = req.getParameter("subcategoriaId");
        p.setSubCategoria_id_subcategoria(subcategoria == null || subcategoria.isBlank() ? null : parseInt(subcategoria, "Subcategoría inválida."));
        String estado = req.getParameter("estado");
        p.setEstado("Inactivo".equals(estado) ? "Inactivo" : "Activo");
        p.setFecha_inicio_oferta(parseFechaHora(req.getParameter("fechaInicioOferta")));
        p.setFecha_fin_oferta(parseFechaHora(req.getParameter("fechaFinOferta")));
        return p;
    }

    private String subirImagenSiViene(HttpServletRequest req, String imagenActual) throws IOException, ServletException {
        Part parte = req.getPart("imagen");
        if (parte == null || parte.getSize() == 0) return imagenActual;
        String nombreOriginal = parte.getSubmittedFileName();
        String extension = "";
        if (nombreOriginal != null && nombreOriginal.contains(".")) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.')).toLowerCase();
        }
        if (!extension.matches("\\.(jpg|jpeg|png|webp|gif)")) {
            throw new IllegalArgumentException("Formato de imagen no permitido (usa jpg, png, webp o gif).");
        }
        String nombreArchivo = UUID.randomUUID() + extension;
        String rutaReal = getServletContext().getRealPath("/assets/productos/");
        File carpeta = new File(rutaReal);
        if (!carpeta.exists()) carpeta.mkdirs();
        try (var entrada = parte.getInputStream()) {
            Files.copy(entrada, new File(carpeta, nombreArchivo).toPath());
        }
        return "assets/productos/" + nombreArchivo;
    }

    private List<Map<String, Object>> listarSubCategorias() {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection c = new Conexion().getConn()) {
            if (c == null) return lista;
            try (PreparedStatement ps = c.prepareStatement("SELECT id_subcategoria, nombre_subcategoria, Categoria_id_categoria FROM SubCategoria ORDER BY Categoria_id_categoria, nombre_subcategoria");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> fila = new LinkedHashMap<>();
                    fila.put("id", rs.getInt(1));
                    fila.put("nombre", rs.getString(2));
                    fila.put("categoriaId", rs.getInt(3));
                    lista.add(fila);
                }
            }
        } catch (SQLException ex) {
            // sin subcategorías disponibles, el formulario sigue funcionando sin ellas
        }
        return lista;
    }

    private Timestamp parseFechaHora(String valor) {
        if (valor == null || valor.isBlank()) return null;
        try {
            return Timestamp.valueOf(LocalDateTime.parse(valor));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Formato de fecha/hora de oferta inválido.");
        }
    }

    private float parseFloat(String v, String mensajeError) {
        try {
            return Float.parseFloat(v);
        } catch (NumberFormatException | NullPointerException ex) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    private int parseInt(String v, String mensajeError) {
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException | NullPointerException ex) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    private String limpiar(String v) {
        return v == null ? "" : v.trim();
    }
}
