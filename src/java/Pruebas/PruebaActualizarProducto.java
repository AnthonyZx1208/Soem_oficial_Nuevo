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
public class PruebaActualizarProducto {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Producto miProducto = new Producto();
        ProductoDAO dao = new ProductoDAO();

        System.out.println("=== ACTUALIZAR PRODUCTO ===");
        System.out.println("Por favor ingrese el ID del producto a actualizar:");
        int idActualizar = sc.nextInt();
        sc.nextLine();
        miProducto.setId_producto(idActualizar);

        System.out.println("Por favor ingrese el nuevo Nombre:");
        miProducto.setNombre_producto(sc.nextLine());

        System.out.println("Por favor ingrese la nueva Descripcion:");
        miProducto.setDescripcion(sc.nextLine());

        System.out.println("Por favor ingrese el nuevo Stock:");
        miProducto.setCantidad_stock(sc.nextInt());
        sc.nextLine();

        System.out.println("Por favor ingrese el nuevo Precio:");
        miProducto.setPrecio_producto(sc.nextFloat());
        sc.nextLine();

        System.out.println("Por favor ingrese el nuevo ID de Categoria (1-10):");
        miProducto.setCategoria_id_categoria(sc.nextInt());


        boolean respuesta = dao.actualizarProducto(miProducto);
        if (respuesta) {
            System.out.println("¡Producto actualizado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el producto. Verifique el ID enviado.");
        }
        sc.close();
    }
}
