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
public class PruebaInsertarColor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Colores miColor = new Colores();
        ColoresDAO dao = new ColoresDAO();

        System.out.println("=== INSERTAR COLOR ===");
        System.out.println("Por favor Ingrese el Codigo RGB (ej: #FF0000):");
        miColor.setCodigoRGB(sc.nextLine());

        boolean resultado = dao.insertarColor(miColor);
        if (resultado) {
            System.out.println("El color se guardo Correctamente");
        } else {
            System.out.println("El color no se pudo registrar");
        }
        sc.close();
    }
}
