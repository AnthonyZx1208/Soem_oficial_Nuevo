/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.ProductoDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarProducto {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDao = new ProductoDAO();

        System.out.println("=== ELIMINAR PRODUCTO ===");
        System.out.println("Ingrese el ID del producto a eliminar:");
        int idEliminar = sc.nextInt();

        System.out.println("Intentando eliminar producto con ID: " + idEliminar);
        boolean respuesta = productoDao.eliminarProducto(idEliminar);
        if (respuesta) {
            System.out.println("El producto con ID " + idEliminar + " fue eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el producto. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
