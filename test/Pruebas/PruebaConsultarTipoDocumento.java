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
public class PruebaConsultarTipoDocumento {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TipoDocumentoDAO miTipoDocumentoDAO = new TipoDocumentoDAO();

        System.out.println("=== CONSULTAR TIPO DE DOCUMENTO ===");
        System.out.println("Ingrese el ID del Tipo de Documento a consultar:");
        int idTipoDocumento = sc.nextInt();

        TipoDocumento miTipoDocumento = miTipoDocumentoDAO.consultarTipoDocumento(idTipoDocumento);
        if (miTipoDocumento != null) {
            System.out.println("ID: " + miTipoDocumento.getIdTipoDocumento());
            System.out.println("Descripcion: " + miTipoDocumento.getDescripDocumento());
        } else {
            System.out.println("Tipo de Documento no encontrado");
        }
        sc.close();
    }
}
