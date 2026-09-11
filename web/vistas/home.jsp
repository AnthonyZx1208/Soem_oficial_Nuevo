<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,Modelo.Producto,Modelo.Categoria,java.text.NumberFormat,Seguridad.Util"%><%
String ctx=request.getContextPath();
NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));
List<Categoria> categorias=(List<Categoria>)request.getAttribute("categorias");
List<Producto> resultadosBusqueda=(List<Producto>)request.getAttribute("resultadosBusqueda");
String termino=request.getParameter("q");
Categoria catHombres=null,catMujeres=null,catNinas=null,catNinos=null;
for(Categoria c:categorias){
    String n=c.getNombre_categoria()==null?"":c.getNombre_categoria().toLowerCase();
    if(n.contains("hombre"))catHombres=c;
    else if(n.contains("mujer"))catMujeres=c;
    else if(n.contains("niña")||n.contains("nina"))catNinas=c;
    else if(n.contains("niño")||n.contains("nino"))catNinos=c;
}
%><%!
private String urlImagenCategoria(String ctx, String valor, String fallback) {
    if (valor == null) return fallback;
    return valor.startsWith("http") ? valor : ctx + "/" + valor;
}
%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>SOEM Oficial | Moda en Colombia</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main><%if(termino!=null&&!termino.isBlank()){%>

    <section class="container py-5">
        <p class="eyebrow">Resultados</p>
        <h1 class="section-title mb-4">Búsqueda: "<%=Util.escapeHtml(termino)%>"</h1><%if(resultadosBusqueda==null||resultadosBusqueda.isEmpty()){%>
        <p class="text-secondary">No encontramos productos con ese nombre. <a href="<%=ctx%>/home">Volver al inicio</a>.</p><%}else{%>
        <div class="row g-4"><%for(Producto p:resultadosBusqueda){double precio=p.getPrecio_oferta()!=null?p.getPrecio_oferta():p.getPrecio_producto();int descuento=p.getPrecio_oferta()==null?0:(int)((p.getPrecio_producto()-p.getPrecio_oferta())*100/p.getPrecio_producto());%><div class="col-sm-6 col-lg-3">
        <article class="product-card h-100">
            <div class="position-relative">
                <img class="product-image" src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="<%=Util.escapeHtml(p.getNombre_producto())%>" onerror="this.onerror=null;this.src='<%=ctx%>/assets/sin-imagen.svg'"><%if(p.getCantidad_stock()==0){%><span class="badge badge-stockout position-absolute top-0 start-0 m-3">SIN STOCK</span><%}else if(descuento>0){%><span class="badge badge-offer position-absolute top-0 start-0 m-3">-<%=descuento%>%</span><%}%></div>
            <div class="card-body d-flex flex-column">
                <h3 class="h6"><%=Util.escapeHtml(p.getNombre_producto())%></h3>
                <p class="mb-1"><%if(descuento>0){%><span class="old-price"><%=cop.format(p.getPrecio_producto())%></span><%}%> <span class="price"><%=cop.format(precio)%></span>
            </p>
            <a class="btn btn-dark mt-auto" href="<%=ctx%>/producto?id=<%=p.getId_producto()%>">Ver producto</a>
        </div>
    </article>
</div><%}%></div><%}%>
    </section><%}else{%>

    <%if(catMujeres!=null||catHombres!=null){%><section class="hero-carousel">
        <div id="heroCarrusel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner"><%if(catMujeres!=null){%>
                <div class="carousel-item active" style="background-image:url('<%=urlImagenCategoria(ctx,catMujeres.getImagen_url(),"https://images.unsplash.com/photo-1483985988355-763728e1935b?auto=format&fit=crop&w=1800&q=85")%>')">
                    <div class="container hero-caption">
                        <p class="eyebrow">Nueva colección</p>
                        <h1>Moda para ellas.</h1>
                        <p class="lead col-lg-6">Prendas seleccionadas para toda ocasión, con envíos a toda Colombia.</p>
                        <a class="btn btn-gold btn-lg px-4 me-2" href="<%=ctx%>/categoria?id=<%=catMujeres.getId_categoria()%>">Ver mujeres</a><%if(catHombres!=null){%><a class="btn btn-outline-light btn-lg px-4" href="<%=ctx%>/categoria?id=<%=catHombres.getId_categoria()%>">Ver hombres</a><%}%>
                    </div>
                </div><%}if(catHombres!=null){%>
                <div class="carousel-item<%=catMujeres==null?" active":""%>" style="background-image:url('<%=urlImagenCategoria(ctx,catHombres.getImagen_url(),"https://images.unsplash.com/photo-1441984904996-e0b6ba687e04?auto=format&fit=crop&w=1800&q=85")%>')">
                    <div class="container hero-caption">
                        <p class="eyebrow">Nueva colección</p>
                        <h1>Moda para ellos.</h1>
                        <p class="lead col-lg-6">Estilo urbano y clásico, con precios en pesos colombianos.</p>
                        <a class="btn btn-gold btn-lg px-4 me-2" href="<%=ctx%>/categoria?id=<%=catHombres.getId_categoria()%>">Ver hombres</a><%if(catMujeres!=null){%><a class="btn btn-outline-light btn-lg px-4" href="<%=ctx%>/categoria?id=<%=catMujeres.getId_categoria()%>">Ver mujeres</a><%}%>
                    </div>
                </div><%}%>
            </div><%if(catMujeres!=null&&catHombres!=null){%>
            <button class="carousel-control-prev" type="button" data-bs-target="#heroCarrusel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#heroCarrusel" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button><%}%>
        </div>
    </section><%}%>

    <section id="productos" class="container py-5">
        <p class="eyebrow">Selección SOEM</p>
        <h2 class="section-title mb-4">Lo más buscado</h2>
        <div class="carrusel-wrap"><%List<Producto> destacados=(List<Producto>)request.getAttribute("productosDestacados");%>
            <div class="carrusel-horizontal" id="carruselDestacados"><%for(Producto p:destacados){double precio=p.getPrecio_oferta()!=null?p.getPrecio_oferta():p.getPrecio_producto();int descuento=p.getPrecio_oferta()==null?0:(int)((p.getPrecio_producto()-p.getPrecio_oferta())*100/p.getPrecio_producto());%>
            <article class="product-card">
                <div class="position-relative">
                    <img class="product-image" src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="<%=Util.escapeHtml(p.getNombre_producto())%>" onerror="this.onerror=null;this.src='<%=ctx%>/assets/sin-imagen.svg'"><%if(p.getCantidad_stock()==0){%><span class="badge badge-stockout position-absolute top-0 start-0 m-3">SIN STOCK</span><%}else if(descuento>0){%><span class="badge badge-offer position-absolute top-0 start-0 m-3">-<%=descuento%>%</span><%}%></div>
                <div class="card-body d-flex flex-column">
                    <h3 class="h6"><%=Util.escapeHtml(p.getNombre_producto())%></h3>
                    <p class="mb-1"><%if(descuento>0){%><span class="old-price"><%=cop.format(p.getPrecio_producto())%></span><%}%> <span class="price"><%=cop.format(precio)%></span>
                </p>
                <a class="btn btn-dark mt-auto" href="<%=ctx%>/producto?id=<%=p.getId_producto()%>">Ver producto</a>
            </div>
        </article><%}%></div>
        <button class="carrusel-flecha carrusel-flecha--prev" type="button" onclick="document.getElementById('carruselDestacados').scrollBy({left:-500,behavior:'smooth'})" aria-label="Anterior">‹</button>
        <button class="carrusel-flecha carrusel-flecha--next" type="button" onclick="document.getElementById('carruselDestacados').scrollBy({left:500,behavior:'smooth'})" aria-label="Siguiente">›</button>
    </div>
</section><%List<Producto> ofertas=(List<Producto>)request.getAttribute("ofertas");Integer maxDescuento=(Integer)request.getAttribute("maxDescuento");if(ofertas!=null&&!ofertas.isEmpty()){%>

    <section class="banner-oferta">
        <div class="container">
            <p class="eyebrow" style="color:#fff">Ofertas activas</p>
            <div class="oferta-pct">-<%=maxDescuento%>% OFF</div>
            <p class="mb-0">En productos seleccionados. Escríbenos por WhatsApp y asegura tu talla.</p>
        </div>
    </section>

    <section class="container py-5">
        <h2 class="section-title mb-4">Ofertas activas</h2>
        <div class="carrusel-wrap">
            <div class="carrusel-horizontal" id="carruselOfertas"><%for(Producto p:ofertas){double precio=p.getPrecio_oferta();int descuento=(int)((p.getPrecio_producto()-p.getPrecio_oferta())*100/p.getPrecio_producto());%>
            <article class="product-card">
                <div class="position-relative">
                    <img class="product-image" src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="<%=Util.escapeHtml(p.getNombre_producto())%>" onerror="this.onerror=null;this.src='<%=ctx%>/assets/sin-imagen.svg'"><span class="badge badge-offer position-absolute top-0 start-0 m-3">-<%=descuento%>%</span></div>
                <div class="card-body d-flex flex-column">
                    <h3 class="h6"><%=Util.escapeHtml(p.getNombre_producto())%></h3>
                    <p class="mb-1"><span class="old-price"><%=cop.format(p.getPrecio_producto())%></span> <span class="price"><%=cop.format(precio)%></span>
                </p>
                <a class="btn btn-dark mt-auto" href="<%=ctx%>/producto?id=<%=p.getId_producto()%>">Ver producto</a>
            </div>
        </article><%}%></div>
        <button class="carrusel-flecha carrusel-flecha--prev" type="button" onclick="document.getElementById('carruselOfertas').scrollBy({left:-500,behavior:'smooth'})" aria-label="Anterior">‹</button>
        <button class="carrusel-flecha carrusel-flecha--next" type="button" onclick="document.getElementById('carruselOfertas').scrollBy({left:500,behavior:'smooth'})" aria-label="Siguiente">›</button>
    </div>
</section><%}if(catNinas!=null||catNinos!=null){%>

    <section class="container py-5">
        <p class="eyebrow">Explora</p>
        <h2 class="section-title mb-4">Completa tu look</h2>
        <div class="editorial-grid"><%if(catNinas!=null){%>
            <a class="editorial-tile text-decoration-none" href="<%=ctx%>/categoria?id=<%=catNinas.getId_categoria()%>" style="background-image:url('<%=urlImagenCategoria(ctx,catNinas.getImagen_url(),"https://images.unsplash.com/photo-1519238263530-99bdd11df2ea?auto=format&fit=crop&w=900&q=80")%>')">
                <div class="editorial-caption">
                    <h3><%=Util.escapeHtml(catNinas.getNombre_categoria())%></h3>
                    <span class="text-gold fw-bold">Ver colección →</span>
                </div>
            </a><%}if(catNinos!=null){%>
            <a class="editorial-tile text-decoration-none" href="<%=ctx%>/categoria?id=<%=catNinos.getId_categoria()%>" style="background-image:url('<%=urlImagenCategoria(ctx,catNinos.getImagen_url(),"https://images.unsplash.com/photo-1503919545889-aef636e10ad4?auto=format&fit=crop&w=900&q=80")%>')">
                <div class="editorial-caption">
                    <h3><%=Util.escapeHtml(catNinos.getNombre_categoria())%></h3>
                    <span class="text-gold fw-bold">Ver colección →</span>
                </div>
            </a><%}%></div>
    </section><%}List<Producto> tira=destacados.size()>6?destacados.subList(0,6):destacados;if(!tira.isEmpty()){%>

    <section class="container py-5">
        <h2 class="section-title mb-1">#SOEMOFICIAL</h2>
        <p class="text-secondary mb-4">Etiquétanos con tus looks favoritos.</p>
        <div class="social-strip"><%for(Producto p:tira){%><a href="<%=ctx%>/producto?id=<%=p.getId_producto()%>"><img src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="<%=Util.escapeHtml(p.getNombre_producto())%>" onerror="this.onerror=null;this.src='<%=ctx%>/assets/sin-imagen.svg'"></a><%}%></div>
    </section><%}}%>
</main><%@include file="footer.jsp"%></body>
</html>
