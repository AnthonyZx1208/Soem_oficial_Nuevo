/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.RolesHasPermisosDAO;
import Modelo.RolesHasPermisos;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaActualizarRolesHasPermisos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesHasPermisos miRelacion = new RolesHasPermisos();
        RolesHasPermisosDAO dao = new RolesHasPermisosDAO();

        System.out.println("=== ACTUALIZAR ROL-PERMISO (relacion) ===");
        System.out.println("--- Nuevos valores ---");
        System.out.println("Ingrese el nuevo ID del Rol:");
        miRelacion.setRolesIdRol(sc.nextInt());

        System.out.println("Ingrese el nuevo ID del Permiso:");
        miRelacion.setPermisosIdPermisos(sc.nextInt());

        System.out.println("--- Valores antiguos (para ubicar el registro) ---");
        System.out.println("Ingrese el ID del Rol antiguo:");
        int idRolAntiguo = sc.nextInt();

        System.out.println("Ingrese el ID del Permiso antiguo:");
        int idPermisoAntiguo = sc.nextInt();

        boolean resultado = dao.actualizarRolesHasPermisos(miRelacion, idRolAntiguo, idPermisoAntiguo);
        if (resultado) {
            System.out.println("La relacion Rol-Permiso se actualizo Correctamente");
        } else {
            System.out.println("La relacion Rol-Permiso no se pudo actualizar");
        }
        sc.close();
    }
}
