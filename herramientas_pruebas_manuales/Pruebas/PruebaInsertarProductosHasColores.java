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
public class PruebaInsertarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColores miRelacion = new ProductosHasColores();
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== INSERTAR PRODUCTO-COLOR (relacion) ===");
        System.out.println("Por favor Ingrese el ID del Producto (1-10):");
        miRelacion.setProductoIdProducto(sc.nextInt());

        System.out.println("Por favor Ingrese el ID del Color (1-10):");
        miRelacion.setColoresIdNombreColor(sc.nextInt());

        boolean resultado = dao.insertarProductosHasColores(miRelacion);
        if (resultado) {
            System.out.println("La relacion Producto-Color se guardo Correctamente");
        } else {
            System.out.println("La relacion Producto-Color no se pudo registrar");
        }
        sc.close();
    }
}
