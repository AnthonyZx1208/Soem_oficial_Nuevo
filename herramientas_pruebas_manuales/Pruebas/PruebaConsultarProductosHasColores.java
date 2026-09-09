/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.ProductosHasColoresDAO;
import Modelo.ProductosHasColores;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== CONSULTAR PRODUCTO-COLOR (relacion) ===");
        System.out.println("Ingrese el ID del Producto:");
        int idProducto = sc.nextInt();

        System.out.println("Ingrese el ID del Color:");
        int idColor = sc.nextInt();

        ProductosHasColores miRelacion = dao.consultarProductosHasColores(idProducto, idColor);
        if (miRelacion != null) {
            System.out.println("ID Producto: " + miRelacion.getProductoIdProducto());
            System.out.println("ID Color: " + miRelacion.getColoresIdNombreColor());
        } else {
            System.out.println("La relacion Producto-Color no fue encontrada");
        }
        sc.close();
    }
}
