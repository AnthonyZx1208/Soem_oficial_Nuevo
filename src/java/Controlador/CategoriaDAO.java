/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Categoria;
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
public class CategoriaDAO {

    private Conexion conect = new Conexion();

    /* ===================== INSERTAR CATEGORIA ===================== */
    public boolean insertarCategoria(Categoria miCategoria) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "INSERT INTO Categoria (nombre_categoria, descripcion, imagen_url, estado) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miCategoria.getNombre_categoria());
            ps.setString(2, miCategoria.getDescripcion());
            ps.setString(3, miCategoria.getImagen_url());
            ps.setString(4, miCategoria.getEstado());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar categoria: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== CONSULTAR CATEGORIA ===================== */
    public Categoria consultarCategoria(int idCategoria) {
        Categoria miCategoria = null;
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_categoria, nombre_categoria, descripcion, imagen_url, estado "
                    + "FROM Categoria WHERE id_categoria = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                miCategoria = mapearCategoria(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e.getMessage());
        }
        return miCategoria;
    }

    /* ===================== ACTUALIZAR CATEGORIA ===================== */
    public boolean actualizarCategoria(Categoria miCategoria) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "UPDATE Categoria SET nombre_categoria = ?, descripcion = ?, imagen_url = ?, "
                    + "estado = ? WHERE id_categoria = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, miCategoria.getNombre_categoria());
            ps.setString(2, miCategoria.getDescripcion());
            ps.setString(3, miCategoria.getImagen_url());
            ps.setString(4, miCategoria.getEstado());
            ps.setInt(5, miCategoria.getId_categoria());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e.getMessage());
        }
        return resultado;
    }

    /* ===================== MODIFICAR CATEGORIA ===================== */
    public boolean modificarCategoria(Categoria miCategoria) {
        return actualizarCategoria(miCategoria);
    }

    /* ===================== ELIMINAR CATEGORIA ===================== */
    public boolean eliminarCategoria(int idCategoria) {
        boolean resultado = false;
        Connection conn = conect.getConn();
        try {
            String querySql = "DELETE FROM Categoria WHERE id_categoria = ?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, idCategoria);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                resultado = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoria: " + e.getMessage()
                    + "\nNota: Si la categoria tiene productos asociados, primero debes eliminarlos.");
        }
        return resultado;
    }

    /* ===================== LISTAR TODAS LAS CATEGORIAS ===================== */
    public List<Categoria> listarCategorias() {
        List<Categoria> listaCategorias = new ArrayList<>();
        Connection conn = conect.getConn();
        try {
            String querySql = "SELECT id_categoria, nombre_categoria, descripcion, imagen_url, estado FROM Categoria";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaCategorias.add(mapearCategoria(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al listar categorias: " + e.getMessage());
        }
        return listaCategorias;
    }

    /* ===================== HELPER: MAPEAR RESULTSET -> CATEGORIA ===================== */
    private Categoria mapearCategoria(ResultSet rs) throws SQLException {
        Categoria miCategoria = new Categoria();
        miCategoria.setId_categoria(rs.getInt("id_categoria"));
        miCategoria.setNombre_categoria(rs.getString("nombre_categoria"));
        miCategoria.setDescripcion(rs.getString("descripcion"));
        miCategoria.setImagen_url(rs.getString("imagen_url"));
        miCategoria.setEstado(rs.getString("estado"));
        return miCategoria;
    }
}
