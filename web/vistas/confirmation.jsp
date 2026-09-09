<%@page contentType="text/html" pageEncoding="UTF-8"%><%String ctx=request.getContextPath();String orden=(String)request.getAttribute("numeroOrden");%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Orden creada | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5 text-center">
    <p class="eyebrow">Compra registrada</p>
    <h1 class="section-title">Tu orden está en verificación</h1>
    <p class="lead">Número de orden: <strong><%=orden%></strong>
</p>
<div class="notice mx-auto text-start" style="max-width:700px">Realiza el pago exacto por NEqui y envía el comprobante al WhatsApp 315 084 6431. Conserva este número de orden. Cuando el pago sea validado podrás ver el avance de preparación y envío en tu historial.</div>
<a class="btn btn-dark mt-4" href="<%=ctx%>/orders">Ver mis compras</a>
</main><%@include file="footer.jsp"%></body>
</html>
