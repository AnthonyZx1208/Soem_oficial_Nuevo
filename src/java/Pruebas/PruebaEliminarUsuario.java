/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.UsuarioDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarUsuario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioDAO usuarioDao = new UsuarioDAO();

        System.out.println("=== ELIMINAR USUARIO ===");
        System.out.println("Ingrese el ID del usuario a eliminar:");
        int idEliminar = sc.nextInt();

        boolean resultado = usuarioDao.eliminarUsuario(idEliminar);
        if (resultado) {
            System.out.println("El usuario con ID " + idEliminar + " fue eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el usuario. Verifica que el ID existe en la base de datos.");
        }
        sc.close();
    }
}
