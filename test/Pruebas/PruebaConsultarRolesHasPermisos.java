package Pruebas;

import Controlador.RolesHasPermisosDAO;
import Modelo.RolesHasPermisos;
import java.util.List;
import java.util.Scanner;

public class PruebaConsultarRolesHasPermisos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RolesHasPermisosDAO dao = new RolesHasPermisosDAO();

        System.out.println("=== CONSULTAR ROL-PERMISO (relacion) ===");
        System.out.println("Ingrese el ID del Rol:");
        int idRol = sc.nextInt();

        System.out.println("Ingrese el ID del Permiso:");
        int idPermiso = sc.nextInt();

        List<RolesHasPermisos> permisosDelRol = dao.listarPorRol(idRol);
        boolean encontrada = permisosDelRol.stream().anyMatch(r -> r.getPermisosIdPermisos() == idPermiso);

        if (encontrada) {
            System.out.println("El rol " + idRol + " SI tiene el permiso " + idPermiso);
        } else {
            System.out.println("La relacion Rol-Permiso no fue encontrada");
        }
        sc.close();
    }
}
