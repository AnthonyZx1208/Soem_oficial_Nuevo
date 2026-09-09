/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.TallaDAO;
import Modelo.Talla;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarTalla {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TallaDAO miTallaDAO = new TallaDAO();

        System.out.println("=== CONSULTAR TALLA ===");
        System.out.println("Ingrese el ID de la talla a consultar:");
        int idTalla = sc.nextInt();

        Talla miTalla = miTallaDAO.consultarTalla(idTalla);
        if (miTalla != null) {
            System.out.println("ID: " + miTalla.getIdTalla());
            System.out.println("Descripcion: " + miTalla.getDescripcionTalla());
        } else {
            System.out.println("Talla no encontrada");
        }
        sc.close();
    }
}
