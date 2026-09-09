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
public class PruebaInsertarRolesHasPermisos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesHasPermisos miRelacion = new RolesHasPermisos();
        RolesHasPermisosDAO dao = new RolesHasPermisosDAO();

        System.out.println("=== INSERTAR ROL-PERMISO (relacion) ===");
        System.out.println("Por favor Ingrese el ID del Rol (1-10):");
        miRelacion.setRolesIdRol(sc.nextInt());

        System.out.println("Por favor Ingrese el ID del Permiso (1-10):");
        miRelacion.setPermisosIdPermisos(sc.nextInt());

        boolean resultado = dao.insertarRolesHasPermisos(miRelacion);
        if (resultado) {
            System.out.println("La relacion Rol-Permiso se guardo Correctamente");
        } else {
            System.out.println("La relacion Rol-Permiso no se pudo registrar");
        }
        sc.close();
    }
}
