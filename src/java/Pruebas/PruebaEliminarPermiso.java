/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.PermisosDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarPermiso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PermisosDAO permisosDao = new PermisosDAO();

        System.out.println("=== ELIMINAR PERMISO ===");
        System.out.println("Ingrese el ID del permiso a eliminar:");
        int idEliminar = sc.nextInt();

        System.out.println("Intentando eliminar permiso con ID: " + idEliminar);
        boolean respuesta = permisosDao.eliminarPermiso(idEliminar);
        if (respuesta) {
            System.out.println("El permiso con ID " + idEliminar + " fue eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el permiso. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
