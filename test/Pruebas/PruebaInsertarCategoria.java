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
public class PruebaInsertarCategoria {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Categoria miCategoria = new Categoria();
        CategoriaDAO dao = new CategoriaDAO();

        System.out.println("=== INSERTAR CATEGORIA ===");
        System.out.println("Por favor Ingrese la Descripcion de la Categoria:");
        miCategoria.setDescripcion(sc.nextLine());

        boolean resultado = dao.insertarCategoria(miCategoria);
        if (resultado) {
            System.out.println("La categoria se guardo Correctamente");
        } else {
            System.out.println("La categoria no se pudo registrar");
        }
        sc.close();
    }
}
