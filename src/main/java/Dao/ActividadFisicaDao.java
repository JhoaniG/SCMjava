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

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

   
    // Registrar una actividad física
    public void insertarActividad(ActividadFisica a) throws SQLException {
        String sql = "INSERT INTO actividadfisica (IdM, IdV, Descripcion, TipoActividad) VALUES (?, ?, ?, ?)";
        try{
        conn=cn.Conexion();
        ps=conn.prepareStatement(sql);
        ps.setInt(1, a.getIdM());
        ps.setInt(2, a.getIdV());
        ps.setString(3, a.getDescripcion());
        ps.setString(4, a.getTipoActividad());
        ps.executeUpdate();
        }catch(Exception e){
            System.out.println("eERRO al insertar DIeta " + e.getMessage());
            
        }
    }
    //lISTAR mascota par apode usarla
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

    
    //lISTAR dueno Por Idu PARA poder mostrar en la vista
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
    

