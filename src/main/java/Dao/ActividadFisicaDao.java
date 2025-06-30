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
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getIdM());
            ps.setInt(2, a.getIdV());
            ps.setString(3, a.getDescripcion());
            ps.setString(4, a.getTipoActividad());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar actividad física: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos en insertarActividad: " + e.getMessage());
            }
        }
    }

    // Listar mascotas por dueño para poder usarla (este método ya existía)
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
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos en listarMascotasPorDueno: " + e.getMessage());
            }
        }
        return lista;
    }

    // Listar dueños por IdU para poder mostrar en la vista (este método ya existía)
    public List<Usuario> listarDuenos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE IdR = 2"; // 2: dueños
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
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos en listarDuenos: " + e.getMessage());
            }
        }
        return lista;
    }

    // Nuevo método para obtener actividades físicas por ID de mascota
     public List<ActividadFisica> obtenerActividadesPorIdMascota(int idMascota) {
        List<ActividadFisica> lista = new ArrayList<>();
        // Consulta que obtiene la descripción, tipo de actividad, nombre de mascota y nombre de veterinario
        String sql = "SELECT af.Descripcion, af.TipoActividad, m.Nombre AS NombreMascota, u.Nombre AS NombreVeterinario " +
                     "FROM actividadfisica af " +
                     "JOIN mascotas m ON af.IdM = m.IdM " +
                     "JOIN veterinario v ON af.IdV = v.IdV " +
                     "JOIN usuarios u ON v.IdU = u.IdU " +
                     "WHERE af.IdM = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idMascota);
            rs = ps.executeQuery();
            while (rs.next()) {
                ActividadFisica af = new ActividadFisica();
                af.setDescripcion(rs.getString("Descripcion"));
                af.setTipoActividad(rs.getString("TipoActividad"));
                af.setNombreMascota(rs.getString("NombreMascota"));
                af.setNombreVeterinario(rs.getString("NombreVeterinario"));
                lista.add(af);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener actividades físicas por mascota: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos en obtenerActividadesPorIdMascota: " + e.getMessage());
            }
        }
        return lista;
    }
}