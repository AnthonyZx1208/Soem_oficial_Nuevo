<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="Modelo.Producto,java.text.NumberFormat,java.util.Locale"%><%Producto p=(Producto)request.getAttribute("producto");if(p==null){response.sendRedirect(request.getContextPath()+"/home");return;}String ctx=request.getContextPath();NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));double precio=p.getPrecio_oferta()==null?p.getPrecio_producto():p.getPrecio_oferta();int descuento=p.getPrecio_oferta()==null?0:(int)((p.getPrecio_producto()-p.getPrecio_oferta())*100/p.getPrecio_producto());%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title><%=p.getNombre_producto()%> | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-4">
    <nav class="breadcrumb-soem mb-4">
        <a href="<%=ctx%>/home">Inicio</a> / <a href="<%=ctx%>/categoria?id=<%=p.getCategoria_id_categoria()%>">Colección</a> / <span><%=p.getNombre_producto()%></span>
    </nav>
    <div class="row g-5">
        <div class="col-lg-6 position-relative">
            <img class="product-detail-image" src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="<%=p.getNombre_producto()%>"><%if(p.getCantidad_stock()==0){%><span class="badge badge-stockout position-absolute top-50 start-50 translate-middle p-3">SIN STOCK</span><%}%></div>
            <div class="col-lg-6">
                <p class="eyebrow">SOEM Oficial</p>
                <h1 class="display-6 fw-bold"><%=p.getNombre_producto()%></h1>
                <p class="text-secondary"><%=p.getDescripcion()%></p>
                <div class="price-panel mb-4"><%if(descuento>0){%><span class="old-price d-block"><%=cop.format(p.getPrecio_producto())%></span><%}%><span class="display-6 fw-bold"><%=cop.format(precio)%></span><%if(descuento>0){%><p class="text-success mb-0">Ahorras <%=cop.format(p.getPrecio_producto()-precio)%> (<%=descuento%>%)</p><%}%></div>
                <p class="<%=p.getCantidad_stock()>0?"stock-ok":"stock-out"%>"><%=p.getCantidad_stock()>0?"Stock disponible: "+p.getCantidad_stock()+" unidades":"Este producto no tiene stock"%></p><%if(p.getCantidad_stock()>0){%><form method="post" action="<%=ctx%>/cart" class="row g-3">
                <input type="hidden" name="csrf" value="<%=csrf%>">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productoId" value="<%=p.getId_producto()%>">
                <div class="col-md-4">
                    <label class="choice-label">Color</label>
                    <select class="form-select" name="color">
                        <option value="1">Negro</option>
                        <option value="2">Blanco</option>
                        <option value="3">Dorado</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="choice-label">Talla</label>
                    <select class="form-select" name="talla">
                        <option value="2">S</option>
                        <option value="3">M</option>
                        <option value="4">L</option>
                        <option value="5">XL</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="choice-label">Cantidad</label>
                    <input class="form-control" type="number" name="cantidad" min="1" max="<%=p.getCantidad_stock()%>" value="1">
                </div>
                <div class="col-12">
                    <button class="btn btn-dark w-100 py-3">Agregar al carrito</button>
                </div>
            </form><%}%><form method="post" action="<%=ctx%>/wishlist" class="mt-2">
            <input type="hidden" name="csrf" value="<%=csrf%>">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="productoId" value="<%=p.getId_producto()%>">
            <button class="btn btn-outline-dark w-100">♡ Guardar en lista de deseos</button>
        </form>
        <div class="notice mt-4">
            <strong>Pago seguro por NEqui.</strong> El inventario se reserva al crear la orden y se confirma tras verificar el pago.</div>
        </div>
    </div>
</main><%@include file="footer.jsp"%></body>
</html>
