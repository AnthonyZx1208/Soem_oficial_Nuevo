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
public class PruebaActualizarCabezaFactura {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CabezaFactura miCabezaFactura = new CabezaFactura();
        CabezaFacturaDAO dao = new CabezaFacturaDAO();

        System.out.println("=== ACTUALIZAR CABEZA DE FACTURA ===");
        System.out.println("Ingrese el ID de la Cabeza de Factura a actualizar:");
        miCabezaFactura.setIdFactura(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese el nuevo Numero de la Factura:");
        miCabezaFactura.setNumeroFac(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese la nueva Fecha de la Factura (AAAA-MM-DD):");
        String fechaStr = sc.nextLine();
        try {
            miCabezaFactura.setFechaFactura(Date.valueOf(fechaStr));
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido. Use AAAA-MM-DD");
            sc.close();
            return;
        }

        System.out.println("Ingrese el nuevo Total de la Factura:");
        miCabezaFactura.setTotalFactura(sc.nextFloat());
        sc.nextLine();

        System.out.println("Ingrese el nuevo ID del Usuario:");
        miCabezaFactura.setUsuarioIdUsuario(sc.nextInt());

        boolean resultado = dao.actualizarCabezaFactura(miCabezaFactura);
        if (resultado) {
            System.out.println("La cabeza de factura se actualizo Correctamente");
        } else {
            System.out.println("La cabeza de factura no se pudo actualizar");
        }
        sc.close();
    }
}
