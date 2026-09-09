/*
 * Prueba Actualizar Usuario - Adaptada a la base de datos Soem_Oficial
 */
package Pruebas;

import Controlador.UsuarioDAO;
import Modelo.Usuario;
import java.sql.Date;
import java.util.Scanner;

public class PruebaActualizarUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuario miUsuario = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();

        System.out.println("Por favor ingrese el ID del usuario a actualizar:");
        int actualizar = sc.nextInt();
        sc.nextLine();
        miUsuario.setIdUsuario(actualizar);

        System.out.println("Por favor ingrese el nombre para actualizar:");
        miUsuario.setNombreUsuario(sc.nextLine());

        System.out.println("Por favor ingrese el apellido para actualizar:");
        miUsuario.setApellidoUsuario(sc.nextLine());

        System.out.println("Por favor ingrese el numero de documento para actualizar:");
        miUsuario.setNumeroDocumento(sc.nextLine());

        System.out.println("Por favor ingrese el telefono para actualizar:");
        miUsuario.setTelefono(sc.nextLine());

        System.out.println("Por favor ingrese el correo para actualizar:");
        miUsuario.setCorreo(sc.nextLine());

        System.out.println("Por favor ingrese la contraseña para actualizar:");
        miUsuario.setContrasena(sc.nextLine());

        System.out.println("Por favor ingrese la direccion para actualizar:");
        miUsuario.setDireccion(sc.nextLine());

        System.out.println("Por favor ingrese la fecha de nacimiento para actualizar (AAAA-MM-DD):");
        miUsuario.setFechaNacimiento(Date.valueOf(sc.nextLine()));

        System.out.println("Por favor ingrese la fecha de vencimiento para actualizar (AAAA-MM-DD):");
        miUsuario.setFechaVencimiento(Date.valueOf(sc.nextLine()));

        System.out.println("¿Autoriza el tratamiento de datos? (Si/No):");
        miUsuario.setAutorizacionDatos(sc.nextLine());

        System.out.println("Por favor ingrese el ID de Rol para actualizar:");
        miUsuario.setRolesIdRol(sc.nextInt());

        System.out.println("Por favor ingrese el ID de Producto para actualizar:");
        miUsuario.setProductoIdProducto(sc.nextInt());

        System.out.println("Por favor ingrese el ID de Tipo de Documento para actualizar:");
        miUsuario.setTipoDocumentoIdTipoDocumento(sc.nextInt());

        boolean respuesta = dao.actualizarUsuario(miUsuario);

        if (respuesta) {
            System.out.println("¡Usuario actualizado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el usuario. Verifique el ID enviado.");
        }
        sc.close();
    }
}
