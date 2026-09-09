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
public class PruebaInsertarDetalleFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DetalleFactura miDetalle = new DetalleFactura();
        DetalleFacturaDAO dao = new DetalleFacturaDAO();

        System.out.println("=== INSERTAR DETALLE DE FACTURA ===");
        System.out.println("Por favor Ingrese la Cantidad:");
        miDetalle.setCantidad(sc.nextInt());

        System.out.println("Por favor Ingrese el Subtotal:");
        miDetalle.setSubtotalFac(sc.nextFloat());
        sc.nextLine();

        System.out.println("Por favor Ingrese el ID de la Cabeza de Factura (1-10):");
        miDetalle.setCabezaFacturaIdFactura(sc.nextInt());

        System.out.println("Por favor Ingrese el ID del Producto (1-10):");
        miDetalle.setProductoIdProducto(sc.nextInt());

        boolean resultado = dao.insertarDetalleFactura(miDetalle);
        if (resultado) {
            System.out.println("El detalle de factura se guardo Correctamente");
        } else {
            System.out.println("El detalle de factura no se pudo registrar");
        }
        sc.close();
    }
}
