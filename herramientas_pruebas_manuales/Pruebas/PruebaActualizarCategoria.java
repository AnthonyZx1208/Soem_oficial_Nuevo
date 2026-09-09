/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CategoriaDAO;
import Modelo.Categoria;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaActualizarCategoria {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Categoria miCategoria = new Categoria();
        CategoriaDAO dao = new CategoriaDAO();

        System.out.println("=== ACTUALIZAR CATEGORIA ===");
        System.out.println("Por favor ingrese el ID de la categoria a actualizar:");
        int idActualizar = sc.nextInt();
        sc.nextLine();
        miCategoria.setId_categoria(idActualizar);

        System.out.println("Por favor ingrese la nueva Descripcion:");
        miCategoria.setDescripcion(sc.nextLine());

        boolean respuesta = dao.actualizarCategoria(miCategoria);
        if (respuesta) {
            System.out.println("¡Categoria actualizada correctamente!");
        } else {
            System.out.println("No se pudo actualizar la categoria. Verifique el ID enviado.");
        }
        sc.close();
    }
}
