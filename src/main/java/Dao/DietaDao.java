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
        String sql = "INSERT INTO dieta (IdM, IdV, Descripcion, TipoDieta) VALUES (?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, d.getIdM());
            ps.setInt(2, d.getIdV());
            ps.setString(3, d.getDescripcion());
            ps.setString(4, d.getTipoDieta());
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
}
