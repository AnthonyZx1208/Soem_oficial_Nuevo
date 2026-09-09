<%@page contentType="text/html" pageEncoding="UTF-8"%><%String ctx=request.getContextPath();%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Ingresar | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="auth-wrap container">
    <form class="auth-card" method="post" action="<%=ctx%>/login">
        <p class="eyebrow">Bienvenido</p>
        <h1 class="h2 fw-bold mb-4">Inicia sesión</h1><%if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=request.getAttribute("error")%></div><%}%><input type="hidden" name="csrf" value="<%=csrf%>">
        <label class="form-label">Correo electrónico</label>
        <input class="form-control mb-3" name="correo" type="email" required autocomplete="email">
        <label class="form-label">Contraseña</label>
        <input class="form-control mb-4" name="contrasena" type="password" required autocomplete="current-password">
        <button class="btn btn-dark w-100" type="submit" name="modo" value="cliente">Ingresar como cliente</button>
        <button class="btn btn-outline-dark w-100 mt-2" type="submit" name="modo" value="admin">Ingresar como administrador</button>
        <p class="text-center mt-3 mb-0">¿No tienes cuenta? <a href="<%=ctx%>/register">Regístrate</a>
    </p>
</form>
</main><%@include file="footer.jsp"%></body>
</html>
