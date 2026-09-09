/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.ProductoDAO;
import Modelo.Producto;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarProducto {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductoDAO miProductoDAO = new ProductoDAO();

        System.out.println("=== CONSULTAR PRODUCTO ===");
        System.out.println("Ingrese el ID del producto a consultar:");
        int idProducto = sc.nextInt();

        Producto miProducto = miProductoDAO.consultarProducto(idProducto);
        if (miProducto != null) {
            System.out.println("ID: " + miProducto.getId_producto());
            System.out.println("Nombre: " + miProducto.getNombre_producto());
            System.out.println("Descripcion: " + miProducto.getDescripcion());
            System.out.println("Stock: " + miProducto.getCantidad_stock());
            System.out.println("Precio: " + miProducto.getPrecio_producto());
            System.out.println("Categoria ID: " + miProducto.getCategoria_id_categoria());
            System.out.println("Precio Oferta: " + miProducto.getPrecio_oferta());
            System.out.println("Estado: " + miProducto.getEstado());
        } else {
            System.out.println("Producto no encontrado");
        }
        sc.close();
    }
}
