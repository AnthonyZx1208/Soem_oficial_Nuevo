/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CarritoCompraDAO;
import Modelo.CarritoCompra;
import java.sql.Date;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaActualizarCarrito {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarritoCompra miCarrito = new CarritoCompra();
        CarritoCompraDAO dao = new CarritoCompraDAO();

        System.out.println("=== ACTUALIZAR CARRITO DE COMPRA ===");
        System.out.println("Ingrese el ID del Carrito a actualizar:");
        miCarrito.setIdCarrito(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese la nueva Cantidad:");
        miCarrito.setCantidad(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese la nueva Fecha de Agregado (AAAA-MM-DD):");
        String fechaStr = sc.nextLine();
        try {
            miCarrito.setFechaAgregado(Date.valueOf(fechaStr));
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido. Use AAAA-MM-DD");
            sc.close();
            return;
        }

        System.out.println("Ingrese el nuevo ID del Producto:");
        miCarrito.setProductoIdProducto(sc.nextInt());

        boolean resultado = dao.actualizarCarrito(miCarrito);
        if (resultado) {
            System.out.println("El carrito de compra se actualizo Correctamente");
        } else {
            System.out.println("El carrito de compra no se pudo actualizar");
        }
        sc.close();
    }
}
