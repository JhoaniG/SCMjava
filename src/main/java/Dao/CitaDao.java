package Dao;

import conf.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Cita;

public class CitaDao {
    private Conexion cn = new Conexion();
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    /** Inserta una nueva cita */
    public void insertarCita(Cita c) {
        String sql = "INSERT INTO cita(IdM, IdV, FechaCita, MotivoCita, EstadoCita, IdD) VALUES (?,?,?,?,?,?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getIdM());
            ps.setInt(2, c.getIdV());
            ps.setDate(3, c.getFechaCita());
            ps.setString(4, c.getMotivoCita());
            ps.setString(5, c.getEstadoCita());
            ps.setInt(6, c.getIdD());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar cita: " + e.getMessage());
        }
    }

    /** Lista todas las citas de un veterinario */
    public List<Cita> listarCitasPorVeterinario(int idV) {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita WHERE IdV = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idV);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdC(rs.getInt("IdC"));
                c.setIdM(rs.getInt("IdM"));
                c.setIdV(rs.getInt("IdV"));
                c.setFechaCita(rs.getDate("FechaCita"));
                c.setMotivoCita(rs.getString("MotivoCita"));
                c.setEstadoCita(rs.getString("EstadoCita"));
                c.setIdD(rs.getInt("IdD"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error listando citas: " + e.getMessage());
        }
        return lista;
    }

    /** Lista las citas de un diagnóstico concreto (IdD) */
    public List<Cita> listarCitasPorDiagnostico(int idD) {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita WHERE IdD = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idD);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdC(rs.getInt("IdC"));
                c.setIdM(rs.getInt("IdM"));
                c.setIdV(rs.getInt("IdV"));
                c.setFechaCita(rs.getDate("FechaCita"));
                c.setMotivoCita(rs.getString("MotivoCita"));
                c.setEstadoCita(rs.getString("EstadoCita"));
                c.setIdD(rs.getInt("IdD"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error listando citas por diagnóstico: " + e.getMessage());
        }
        return lista;
    }
    
    
    
    public List<Cita> listarCitasPorVeterinarioo(int idV) {
    List<Cita> lista = new ArrayList<>();
    String sql = "SELECT c.IdC, c.FechaCita, c.MotivoCita, c.EstadoCita, " +
                 "m.Nombre AS nombreMascota, u.Nombre AS nombreDueno " +
                 "FROM cita c " +
                 "JOIN mascotas m ON c.IdM = m.IdM " +
                 "JOIN usuarios u ON m.IdU = u.IdU " +
                 "WHERE c.IdV = ?";
    try {
        conn = cn.Conexion();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idV);
        rs = ps.executeQuery();
        while (rs.next()) {
            Cita c = new Cita();
            c.setIdC(rs.getInt("IdC"));
            c.setFechaCita(rs.getDate("FechaCita"));
            c.setMotivoCita(rs.getString("MotivoCita"));
            c.setEstadoCita(rs.getString("EstadoCita"));
            c.setNombreMascota(rs.getString("nombreMascota"));
            c.setNombreDueno(rs.getString("nombreDueno")); // Es el dueño de la mascota
            lista.add(c);
        }
    } catch (Exception e) {
        System.out.println("Error en listarCitasPorVeterinario: " + e.getMessage());
    }
    return lista;
}
public List<Cita> listarCitasPorDueno(int idU) {
    List<Cita> lista = new ArrayList<>();
    String sql = "SELECT c.IdC, c.FechaCita, c.MotivoCita, c.EstadoCita, " +
                 "m.Nombre AS nombreMascota, uVet.Nombre AS nombreVeterinario " +
                 "FROM cita c " +
                 "JOIN mascotas m ON c.IdM = m.IdM " +
                 "JOIN usuarios uDueno ON m.IdU = uDueno.IdU " +
                 "JOIN veterinario v ON c.IdV = v.IdV " +
                 "JOIN usuarios uVet ON v.IdU = uVet.IdU " +
                 "WHERE uDueno.IdU = ?";

    try {
        conn = cn.Conexion();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, idU);
        rs = ps.executeQuery();
        while (rs.next()) {
            Cita c = new Cita();
            c.setIdC(rs.getInt("IdC"));
            c.setFechaCita(rs.getDate("FechaCita"));
            c.setMotivoCita(rs.getString("MotivoCita"));
            c.setEstadoCita(rs.getString("EstadoCita"));
            c.setNombreMascota(rs.getString("nombreMascota"));
            c.setNombreVeterinario(rs.getString("nombreVeterinario"));
            lista.add(c);
        }
    } catch (Exception e) {
        System.out.println("Error en listarCitasPorDueno: " + e.getMessage());
    }
    return lista;
}
}

