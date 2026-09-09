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
public class PruebaActualizarTalla {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Talla miTalla = new Talla();
        TallaDAO dao = new TallaDAO();

        System.out.println("=== ACTUALIZAR TALLA ===");
        System.out.println("Por favor ingrese el ID de la talla a actualizar:");
        int idActualizar = sc.nextInt();
        sc.nextLine();
        miTalla.setIdTalla(idActualizar);

        System.out.println("Por favor ingrese la nueva Descripcion:");
        miTalla.setDescripcionTalla(sc.nextLine());

        boolean respuesta = dao.actualizarTalla(miTalla);
        if (respuesta) {
            System.out.println("¡Talla actualizada correctamente!");
        } else {
            System.out.println("No se pudo actualizar la talla. Verifique el ID enviado.");
        }
        sc.close();
    }
}
