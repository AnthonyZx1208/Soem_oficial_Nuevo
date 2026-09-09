/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.PagoDAO;
import Modelo.Pago;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaModificarPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pago miPago = new Pago();
        PagoDAO dao = new PagoDAO();

        System.out.println("=== MODIFICAR PAGO ===");
        System.out.println("Por favor ingrese el ID del pago a modificar:");
        int idModificar = sc.nextInt();
        sc.nextLine();
        miPago.setIdPago(idModificar);

        System.out.println("Por favor ingrese la nueva Fecha (AAAA-MM-DD HH:MM:SS):");
        String fechaStr = sc.nextLine();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fecha = sdf.parse(fechaStr);
            miPago.setFechaPago(fecha);
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido. Use AAAA-MM-DD HH:MM:SS");
            sc.close();
            return;
        }

        System.out.println("Por favor ingrese el nuevo Monto:");
        miPago.setMontoPago(sc.nextFloat());
        sc.nextLine();

        System.out.println("Por favor ingrese el nuevo ID del Metodo de Pago (1-10):");
        miPago.setMetodoDePagoIdMetodoPago(sc.nextInt());

        System.out.println("Por favor ingrese el nuevo ID de la Cabeza de Factura (1-10):");
        miPago.setCabezaFacturaIdFactura(sc.nextInt());

        boolean respuesta = dao.modificarPago(miPago);
        if (respuesta) {
            System.out.println("¡Pago modificado correctamente!");
        } else {
            System.out.println("No se pudo modificar el pago. Verifique el ID enviado.");
        }
        sc.close();
    }
}
