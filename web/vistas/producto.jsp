<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="Modelo.Producto,Modelo.ConfiguracionSitio,Modelo.Talla,Controlador.ConfiguracionDAO,java.text.NumberFormat,java.util.List,java.util.Locale,Seguridad.Util"%><%Producto p=(Producto)request.getAttribute("producto");if(p==null){response.sendRedirect(request.getContextPath()+"/home");return;}String ctx=request.getContextPath();NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));double precio=p.getPrecio_oferta()==null?p.getPrecio_producto():p.getPrecio_oferta();int descuento=p.getPrecio_oferta()==null?0:(int)((p.getPrecio_producto()-p.getPrecio_oferta())*100/p.getPrecio_producto());String numeroWhatsapp=new ConfiguracionDAO().obtener().getContactoWhatsapp().replaceAll("\\D","");if(!numeroWhatsapp.startsWith("57"))numeroWhatsapp="57"+numeroWhatsapp;List<Talla> tallasProducto=(List<Talla>)request.getAttribute("tallasProducto");%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title><%=Util.escapeHtml(p.getNombre_producto())%> | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-4">
    <nav class="breadcrumb-soem mb-4">
        <a href="<%=ctx%>/home">Inicio</a> / <a href="<%=ctx%>/categoria?id=<%=p.getCategoria_id_categoria()%>">Colección</a> / <span><%=Util.escapeHtml(p.getNombre_producto())%></span>
    </nav>
    <div class="row g-5">
        <div class="col-lg-6 position-relative">
            <img class="product-detail-image" src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="<%=Util.escapeHtml(p.getNombre_producto())%>" onerror="this.onerror=null;this.src='<%=ctx%>/assets/sin-imagen.svg'"><%if(p.getCantidad_stock()==0){%><span class="badge badge-stockout position-absolute top-50 start-50 translate-middle p-3">SIN STOCK</span><%}%></div>
            <div class="col-lg-6">
                <p class="eyebrow">SOEM Oficial</p>
                <h1 class="display-6 fw-bold" id="nombreProducto"><%=Util.escapeHtml(p.getNombre_producto())%></h1>
                <p class="text-secondary"><%=Util.escapeHtml(p.getDescripcion())%></p>
                <div class="price-panel mb-4"><%if(descuento>0){%><span class="old-price d-block"><%=cop.format(p.getPrecio_producto())%></span><%}%><span class="display-6 fw-bold" id="precioProducto"><%=cop.format(precio)%></span><%if(descuento>0){%><p class="text-success mb-0">Ahorras <%=cop.format(p.getPrecio_producto()-precio)%> (<%=descuento%>%)</p><%}%></div>
                <p class="<%=p.getCantidad_stock()>0?"stock-ok":"stock-out"%>"><%=p.getCantidad_stock()>0?"Stock disponible: "+p.getCantidad_stock()+" unidades":"Este producto no tiene stock"%></p><%if(p.getCantidad_stock()>0){%><div class="row g-3">
                <div class="col-md-4">
                    <label class="choice-label">Color</label>
                    <select class="form-select" id="colorSel">
                        <option value="Negro">Negro</option>
                        <option value="Blanco">Blanco</option>
                        <option value="Dorado">Dorado</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="choice-label">Talla</label>
                    <select class="form-select" id="tallaSel"><%if(tallasProducto==null||tallasProducto.isEmpty()){%>
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option><%}else{for(Talla t:tallasProducto){%>
                        <option value="<%=Util.escapeHtml(t.getNombreTalla())%>"><%=Util.escapeHtml(t.getNombreTalla())%></option><%}}%>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="choice-label">Cantidad (disponibles: <%=p.getCantidad_stock()%>)</label>
                    <input class="form-control" type="number" id="cantidadSel" min="1" max="<%=p.getCantidad_stock()%>" value="1">
                </div>
                <div class="col-12">
                    <a class="btn btn-dark w-100 py-3" id="btnComprarWhatsapp" href="#" target="_blank" rel="noopener noreferrer">💬 Comprar por WhatsApp</a>
                </div>
            </div>
            <script>
            (function(){
                var telefono='<%=numeroWhatsapp%>';
                var btn=document.getElementById('btnComprarWhatsapp');
                var colorSel=document.getElementById('colorSel');
                var tallaSel=document.getElementById('tallaSel');
                var cantidadSel=document.getElementById('cantidadSel');
                function actualizar(){
                    var nombre=document.getElementById('nombreProducto').textContent.trim();
                    var precio=document.getElementById('precioProducto').textContent.trim();
                    var partes=['Hola, quiero comprar:',nombre,'Color: '+colorSel.value,'Talla: '+tallaSel.value,'Cantidad: '+cantidadSel.value,'Precio: '+precio];
                    btn.href='https://wa.me/'+telefono+'?text='+encodeURIComponent(partes.join('\n'));
                }
                [colorSel,tallaSel,cantidadSel].forEach(function(el){ el.addEventListener('input',actualizar); });
                actualizar();
            })();
            </script><%}%><%boolean enFavoritos=Boolean.TRUE.equals(request.getAttribute("enFavoritos"));%><form method="post" action="<%=ctx%>/wishlist" class="mt-2">
            <input type="hidden" name="csrf" value="<%=csrf%>">
            <input type="hidden" name="action" value="<%=enFavoritos?"remove":"add"%>">
            <input type="hidden" name="productoId" value="<%=p.getId_producto()%>">
            <input type="hidden" name="redir" value="<%=ctx%>/producto?id=<%=p.getId_producto()%>">
            <button class="btn <%=enFavoritos?"btn-dark":"btn-outline-dark"%> w-100"><%=enFavoritos?"♥ Ya está en tus favoritos (quitar)":"♡ Guardar en favoritos"%></button>
        </form>
        <div class="notice mt-4">
            <strong>Compra por WhatsApp.</strong> Escríbenos, te confirmamos disponibilidad y coordinamos el pago y el envío directamente.</div>
        </div>
    </div>
</main><%@include file="footer.jsp"%></body>
</html>
