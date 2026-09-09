<%@page contentType="text/html" pageEncoding="UTF-8"%><%String ctx=request.getContextPath();%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Registro | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="auth-wrap container py-5">
    <form class="auth-card" method="post" action="<%=ctx%>/register">
        <p class="eyebrow">Nueva cuenta</p>
        <h1 class="h2 fw-bold mb-4">Crea tu cuenta</h1><%if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=request.getAttribute("error")%></div><%}if(request.getAttribute("exito")!=null){%><div class="alert alert-success"><%=request.getAttribute("exito")%></div><%}%><input type="hidden" name="csrf" value="<%=csrf%>">
        <div class="row">
            <div class="col">
                <label class="form-label">Nombre</label>
                <input class="form-control mb-3" name="nombre" required>
            </div>
            <div class="col">
                <label class="form-label">Apellido</label>
                <input class="form-control mb-3" name="apellido" required>
            </div>
        </div>
        <label class="form-label">Documento</label>
        <input class="form-control mb-3" name="numeroDocumento" required>
        <label class="form-label">Teléfono</label>
        <input class="form-control mb-3" name="telefono" inputmode="numeric">
        <label class="form-label">Correo</label>
        <input class="form-control mb-3" name="correo" type="email" required>
        <label class="form-label">Dirección</label>
        <input class="form-control mb-3" name="direccion">
        <label class="form-label">Contraseña</label>
        <input class="form-control mb-3" name="contrasena" type="password" minlength="10" required>
        <label class="form-label">Confirma la contraseña</label>
        <input class="form-control mb-4" name="confirmarContrasena" type="password" minlength="10" required>
        <button class="btn btn-gold w-100">Crear cuenta</button>
    </form>
</main><%@include file="footer.jsp"%></body>
</html>
