package Servlets;
import Controlador.RolesDAO;
import Controlador.TiendaDAO;
import Controlador.UsuarioDAO;
import Modelo.Usuario;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/usuarios")
public class AdminUsuariosServlet extends HttpServlet {

    private boolean admin(HttpServletRequest r) {
        return SeguridadAplicacion.tienePermiso(r, "Gestionar usuarios");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!admin(req)) {
            res.sendError(403);
            return;
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        req.setAttribute("usuarios", usuarios);
        req.setAttribute("roles", new RolesDAO().listarRoles());
        String verId = req.getParameter("ver");
        if (verId != null) {
            try {
                int id = Integer.parseInt(verId);
                usuarios.stream().filter(u -> u.getIdUsuario() == id).findFirst().ifPresent(u -> {
                    req.setAttribute("verUsuario", u);
                    req.setAttribute("ordenesUsuario", new TiendaDAO().ordenesUsuario(id));
                });
            } catch (NumberFormatException ignorado) { }
        }
        req.getRequestDispatcher("/vistas/admin_usuarios.jsp").forward(req, res);
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
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            if ("crearEmpleado".equals(accion)) {
                String nombre = limpiar(req.getParameter("nombre"));
                String apellido = limpiar(req.getParameter("apellido"));
                String documento = limpiar(req.getParameter("numeroDocumento"));
                String telefono = limpiar(req.getParameter("telefono"));
                String correo = limpiar(req.getParameter("correo")).toLowerCase();
                String contrasena = req.getParameter("contrasena");
                String confirmar = req.getParameter("confirmarContrasena");
                int rolId = parseInt(req.getParameter("rolId"), "Selecciona un rol.");
                if (rolId != 1 && rolId != 3) {
                    throw new IllegalArgumentException("Solo se pueden crear cuentas de personal (Admin o Vendedor) desde aquí.");
                }
                if (nombre.isBlank() || apellido.isBlank() || documento.isBlank()
                        || !correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
                        || contrasena == null || contrasena.length() < 10 || !contrasena.equals(confirmar)) {
                    throw new IllegalArgumentException("Revisa los datos: correo válido, documento y una contraseña de al menos 10 caracteres iguales en ambos campos.");
                }
                if (usuarioDAO.consultarUsuarioPorCorreo(correo) != null) {
                    throw new IllegalArgumentException("Ya existe una cuenta con ese correo.");
                }
                Usuario u = new Usuario();
                u.setNombreUsuario(nombre);
                u.setApellidoUsuario(apellido);
                u.setNumeroDocumento(documento);
                u.setTelefono(telefono);
                u.setCorreo(correo);
                u.setContrasena(SeguridadAplicacion.hashPassword(contrasena));
                u.setDireccion(limpiar(req.getParameter("direccion")));
                u.setAutorizacionDatos("Si");
                u.setRolesIdRol(rolId);
                u.setTipoDocumentoIdTipoDocumento(1);
                boolean ok = usuarioDAO.insertarUsuario(u);
                req.setAttribute(ok ? "exito" : "error", ok ? "Cuenta de personal creada." : "No fue posible crear la cuenta. Verifica documento y correo.");
            } else if ("cambiarRol".equals(accion)) {
                int id = parseInt(req.getParameter("id"), "Usuario inválido.");
                int rolId = parseInt(req.getParameter("rolId"), "Selecciona un rol.");
                boolean ok = usuarioDAO.cambiarRol(id, rolId);
                req.setAttribute(ok ? "exito" : "error", ok ? "Rol actualizado." : "No fue posible actualizar el rol.");
            } else {
                req.setAttribute("error", "Acción no reconocida.");
            }
        } catch (IllegalArgumentException ex) {
            req.setAttribute("error", ex.getMessage());
        }
        doGet(req, res);
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
