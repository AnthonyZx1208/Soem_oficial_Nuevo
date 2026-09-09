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
public class PruebaConsultarCategoria {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CategoriaDAO miCategoriaDAO = new CategoriaDAO();

        System.out.println("=== CONSULTAR CATEGORIA ===");
        System.out.println("Ingrese el ID de la categoria a consultar:");
        int idCategoria = sc.nextInt();

        Categoria miCategoria = miCategoriaDAO.consultarCategoria(idCategoria);
        if (miCategoria != null) {
            System.out.println("ID: " + miCategoria.getId_categoria());
            System.out.println("Descripcion: " + miCategoria.getDescripcion());
        } else {
            System.out.println("Categoria no encontrada");
        }
        sc.close();
    }
}
