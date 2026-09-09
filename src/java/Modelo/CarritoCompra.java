/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 * Modelo de CarritoCompra - coincide con la tabla Carrito_Compra de database.sql.
 * El campo precioUnitario NO se persiste en base de datos: se calcula/asigna en
 * memoria (sesion) a partir del precio del producto en el momento de agregarlo.
 * @author Aprendiz
 */
public class CarritoCompra {

    private int idCarrito;
    private Integer usuarioIdUsuario;
    private int productoIdProducto;
    private Integer colores_id_color;
    private Integer talla_id_talla;
    private int cantidad;
    private Date fechaAgregado;
    private double precioUnitario;

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Integer getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Integer usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public int getProductoIdProducto() {
        return productoIdProducto;
    }

    public void setProductoIdProducto(int productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }

    public Integer getColores_id_color() {
        return colores_id_color;
    }

    public void setColores_id_color(Integer colores_id_color) {
        this.colores_id_color = colores_id_color;
    }

    public Integer getTalla_id_talla() {
        return talla_id_talla;
    }

    public void setTalla_id_talla(Integer talla_id_talla) {
        this.talla_id_talla = talla_id_talla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Date fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    /** No persistido en BD: se fija en memoria al agregar el producto al carrito. */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
