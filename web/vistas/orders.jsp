<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,java.text.NumberFormat,Seguridad.Util"%><%String ctx=request.getContextPath();List<Map<String,Object>> ordenes=(List<Map<String,Object>>)request.getAttribute("ordenes");List<Map<String,Object>> historial=(List<Map<String,Object>>)request.getAttribute("historial");NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Mis compras | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Seguimiento</p>
    <h1 class="section-title">Mis compras</h1>
    <div class="row g-4">
        <div class="col-lg-7">
            <div class="bg-white p-3"><%for(Map<String,Object> o:ordenes){%><a class="d-block text-decoration-none text-dark border-bottom py-3" href="<%=ctx%>/orders?id=<%=o.get("id_cabeza_factura")%>">
            <div class="d-flex justify-content-between">
                <strong><%=o.get("numero_orden")%></strong>
                <strong><%=cop.format(o.get("total_compra"))%></strong>
            </div>
            <small><%=o.get("estado_compra")%> · <%=o.get("fecha_compra")%></small><%if(o.get("motivo_rechazo")!=null){%><div class="text-danger small"><%=Util.escapeHtml(o.get("motivo_rechazo"))%></div><%}%></a><%}%></div>
        </div>
        <div class="col-lg-5">
            <h2 class="h5">Línea de tiempo</h2>
            <div class="timeline bg-white p-4"><%if(historial!=null){for(Map<String,Object> h:historial){%><div class="timeline-item">
            <strong><%=h.get("estado")%></strong>
            <div class="small text-secondary"><%=Util.escapeHtml(h.get("detalle"))%></div>
            <small><%=h.get("fecha")%></small>
        </div><%}}else{%><p class="text-secondary">Selecciona una compra para ver su seguimiento.</p><%}%></div>
    </div>
</div>
</main><%@include file="footer.jsp"%></body>
</html>
