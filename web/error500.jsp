<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%><%
String ctx=request.getContextPath();
Object uriOriginal=request.getAttribute("jakarta.servlet.error.request_uri");
if(exception!=null){System.err.println("Error 500 en "+(uriOriginal!=null?uriOriginal:request.getRequestURI())+": "+exception);}
%><!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Algo salió mal | SOEM Oficial</title>
    <style>
        body{font-family:system-ui,-apple-system,"Segoe UI",sans-serif;background:#f7f7f7;color:#0a0a0a;margin:0;min-height:100vh;display:flex;align-items:center;justify-content:center;text-align:center;padding:2rem}
        .caja{max-width:480px}
        .codigo{font-size:5rem;font-weight:800;color:#0a0a0a;margin:0}
        h1{font-size:1.5rem;margin:.5rem 0 1rem}
        p{color:#6b6b6b;margin-bottom:1.5rem}
        a{display:inline-block;background:#0a0a0a;color:#fff;text-decoration:none;padding:.75rem 2rem;font-weight:700;letter-spacing:.04em}
    </style>
</head>
<body>
    <div class="caja">
        <p class="codigo">500</p>
        <h1>Algo salió mal de nuestro lado</h1>
        <p>Ya quedó registrado. Intenta de nuevo en un momento.</p>
        <a href="<%=ctx%>/home">Volver al inicio</a>
    </div>
</body>
</html>
