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
public class PruebaInsertarPermiso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Permisos miPermiso = new Permisos();
        PermisosDAO dao = new PermisosDAO();

        System.out.println("=== INSERTAR PERMISO ===");
        System.out.println("Por favor Ingrese el nombre del Permiso (ej: Gestionar productos):");
        miPermiso.setNombrePermiso(sc.nextLine());

        System.out.println("Por favor Ingrese la Descripcion del Permiso:");
        miPermiso.setDescripcion(sc.nextLine());

        boolean resultado = dao.insertarPermiso(miPermiso);
        if (resultado) {
            System.out.println("El permiso se guardo Correctamente");
        } else {
            System.out.println("El permiso no se pudo registrar");
        }
        sc.close();
    }
}
