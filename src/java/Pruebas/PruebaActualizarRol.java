/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.RolesDAO;
import Modelo.Roles;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaActualizarRol {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Roles miRol = new Roles();
        RolesDAO dao = new RolesDAO();

        System.out.println("=== ACTUALIZAR ROL ===");
        System.out.println("Ingrese el ID del Rol a actualizar:");
        miRol.setIdRol(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese el nuevo Nombre del Rol:");
        miRol.setNombreRol(sc.nextLine());

        System.out.println("Ingrese la nueva Descripcion del Rol:");
        miRol.setDescripRol(sc.nextLine());

        boolean resultado = dao.actualizarRol(miRol);
        if (resultado) {
            System.out.println("El rol se actualizo Correctamente");
        } else {
            System.out.println("El rol no se pudo actualizar");
        }
        sc.close();
    }
}
