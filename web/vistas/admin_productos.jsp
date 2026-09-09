<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.util.*,java.text.NumberFormat,Modelo.Producto,Modelo.Categoria,Modelo.Talla,Modelo.Colores,Modelo.ProductosHasColores,Seguridad.Util"%><%
String ctx=request.getContextPath();
List<Producto> productos=(List<Producto>)request.getAttribute("productos");
List<Categoria> categorias=(List<Categoria>)request.getAttribute("categorias");
List<Map<String,Object>> subcategorias=(List<Map<String,Object>>)request.getAttribute("subcategorias");
List<Talla> tallas=(List<Talla>)request.getAttribute("tallas");
List<Colores> colores=(List<Colores>)request.getAttribute("colores");
List<ProductosHasColores> variantes=(List<ProductosHasColores>)request.getAttribute("variantes");
Producto editando=(Producto)request.getAttribute("editando");
String filtroCategoria=(String)request.getAttribute("filtroCategoria");
String filtroEstado=(String)request.getAttribute("filtroEstado");
NumberFormat cop=NumberFormat.getCurrencyInstance(new Locale("es","CO"));
%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Productos | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Administración</p>
    <h1 class="section-title">Productos</h1><%if(request.getAttribute("exito")!=null){%><div class="alert alert-success"><%=request.getAttribute("exito")%></div><%}if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=Util.escapeHtml(request.getAttribute("error"))%></div><%}%>

    <div class="row g-4">
        <div class="col-lg-5">
            <h2 class="h5 mb-3"><%=editando!=null?"Editar producto":"Nuevo producto"%></h2>
            <form class="bg-white p-4" method="post" action="<%=ctx%>/admin/productos" enctype="multipart/form-data">
                <input type="hidden" name="csrf" value="<%=csrf%>">
                <input type="hidden" name="action" value="<%=editando!=null?"actualizar":"crear"%>"><%if(editando!=null){%><input type="hidden" name="id" value="<%=editando.getId_producto()%>"><%}%>
                <label class="form-label">Nombre</label>
                <input class="form-control mb-3" name="nombre" required minlength="3" value="<%=editando!=null?Util.escapeHtml(editando.getNombre_producto()):""%>">
                <label class="form-label">Descripción</label>
                <textarea class="form-control mb-3" name="descripcion" rows="3"><%=editando!=null&&editando.getDescripcion()!=null?Util.escapeHtml(editando.getDescripcion()):""%></textarea>
                <div class="row">
                    <div class="col-6">
                        <label class="form-label">Precio</label>
                        <input class="form-control mb-3" type="number" step="1" min="1" name="precioProducto" required value="<%=editando!=null?(long)editando.getPrecio_producto():""%>">
                    </div>
                    <div class="col-6">
                        <label class="form-label">Precio oferta</label>
                        <input class="form-control mb-3" type="number" step="1" min="1" name="precioOferta" value="<%=editando!=null&&editando.getPrecio_oferta()!=null?editando.getPrecio_oferta().longValue():""%>">
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <label class="form-label">Inicio oferta</label>
                        <input class="form-control mb-3" type="datetime-local" name="fechaInicioOferta" value="<%=editando!=null&&editando.getFecha_inicio_oferta()!=null?editando.getFecha_inicio_oferta().toLocalDateTime().toString():""%>">
                    </div>
                    <div class="col-6">
                        <label class="form-label">Fin oferta</label>
                        <input class="form-control mb-3" type="datetime-local" name="fechaFinOferta" value="<%=editando!=null&&editando.getFecha_fin_oferta()!=null?editando.getFecha_fin_oferta().toLocalDateTime().toString():""%>">
                    </div>
                </div>
                <label class="form-label">Stock</label>
                <input class="form-control mb-3" type="number" min="0" name="cantidadStock" required value="<%=editando!=null?editando.getCantidad_stock():"0"%>">
                <label class="form-label">Categoría</label>
                <select class="form-select mb-3" name="categoriaId" id="categoriaSelect" required><%for(Categoria c:categorias){%>
                    <option value="<%=c.getId_categoria()%>" <%=editando!=null&&editando.getCategoria_id_categoria()==c.getId_categoria()?"selected":""%>><%=Util.escapeHtml(c.getNombre_categoria())%></option><%}%>
                </select>
                <label class="form-label">Subcategoría</label>
                <select class="form-select mb-3" name="subcategoriaId" id="subcategoriaSelect">
                    <option value="">Sin subcategoría</option><%for(Map<String,Object> s:subcategorias){%>
                    <option value="<%=s.get("id")%>" data-categoria="<%=s.get("categoriaId")%>" <%=editando!=null&&s.get("id").equals(editando.getSubCategoria_id_subcategoria())?"selected":""%>><%=Util.escapeHtml(s.get("nombre"))%></option><%}%>
                </select>
                <label class="form-label">Estado</label>
                <select class="form-select mb-3" name="estado">
                    <option value="Activo" <%=editando==null||"Activo".equals(editando.getEstado())?"selected":""%>>Activo</option>
                    <option value="Inactivo" <%=editando!=null&&"Inactivo".equals(editando.getEstado())?"selected":""%>>Inactivo</option>
                </select>
                <label class="form-label">Imagen<%if(editando!=null){%> (deja vacío para conservar la actual)<%}%></label>
                <input class="form-control mb-4" type="file" name="imagen" accept=".jpg,.jpeg,.png,.webp,.gif">
                <button class="btn btn-gold"><%=editando!=null?"Guardar cambios":"Crear producto"%></button><%if(editando!=null){%> <a class="btn btn-outline-dark" href="<%=ctx%>/admin/productos">Cancelar</a><%}%>
            </form>
        </div>

        <div class="col-lg-7">
            <form class="d-flex gap-2 mb-3" method="get" action="<%=ctx%>/admin/productos">
                <select class="form-select form-select-sm" name="categoria" onchange="this.form.submit()">
                    <option value="">Todas las categorías</option><%for(Categoria c:categorias){%>
                    <option value="<%=c.getId_categoria()%>" <%=String.valueOf(c.getId_categoria()).equals(filtroCategoria)?"selected":""%>><%=Util.escapeHtml(c.getNombre_categoria())%></option><%}%>
                </select>
                <select class="form-select form-select-sm" name="estado" onchange="this.form.submit()">
                    <option value="">Todos los estados</option>
                    <option value="Activo" <%="Activo".equals(filtroEstado)?"selected":""%>>Activo</option>
                    <option value="Inactivo" <%="Inactivo".equals(filtroEstado)?"selected":""%>>Inactivo</option>
                </select>
            </form>
            <div class="table-responsive bg-white p-3">
                <table class="table align-middle admin-table">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Stock</th>
                            <th>Estado</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody><%for(Producto p:productos){%><tr>
                    <td><%=Util.escapeHtml(p.getNombre_producto())%><%if(p.getPrecio_oferta()!=null){%><br><small class="text-gold">Oferta: <%=cop.format(p.getPrecio_oferta())%></small><%}%></td>
                    <td><%=cop.format(p.getPrecio_producto())%></td>
                    <td><%=p.getCantidad_stock()%></td>
                    <td><%=p.getEstado()%></td>
                    <td class="d-flex gap-2">
                        <a class="btn btn-sm btn-outline-dark" href="<%=ctx%>/admin/productos?editar=<%=p.getId_producto()%>">Editar</a>
                        <form method="post" action="<%=ctx%>/admin/productos" onsubmit="return confirm('¿Eliminar este producto?');">
                            <input type="hidden" name="csrf" value="<%=csrf%>">
                            <input type="hidden" name="action" value="eliminar">
                            <input type="hidden" name="id" value="<%=p.getId_producto()%>">
                            <button class="btn btn-sm btn-outline-danger">Eliminar</button>
                        </form>
                    </td>
                </tr><%}%></tbody>
                </table>
            </div>
        </div>
    </div><%if(editando!=null){%>

    <h2 class="h5 mt-5 mb-3">Variantes (talla / color / stock) de "<%=Util.escapeHtml(editando.getNombre_producto())%>"</h2>
    <div class="row g-4">
        <div class="col-lg-5">
            <form class="bg-white p-4" method="post" action="<%=ctx%>/admin/productos">
                <input type="hidden" name="csrf" value="<%=csrf%>">
                <input type="hidden" name="action" value="guardarVariante">
                <input type="hidden" name="productoId" value="<%=editando.getId_producto()%>">
                <label class="form-label">Color</label>
                <select class="form-select mb-3" name="colorId" required><%for(Colores co:colores){%>
                    <option value="<%=co.getIdColor()%>"><%=Util.escapeHtml(co.getNombreColor())%></option><%}%>
                </select>
                <label class="form-label">Talla</label>
                <select class="form-select mb-3" name="tallaId" required><%for(Talla t:tallas){%>
                    <option value="<%=t.getIdTalla()%>"><%=Util.escapeHtml(t.getNombreTalla())%></option><%}%>
                </select>
                <label class="form-label">Cantidad disponible</label>
                <input class="form-control mb-4" type="number" min="0" name="cantidad" required value="0">
                <button class="btn btn-dark">Guardar variante</button>
            </form>
        </div>
        <div class="col-lg-7">
            <div class="table-responsive bg-white p-3">
                <table class="table align-middle admin-table">
                    <thead>
                        <tr>
                            <th>Color</th>
                            <th>Talla</th>
                            <th>Cantidad</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody><%for(ProductosHasColores v:variantes){String nombreColor="";for(Colores co:colores)if(co.getIdColor()==v.getColoresIdColor())nombreColor=co.getNombreColor();String nombreTalla="";for(Talla t:tallas)if(t.getIdTalla()==v.getTallaIdTalla())nombreTalla=t.getNombreTalla();%><tr>
                    <td><%=Util.escapeHtml(nombreColor)%></td>
                    <td><%=Util.escapeHtml(nombreTalla)%></td>
                    <td><%=v.getCantidadDisponible()%></td>
                    <td>
                        <form method="post" action="<%=ctx%>/admin/productos" onsubmit="return confirm('¿Eliminar esta variante?');">
                            <input type="hidden" name="csrf" value="<%=csrf%>">
                            <input type="hidden" name="action" value="eliminarVariante">
                            <input type="hidden" name="productoId" value="<%=v.getProductoIdProducto()%>">
                            <input type="hidden" name="colorId" value="<%=v.getColoresIdColor()%>">
                            <input type="hidden" name="tallaId" value="<%=v.getTallaIdTalla()%>">
                            <button class="btn btn-sm btn-outline-danger">Eliminar</button>
                        </form>
                    </td>
                </tr><%}%></tbody>
                </table>
            </div>
        </div>
    </div><%}%>
</main><%@include file="footer.jsp"%>
<script>
(function(){
    var catSel=document.getElementById('categoriaSelect');
    var subSel=document.getElementById('subcategoriaSelect');
    if(!catSel||!subSel) return;
    var todas=Array.prototype.slice.call(subSel.querySelectorAll('option[data-categoria]'));
    function filtrar(){
        var cat=catSel.value;
        var actual=subSel.value;
        todas.forEach(function(o){ o.hidden = o.getAttribute('data-categoria')!==cat; });
        if(subSel.selectedOptions[0] && subSel.selectedOptions[0].hidden) subSel.value='';
    }
    catSel.addEventListener('change',filtrar);
    filtrar();
})();
</script>
</body>
</html>
