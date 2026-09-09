/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.PermisosDAO;
import Modelo.Permisos;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarPermiso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PermisosDAO miPermisosDAO = new PermisosDAO();

        System.out.println("=== CONSULTAR PERMISO ===");
        System.out.println("Ingrese el ID del permiso a consultar:");
        int idPermiso = sc.nextInt();

        Permisos miPermiso = miPermisosDAO.consultarPermiso(idPermiso);
        if (miPermiso != null) {
            System.out.println("ID: " + miPermiso.getIdPermisos());
            System.out.println("Descripcion: " + miPermiso.getDescripPermisos());
        } else {
            System.out.println("Permiso no encontrado");
        }
        sc.close();
    }
}
