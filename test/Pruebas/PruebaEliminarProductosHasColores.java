package Pruebas;

import Controlador.ProductosHasColoresDAO;
import java.util.Scanner;

public class PruebaEliminarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== ELIMINAR VARIANTE PRODUCTO-COLOR-TALLA ===");
        System.out.println("Ingrese el ID del Producto:");
        int idProducto = sc.nextInt();

        System.out.println("Ingrese el ID del Color:");
        int idColor = sc.nextInt();

        System.out.println("Ingrese el ID de la Talla:");
        int idTalla = sc.nextInt();

        boolean resultado = dao.eliminarVariante(idProducto, idColor, idTalla);
        if (resultado) {
            System.out.println("La variante se elimino Correctamente");
        } else {
            System.out.println("La variante no se pudo eliminar");
        }
        sc.close();
    }
}
