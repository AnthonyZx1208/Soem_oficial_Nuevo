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
public class PruebaInsertarRol {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Roles miRol = new Roles();
        RolesDAO dao = new RolesDAO();

        System.out.println("=== INSERTAR ROL ===");
        System.out.println("Por favor Ingrese el Nombre del Rol:");
        miRol.setNombreRol(sc.nextLine());

        System.out.println("Por favor Ingrese la Descripcion del Rol:");
        miRol.setDescripcion(sc.nextLine());

        boolean resultado = dao.insertarRol(miRol);
        if (resultado) {
            System.out.println("El rol se guardo Correctamente");
        } else {
            System.out.println("El rol no se pudo registrar");
        }
        sc.close();
    }
}
