<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,java.util.Set,java.util.Collections,Modelo.ConfiguracionSitio,Modelo.Categoria,Controlador.TiendaDAO,Controlador.ConfiguracionDAO,Controlador.CategoriaDAO,Controlador.PermisosDAO,Seguridad.SeguridadAplicacion,Seguridad.Util" %>
<%
HttpSession sesion=request.getSession(false); Integer usuarioId=sesion==null?null:(Integer)sesion.getAttribute("usuarioId"); Integer usuarioRol=sesion==null?null:(Integer)sesion.getAttribute("usuarioRol"); String usuarioNombre=sesion==null?null:(String)sesion.getAttribute("usuarioNombre"); int deseos=usuarioId==null?0:new TiendaDAO().contarDeseos(usuarioId); String csrf=SeguridadAplicacion.csrf(request.getSession()); String appCtx=request.getContextPath(); ConfiguracionSitio identidad=new ConfiguracionDAO().obtener(); String nombreTienda=identidad.getNombreTienda()!=null?identidad.getNombreTienda().toUpperCase():"SOEM OFICIAL"; int espacio=nombreTienda.indexOf(' '); String marcaPrincipal=espacio<0?nombreTienda:nombreTienda.substring(0,espacio); String marcaResto=espacio<0?"":nombreTienda.substring(espacio+1);
Set<String> misPermisos=usuarioRol==null?Collections.emptySet():new PermisosDAO().permisosDeRol(usuarioRol);
boolean puedeCategorias=misPermisos.contains("Gestionar categorías");
boolean puedeProductos=misPermisos.contains("Gestionar productos");
boolean puedeIdentidad=misPermisos.contains("Gestionar configuración");
boolean puedeUsuarios=misPermisos.contains("Gestionar usuarios");
boolean esPersonalAdmin=puedeCategorias||puedeProductos||puedeIdentidad||puedeUsuarios;
List<Categoria> categoriasNav=new CategoriaDAO().listarCategorias();
%>
<div class="ticker"><div class="ticker-track">
    <span>PEDIDOS POR WHATSAPP</span><span>ENVÍOS A TODA COLOMBIA</span><span>ATENCIÓN POR WHATSAPP <%=Util.escapeHtml(identidad.getContactoWhatsapp())%></span>
</div></div>
<header class="site-header sticky-top">
    <nav class="navbar navbar-expand-lg navbar-light container py-3">
        <a class="navbar-brand brand" href="<%=appCtx%>/home"><%if(identidad.getLogoUrl()!=null){%><img src="<%=appCtx%>/<%=identidad.getLogoUrl()%>" alt="<%=Util.escapeHtml(identidad.getNombreTienda())%>" style="height:32px"><%}else{%><%=Util.escapeHtml(marcaPrincipal)%> <span><%=Util.escapeHtml(marcaResto)%></span><%}%>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu">
        <span class="navbar-toggler-icon">
        </span>
    </button>
    <div class="collapse navbar-collapse" id="menu">
        <ul class="navbar-nav mx-auto"><%for(Categoria c:categoriasNav){%>
            <li class="nav-item">
                <a class="nav-link" href="<%=appCtx%>/categoria?id=<%=c.getId_categoria()%>"><%=Util.escapeHtml(c.getNombre_categoria())%></a>
            </li><%}%></ul>
        <div class="d-flex align-items-center gap-2 header-iconos">
            <button class="icon-btn" type="button" aria-label="Buscar" onclick="document.getElementById('cajaBusqueda').classList.toggle('mostrar')">🔍<span class="header-accion-label">Buscar</span></button>
            <a class="header-action" href="<%=appCtx%>/wishlist" aria-label="Favoritos">♥<span class="header-badge"><%=deseos%></span><span class="header-accion-label">Favoritos</span>
        </a><%if(usuarioId==null){%><a class="icon-btn" href="<%=appCtx%>/login" aria-label="Ingresar">👤<span class="header-accion-label">Ingresar</span></a><%}else{%><div class="dropdown">
    <button class="icon-btn dropdown-toggle" data-bs-toggle="dropdown" aria-label="Mi cuenta">👤<span class="header-accion-label">Cuenta</span></button>
    <ul class="dropdown-menu dropdown-menu-end">
        <li>
            <h6 class="dropdown-header"><%=Util.escapeHtml(usuarioNombre)%></h6>
        </li>
        <li>
            <a class="dropdown-item" href="<%=appCtx%>/profile">Mi perfil</a>
        </li><%if(esPersonalAdmin){%><li>
            <hr class="dropdown-divider">
        </li><li>
            <h6 class="dropdown-header">Administración</h6>
        </li><%}if(puedeCategorias){%><li>
            <a class="dropdown-item" href="<%=appCtx%>/admin/categorias">Categorías</a>
        </li><%}if(puedeProductos){%><li>
            <a class="dropdown-item" href="<%=appCtx%>/admin/productos">Productos</a>
        </li><%}if(puedeIdentidad){%><li>
            <a class="dropdown-item" href="<%=appCtx%>/admin/identidad">Identidad</a>
        </li><%}if(puedeUsuarios){%><li>
            <a class="dropdown-item" href="<%=appCtx%>/admin/usuarios">Usuarios</a>
        </li><%}%><li>
            <hr class="dropdown-divider">
        </li>
        <li>
            <a class="dropdown-item" href="<%=appCtx%>/logout">Cerrar sesión</a>
        </li>
    </ul>
</div><%}%></div>
</div>
</nav>
<div class="search-box" id="cajaBusqueda">
    <form class="container d-flex" method="get" action="<%=appCtx%>/home">
        <input class="form-control" type="search" name="q" placeholder="Buscar productos..." value="<%=Util.escapeHtml(Util.parametroUtf8(request,"q"))%>" autofocus>
    </form>
</div>
</header>
