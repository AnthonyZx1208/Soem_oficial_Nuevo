/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 * Modelo de Producto - coincide con la tabla Producto de database.sql
 * @author Aprendiz
 */
public class Producto {

    private int id_producto;
    private String nombre_producto;
    private String descripcion;
    private float precio_producto;
    private Double precio_oferta;
    private int cantidad_stock;
    private Date fecha_creacion;
    private Date fecha_actualizacion;
    private String imagen_principal;
    private int categoria_id_categoria;
    private Integer subCategoria_id_subcategoria;
    private String estado;
    private java.sql.Timestamp fecha_inicio_oferta;
    private java.sql.Timestamp fecha_fin_oferta;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(float precio_producto) {
        this.precio_producto = precio_producto;
    }

    public Double getPrecio_oferta() {
        return precio_oferta;
    }

    public void setPrecio_oferta(Double precio_oferta) {
        this.precio_oferta = precio_oferta;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public String getImagen_principal() {
        return imagen_principal;
    }

    public void setImagen_principal(String imagen_principal) {
        this.imagen_principal = imagen_principal;
    }

    public int getCategoria_id_categoria() {
        return categoria_id_categoria;
    }

    public void setCategoria_id_categoria(int categoria_id_categoria) {
        this.categoria_id_categoria = categoria_id_categoria;
    }

    public Integer getSubCategoria_id_subcategoria() {
        return subCategoria_id_subcategoria;
    }

    public void setSubCategoria_id_subcategoria(Integer subCategoria_id_subcategoria) {
        this.subCategoria_id_subcategoria = subCategoria_id_subcategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public java.sql.Timestamp getFecha_inicio_oferta() {
        return fecha_inicio_oferta;
    }

    public void setFecha_inicio_oferta(java.sql.Timestamp fecha_inicio_oferta) {
        this.fecha_inicio_oferta = fecha_inicio_oferta;
    }

    public java.sql.Timestamp getFecha_fin_oferta() {
        return fecha_fin_oferta;
    }

    public void setFecha_fin_oferta(java.sql.Timestamp fecha_fin_oferta) {
        this.fecha_fin_oferta = fecha_fin_oferta;
    }
}
