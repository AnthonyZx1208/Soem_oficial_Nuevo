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
public class PruebaConsultarRol {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesDAO miRolesDAO = new RolesDAO();

        System.out.println("=== CONSULTAR ROL ===");
        System.out.println("Ingrese el ID del Rol a consultar:");
        int idRol = sc.nextInt();

        Roles miRol = miRolesDAO.consultarRol(idRol);
        if (miRol != null) {
            System.out.println("ID: " + miRol.getIdRol());
            System.out.println("Nombre: " + miRol.getNombreRol());
            System.out.println("Descripcion: " + miRol.getDescripRol());
        } else {
            System.out.println("Rol no encontrado");
        }
        sc.close();
    }
}
