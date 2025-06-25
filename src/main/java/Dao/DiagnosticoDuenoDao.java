/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import conf.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.DiagnosticoDueno;

/**
 *
 * @author jhoan
 */
public class DiagnosticoDuenoDao {
    Conexion cn = new Conexion(); // Tu clase que conecta a la BD
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
public void InsertarDiagnostico(DiagnosticoDueno d){
String sql="INSERT INTO diagnosticodueno(IdM, IdV, FechaDiagnostico,Observaciones)VALUES(?,?,?,?)";
try{
conn=cn.Conexion();
ps=conn.prepareStatement(sql);
ps.setInt(1,d.getIdM());
ps.setInt(2, d.getIdV());
ps.setDate(3, d.getFechaDiagnostico());
ps.setString(4, d.getObservaciones());
 ps.executeUpdate();
}catch(Exception e){
    System.out.println("Error al Ingresar Diagnostico" + e.getMessage());

}

}
    
}
//{}
