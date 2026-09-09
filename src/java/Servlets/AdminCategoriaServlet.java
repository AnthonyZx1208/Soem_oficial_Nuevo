package Servlets;
import Controlador.CategoriaDAO;
import Controlador.ProductoDAO;
import Modelo.Categoria;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/admin/categorias")
public class AdminCategoriaServlet extends HttpServlet {
    private boolean admin(HttpServletRequest r) {
        return SeguridadAplicacion.tienePermiso(r, "Gestionar categorías");
    }
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        if(!admin(req)) {
            res.sendError(403);
            return;
        }
        CategoriaDAO dao=new CategoriaDAO();
        req.setAttribute("categorias",dao.listarCategorias());
        String editarId=req.getParameter("editar");
        if(editarId!=null) {
            try {
                req.setAttribute("editando",dao.consultarCategoria(Integer.parseInt(editarId)));
            }catch(NumberFormatException ignorado) { }
        }
        req.getRequestDispatcher("/vistas/admin_categorias.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        if(!admin(req)) {
            res.sendError(403);
            return;
        }if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403);
            return;
        }
        String accion=req.getParameter("action");
        CategoriaDAO dao=new CategoriaDAO();
        try {
            if("crear".equals(accion)) {
                Categoria c=leerFormulario(req,new Categoria());
                if(c.getNombre_categoria().isBlank()) throw new IllegalArgumentException("El nombre es obligatorio.");
                boolean creada=dao.insertarCategoria(c);
                req.setAttribute(creada?"exito":"error",creada?"Categoría creada.":"No fue posible crear la categoría.");
            }else if("actualizar".equals(accion)) {
                Categoria c=leerFormulario(req,new Categoria());
                c.setId_categoria(Integer.parseInt(req.getParameter("id")));
                if(c.getNombre_categoria().isBlank()) throw new IllegalArgumentException("El nombre es obligatorio.");
                boolean ok=dao.actualizarCategoria(c);
                req.setAttribute(ok?"exito":"error",ok?"Categoría actualizada.":"No fue posible actualizar la categoría.");
            }else if("eliminar".equals(accion)) {
                int id=Integer.parseInt(req.getParameter("id"));
                if(new ProductoDAO().consultarProductosPorCategoria(id).size()>0) {
                    req.setAttribute("error","No se puede eliminar: la categoría tiene productos asociados.");
                }else {
                    boolean ok=dao.eliminarCategoria(id);
                    req.setAttribute(ok?"exito":"error",ok?"Categoría eliminada.":"No fue posible eliminar la categoría.");
                }
            }
        }catch(IllegalArgumentException ex) {
            req.setAttribute("error",ex.getMessage()!=null?ex.getMessage():"Datos inválidos.");
        }
        doGet(req,res);
    }
    private Categoria leerFormulario(HttpServletRequest req,Categoria c) {
        c.setNombre_categoria(limpiar(req.getParameter("nombre")));
        c.setDescripcion(limpiar(req.getParameter("descripcion")));
        String imagen=limpiar(req.getParameter("imagenUrl"));
        c.setImagen_url(imagen.isBlank()?null:imagen);
        String estado=req.getParameter("estado");
        c.setEstado("Inactivo".equals(estado)?"Inactivo":"Activo");
        return c;
    }
    private String limpiar(String v) {
        return v==null?"":v.trim();
    }
}
