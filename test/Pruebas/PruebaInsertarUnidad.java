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
public class PruebaInsertarUnidad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Unidad miUnidad = new Unidad();
        UnidadDAO dao = new UnidadDAO();

        System.out.println("=== INSERTAR UNIDAD ===");
        System.out.println("Por favor Ingrese la Descripcion de la Unidad:");
        miUnidad.setDescripcionUnidad(sc.nextLine());

        boolean resultado = dao.insertarUnidad(miUnidad);
        if (resultado) {
            System.out.println("La unidad se guardo Correctamente");
        } else {
            System.out.println("La unidad no se pudo registrar");
        }
        sc.close();
    }
}
