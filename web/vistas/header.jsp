<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,Modelo.CarritoCompra,Controlador.TiendaDAO,Seguridad.SeguridadAplicacion,Seguridad.Util" %>
<%
HttpSession sesion=request.getSession(false); Integer usuarioId=sesion==null?null:(Integer)sesion.getAttribute("usuarioId"); Integer usuarioRol=sesion==null?null:(Integer)sesion.getAttribute("usuarioRol"); String usuarioNombre=sesion==null?null:(String)sesion.getAttribute("usuarioNombre"); int carrito=0; if(sesion!=null&&sesion.getAttribute("carrito") instanceof List) for(CarritoCompra item:(List<CarritoCompra>)sesion.getAttribute("carrito")) carrito+=item.getCantidad(); int deseos=usuarioId==null?0:new TiendaDAO().contarDeseos(usuarioId); String csrf=SeguridadAplicacion.csrf(request.getSession()); String appCtx=request.getContextPath();
%>
<header class="site-header sticky-top">
    <nav class="navbar navbar-expand-lg navbar-dark container py-3">
        <a class="navbar-brand brand" href="<%=appCtx%>/home">SOEM <span>OFICIAL</span>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu">
        <span class="navbar-toggler-icon">
        </span>
    </button>
    <div class="collapse navbar-collapse" id="menu">
        <ul class="navbar-nav mx-auto">
            <li class="nav-item">
                <a class="nav-link" href="<%=appCtx%>/home">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=appCtx%>/home#categorias">Colecciones</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=appCtx%>/home#productos">Novedades</a>
            </li><%if(usuarioRol!=null&&usuarioRol==1){%><li class="nav-item">
            <a class="nav-link" href="<%=appCtx%>/admin">Administración</a>
        </li><li class="nav-item">
            <a class="nav-link" href="<%=appCtx%>/admin/categorias">Categorías</a>
        </li><li class="nav-item">
            <a class="nav-link" href="<%=appCtx%>/admin/productos">Productos</a>
        </li><li class="nav-item">
            <a class="nav-link" href="<%=appCtx%>/admin/identidad">Identidad</a>
        </li><%}%></ul>
        <div class="d-flex align-items-center gap-2">
            <a class="header-action" href="<%=appCtx%>/wishlist" aria-label="Lista de deseos">♥<span><%=deseos%></span>
        </a>
        <a class="header-action" href="<%=appCtx%>/cart?action=view" aria-label="Carrito">🛍<span><%=carrito%></span>
    </a><%if(usuarioId==null){%><a class="btn btn-gold btn-sm" href="<%=appCtx%>/login">Ingresar</a><%}else{%><div class="dropdown">
    <button class="btn btn-outline-light btn-sm dropdown-toggle" data-bs-toggle="dropdown"><%=Util.escapeHtml(usuarioNombre)%></button>
    <ul class="dropdown-menu dropdown-menu-end">
        <li>
            <a class="dropdown-item" href="<%=appCtx%>/profile">Mi perfil</a>
        </li>
        <li>
            <a class="dropdown-item" href="<%=appCtx%>/orders">Mis compras</a>
        </li>
        <li>
            <hr class="dropdown-divider">
        </li>
        <li>
            <a class="dropdown-item" href="<%=appCtx%>/logout">Cerrar sesión</a>
        </li>
    </ul>
</div><%}%></div>
</div>
</nav>
</header>
