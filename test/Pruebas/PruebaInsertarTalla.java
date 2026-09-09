package Pruebas;

import Controlador.TallaDAO;
import Modelo.Talla;
import java.util.Scanner;

public class PruebaInsertarTalla {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Talla miTalla = new Talla();
        TallaDAO dao = new TallaDAO();

        System.out.println("=== INSERTAR TALLA ===");
        System.out.println("Por favor Ingrese el nombre de la Talla (ej: M):");
        miTalla.setNombreTalla(sc.nextLine());

        boolean resultado = dao.insertarTalla(miTalla);
        if (resultado) {
            System.out.println("La talla se guardo Correctamente");
        } else {
            System.out.println("La talla no se pudo registrar");
        }
        sc.close();
    }
}
