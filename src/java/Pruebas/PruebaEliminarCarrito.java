/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CarritoCompraDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarCarrito {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarritoCompraDAO dao = new CarritoCompraDAO();

        System.out.println("=== ELIMINAR CARRITO DE COMPRA ===");
        System.out.println("Ingrese el ID del Carrito a eliminar:");
        int idCarrito = sc.nextInt();

        boolean resultado = dao.eliminarCarrito(idCarrito);
        if (resultado) {
            System.out.println("El carrito de compra se elimino Correctamente");
        } else {
            System.out.println("El carrito de compra no se pudo eliminar");
        }
        sc.close();
    }
}
