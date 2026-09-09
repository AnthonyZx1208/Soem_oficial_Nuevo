/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.DetalleFacturaDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarDetalleFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DetalleFacturaDAO dao = new DetalleFacturaDAO();

        System.out.println("=== ELIMINAR DETALLE DE FACTURA ===");
        System.out.println("Ingrese el ID del Detalle de Factura a eliminar:");
        int idDetalleFactura = sc.nextInt();

        boolean resultado = dao.eliminarDetalleFactura(idDetalleFactura);
        if (resultado) {
            System.out.println("El detalle de factura se elimino Correctamente");
        } else {
            System.out.println("El detalle de factura no se pudo eliminar");
        }
        sc.close();
    }
}
