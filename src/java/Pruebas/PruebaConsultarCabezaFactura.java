/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CabezaFacturaDAO;
import Modelo.CabezaFactura;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarCabezaFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CabezaFacturaDAO miCabezaFacturaDAO = new CabezaFacturaDAO();

        System.out.println("=== CONSULTAR CABEZA DE FACTURA ===");
        System.out.println("Ingrese el ID de la Cabeza de Factura a consultar:");
        int idFactura = sc.nextInt();

        CabezaFactura miCabezaFactura = miCabezaFacturaDAO.consultarCabezaFactura(idFactura);
        if (miCabezaFactura != null) {
            System.out.println("ID Factura: " + miCabezaFactura.getIdFactura());
            System.out.println("Numero: " + miCabezaFactura.getNumeroFac());
            System.out.println("Fecha: " + miCabezaFactura.getFechaFactura());
            System.out.println("Total: " + miCabezaFactura.getTotalFactura());
            System.out.println("ID Usuario: " + miCabezaFactura.getUsuarioIdUsuario());
        } else {
            System.out.println("Cabeza de Factura no encontrada");
        }
        sc.close();
    }
}
