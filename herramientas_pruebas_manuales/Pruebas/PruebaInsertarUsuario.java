/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.UsuarioDAO;
import Modelo.Usuario;
import java.sql.Date;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaInsertarUsuario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuario miUsuario = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();

        System.out.println("=== INSERTAR USUARIO ===");
        System.out.println("Por Favor Ingrese su Nombre:");
        miUsuario.setNombreUsuario(sc.nextLine());

        System.out.println("Por favor Ingrese su Apellido:");
        miUsuario.setApellidoUsuario(sc.nextLine());

        System.out.println("Por favor Ingrese su Numero de Documento:");
        miUsuario.setNumeroDocumento(sc.nextLine());

        System.out.println("Por favor Ingrese su Numero de Telefono:");
        miUsuario.setTelefono(sc.nextLine());

        System.out.println("Por favor Ingrese su Correo:");
        miUsuario.setCorreo(sc.nextLine());

        System.out.println("Por favor Ingrese su Clave (Contraseña):");
        miUsuario.setContrasena(sc.nextLine());

        System.out.println("Por favor Ingrese su Direccion:");
        miUsuario.setDireccion(sc.nextLine());

        System.out.println("Por favor Ingrese su Fecha de Nacimiento (AAAA-MM-DD):");
        String fechaNacStr = sc.nextLine();
        try {
            miUsuario.setFechaNacimiento(Date.valueOf(fechaNacStr));
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido. Use AAAA-MM-DD");
            sc.close();
            return;
        }

        System.out.println("Por favor Ingrese su Fecha de Vencimiento (AAAA-MM-DD):");
        String fechaVenStr = sc.nextLine();
        try {
            miUsuario.setFechaVencimiento(Date.valueOf(fechaVenStr));
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido. Use AAAA-MM-DD");
            sc.close();
            return;
        }

        System.out.println("Por favor Ingrese la Autorizacion de Datos (Si/No):");
        miUsuario.setAutorizacionDatos(sc.nextLine());

        System.out.println("Por favor Ingrese el ID del Rol (1-10):");
        miUsuario.setRolesIdRol(sc.nextInt());

        System.out.println("Por favor Ingrese el ID del Producto (1-10):");
        miUsuario.setProductoIdProducto(sc.nextInt());

        System.out.println("Por favor Ingrese el ID del Tipo de Documento (1-10):");
        miUsuario.setTipoDocumentoIdTipoDocumento(sc.nextInt());

        boolean resultado = dao.insertarUsuario(miUsuario);
        if (resultado) {
            System.out.println("El usuario se guardo Correctamente");
        } else {
            System.out.println("El usuario no se pudo registrar");
        }
        sc.close();
    }
}
