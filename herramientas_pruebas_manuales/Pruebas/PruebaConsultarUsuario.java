/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.UsuarioDAO;
import Modelo.Usuario;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarUsuario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioDAO miUsuarioDAO = new UsuarioDAO();

        System.out.println("=== CONSULTAR USUARIO ===");
        System.out.println("Ingrese el Numero de Documento del usuario a consultar:");
        String numeroDocumento = sc.nextLine();

        Usuario miUsuario = miUsuarioDAO.consultarUsuario(numeroDocumento);
        if (miUsuario != null) {
            System.out.println("ID: " + miUsuario.getIdUsuario());
            System.out.println("Nombre: " + miUsuario.getNombreUsuario());
            System.out.println("Apellido: " + miUsuario.getApellidoUsuario());
            System.out.println("Numero de Documento: " + miUsuario.getNumeroDocumento());
            System.out.println("Telefono: " + miUsuario.getTelefono());
            System.out.println("Correo: " + miUsuario.getCorreo());
            System.out.println("Direccion: " + miUsuario.getDireccion());
            System.out.println("Fecha Nacimiento: " + miUsuario.getFechaNacimiento());
            System.out.println("Fecha Vencimiento: " + miUsuario.getFechaVencimiento());
            System.out.println("Autorizacion Datos: " + miUsuario.getAutorizacionDatos());
            System.out.println("ID Rol: " + miUsuario.getRolesIdRol());
            System.out.println("ID Producto: " + miUsuario.getProductoIdProducto());
            System.out.println("ID Tipo Documento: " + miUsuario.getTipoDocumentoIdTipoDocumento());
        } else {
            System.out.println("Usuario no encontrado");
        }
        sc.close();
    }
}
