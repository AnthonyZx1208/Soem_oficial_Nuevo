/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Controlador.CabezaFacturaDAO;
import Modelo.CabezaFactura;
import java.sql.Date;
import java.util.Scanner;

/**
 *
 * @author Aprendiz
 */
public class PruebaInsertarCabezaFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CabezaFactura miCabezaFactura = new CabezaFactura();
        CabezaFacturaDAO dao = new CabezaFacturaDAO();

        System.out.println("=== INSERTAR CABEZA DE FACTURA ===");
        System.out.println("Por favor Ingrese el Numero de la Factura:");
        miCabezaFactura.setNumeroFac(sc.nextInt());
        sc.nextLine();

        System.out.println("Por favor Ingrese la Fecha de la Factura (AAAA-MM-DD):");
        String fechaStr = sc.nextLine();
        try {
            miCabezaFactura.setFechaFactura(Date.valueOf(fechaStr));
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido. Use AAAA-MM-DD");
            sc.close();
            return;
        }

        System.out.println("Por favor Ingrese el Total de la Factura:");
        miCabezaFactura.setTotalFactura(sc.nextFloat());
        sc.nextLine();

        System.out.println("Por favor Ingrese el ID del Usuario (1-10):");
        miCabezaFactura.setUsuarioIdUsuario(sc.nextInt());

        boolean resultado = dao.insertarCabezaFactura(miCabezaFactura);
        if (resultado) {
            System.out.println("La cabeza de factura se guardo Correctamente");
        } else {
            System.out.println("La cabeza de factura no se pudo registrar");
        }
        sc.close();
    }
}
