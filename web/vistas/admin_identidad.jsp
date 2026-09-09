<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="Modelo.ConfiguracionSitio,Seguridad.Util"%><%String ctx=request.getContextPath();ConfiguracionSitio config=(ConfiguracionSitio)request.getAttribute("configuracion");%><!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Identidad de marca | SOEM Oficial</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=ctx%>/css/estilo.css" rel="stylesheet">
    </head>
    <body><%@include file="header.jsp"%><main class="container py-5">
    <p class="eyebrow">Administración</p>
    <h1 class="section-title">Identidad de marca</h1><%if(request.getAttribute("exito")!=null){%><div class="alert alert-success"><%=request.getAttribute("exito")%></div><%}if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=Util.escapeHtml(request.getAttribute("error"))%></div><%}%>
    <form class="bg-white p-4 col-lg-8" method="post" action="<%=ctx%>/admin/identidad" enctype="multipart/form-data">
        <input type="hidden" name="csrf" value="<%=csrf%>">
        <label class="form-label">Nombre de la tienda</label>
        <input class="form-control mb-3" name="nombreTienda" required value="<%=Util.escapeHtml(config.getNombreTienda())%>">

        <div class="row">
            <div class="col-md-4">
                <label class="form-label">Color primario</label>
                <input class="form-control form-control-color mb-3" type="color" name="colorPrimario" value="<%=config.getColorPrimario()%>">
            </div>
            <div class="col-md-4">
                <label class="form-label">Color secundario</label>
                <input class="form-control form-control-color mb-3" type="color" name="colorSecundario" value="<%=config.getColorSecundario()%>">
            </div>
            <div class="col-md-4">
                <label class="form-label">Color de acento (dorado)</label>
                <input class="form-control form-control-color mb-3" type="color" name="colorAcento" value="<%=config.getColorAcento()%>">
            </div>
        </div>

        <label class="form-label">WhatsApp de contacto</label>
        <input class="form-control mb-3" name="contactoWhatsapp" value="<%=Util.escapeHtml(config.getContactoWhatsapp())%>">
        <label class="form-label">Correo de contacto</label>
        <input class="form-control mb-3" type="email" name="contactoEmail" value="<%=config.getContactoEmail()!=null?Util.escapeHtml(config.getContactoEmail()):""%>">
        <label class="form-label">Descripción de la tienda</label>
        <textarea class="form-control mb-4" name="descripcionTienda" rows="3"><%=config.getDescripcionTienda()!=null?Util.escapeHtml(config.getDescripcionTienda()):""%></textarea>

        <div class="row">
            <div class="col-md-6">
                <label class="form-label">Logo<%if(config.getLogoUrl()!=null){%> (actual: <img src="<%=ctx%>/<%=config.getLogoUrl()%>" alt="" style="height:24px">)<%}%></label>
                <input class="form-control mb-3" type="file" name="logo" accept=".jpg,.jpeg,.png,.webp,.svg">
            </div>
            <div class="col-md-6">
                <label class="form-label">Favicon<%if(config.getFaviconUrl()!=null){%> (actual: <img src="<%=ctx%>/<%=config.getFaviconUrl()%>" alt="" style="height:24px">)<%}%></label>
                <input class="form-control mb-3" type="file" name="favicon" accept=".png,.ico,.svg">
            </div>
        </div>
        <button class="btn btn-gold">Guardar identidad</button>
    </form>
</main><%@include file="footer.jsp"%></body>
</html>
