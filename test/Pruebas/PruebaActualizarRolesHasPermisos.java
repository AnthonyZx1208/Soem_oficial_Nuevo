package Pruebas;

import Controlador.RolesHasPermisosDAO;
import Modelo.RolesHasPermisos;
import java.util.Scanner;

public class PruebaActualizarRolesHasPermisos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesHasPermisosDAO dao = new RolesHasPermisosDAO();

        System.out.println("=== MOVER UNA RELACION ROL-PERMISO ===");
        System.out.println("Roles_Has_Permisos es una tabla puente sin datos propios (solo la");
        System.out.println("llave compuesta Rol+Permiso), asi que 'actualizar' equivale a borrar");
        System.out.println("la relacion antigua e insertar la nueva.");
        System.out.println("--- Valores antiguos (para ubicar el registro) ---");
        System.out.println("Ingrese el ID del Rol antiguo:");
        int idRolAntiguo = sc.nextInt();

        System.out.println("Ingrese el ID del Permiso antiguo:");
        int idPermisoAntiguo = sc.nextInt();

        System.out.println("--- Nuevos valores ---");
        System.out.println("Ingrese el nuevo ID del Rol:");
        int idRolNuevo = sc.nextInt();

        System.out.println("Ingrese el nuevo ID del Permiso:");
        int idPermisoNuevo = sc.nextInt();

        dao.eliminarRolesHasPermisos(idRolAntiguo, idPermisoAntiguo);

        RolesHasPermisos nueva = new RolesHasPermisos();
        nueva.setRolesIdRol(idRolNuevo);
        nueva.setPermisosIdPermisos(idPermisoNuevo);

        boolean resultado = dao.insertarRolesHasPermisos(nueva);
        if (resultado) {
            System.out.println("La relacion Rol-Permiso se actualizo Correctamente");
        } else {
            System.out.println("La relacion Rol-Permiso no se pudo actualizar");
        }
        sc.close();
    }
}
