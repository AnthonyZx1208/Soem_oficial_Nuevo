/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.MetodoPagoDAO;
import Modelo.MetodoPago;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaConsultarMetodoPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MetodoPagoDAO miMetodoPagoDAO = new MetodoPagoDAO();

        System.out.println("=== CONSULTAR METODO DE PAGO ===");
        System.out.println("Ingrese el ID del metodo de pago a consultar:");
        int idMetodoPago = sc.nextInt();

        MetodoPago miMetodoPago = miMetodoPagoDAO.consultarMetodoPago(idMetodoPago);
        if (miMetodoPago != null) {
            System.out.println("ID: " + miMetodoPago.getIdMetodoPago());
            System.out.println("Descripcion: " + miMetodoPago.getDescripcionMetodoPago());
        } else {
            System.out.println("Metodo de pago no encontrado");
        }
        sc.close();
    }
}
