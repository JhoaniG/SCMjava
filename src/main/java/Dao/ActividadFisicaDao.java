/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import conf.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.ActividadFisica;
import modelo.Mascota;
import modelo.Usuario;


public class ActividadFisicaDao {

  
    

    // Registrar una actividad física
    public void insertarActividad(ActividadFisica a) throws SQLException {
        String sql = "INSERT INTO actividadfisica (IdM, IdV, Descripcion, TipoActividad) VALUES (?, ?, ?, ?)";
        
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, a.getIdM());
        ps.setInt(2, a.getIdV());
        ps.setString(3, a.getDescripcion());
        ps.setString(4, a.getTipoActividad());
        ps.executeUpdate();
    }

    // Listar todos los dueños (rol 2)
    public List<Usuario> listarDuenos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE IdR = 2";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setIdU(rs.getInt("IdU"));
            u.setNombre(rs.getString("Nombre"));
            u.setApellido(rs.getString("Apellido"));
            lista.add(u);
        }
        return lista;
    }

    // Listar mascotas por dueño
    public List<Mascota> listarMascotasPorDueno(int idDueno) throws SQLException {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascota WHERE IdU = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idDueno);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Mascota m = new Mascota();
            m.setIdM(rs.getInt("IdM"));
            m.setNombre(rs.getString("Nombre"));
            lista.add(m);
        }
        return lista;
    }
}
