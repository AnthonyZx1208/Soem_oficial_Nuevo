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
public class PruebaActualizarMetodoPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MetodoPago miMetodoPago = new MetodoPago();
        MetodoPagoDAO dao = new MetodoPagoDAO();

        System.out.println("=== ACTUALIZAR METODO DE PAGO ===");
        System.out.println("Por favor ingrese el ID del metodo de pago a actualizar:");
        int idActualizar = sc.nextInt();
        sc.nextLine();
        miMetodoPago.setIdMetodoPago(idActualizar);

        System.out.println("Por favor ingrese la nueva Descripcion:");
        miMetodoPago.setDescripcionMetodoPago(sc.nextLine());

        boolean respuesta = dao.actualizarMetodoPago(miMetodoPago);
        if (respuesta) {
            System.out.println("¡Metodo de pago actualizado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el metodo de pago. Verifique el ID enviado.");
        }
        sc.close();
    }
}
