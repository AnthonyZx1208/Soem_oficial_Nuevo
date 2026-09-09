/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.PagoDAO;
import Modelo.Pago;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaInsertarPago {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pago miPago = new Pago();
        PagoDAO dao = new PagoDAO();

        System.out.println("=== INSERTAR PAGO ===");
        System.out.println("Por favor Ingrese la Fecha del Pago (AAAA-MM-DD HH:MM:SS):");
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

        System.out.println("Por favor Ingrese el Monto del Pago:");
        miPago.setMontoPago(sc.nextFloat());
        sc.nextLine();

        System.out.println("Por favor Ingrese el ID del Metodo de Pago (1-10):");
        miPago.setMetodoDePagoIdMetodoPago(sc.nextInt());

        System.out.println("Por favor Ingrese el ID de la Cabeza de Factura (1-10):");
        miPago.setCabezaFacturaIdFactura(sc.nextInt());

        boolean resultado = dao.insertarPago(miPago);
        if (resultado) {
            System.out.println("El pago se guardo Correctamente");
        } else {
            System.out.println("El pago no se pudo registrar");
        }
        sc.close();
    }
}
