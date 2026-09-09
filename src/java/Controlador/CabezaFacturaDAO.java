/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.CabezaFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aprendiz
 */
public class CabezaFacturaDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR CABEZA FACTURA ===================== */
    public boolean insertarCabezaFactura(CabezaFactura miCabezaFactura) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Cabeza_Factura (numero_fac, fecha_factura, total_factura, Usuario_id_usuario) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miCabezaFactura.getNumeroFac());
            ps.setDate(2, new Date(miCabezaFactura.getFechaFactura().getTime()));
            ps.setFloat(3, miCabezaFactura.getTotalFactura());
            ps.setInt(4, miCabezaFactura.getUsuarioIdUsuario());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar cabeza factura: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR CABEZA FACTURA ===================== */
    public CabezaFactura consultarCabezaFactura(int idFactura) {
        CabezaFactura miCabezaFactura = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_factura, numero_fac, fecha_factura, total_factura, Usuario_id_usuario "
                    + "FROM Cabeza_Factura WHERE id_factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idFactura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miCabezaFactura = new CabezaFactura();
                miCabezaFactura.setIdFactura(rs.getInt("id_factura"));
                miCabezaFactura.setNumeroFac(rs.getInt("numero_fac"));
                miCabezaFactura.setFechaFactura(rs.getDate("fecha_factura"));
                miCabezaFactura.setTotalFactura(rs.getFloat("total_factura"));
                miCabezaFactura.setUsuarioIdUsuario(rs.getInt("Usuario_id_usuario"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar cabeza factura: " + e.getMessage());
        }
        return miCabezaFactura;
    }

    /* ===================== ACTUALIZAR CABEZA FACTURA ===================== */
    public boolean actualizarCabezaFactura(CabezaFactura miCabezaFactura) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Cabeza_Factura SET numero_fac = ?, fecha_factura = ?, total_factura = ?, "
                    + "Usuario_id_usuario = ? WHERE id_factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miCabezaFactura.getNumeroFac());
            ps.setDate(2, new Date(miCabezaFactura.getFechaFactura().getTime()));
            ps.setFloat(3, miCabezaFactura.getTotalFactura());
            ps.setInt(4, miCabezaFactura.getUsuarioIdUsuario());
            ps.setInt(5, miCabezaFactura.getIdFactura());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabeza factura: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR CABEZA FACTURA ===================== */
    public boolean modificarCabezaFactura(CabezaFactura miCabezaFactura) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Cabeza_Factura SET numero_fac = ?, fecha_factura = ?, total_factura = ?, "
                    + "Usuario_id_usuario = ? WHERE id_factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, miCabezaFactura.getNumeroFac());
            ps.setDate(2, new Date(miCabezaFactura.getFechaFactura().getTime()));
            ps.setFloat(3, miCabezaFactura.getTotalFactura());
            ps.setInt(4, miCabezaFactura.getUsuarioIdUsuario());
            ps.setInt(5, miCabezaFactura.getIdFactura());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar cabeza factura: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR CABEZA FACTURA ===================== */
    public boolean eliminarCabezaFactura(int idFactura) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Cabeza_Factura WHERE id_factura = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idFactura);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cabeza factura: " + e.getMessage()
                    + "\nNota: Si la factura tiene pagos o detalles asociados, primero debes eliminarlos.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODAS LAS CABEZAS DE FACTURA ===================== */
    public List<CabezaFactura> listarCabezasFactura() {
        List<CabezaFactura> listaCabezas = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_factura, numero_fac, fecha_factura, total_factura, Usuario_id_usuario FROM Cabeza_Factura";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CabezaFactura miCabezaFactura = new CabezaFactura();
                miCabezaFactura.setIdFactura(rs.getInt("id_factura"));
                miCabezaFactura.setNumeroFac(rs.getInt("numero_fac"));
                miCabezaFactura.setFechaFactura(rs.getDate("fecha_factura"));
                miCabezaFactura.setTotalFactura(rs.getFloat("total_factura"));
                miCabezaFactura.setUsuarioIdUsuario(rs.getInt("Usuario_id_usuario"));
                listaCabezas.add(miCabezaFactura);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar cabezas de factura: " + e.getMessage());
        }
        return listaCabezas;
    }
}
