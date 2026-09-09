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
public class PruebaActualizarPermiso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Permisos miPermiso = new Permisos();
        PermisosDAO dao = new PermisosDAO();

        System.out.println("=== ACTUALIZAR PERMISO ===");
        System.out.println("Por favor ingrese el ID del permiso a actualizar:");
        int idActualizar = sc.nextInt();
        sc.nextLine();
        miPermiso.setIdPermiso(idActualizar);

        System.out.println("Por favor ingrese el nuevo nombre:");
        miPermiso.setNombrePermiso(sc.nextLine());

        System.out.println("Por favor ingrese la nueva Descripcion:");
        miPermiso.setDescripcion(sc.nextLine());

        boolean respuesta = dao.actualizarPermiso(miPermiso);
        if (respuesta) {
            System.out.println("¡Permiso actualizado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el permiso. Verifique el ID enviado.");
        }
        sc.close();
    }
}
