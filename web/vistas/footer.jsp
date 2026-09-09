<%@page import="Modelo.ConfiguracionSitio,Controlador.ConfiguracionDAO,Seguridad.Util"%><%ConfiguracionSitio identidadFooter=new ConfiguracionDAO().obtener();%><footer class="footer mt-5">
    <div class="container py-5">
        <div class="row g-4">
            <div class="col-md-5">
                <h6><%=Util.escapeHtml(identidadFooter.getNombreTienda())%></h6>
                <p class="mb-0"><%=identidadFooter.getDescripcionTienda()!=null?Util.escapeHtml(identidadFooter.getDescripcionTienda()):"Moda seleccionada para hombres, mujeres, niñas y niños. Precios en pesos colombianos."%></p>
            </div>
            <div class="col-md-3">
                <h6>Compra segura</h6>
                <p class="mb-0">Pago por NEqui y verificación manual de cada orden.</p>
            </div>
            <div class="col-md-4">
                <h6>Atención</h6>
                <p class="mb-0">WhatsApp <%=Util.escapeHtml(identidadFooter.getContactoWhatsapp())%><%if(identidadFooter.getContactoEmail()!=null){%><br><%=Util.escapeHtml(identidadFooter.getContactoEmail())%><%}%><br>Colombia</p>
            </div>
        </div>
        <hr class="border-secondary">
        <small>© <%=java.time.Year.now()%> <%=Util.escapeHtml(identidadFooter.getNombreTienda())%>. Todos los derechos reservados.</small>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>
