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
public class PruebaInsertarTipoDocumento {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TipoDocumento miTipoDocumento = new TipoDocumento();
        TipoDocumentoDAO dao = new TipoDocumentoDAO();

        System.out.println("=== INSERTAR TIPO DE DOCUMENTO ===");
        System.out.println("Por favor Ingrese la Descripcion del Tipo de Documento:");
        miTipoDocumento.setDescripDocumento(sc.nextLine());

        boolean resultado = dao.insertarTipoDocumento(miTipoDocumento);
        if (resultado) {
            System.out.println("El tipo de documento se guardo Correctamente");
        } else {
            System.out.println("El tipo de documento no se pudo registrar");
        }
        sc.close();
    }
}
