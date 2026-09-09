/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Aprendiz
 */
public class DetalleFactura {

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotalFac() {
        return subtotalFac;
    }

    public void setSubtotalFac(float subtotalFac) {
        this.subtotalFac = subtotalFac;
    }

    public int getCabezaFacturaIdFactura() {
        return cabezaFacturaIdFactura;
    }

    public void setCabezaFacturaIdFactura(int cabezaFacturaIdFactura) {
        this.cabezaFacturaIdFactura = cabezaFacturaIdFactura;
    }

    public int getProductoIdProducto() {
        return productoIdProducto;
    }

    public void setProductoIdProducto(int productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }
    
    private int idDetalleFactura;
    private int cantidad;
    private float subtotalFac;
    private int cabezaFacturaIdFactura;
    private int productoIdProducto;
    
}
