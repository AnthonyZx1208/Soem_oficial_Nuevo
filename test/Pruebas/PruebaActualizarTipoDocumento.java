/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.TipoDocumentoDAO;
import Modelo.TipoDocumento;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaActualizarTipoDocumento {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TipoDocumento miTipoDocumento = new TipoDocumento();
        TipoDocumentoDAO dao = new TipoDocumentoDAO();

        System.out.println("=== ACTUALIZAR TIPO DE DOCUMENTO ===");
        System.out.println("Ingrese el ID del Tipo de Documento a actualizar:");
        miTipoDocumento.setIdTipoDocumento(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese la nueva Descripcion del Tipo de Documento:");
        miTipoDocumento.setDescripDocumento(sc.nextLine());

        boolean resultado = dao.actualizarTipoDocumento(miTipoDocumento);
        if (resultado) {
            System.out.println("El tipo de documento se actualizo Correctamente");
        } else {
            System.out.println("El tipo de documento no se pudo actualizar");
        }
        sc.close();
    }
}
