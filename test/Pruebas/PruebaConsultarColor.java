package Pruebas;

import Controlador.ColoresDAO;
import Modelo.Colores;
import java.util.Scanner;

public class PruebaConsultarColor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColoresDAO miColoresDAO = new ColoresDAO();

        System.out.println("=== CONSULTAR COLOR ===");
        System.out.println("Ingrese el ID del color a consultar:");
        int idColor = sc.nextInt();

        Colores miColor = miColoresDAO.consultarColor(idColor);
        if (miColor != null) {
            System.out.println("ID: " + miColor.getIdColor());
            System.out.println("Nombre: " + miColor.getNombreColor());
            System.out.println("Codigo hexadecimal: " + miColor.getCodigoHexadecimal());
        } else {
            System.out.println("Color no encontrado");
        }
        sc.close();
    }
}
