/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.MetodoPagoDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarMetodoPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MetodoPagoDAO metodoPagoDao = new MetodoPagoDAO();

        System.out.println("=== ELIMINAR METODO DE PAGO ===");
        System.out.println("Ingrese el ID del metodo de pago a eliminar:");
        int idEliminar = sc.nextInt();

        System.out.println("Intentando eliminar metodo de pago con ID: " + idEliminar);
        boolean respuesta = metodoPagoDao.eliminarMetodoPago(idEliminar);
        if (respuesta) {
            System.out.println("El metodo de pago con ID " + idEliminar + " fue eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el metodo de pago. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
