/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.RolesDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarRol {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesDAO dao = new RolesDAO();

        System.out.println("=== ELIMINAR ROL ===");
        System.out.println("Ingrese el ID del Rol a eliminar:");
        int idRol = sc.nextInt();

        boolean resultado = dao.eliminarRol(idRol);
        if (resultado) {
            System.out.println("El rol se elimino Correctamente");
        } else {
            System.out.println("El rol no se pudo eliminar");
        }
        sc.close();
    }
}
