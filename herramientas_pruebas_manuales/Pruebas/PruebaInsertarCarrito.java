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
public class PruebaInsertarCarrito {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarritoCompra miCarrito = new CarritoCompra();
        CarritoCompraDAO dao = new CarritoCompraDAO();

        System.out.println("=== INSERTAR CARRITO DE COMPRA ===");
        System.out.println("Por favor Ingrese la Cantidad:");
        miCarrito.setCantidad(sc.nextInt());
        sc.nextLine();

        System.out.println("Por favor Ingrese la Fecha de Agregado (AAAA-MM-DD):");
        String fechaStr = sc.nextLine();
        try {
            miCarrito.setFechaAgregado(Date.valueOf(fechaStr));
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido. Use AAAA-MM-DD");
            sc.close();
            return;
        }

        System.out.println("Por favor Ingrese el ID del Producto (1-10):");
        miCarrito.setProductoIdProducto(sc.nextInt());

        boolean resultado = dao.insertarCarrito(miCarrito);
        if (resultado) {
            System.out.println("El carrito de compra se guardo Correctamente");
        } else {
            System.out.println("El carrito de compra no se pudo registrar");
        }
        sc.close();
    }
}
