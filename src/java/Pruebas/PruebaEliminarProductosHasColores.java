/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.ProductosHasColoresDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== ELIMINAR PRODUCTO-COLOR (relacion) ===");
        System.out.println("Ingrese el ID del Producto:");
        int idProducto = sc.nextInt();

        System.out.println("Ingrese el ID del Color:");
        int idColor = sc.nextInt();

        boolean resultado = dao.eliminarProductosHasColores(idProducto, idColor);
        if (resultado) {
            System.out.println("La relacion Producto-Color se elimino Correctamente");
        } else {
            System.out.println("La relacion Producto-Color no se pudo eliminar");
        }
        sc.close();
    }
}
