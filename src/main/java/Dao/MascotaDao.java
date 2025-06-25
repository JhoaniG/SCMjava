/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import conf.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Mascota;

/**
 *
 * @author jhoan {}
 */
public class MascotaDao {

    Conexion cn = new Conexion(); // Tu clase que conecta a la BD
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public void insertarMascota(Mascota m) throws ClassNotFoundException {
        String sql = "INSERT INTO mascotas(IdU, Nombre, Genero, FechaNacimineto,Raza) VALUES(?,?,?,?,?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, m.getIdU());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getGenero());
            ps.setDate(4, m.getFechaNacimineto());
            ps.setString(5, m.getRaza());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Insersion mal " + e.getMessage());

        }

    }

    public List<Mascota> ListarMascotaPorUsuario(int IdU) throws ClassNotFoundException {
        List<Mascota> lista = new ArrayList<>();

        String sql = "SELECT*FROM mascotas WHERE IdU=?";

        try {

            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, IdU);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mascota m = new Mascota();
                m.setIdM(rs.getInt("IdM"));
                m.setIdU(rs.getInt("IdU"));
                m.setNombre(rs.getString("Nombre"));
                m.setGenero(rs.getString("Genero"));
                m.setFechaNacimineto(rs.getDate("FechaNacimineto"));
                m.setRaza(rs.getString("Raza"));
                lista.add(m);

            }

        } catch (SQLException e) {
            System.out.println("Problemas apra listar" + e.getMessage());

        }

        return lista;

    }

    public Mascota obtenerMascotaPoriD(int IdM) throws ClassNotFoundException {
        Mascota m = new Mascota();
        String sql = "SELECT*FROM mascotas WHERE IdM=?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, IdM);
            rs = ps.executeQuery();
            if (rs.next()) {
                m.setIdM(rs.getInt("IdM"));
                m.setIdU(rs.getInt("IdU"));
                m.setNombre(rs.getString("Nombre"));
                m.setGenero(rs.getString("Genero"));
                m.setFechaNacimineto(rs.getDate("FechaNacimineto"));
                m.setRaza(rs.getString("Raza"));
            }

        } catch (SQLException e) {
            System.out.println("Erro al actulizar" + e.getMessage());

        }

        return m;
    }
    
    
    
    
    public void actulizarMascota(Mascota m) throws ClassNotFoundException{
    String sql ="UPDATE mascotas SET Nombre=?, Genero=?, FechaNacimineto=?, Raza=? WHERE IdM=?";
    try{
    conn=cn.Conexion();
    ps=conn.prepareStatement(sql);
    ps.setString(1, m.getNombre());
    ps.setString(2, m.getGenero());
    ps.setDate(3, m.getFechaNacimineto());
    ps.setString(4, m.getRaza());
    ps.setInt(5, m.getIdM());
    ps.executeUpdate();
            
    
    }catch(SQLException e){
        System.out.println("Erro Actualizando datos" + e.getMessage());
    
    }
    
   
    
    
    
    
    
    }
    
    public void eliminarMascota(int IdU) throws ClassNotFoundException{
    String sql="DELETE FROM mascotas WHERE IdM=?";
    try{
        conn=cn.Conexion();
        ps=conn.prepareStatement(sql);
        ps.setInt(1, IdU);
        ps.executeUpdate();
        
    }catch(SQLException e){
        System.out.println("Erro al eliminar Masotas"+ e.getMessage());}
    
    }
    

}
//{}
