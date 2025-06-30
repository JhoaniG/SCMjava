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
 * @author jhoan
 */
public class MascotaDao {

    Conexion cn = new Conexion(); // Tu clase que conecta a la BD
    Connection conn;
    PreparedStatement ps;
    ResultSet rs; // Se declara aquí para que sea accesible en el finally

    public void insertarMascota(Mascota m) throws ClassNotFoundException {
        // Añadido Foto a la sentencia INSERT
        String sql = "INSERT INTO mascotas(IdU, Nombre, Genero, FechaNacimineto, Raza, Foto) VALUES(?,?,?,?,?,?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, m.getIdU());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getGenero());
            ps.setDate(4, m.getFechaNacimineto());
            ps.setString(5, m.getRaza());
            ps.setString(6, m.getFoto()); // <-- Asignando la Foto
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar mascota: " + e.getMessage());
        } finally { // Asegurar que los recursos se cierren
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando recursos en insertarMascota: " + ex.getMessage());
            }
        }
    }

    public List<Mascota> ListarMascotaPorUsuario(int IdU) throws ClassNotFoundException {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT IdM, IdU, Nombre, Genero, FechaNacimineto, Raza, Foto FROM mascotas WHERE IdU=?"; // Asegurar que 'Foto' se selecciona
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
                m.setFoto(rs.getString("Foto")); // <-- ¡Ahora se recupera la Foto!
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar mascotas por usuario: " + e.getMessage());
        } finally { // Asegurar que los recursos se cierren
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando recursos en ListarMascotaPorUsuario: " + ex.getMessage());
            }
        }
        return lista;
    }

    public Mascota obtenerMascotaPoriD(int IdM) throws ClassNotFoundException {
        Mascota m = new Mascota();
        // Asegurar que 'Foto' se selecciona
        String sql = "SELECT IdM, IdU, Nombre, Genero, FechaNacimineto, Raza, Foto FROM mascotas WHERE IdM=?"; 
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
                m.setFoto(rs.getString("Foto")); // <-- ¡Ahora se recupera la Foto para editar!
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mascota por ID: " + e.getMessage());
        } finally { // Asegurar que los recursos se cierren
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando recursos en obtenerMascotaPoriD: " + ex.getMessage());
            }
        }
        return m;
    }
    
    public void actulizarMascota(Mascota m) throws ClassNotFoundException{
        // Añadido 'Foto=?' a la sentencia UPDATE
        String sql ="UPDATE mascotas SET Nombre=?, Genero=?, FechaNacimineto=?, Raza=?, Foto=? WHERE IdM=?";
        try{
            conn=cn.Conexion();
            ps=conn.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getGenero());
            ps.setDate(3, m.getFechaNacimineto());
            ps.setString(4, m.getRaza());
            ps.setString(5, m.getFoto()); // <-- ¡Ahora se asigna la Foto en la actualización!
            ps.setInt(6, m.getIdM());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error al actualizar datos de mascota: " + e.getMessage());
        } finally { // Asegurar que los recursos se cierren
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando recursos en actulizarMascota: " + ex.getMessage());
            }
        }
    }
    
    public void eliminarMascota(int IdM) throws ClassNotFoundException{ // Cambiado IdU a IdM por consistencia
        String sql="DELETE FROM mascotas WHERE IdM=?";
        try{
            conn=cn.Conexion();
            ps=conn.prepareStatement(sql);
            ps.setInt(1, IdM);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error al eliminar mascota: " + e.getMessage());
        } finally { // Asegurar que los recursos se cierren
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando recursos en eliminarMascota: " + ex.getMessage());
            }
        }
    }
}