package Pruebas;

import Controlador.ProductosHasColoresDAO;
import Modelo.ProductosHasColores;
import java.util.Scanner;

public class PruebaActualizarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColores miVariante = new ProductosHasColores();
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== ACTUALIZAR CANTIDAD DE UNA VARIANTE PRODUCTO-COLOR-TALLA ===");
        System.out.println("La variante se identifica por Producto+Color+Talla; guardarVariante");
        System.out.println("actualiza la cantidad si ya existe (INSERT ... ON DUPLICATE KEY UPDATE).");
        System.out.println("Ingrese el ID del Producto:");
        miVariante.setProductoIdProducto(sc.nextInt());

        System.out.println("Ingrese el ID del Color:");
        miVariante.setColoresIdColor(sc.nextInt());

        System.out.println("Ingrese el ID de la Talla:");
        miVariante.setTallaIdTalla(sc.nextInt());

        System.out.println("Ingrese la nueva cantidad disponible:");
        miVariante.setCantidadDisponible(sc.nextInt());

        boolean resultado = dao.guardarVariante(miVariante);
        if (resultado) {
            System.out.println("La variante se actualizo Correctamente");
        } else {
            System.out.println("La variante no se pudo actualizar");
        }
        sc.close();
    }
}
