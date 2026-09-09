package Pruebas;

import Controlador.ProductosHasColoresDAO;
import Modelo.ProductosHasColores;
import java.util.Scanner;

public class PruebaInsertarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColores miVariante = new ProductosHasColores();
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== INSERTAR VARIANTE PRODUCTO-COLOR-TALLA ===");
        System.out.println("Por favor Ingrese el ID del Producto:");
        miVariante.setProductoIdProducto(sc.nextInt());

        System.out.println("Por favor Ingrese el ID del Color:");
        miVariante.setColoresIdColor(sc.nextInt());

        System.out.println("Por favor Ingrese el ID de la Talla:");
        miVariante.setTallaIdTalla(sc.nextInt());

        System.out.println("Por favor Ingrese la cantidad disponible:");
        miVariante.setCantidadDisponible(sc.nextInt());

        boolean resultado = dao.guardarVariante(miVariante);
        if (resultado) {
            System.out.println("La variante se guardo Correctamente");
        } else {
            System.out.println("La variante no se pudo registrar");
        }
        sc.close();
    }
}
