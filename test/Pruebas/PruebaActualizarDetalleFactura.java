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
public class PruebaActualizarDetalleFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DetalleFactura miDetalle = new DetalleFactura();
        DetalleFacturaDAO dao = new DetalleFacturaDAO();

        System.out.println("=== ACTUALIZAR DETALLE DE FACTURA ===");
        System.out.println("Ingrese el ID del Detalle de Factura a actualizar:");
        miDetalle.setIdDetalleFactura(sc.nextInt());

        System.out.println("Ingrese la nueva Cantidad:");
        miDetalle.setCantidad(sc.nextInt());

        System.out.println("Ingrese el nuevo Subtotal:");
        miDetalle.setSubtotalFac(sc.nextFloat());
        sc.nextLine();

        System.out.println("Ingrese el nuevo ID de la Cabeza de Factura:");
        miDetalle.setCabezaFacturaIdFactura(sc.nextInt());

        System.out.println("Ingrese el nuevo ID del Producto:");
        miDetalle.setProductoIdProducto(sc.nextInt());

        boolean resultado = dao.actualizarDetalleFactura(miDetalle);
        if (resultado) {
            System.out.println("El detalle de factura se actualizo Correctamente");
        } else {
            System.out.println("El detalle de factura no se pudo actualizar");
        }
        sc.close();
    }
}
