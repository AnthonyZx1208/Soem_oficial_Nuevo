package Pruebas;

import Controlador.ProductosHasColoresDAO;
import Modelo.ProductosHasColores;
import java.util.List;
import java.util.Scanner;

public class PruebaConsultarProductosHasColores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductosHasColoresDAO dao = new ProductosHasColoresDAO();

        System.out.println("=== CONSULTAR VARIANTE PRODUCTO-COLOR-TALLA ===");
        System.out.println("Ingrese el ID del Producto:");
        int idProducto = sc.nextInt();

        System.out.println("Ingrese el ID del Color:");
        int idColor = sc.nextInt();

        System.out.println("Ingrese el ID de la Talla:");
        int idTalla = sc.nextInt();

        List<ProductosHasColores> variantes = dao.listarPorProducto(idProducto);
        ProductosHasColores encontrada = null;
        for (ProductosHasColores v : variantes) {
            if (v.getColoresIdColor() == idColor && v.getTallaIdTalla() == idTalla) {
                encontrada = v;
                break;
            }
        }

        if (encontrada != null) {
            System.out.println("ID Producto: " + encontrada.getProductoIdProducto());
            System.out.println("ID Color: " + encontrada.getColoresIdColor());
            System.out.println("ID Talla: " + encontrada.getTallaIdTalla());
            System.out.println("Cantidad disponible: " + encontrada.getCantidadDisponible());
        } else {
            System.out.println("La variante no fue encontrada");
        }
        sc.close();
    }
}
