/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.DetalleFacturaDAO;
import Modelo.DetalleFactura;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarDetalleFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DetalleFacturaDAO miDetalleDAO = new DetalleFacturaDAO();

        System.out.println("=== CONSULTAR DETALLE DE FACTURA ===");
        System.out.println("Ingrese el ID del Detalle de Factura a consultar:");
        int idDetalleFactura = sc.nextInt();

        DetalleFactura miDetalle = miDetalleDAO.consultarDetalleFactura(idDetalleFactura);
        if (miDetalle != null) {
            System.out.println("ID Detalle: " + miDetalle.getIdDetalleFactura());
            System.out.println("Cantidad: " + miDetalle.getCantidad());
            System.out.println("Subtotal: " + miDetalle.getSubtotalFac());
            System.out.println("ID Cabeza Factura: " + miDetalle.getCabezaFacturaIdFactura());
            System.out.println("ID Producto: " + miDetalle.getProductoIdProducto());
        } else {
            System.out.println("Detalle de Factura no encontrado");
        }
        sc.close();
    }
}
