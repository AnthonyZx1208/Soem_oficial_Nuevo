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
public class PruebaInsertarProducto {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Producto miProducto = new Producto();
        ProductoDAO dao = new ProductoDAO();

        System.out.println("=== INSERTAR PRODUCTO ===");
        System.out.println("Por favor Ingrese el Nombre del Producto:");
        miProducto.setNombre_producto(sc.nextLine());

        System.out.println("Por favor Ingrese la Descripcion del Producto:");
        miProducto.setDescripcion(sc.nextLine());

        System.out.println("Por favor Ingrese el Stock:");
        miProducto.setCantidad_stock(sc.nextInt());
        sc.nextLine();

        System.out.println("Por favor Ingrese el Precio del Producto:");
        miProducto.setPrecio_producto(sc.nextFloat());
        sc.nextLine();

        System.out.println("Por favor Ingrese el ID de la Categoria (1-10):");
        miProducto.setCategoria_id_categoria(sc.nextInt());

        miProducto.setEstado("Activo");

        boolean resultado = dao.insertarProducto(miProducto);
        if (resultado) {
            System.out.println("El producto se guardo Correctamente");
        } else {
            System.out.println("El producto no se pudo registrar");
        }
        sc.close();
    }
}
