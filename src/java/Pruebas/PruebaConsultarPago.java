/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.PagoDAO;
import Modelo.Pago;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PagoDAO miPagoDAO = new PagoDAO();

        System.out.println("=== CONSULTAR PAGO ===");
        System.out.println("Ingrese el ID del pago a consultar:");
        int idPago = sc.nextInt();

        Pago miPago = miPagoDAO.consultarPago(idPago);
        if (miPago != null) {
            System.out.println("ID: " + miPago.getIdPago());
            System.out.println("Fecha de Pago: " + miPago.getFechaPago());
            System.out.println("Monto: " + miPago.getMontoPago());
            System.out.println("Metodo de Pago ID: " + miPago.getMetodoDePagoIdMetodoPago());
            System.out.println("Cabeza de Factura ID: " + miPago.getCabezaFacturaIdFactura());
        } else {
            System.out.println("Pago no encontrado");
        }
        sc.close();
    }
}
