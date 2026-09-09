<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.Map,Seguridad.Util"%><%String ctx=request.getContextPath();Map<String,Object> p=(Map<String,Object>)request.getAttribute("perfil");%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Mi perfil | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Cuenta</p>
    <h1 class="section-title">Mi perfil</h1>
    <form class="bg-white p-4 col-lg-8" method="post" action="<%=ctx%>/profile"><%if(request.getAttribute("exito")!=null){%><div class="alert alert-success"><%=request.getAttribute("exito")%></div><%}%><input type="hidden" name="csrf" value="<%=csrf%>">
    <div class="row">
        <div class="col-md-6">
            <label class="form-label">Nombre</label>
            <input class="form-control mb-3" name="nombre" value="<%=Util.escapeHtml(p.get("Nombre_Usuario"))%>" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Apellido</label>
            <input class="form-control mb-3" name="apellido" value="<%=Util.escapeHtml(p.get("Apellido_Usuario"))%>" required>
        </div>
    </div>
    <label class="form-label">Correo</label>
    <input class="form-control mb-3" value="<%=Util.escapeHtml(p.get("correo"))%>" disabled>
    <label class="form-label">Teléfono</label>
    <input class="form-control mb-3" name="telefono" value="<%=Util.escapeHtml(p.get("Telefono"))%>">
    <label class="form-label">Dirección</label>
    <input class="form-control mb-4" name="direccion" value="<%=Util.escapeHtml(p.get("Direccion"))%>">
    <button class="btn btn-gold">Guardar cambios</button>
</form>
</main><%@include file="footer.jsp"%></body>
</html>
