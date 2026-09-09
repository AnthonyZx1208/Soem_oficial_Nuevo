/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.UnidadDAO;
import Modelo.Unidad;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaActualizarUnidad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Unidad miUnidad = new Unidad();
        UnidadDAO dao = new UnidadDAO();

        System.out.println("=== ACTUALIZAR UNIDAD ===");
        System.out.println("Ingrese el ID de la Unidad a actualizar:");
        miUnidad.setIdUnidad(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese la nueva Descripcion de la Unidad:");
        miUnidad.setDescripcionUnidad(sc.nextLine());

        boolean resultado = dao.actualizarUnidad(miUnidad);
        if (resultado) {
            System.out.println("La unidad se actualizo Correctamente");
        } else {
            System.out.println("La unidad no se pudo actualizar");
        }
        sc.close();
    }
}
