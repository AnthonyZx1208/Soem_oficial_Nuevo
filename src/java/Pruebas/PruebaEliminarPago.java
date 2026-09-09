/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.PagoDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PagoDAO pagoDao = new PagoDAO();

        System.out.println("=== ELIMINAR PAGO ===");
        System.out.println("Ingrese el ID del pago a eliminar:");
        int idEliminar = sc.nextInt();

        System.out.println("Intentando eliminar pago con ID: " + idEliminar);
        boolean respuesta = pagoDao.eliminarPago(idEliminar);
        if (respuesta) {
            System.out.println("El pago con ID " + idEliminar + " fue eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el pago. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
