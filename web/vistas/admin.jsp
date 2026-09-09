<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,java.text.NumberFormat,Seguridad.Util"%><%String ctx=request.getContextPath();List<Map<String,Object>> ordenes=(List<Map<String,Object>>)request.getAttribute("ordenes");%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Administración | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Administración</p>
    <h1 class="section-title">Tablero de operaciones</h1><%if(request.getAttribute("exito")!=null){%><div class="alert alert-success"><%=request.getAttribute("exito")%></div><%}if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=request.getAttribute("error")%></div><%}%><div class="row g-3 mb-5">
    <div class="col-md-3">
        <div class="dashboard-card p-4">
            <small>Usuarios</small>
            <div class="metric"><%=request.getAttribute("usuarios")%></div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="dashboard-card p-4">
            <small>Productos publicados</small>
            <div class="metric"><%=request.getAttribute("productos")%></div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="dashboard-card p-4">
            <small>Total compras</small>
            <div class="metric"><%=request.getAttribute("compras")%></div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="dashboard-card p-4">
            <small>Por verificar</small>
            <div class="metric text-gold"><%=request.getAttribute("pendientes")%></div>
        </div>
    </div>
</div>
<h2 class="h4 mb-3">Procesar compras</h2>
<div class="table-responsive bg-white p-3">
    <table class="table align-middle admin-table">
        <thead>
            <tr>
                <th>Orden</th>
                <th>Cliente</th>
                <th>Total</th>
                <th>Estado</th>
                <th>Actualizar</th>
            </tr>
        </thead>
        <tbody><%for(Map<String,Object> o:ordenes){%><tr>
        <td>
            <strong><%=o.get("numero_orden")%></strong>
        </td>
        <td><%=Util.escapeHtml(o.get("Nombre_Usuario"))%> <%=Util.escapeHtml(o.get("Apellido_Usuario"))%><br>
        <small><%=Util.escapeHtml(o.get("correo"))%></small>
    </td>
    <td>$<%=o.get("total_compra")%></td>
    <td><%=o.get("estado_compra")%></td>
    <td>
        <form method="post" action="<%=ctx%>/admin" class="d-flex gap-1">
            <input type="hidden" name="csrf" value="<%=csrf%>">
            <input type="hidden" name="ordenId" value="<%=o.get("id_cabeza_factura")%>">
            <select class="form-select form-select-sm" name="estado">
                <option>Verificacion de Pago</option>
                <option>Pago Aprobado</option>
                <option>Preparando Producto</option>
                <option>Producto Enviado</option>
                <option>Producto Entregado</option>
                <option>Rechazado</option>
            </select>
            <input class="form-control form-control-sm" name="motivo" placeholder="Motivo si rechaza">
            <button class="btn btn-dark btn-sm">Guardar</button>
        </form>
    </td>
</tr><%}%></tbody>
</table>
</div>
<div class="notice mt-4">La gestión de catálogo, categorías, imágenes, colores de marca e inventario se conecta a las tablas Producto, Categoria y Configuracion_Sitio. Las órdenes rechazadas liberan automáticamente el stock reservado.</div>
</main><%@include file="footer.jsp"%></body>
</html>
