<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,java.text.NumberFormat,Modelo.Usuario,Modelo.Roles,Seguridad.Util"%><%
String ctx=request.getContextPath();
List<Usuario> usuarios=(List<Usuario>)request.getAttribute("usuarios");
List<Roles> roles=(List<Roles>)request.getAttribute("roles");
Usuario verUsuario=(Usuario)request.getAttribute("verUsuario");
List<Map<String,Object>> ordenesUsuario=(List<Map<String,Object>>)request.getAttribute("ordenesUsuario");
NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));
%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Usuarios | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Administración</p>
    <h1 class="section-title">Usuarios</h1><%if(request.getAttribute("exito")!=null){%><div class="alert alert-success"><%=request.getAttribute("exito")%></div><%}if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=Util.escapeHtml(request.getAttribute("error"))%></div><%}%>

    <div class="row g-4">
        <div class="col-lg-5">
            <h2 class="h5 mb-3">Nueva cuenta de personal</h2>
            <form class="bg-white p-4" method="post" action="<%=ctx%>/admin/usuarios">
                <input type="hidden" name="csrf" value="<%=csrf%>">
                <input type="hidden" name="action" value="crearEmpleado">
                <div class="row">
                    <div class="col-6">
                        <label class="form-label">Nombre</label>
                        <input class="form-control mb-3" name="nombre" required>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Apellido</label>
                        <input class="form-control mb-3" name="apellido" required>
                    </div>
                </div>
                <label class="form-label">Documento</label>
                <input class="form-control mb-3" name="numeroDocumento" required>
                <label class="form-label">Teléfono</label>
                <input class="form-control mb-3" name="telefono">
                <label class="form-label">Correo</label>
                <input class="form-control mb-3" name="correo" type="email" required>
                <label class="form-label">Dirección</label>
                <input class="form-control mb-3" name="direccion">
                <label class="form-label">Rol</label>
                <select class="form-select mb-3" name="rolId" required>
                    <option value="3">Vendedor (productos y pedidos)</option>
                    <option value="1">Admin (acceso total)</option>
                </select>
                <label class="form-label">Contraseña</label>
                <input class="form-control mb-3" name="contrasena" type="password" minlength="10" required>
                <label class="form-label">Confirmar contraseña</label>
                <input class="form-control mb-4" name="confirmarContrasena" type="password" minlength="10" required>
                <button class="btn btn-gold">Crear cuenta</button>
            </form>
        </div>

        <div class="col-lg-7">
            <div class="table-responsive bg-white p-3">
                <table class="table align-middle admin-table">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Rol</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody><%for(Usuario u:usuarios){%><tr>
                    <td><%=Util.escapeHtml(u.getNombreUsuario())%> <%=Util.escapeHtml(u.getApellidoUsuario())%></td>
                    <td><%=Util.escapeHtml(u.getCorreo())%></td>
                    <td>
                        <form method="post" action="<%=ctx%>/admin/usuarios" class="d-flex gap-1">
                            <input type="hidden" name="csrf" value="<%=csrf%>">
                            <input type="hidden" name="action" value="cambiarRol">
                            <input type="hidden" name="id" value="<%=u.getIdUsuario()%>">
                            <select class="form-select form-select-sm" name="rolId" onchange="this.form.submit()"><%for(Roles r:roles){%>
                                <option value="<%=r.getIdRol()%>" <%=r.getIdRol()==u.getRolesIdRol()?"selected":""%>><%=Util.escapeHtml(r.getNombreRol())%></option><%}%>
                            </select>
                        </form>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-outline-dark" href="<%=ctx%>/admin/usuarios?ver=<%=u.getIdUsuario()%>">Ver</a>
                    </td>
                </tr><%}%></tbody>
                </table>
            </div>
        </div>
    </div><%if(verUsuario!=null){%>

    <h2 class="h5 mt-5 mb-3">Historial de <%=Util.escapeHtml(verUsuario.getNombreUsuario())%> <%=Util.escapeHtml(verUsuario.getApellidoUsuario())%></h2>
    <div class="bg-white p-4 mb-4">
        <p class="mb-1"><strong>Correo:</strong> <%=Util.escapeHtml(verUsuario.getCorreo())%></p>
        <p class="mb-1"><strong>Teléfono:</strong> <%=verUsuario.getTelefono()!=null?Util.escapeHtml(verUsuario.getTelefono()):"—"%></p>
        <p class="mb-0"><strong>Dirección:</strong> <%=verUsuario.getDireccion()!=null?Util.escapeHtml(verUsuario.getDireccion()):"—"%></p>
    </div>
    <div class="table-responsive bg-white p-3">
        <table class="table align-middle admin-table">
            <thead>
                <tr>
                    <th>Orden</th>
                    <th>Total</th>
                    <th>Estado</th>
                    <th>Fecha</th>
                </tr>
            </thead>
            <tbody><%if(ordenesUsuario!=null){for(Map<String,Object> o:ordenesUsuario){%><tr>
            <td><%=o.get("numero_orden")%></td>
            <td><%=cop.format(o.get("total_compra"))%></td>
            <td><%=o.get("estado_compra")%></td>
            <td><%=o.get("fecha_compra")%></td>
        </tr><%}}%></tbody>
        </table><%if(ordenesUsuario!=null&&ordenesUsuario.isEmpty()){%><p class="text-secondary mb-0">Este usuario aún no tiene compras.</p><%}%>
    </div><%}%>
</main><%@include file="footer.jsp"%></body>
</html>
