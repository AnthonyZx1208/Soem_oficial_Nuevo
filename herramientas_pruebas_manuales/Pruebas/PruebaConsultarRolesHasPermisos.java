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
public class PruebaConsultarRolesHasPermisos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesHasPermisosDAO dao = new RolesHasPermisosDAO();

        System.out.println("=== CONSULTAR ROL-PERMISO (relacion) ===");
        System.out.println("Ingrese el ID del Rol:");
        int idRol = sc.nextInt();

        System.out.println("Ingrese el ID del Permiso:");
        int idPermiso = sc.nextInt();

        RolesHasPermisos miRelacion = dao.consultarRolesHasPermisos(idRol, idPermiso);
        if (miRelacion != null) {
            System.out.println("ID Rol: " + miRelacion.getRolesIdRol());
            System.out.println("ID Permiso: " + miRelacion.getPermisosIdPermisos());
        } else {
            System.out.println("La relacion Rol-Permiso no fue encontrada");
        }
        sc.close();
    }
}
