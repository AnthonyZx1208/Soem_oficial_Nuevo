<%@page contentType="text/html" pageEncoding="UTF-8"%><%String ctx=request.getContextPath();%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Pago NEqui | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <p class="eyebrow">Último paso</p>
            <h1 class="section-title">Confirmar compra</h1><%if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=request.getAttribute("error")%></div><%}%><div class="qr-box mb-4">
            <div class="display-4">▦</div>
            <strong>Pago por NEqui</strong>
            <p class="mb-0">Escanea el QR desde la aplicación NEqui.</p>
        </div>
        <div class="notice">
            <strong>Instrucciones de pago</strong>
            <ol class="mb-0 mt-2">
                <li>Realiza el pago por el monto exacto que aparece en tu orden.</li>
                <li>Envía el comprobante al WhatsApp 315 084 6431 e indica tu número de orden.</li>
                <li>Tienes 1 hora para completar el pago y enviar el comprobante; después se libera la reserva de inventario.</li>
            </ol>
        </div>
        <form method="post" action="<%=ctx%>/checkout" class="mt-4">
            <input type="hidden" name="csrf" value="<%=csrf%>">
            <button class="btn btn-gold w-100 py-3">Crear orden y reservar stock</button>
        </form>
    </div>
</div>
</main><%@include file="footer.jsp"%></body>
</html>
