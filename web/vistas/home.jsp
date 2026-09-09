<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,Modelo.Producto,Modelo.Categoria,java.text.NumberFormat"%><%String ctx=request.getContextPath();NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>SOEM Oficial | Moda en Colombia</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main>
    <section class="hero-soem">
        <div class="container">
            <p class="eyebrow">Colección 2026</p>
            <h1>La ropa que acompaña tu historia.</h1>
            <p class="lead col-lg-6">Prendas para toda la familia, ofertas reales y envío sin costo.</p>
            <a class="btn btn-gold btn-lg px-4" href="#productos">Ver catálogo</a>
        </div>
    </section>
    <section id="categorias" class="container py-5">
        <div class="d-flex justify-content-between align-items-end mb-4">
            <div>
                <p class="eyebrow">Explora</p>
                <h2 class="section-title mb-0">Compra por categoría</h2>
            </div>
        </div>
        <div class="row g-4"><%for(Categoria c:(List<Categoria>)request.getAttribute("categorias")){%><div class="col-6 col-lg-3">
        <a class="text-decoration-none text-dark" href="<%=ctx%>/categoria?id=<%=c.getId_categoria()%>">
            <article class="category-card h-100">
                <img src="<%=c.getImagen_url()==null?"https://images.unsplash.com/photo-1445205170230-053b83016050?auto=format&fit=crop&w=600&q=80":c.getImagen_url()%>" alt="<%=c.getNombre_categoria()%>">
                <div class="p-3">
                    <strong><%=c.getNombre_categoria()%></strong>
                    <span class="float-end text-gold">→</span>
                </div>
            </article>
        </a>
    </div><%}%></div>
</section>
<section id="productos" class="container pb-5">
    <p class="eyebrow">Selección SOEM</p>
    <h2 class="section-title mb-4">Productos destacados</h2>
    <div class="row g-4"><%for(Producto p:(List<Producto>)request.getAttribute("productosDestacados")){double precio=p.getPrecio_oferta()!=null?p.getPrecio_oferta():p.getPrecio_producto();int descuento=p.getPrecio_oferta()==null?0:(int)((p.getPrecio_producto()-p.getPrecio_oferta())*100/p.getPrecio_producto());%><div class="col-sm-6 col-lg-4">
    <article class="product-card h-100">
        <div class="position-relative">
            <img class="product-image" src="<%=p.getImagen_principal()!=null&&p.getImagen_principal().startsWith("http")?p.getImagen_principal():ctx+"/"+p.getImagen_principal()%>" alt="<%=p.getNombre_producto()%>"><%if(p.getCantidad_stock()==0){%><span class="badge badge-stockout position-absolute top-0 start-0 m-3">SIN STOCK</span><%}else if(descuento>0){%><span class="badge badge-offer position-absolute top-0 start-0 m-3">-<%=descuento%>% OFERTA</span><%}%></div>
            <div class="card-body d-flex flex-column">
                <h3 class="h6"><%=p.getNombre_producto()%></h3>
                <p class="text-secondary small"><%=p.getDescripcion()%></p>
                <p class="mb-1"><%if(descuento>0){%><span class="old-price"><%=cop.format(p.getPrecio_producto())%></span><%}%> <span class="price"><%=cop.format(precio)%></span>
            </p>
            <p class="<%=p.getCantidad_stock()>0?"stock-ok":"stock-out"%>"><%=p.getCantidad_stock()>0?p.getCantidad_stock()+" unidades disponibles":"Sin disponibilidad"%></p>
            <a class="btn btn-dark mt-auto" href="<%=ctx%>/producto?id=<%=p.getId_producto()%>">Ver producto</a>
        </div>
    </article>
</div><%}%></div>
</section>
</main><%@include file="footer.jsp"%></body>
</html>
