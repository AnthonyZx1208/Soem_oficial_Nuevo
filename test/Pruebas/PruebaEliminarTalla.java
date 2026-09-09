/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.TallaDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarTalla {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TallaDAO tallaDao = new TallaDAO();

        System.out.println("=== ELIMINAR TALLA ===");
        System.out.println("Ingrese el ID de la talla a eliminar:");
        int idEliminar = sc.nextInt();

        System.out.println("Intentando eliminar talla con ID: " + idEliminar);
        boolean respuesta = tallaDao.eliminarTalla(idEliminar);
        if (respuesta) {
            System.out.println("La talla con ID " + idEliminar + " fue eliminada exitosamente.");
        } else {
            System.out.println("No se pudo eliminar la talla. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
