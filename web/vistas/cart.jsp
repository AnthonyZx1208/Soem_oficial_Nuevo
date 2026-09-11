<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,Modelo.CarritoCompra,Modelo.Producto,Controlador.ProductoDAO,java.text.NumberFormat,Seguridad.Util"%><%String ctx=request.getContextPath();List<CarritoCompra> items=(List<CarritoCompra>)request.getAttribute("carrito");double total=(Double)request.getAttribute("total");int unidades=(Integer)request.getAttribute("unidades");NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));ProductoDAO productosDao=new ProductoDAO();%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Carrito | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Tu selección</p>
    <h1 class="section-title mb-4">Carrito de compras</h1><%if(items.isEmpty()){%><div class="text-center bg-white p-5">
    <h2 class="h4">Tu carrito está vacío</h2>
    <a href="<%=ctx%>/home" class="btn btn-gold mt-3">Explorar productos</a>
</div><%}else{%><div class="row g-4">
<div class="col-lg-8">
    <div class="bg-white p-3"><%for(int i=0;i<items.size();i++){CarritoCompra item=items.get(i);Producto p=productosDao.consultarProducto(item.getProductoIdProducto());if(p==null)continue;%><div class="d-flex gap-3 border-bottom py-3">
    <img class="cart-thumb" src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="" onerror="this.onerror=null;this.src='<%=ctx%>/assets/sin-imagen.svg'">
    <div class="flex-grow-1">
        <strong><%=Util.escapeHtml(p.getNombre_producto())%></strong>
        <div class="small text-secondary">Talla y color seleccionados</div>
        <span class="price"><%=cop.format(item.getPrecioUnitario())%></span>
    </div>
    <form method="post" action="<%=ctx%>/cart" class="d-flex align-items-center gap-2">
        <input type="hidden" name="csrf" value="<%=csrf%>">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="index" value="<%=i%>">
        <input class="form-control form-control-sm" style="width:70px" name="cantidad" type="number" min="1" max="<%=p.getCantidad_stock()%>" value="<%=item.getCantidad()%>">
        <button class="btn btn-sm btn-outline-dark">Actualizar</button>
    </form>
    <form method="post" action="<%=ctx%>/cart">
        <input type="hidden" name="csrf" value="<%=csrf%>">
        <input type="hidden" name="action" value="remove">
        <input type="hidden" name="index" value="<%=i%>">
        <button class="btn btn-sm btn-link text-danger">Eliminar</button>
    </form>
</div><%}%></div>
</div>
<aside class="col-lg-4">
    <div class="summary-card p-4">
        <h2 class="h5">Resumen</h2>
        <div class="d-flex justify-content-between">
            <span>Productos</span>
            <span><%=unidades%></span>
        </div>
        <div class="d-flex justify-content-between">
            <span>Envío</span>
            <span>Gratis</span>
        </div>
        <hr>
        <div class="d-flex justify-content-between total">
            <span>Total</span>
            <span><%=cop.format(total)%></span>
        </div>
        <button class="btn btn-gold w-100 mt-4" type="button" data-bs-toggle="modal" data-bs-target="#modalPago">Continuar al pago</button>
    </div>
</aside>
</div><%}%></main>
<div class="modal fade" id="modalPago" tabindex="-1" aria-labelledby="tituloPago" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h2 class="modal-title fs-5" id="tituloPago">Pago por NEqui</h2><button type="button" class="btn-close" data-bs-dismiss="modal"></button></div><div class="modal-body"><p>Tu orden quedará en <strong>verificación de pago</strong> y el inventario se reservará durante 24 horas.</p><ol><li>Confirma tu compra.</li><li>Paga el monto exacto desde NEqui.</li><li>Envía el comprobante y número de orden al WhatsApp <strong>315 084 6431</strong>.</li></ol><p class="small text-secondary mb-0">Por seguridad, el QR real de la cuenta NEqui debe cargarlo el administrador antes de publicar la tienda.</p></div><div class="modal-footer"><button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Volver</button><a class="btn btn-gold" href="<%=ctx%>/checkout">Confirmar y ver instrucciones</a></div></div></div></div>
<%@include file="footer.jsp"%></body>
</html>
