/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.UnidadDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarUnidad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UnidadDAO dao = new UnidadDAO();

        System.out.println("=== ELIMINAR UNIDAD ===");
        System.out.println("Ingrese el ID de la Unidad a eliminar:");
        int idUnidad = sc.nextInt();

        boolean resultado = dao.eliminarUnidad(idUnidad);
        if (resultado) {
            System.out.println("La unidad se elimino Correctamente");
        } else {
            System.out.println("La unidad no se pudo eliminar");
        }
        sc.close();
    }
}
