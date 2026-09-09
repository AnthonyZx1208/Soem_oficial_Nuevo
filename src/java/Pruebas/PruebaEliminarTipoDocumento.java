/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.TipoDocumentoDAO;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaEliminarTipoDocumento {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TipoDocumentoDAO dao = new TipoDocumentoDAO();

        System.out.println("=== ELIMINAR TIPO DE DOCUMENTO ===");
        System.out.println("Ingrese el ID del Tipo de Documento a eliminar:");
        int idTipoDocumento = sc.nextInt();

        boolean resultado = dao.eliminarTipoDocumento(idTipoDocumento);
        if (resultado) {
            System.out.println("El tipo de documento se elimino Correctamente");
        } else {
            System.out.println("El tipo de documento no se pudo eliminar");
        }
        sc.close();
    }
}
