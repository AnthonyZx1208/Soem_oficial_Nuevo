/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aprendiz
 */
public class TipoDocumentoDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR TIPO DE DOCUMENTO ===================== */
    public boolean insertarTipoDocumento(TipoDocumento miTipoDocumento) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO TipoDocumento (Descrip_documento) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miTipoDocumento.getDescripDocumento());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar tipo de documento: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR TIPO DE DOCUMENTO ===================== */
    public TipoDocumento consultarTipoDocumento(int idTipoDocumento) {
        TipoDocumento miTipoDocumento = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_TipoDocumento, Descrip_documento FROM TipoDocumento WHERE id_TipoDocumento = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idTipoDocumento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miTipoDocumento = new TipoDocumento();
                miTipoDocumento.setIdTipoDocumento(rs.getInt("id_TipoDocumento"));
                miTipoDocumento.setDescripDocumento(rs.getString("Descrip_documento"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar tipo de documento: " + e.getMessage());
        }
        return miTipoDocumento;
    }

    /* ===================== ACTUALIZAR TIPO DE DOCUMENTO ===================== */
    public boolean actualizarTipoDocumento(TipoDocumento miTipoDocumento) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE TipoDocumento SET Descrip_documento = ? WHERE id_TipoDocumento = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miTipoDocumento.getDescripDocumento());
            ps.setInt(2, miTipoDocumento.getIdTipoDocumento());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tipo de documento: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR TIPO DE DOCUMENTO ===================== */
    public boolean modificarTipoDocumento(TipoDocumento miTipoDocumento) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE TipoDocumento SET Descrip_documento = ? WHERE id_TipoDocumento = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miTipoDocumento.getDescripDocumento());
            ps.setInt(2, miTipoDocumento.getIdTipoDocumento());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar tipo de documento: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== ELIMINAR TIPO DE DOCUMENTO ===================== */
    public boolean eliminarTipoDocumento(int idTipoDocumento) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM TipoDocumento WHERE id_TipoDocumento = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idTipoDocumento);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo de documento: " + e.getMessage()
                    + "\nNota: Si el tipo de documento tiene usuarios asociados, primero debes eliminarlos.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODOS LOS TIPOS DE DOCUMENTO ===================== */
    public List<TipoDocumento> listarTiposDocumento() {
        List<TipoDocumento> listaTiposDocumento = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_TipoDocumento, Descrip_documento FROM TipoDocumento";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoDocumento miTipoDocumento = new TipoDocumento();
                miTipoDocumento.setIdTipoDocumento(rs.getInt("id_TipoDocumento"));
                miTipoDocumento.setDescripDocumento(rs.getString("Descrip_documento"));
                listaTiposDocumento.add(miTipoDocumento);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar tipos de documento: " + e.getMessage());
        }
        return listaTiposDocumento;
    }
}
