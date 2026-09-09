/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CabezaFacturaDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarCabezaFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CabezaFacturaDAO dao = new CabezaFacturaDAO();

        System.out.println("=== ELIMINAR CABEZA DE FACTURA ===");
        System.out.println("Ingrese el ID de la Cabeza de Factura a eliminar:");
        int idFactura = sc.nextInt();

        boolean resultado = dao.eliminarCabezaFactura(idFactura);
        if (resultado) {
            System.out.println("La cabeza de factura se elimino Correctamente");
        } else {
            System.out.println("La cabeza de factura no se pudo eliminar");
        }
        sc.close();
    }
}
