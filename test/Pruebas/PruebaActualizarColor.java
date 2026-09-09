package Pruebas;

import Controlador.ColoresDAO;
import Modelo.Colores;
import java.util.Scanner;

public class PruebaActualizarColor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Colores miColor = new Colores();
        ColoresDAO dao = new ColoresDAO();

        System.out.println("=== ACTUALIZAR COLOR ===");
        System.out.println("Por favor ingrese el ID del color a actualizar:");
        int idActualizar = sc.nextInt();
        sc.nextLine();
        miColor.setIdColor(idActualizar);

        System.out.println("Por favor ingrese el nuevo nombre:");
        miColor.setNombreColor(sc.nextLine());

        System.out.println("Por favor ingrese el nuevo codigo hexadecimal (ej: #FF0000):");
        miColor.setCodigoHexadecimal(sc.nextLine());

        boolean respuesta = dao.actualizarColor(miColor);
        if (respuesta) {
            System.out.println("¡Color actualizado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el color. Verifique el ID enviado.");
        }
        sc.close();
    }
}
