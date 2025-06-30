/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import conf.Conexion;
import java.sql.*;
import java.util.*;
import modelo.Dieta;
import modelo.Mascota;
import modelo.Usuario;

public class DietaDao {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public void insertarDieta(Dieta d) {
        String sql = "INSERT INTO dieta (IdM, IdV, Descripcion, TipoDieta, Foto) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, d.getIdM());
            ps.setInt(2, d.getIdV());
            ps.setString(3, d.getDescripcion());
            ps.setString(4, d.getTipoDieta());
            ps.setString(5, d.getFoto());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar dieta: " + e.getMessage());
        }
    }

    public List<Mascota> listarMascotasPorDueno(int idU) {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE IdU = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idU);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIdM(rs.getInt("IdM"));
                m.setNombre(rs.getString("Nombre"));
                
                lista.add(m);
            }
        } catch (Exception e) {
            System.out.println("Error al listar mascotas: " + e.getMessage());
        }
        return lista;
    }

    public List<Usuario> listarDuenos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE IdR = 2"; // 3: dueños
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setFoto(rs.getString("Foto"));
                u.setIdU(rs.getInt("IdU"));
                u.setNombre(rs.getString("Nombre"));
                u.setApellido(rs.getString("Apellido"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error al listar dueños: " + e.getMessage());
        }
        return lista;
    }
    public List<Mascota> listarMascotasPorDuenoDos(int idU) {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE IdU = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idU);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIdM(rs.getInt("IdM"));
                m.setNombre(rs.getString("Nombre"));
                m.setGenero(rs.getString("Genero"));
                
                lista.add(m);
            }
        } catch (Exception e) {
            System.out.println("Error al listar mascotas: " + e.getMessage());
        }
        return lista;
    }
    
    
    public List<Dieta> obtenerDietasPorIdMascota(int idM) {
    List<Dieta> lista = new ArrayList<>();
    String sql = "SELECT d.IdDi, d.Descripcion, d.TipoDieta, d.Foto, m.Nombre AS nombreMascota, u.Nombre AS nombreVeterinario" +
                 " FROM dieta d " + // <-- Agregado el espacio aquí
                 "JOIN mascotas m ON d.IdM = m.IdM " +
                 "JOIN veterinario v ON d.IdV = v.IdV " +
                 "JOIN usuarios u ON v.IdU = u.IdU " +
                 "WHERE d.IdM = ?";

    try {
        conn = cn.Conexion();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idM);
        rs = ps.executeQuery();

        while (rs.next()) {
            Dieta d = new Dieta();
            d.setIdDi(rs.getInt("IdDi"));
            d.setDescripcion(rs.getString("Descripcion"));
            d.setTipoDieta(rs.getString("TipoDieta"));
            d.setFoto(rs.getString("Foto"));
            d.setNombreMascota(rs.getString("nombreMascota")); // necesitas este campo en el modelo
            d.setNombreVeterinario(rs.getString("nombreVeterinario"));
            // también este

            lista.add(d);
        }
    } catch (Exception e) {
        System.out.println("Error al obtener dietas por ID de mascota: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            System.out.println("Error cerrando recursos: " + ex.getMessage());
        }
    }

    return lista;
}


}
