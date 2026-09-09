package Pruebas;

import Controlador.ColoresDAO;
import Modelo.Colores;
import java.util.Scanner;

public class PruebaInsertarColor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Colores miColor = new Colores();
        ColoresDAO dao = new ColoresDAO();

        System.out.println("=== INSERTAR COLOR ===");
        System.out.println("Por favor Ingrese el nombre del color (ej: Negro):");
        miColor.setNombreColor(sc.nextLine());

        System.out.println("Por favor Ingrese el codigo hexadecimal (ej: #000000):");
        miColor.setCodigoHexadecimal(sc.nextLine());

        boolean resultado = dao.insertarColor(miColor);
        if (resultado) {
            System.out.println("El color se guardo Correctamente");
        } else {
            System.out.println("El color no se pudo registrar");
        }
        sc.close();
    }
}
