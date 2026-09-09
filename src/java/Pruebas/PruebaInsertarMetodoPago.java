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
public class PruebaInsertarMetodoPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MetodoPago miMetodoPago = new MetodoPago();
        MetodoPagoDAO dao = new MetodoPagoDAO();

        System.out.println("=== INSERTAR METODO DE PAGO ===");
        System.out.println("Por favor Ingrese la Descripcion del Metodo de Pago:");
        miMetodoPago.setDescripcionMetodoPago(sc.nextLine());

        boolean resultado = dao.insertarMetodoPago(miMetodoPago);
        if (resultado) {
            System.out.println("El metodo de pago se guardo Correctamente");
        } else {
            System.out.println("El metodo de pago no se pudo registrar");
        }
        sc.close();
    }
}
