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
public class PruebaInsertarTalla {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Talla miTalla = new Talla();
        TallaDAO dao = new TallaDAO();

        System.out.println("=== INSERTAR TALLA ===");
        System.out.println("Por favor Ingrese la Descripcion de la Talla:");
        miTalla.setDescripcionTalla(sc.nextLine());

        boolean resultado = dao.insertarTalla(miTalla);
        if (resultado) {
            System.out.println("La talla se guardo Correctamente");
        } else {
            System.out.println("La talla no se pudo registrar");
        }
        sc.close();
    }
}
