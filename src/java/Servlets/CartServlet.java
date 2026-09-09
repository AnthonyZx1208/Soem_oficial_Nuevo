package Servlets;
import Controlador.ProductoDAO;
import Modelo.CarritoCompra;
import Modelo.Producto;
import Seguridad.SeguridadAplicacion;
import java.io.IOException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        List<CarritoCompra> cart=carrito(req.getSession());
        double total=cart.stream().mapToDouble(i->i.getPrecioUnitario()*i.getCantidad()).sum();
        int unidades=cart.stream().mapToInt(CarritoCompra::getCantidad).sum();
        req.setAttribute("carrito",cart);
        req.setAttribute("total",total);
        req.setAttribute("unidades",unidades);
        req.getRequestDispatcher("/vistas/cart.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
        if(!SeguridadAplicacion.csrfValido(req)) {
            res.sendError(403);
            return;
        }List<CarritoCompra> cart=carrito(req.getSession());
        String a=req.getParameter("action");
        int index=num(req.getParameter("index"));
        if("remove".equals(a)&&index>=0&&index<cart.size())cart.remove(index);
        else if("update".equals(a)&&index>=0&&index<cart.size()) {
            int cantidad=Math.max(0,num(req.getParameter("cantidad")));
            if(cantidad==0)cart.remove(index);
            else {
                Producto p=new ProductoDAO().consultarProducto(cart.get(index).getProductoIdProducto());
                if(p!=null)cart.get(index).setCantidad(Math.min(cantidad,p.getCantidad_stock()));
            }
        }else if("add".equals(a)) {
            int producto=num(req.getParameter("productoId")),cantidad=Math.max(1,num(req.getParameter("cantidad")));
            Producto p=new ProductoDAO().consultarProducto(producto);
            if(p!=null&&p.getCantidad_stock()>=cantidad) {
                CarritoCompra i=new CarritoCompra();
                i.setProductoIdProducto(producto);
                i.setCantidad(cantidad);
                i.setColores_id_color(num(req.getParameter("color")));
                i.setTalla_id_talla(num(req.getParameter("talla")));
                i.setPrecioUnitario(p.getPrecio_oferta()==null?p.getPrecio_producto():p.getPrecio_oferta());
                // Una misma variante se agrupa en una única línea de carrito.
                CarritoCompra existente=null;
                for(CarritoCompra actual:cart) if(actual.getProductoIdProducto()==producto
                        && Objects.equals(actual.getColores_id_color(),i.getColores_id_color())
                        && Objects.equals(actual.getTalla_id_talla(),i.getTalla_id_talla())) { existente=actual; break; }
                if(existente==null) cart.add(i);
                else existente.setCantidad(Math.min(p.getCantidad_stock(),existente.getCantidad()+cantidad));
            }
        }res.sendRedirect(req.getContextPath()+"/cart?action=view");
    }
    private List<CarritoCompra> carrito(HttpSession s) {
        List<CarritoCompra> c=(List<CarritoCompra>)s.getAttribute("carrito");
        if(c==null) {
            c=new ArrayList<>();
            s.setAttribute("carrito",c);
        }return c;
    }private int num(String v) {
        try {
            return Integer.parseInt(v);
        }catch(Exception e) {
            return 0;
        }
    }
}
