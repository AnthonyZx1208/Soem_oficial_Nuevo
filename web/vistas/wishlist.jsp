<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,java.text.NumberFormat,Seguridad.Util"%><%String ctx=request.getContextPath();List<Map<String,Object>> misDeseos=(List<Map<String,Object>>)request.getAttribute("deseos");NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Lista de deseos | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Guardados para ti</p>
    <h1 class="section-title">Lista de deseos</h1>
    <div class="row g-4 mt-1"><%for(Map<String,Object> p:misDeseos){%><div class="col-sm-6 col-lg-4">
    <article class="product-card">
        <%String imagen=String.valueOf(p.get("imagen_principal"));%><img class="product-image" src="<%=imagen.startsWith("http")?imagen:ctx+"/"+imagen%>" alt="" onerror="this.onerror=null;this.src='<%=ctx%>/assets/sin-imagen.svg'">
        <div class="card-body">
            <h2 class="h6"><%=Util.escapeHtml(p.get("nombre_producto"))%></h2>
            <p class="price"><%=cop.format(p.get("precio_oferta")!=null?p.get("precio_oferta"):p.get("precio_producto"))%></p>
            <div class="d-flex gap-2">
                <a class="btn btn-dark btn-sm flex-grow-1" href="<%=ctx%>/producto?id=<%=p.get("id_producto")%>">Ver</a>
                <form method="post" action="<%=ctx%>/wishlist">
                    <input type="hidden" name="csrf" value="<%=csrf%>">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="productoId" value="<%=p.get("id_producto")%>">
                    <button class="btn btn-outline-danger btn-sm">Eliminar</button>
                </form>
            </div>
        </div>
    </article>
</div><%}%><%if(misDeseos.isEmpty()){%><p>No has guardado productos aún.</p><%}%></div>
</main><%@include file="footer.jsp"%></body>
</html>
