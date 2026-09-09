/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.RolesHasPermisosDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarRolesHasPermisos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesHasPermisosDAO dao = new RolesHasPermisosDAO();

        System.out.println("=== ELIMINAR ROL-PERMISO (relacion) ===");
        System.out.println("Ingrese el ID del Rol:");
        int idRol = sc.nextInt();

        System.out.println("Ingrese el ID del Permiso:");
        int idPermiso = sc.nextInt();

        boolean resultado = dao.eliminarRolesHasPermisos(idRol, idPermiso);
        if (resultado) {
            System.out.println("La relacion Rol-Permiso se elimino Correctamente");
        } else {
            System.out.println("La relacion Rol-Permiso no se pudo eliminar");
        }
        sc.close();
    }
}
