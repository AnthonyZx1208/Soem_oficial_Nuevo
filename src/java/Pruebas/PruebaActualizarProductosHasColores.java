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
public class PruebaActualizarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColores miRelacion = new ProductosHasColores();
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== ACTUALIZAR PRODUCTO-COLOR (relacion) ===");
        System.out.println("--- Nuevos valores ---");
        System.out.println("Ingrese el nuevo ID del Producto:");
        miRelacion.setProductoIdProducto(sc.nextInt());

        System.out.println("Ingrese el nuevo ID del Color:");
        miRelacion.setColoresIdNombreColor(sc.nextInt());

        System.out.println("--- Valores antiguos (para ubicar el registro) ---");
        System.out.println("Ingrese el ID del Producto antiguo:");
        int idProductoAntiguo = sc.nextInt();

        System.out.println("Ingrese el ID del Color antiguo:");
        int idColorAntiguo = sc.nextInt();

        boolean resultado = dao.actualizarProductosHasColores(miRelacion, idProductoAntiguo, idColorAntiguo);
        if (resultado) {
            System.out.println("La relacion Producto-Color se actualizo Correctamente");
        } else {
            System.out.println("La relacion Producto-Color no se pudo actualizar");
        }
        sc.close();
    }
}
