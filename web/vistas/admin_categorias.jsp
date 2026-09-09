<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,Modelo.Categoria,Seguridad.Util"%><%String ctx=request.getContextPath();List<Categoria> categorias=(List<Categoria>)request.getAttribute("categorias");Categoria editando=(Categoria)request.getAttribute("editando");%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Categorías | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Administración</p>
    <h1 class="section-title">Categorías</h1><%if(request.getAttribute("exito")!=null){%><div class="alert alert-success"><%=request.getAttribute("exito")%></div><%}if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=Util.escapeHtml(request.getAttribute("error"))%></div><%}%>
    <div class="row g-4">
        <div class="col-lg-5">
            <h2 class="h5 mb-3"><%=editando!=null?"Editar categoría":"Nueva categoría"%></h2>
            <form class="bg-white p-4" method="post" action="<%=ctx%>/admin/categorias">
                <input type="hidden" name="csrf" value="<%=csrf%>">
                <input type="hidden" name="action" value="<%=editando!=null?"actualizar":"crear"%>"><%if(editando!=null){%><input type="hidden" name="id" value="<%=editando.getId_categoria()%>"><%}%>
                <label class="form-label">Nombre</label>
                <input class="form-control mb-3" name="nombre" required value="<%=editando!=null?Util.escapeHtml(editando.getNombre_categoria()):""%>">
                <label class="form-label">Descripción</label>
                <textarea class="form-control mb-3" name="descripcion" rows="3"><%=editando!=null&&editando.getDescripcion()!=null?Util.escapeHtml(editando.getDescripcion()):""%></textarea>
                <label class="form-label">Imagen (URL)</label>
                <input class="form-control mb-3" name="imagenUrl" value="<%=editando!=null&&editando.getImagen_url()!=null?Util.escapeHtml(editando.getImagen_url()):""%>">
                <label class="form-label">Estado</label>
                <select class="form-select mb-4" name="estado">
                    <option value="Activo" <%=editando==null||"Activo".equals(editando.getEstado())?"selected":""%>>Activo</option>
                    <option value="Inactivo" <%=editando!=null&&"Inactivo".equals(editando.getEstado())?"selected":""%>>Inactivo</option>
                </select>
                <button class="btn btn-gold"><%=editando!=null?"Guardar cambios":"Crear categoría"%></button><%if(editando!=null){%> <a class="btn btn-outline-dark" href="<%=ctx%>/admin/categorias">Cancelar</a><%}%>
            </form>
        </div>
        <div class="col-lg-7">
            <div class="table-responsive bg-white p-3">
                <table class="table align-middle admin-table">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Estado</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody><%for(Categoria c:categorias){%><tr>
                    <td><%=Util.escapeHtml(c.getNombre_categoria())%></td>
                    <td><%=c.getEstado()%></td>
                    <td class="d-flex gap-2">
                        <a class="btn btn-sm btn-outline-dark" href="<%=ctx%>/admin/categorias?editar=<%=c.getId_categoria()%>">Editar</a>
                        <form method="post" action="<%=ctx%>/admin/categorias" onsubmit="return confirm('¿Eliminar esta categoría?');">
                            <input type="hidden" name="csrf" value="<%=csrf%>">
                            <input type="hidden" name="action" value="eliminar">
                            <input type="hidden" name="id" value="<%=c.getId_categoria()%>">
                            <button class="btn btn-sm btn-outline-danger">Eliminar</button>
                        </form>
                    </td>
                </tr><%}%></tbody>
                </table>
            </div>
        </div>
    </div>
</main><%@include file="footer.jsp"%></body>
</html>
