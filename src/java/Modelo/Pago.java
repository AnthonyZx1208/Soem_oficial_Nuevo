/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Date;
/**
 *
 * @author Aprendiz
 */
public class Pago {

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public float getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(float montoPago) {
        this.montoPago = montoPago;
    }

    public int getMetodoDePagoIdMetodoPago() {
        return metodoDePagoIdMetodoPago;
    }

    public void setMetodoDePagoIdMetodoPago(int metodoDePagoIdMetodoPago) {
        this.metodoDePagoIdMetodoPago = metodoDePagoIdMetodoPago;
    }

    public int getCabezaFacturaIdFactura() {
        return cabezaFacturaIdFactura;
    }

    public void setCabezaFacturaIdFactura(int cabezaFacturaIdFactura) {
        this.cabezaFacturaIdFactura = cabezaFacturaIdFactura;
    }
    
    private int idPago;
    private Date fechaPago;
    private float montoPago;
    private int metodoDePagoIdMetodoPago;
    private int cabezaFacturaIdFactura;
    
}
