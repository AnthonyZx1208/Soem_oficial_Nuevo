/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.ColoresDAO;
import Modelo.Colores;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarColor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColoresDAO miColoresDAO = new ColoresDAO();

        System.out.println("=== CONSULTAR COLOR ===");
        System.out.println("Ingrese el ID del color a consultar:");
        int idColor = sc.nextInt();

        Colores miColor = miColoresDAO.consultarColor(idColor);
        if (miColor != null) {
            System.out.println("ID: " + miColor.getIdNombreColor());
            System.out.println("Codigo RGB: " + miColor.getCodigoRGB());
        } else {
            System.out.println("Color no encontrado");
        }
        sc.close();
    }
}
