package Dao;

import conf.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.DiagnosticoDueno;

public class DiagnosticoDuenoDao {
    private final Conexion cn = new Conexion();
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Inserta un nuevo diagnóstico en la tabla diagnosticodueno.
     */
    public void InsertarDiagnostico(DiagnosticoDueno d) {
        String sql = "INSERT INTO diagnosticodueno(IdM, IdV, FechaDiagnostico, Observaciones) VALUES (?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, d.getIdM());
            ps.setInt(2, d.getIdV());
            ps.setDate(3, d.getFechaDiagnostico());
            ps.setString(4, d.getObservaciones());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al Ingresar Diagnostico: " + e.getMessage());
        }
    }

    /**
     * Lista todos los diagnósticos de un veterinario, trayendo además
     * el nombre de la mascota y del dueño.
     */
    public List<DiagnosticoDueno> listarDiagnosticosPorVeterinario(int idVeterinario) {
        List<DiagnosticoDueno> lista = new ArrayList<>();
        String sql =
            "SELECT d.IdD, d.IdM, d.IdV, d.FechaDiagnostico, d.Observaciones, " +
            "       m.Nombre AS NombreMascota, u.Nombre AS NombreDueno " +
            "FROM diagnosticodueno d " +
            "  INNER JOIN mascotas m ON d.IdM = m.IdM " +
            "  INNER JOIN usuarios u ON m.IdU = u.IdU " +
            "WHERE d.IdV = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idVeterinario);
            rs = ps.executeQuery();
            while (rs.next()) {
                DiagnosticoDueno d = new DiagnosticoDueno();
                d.setIdD(rs.getInt("IdD"));
                d.setIdM(rs.getInt("IdM"));
                d.setIdV(rs.getInt("IdV"));
                d.setFechaDiagnostico(rs.getDate("FechaDiagnostico"));
                d.setObservaciones(rs.getString("Observaciones"));
                d.setNombreM(rs.getString("NombreMascota"));
                d.setNombreDueno(rs.getString("NombreDueno"));
                lista.add(d);
            }
        } catch (Exception e) {
            System.out.println("Error listando diagnósticos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Obtiene un diagnóstico por su IdD (para extraer luego IdM e IdV).
     */
    public DiagnosticoDueno obtenerPorId(int idD) {
        DiagnosticoDueno d = null;
        String sql = "SELECT IdD, IdM, IdV, FechaDiagnostico, Observaciones "
                   + "FROM diagnosticodueno WHERE IdD = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idD);
            rs = ps.executeQuery();
            if (rs.next()) {
                d = new DiagnosticoDueno();
                d.setIdD(rs.getInt("IdD"));
                d.setIdM(rs.getInt("IdM"));
                d.setIdV(rs.getInt("IdV"));
                d.setFechaDiagnostico(rs.getDate("FechaDiagnostico"));
                d.setObservaciones(rs.getString("Observaciones"));
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo diagnóstico por Id: " + e.getMessage());
        }
        return d;
    }
}

    

//{}
