/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.ColoresDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarColor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColoresDAO coloresDao = new ColoresDAO();

        System.out.println("=== ELIMINAR COLOR ===");
        System.out.println("Ingrese el ID del color a eliminar:");
        int idEliminar = sc.nextInt();

        System.out.println("Intentando eliminar color con ID: " + idEliminar);
        boolean respuesta = coloresDao.eliminarColor(idEliminar);
        if (respuesta) {
            System.out.println("El color con ID " + idEliminar + " fue eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el color. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
