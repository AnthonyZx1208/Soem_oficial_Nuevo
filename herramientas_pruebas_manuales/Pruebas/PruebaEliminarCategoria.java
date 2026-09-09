/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CategoriaDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarCategoria {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CategoriaDAO categoriaDao = new CategoriaDAO();

        System.out.println("=== ELIMINAR CATEGORIA ===");
        System.out.println("Ingrese el ID de la categoria a eliminar:");
        int idEliminar = sc.nextInt();

        System.out.println("Intentando eliminar categoria con ID: " + idEliminar);
        boolean respuesta = categoriaDao.eliminarCategoria(idEliminar);
        if (respuesta) {
            System.out.println("La categoria con ID " + idEliminar + " fue eliminada exitosamente.");
        } else {
            System.out.println("No se pudo eliminar la categoria. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
