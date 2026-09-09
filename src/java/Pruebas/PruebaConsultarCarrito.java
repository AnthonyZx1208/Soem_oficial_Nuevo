/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CarritoCompraDAO;
import Modelo.CarritoCompra;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarCarrito {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarritoCompraDAO miCarritoDAO = new CarritoCompraDAO();

        System.out.println("=== CONSULTAR CARRITO DE COMPRA ===");
        System.out.println("Ingrese el ID del Carrito a consultar:");
        int idCarrito = sc.nextInt();

        CarritoCompra miCarrito = miCarritoDAO.consultarCarrito(idCarrito);
        if (miCarrito != null) {
            System.out.println("ID Carrito: " + miCarrito.getIdCarrito());
            System.out.println("Cantidad: " + miCarrito.getCantidad());
            System.out.println("Fecha Agregado: " + miCarrito.getFechaAgregado());
            System.out.println("ID Producto: " + miCarrito.getProductoIdProducto());
        } else {
            System.out.println("Carrito no encontrado");
        }
        sc.close();
    }
}
