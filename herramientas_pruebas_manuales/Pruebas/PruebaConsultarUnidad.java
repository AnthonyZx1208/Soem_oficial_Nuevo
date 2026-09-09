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
public class PruebaConsultarUnidad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UnidadDAO miUnidadDAO = new UnidadDAO();

        System.out.println("=== CONSULTAR UNIDAD ===");
        System.out.println("Ingrese el ID de la Unidad a consultar:");
        int idUnidad = sc.nextInt();

        Unidad miUnidad = miUnidadDAO.consultarUnidad(idUnidad);
        if (miUnidad != null) {
            System.out.println("ID: " + miUnidad.getIdUnidad());
            System.out.println("Descripcion: " + miUnidad.getDescripcionUnidad());
        } else {
            System.out.println("Unidad no encontrada");
        }
        sc.close();
    }
}
