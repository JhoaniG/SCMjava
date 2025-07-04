package Dao;

import conf.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioDao {

    Conexion cn = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    // Validar inicio de sesión
    public Usuario Validar(String Correo, String Contrasena) throws ClassNotFoundException, SQLException {
        Usuario obj_usu = null;
        String sql = "SELECT IdU, Nombre, Apellido, Correo, Contrasena, Telefono, Direccion, IdR, Foto FROM usuarios WHERE Correo=? AND Contrasena=?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, Correo);
            ps.setString(2, Contrasena);
            rs = ps.executeQuery();
            if (rs.next()) {
                obj_usu = new Usuario();
                obj_usu.setIdU(rs.getInt("IdU"));
                obj_usu.setNombre(rs.getString("Nombre"));
                obj_usu.setApellido(rs.getString("Apellido"));
                obj_usu.setCorreo(rs.getString("Correo"));
                obj_usu.setContrasena(rs.getString("Contrasena"));
                obj_usu.setTelefono(rs.getString("Telefono"));
                obj_usu.setDireccion(rs.getString("Direccion"));
                obj_usu.setIdR(rs.getInt("IdR"));
                obj_usu.setFoto(rs.getString("Foto"));
            }
        } catch (SQLException e) {
            System.out.println("Error Validar " + e.getMessage());
        }
        return obj_usu;
    }

    // Insertar nuevo usuario
    public void InsertarUsuario(Usuario u) throws ClassNotFoundException {
        String sql = "INSERT INTO usuarios(Nombre, Apellido, Correo, Contrasena, Telefono, Direccion, IdR, Foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContrasena());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getDireccion());
            ps.setInt(7, u.getIdR());
            ps.setString(8, u.getFoto());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al ingresar usuario: " + e.getMessage());
        }
    }

    // Obtener usuario por ID
    public Usuario ObtenerUsuarioPorId(int IdU) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE IdU=?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, IdU);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setIdU(rs.getInt("IdU"));
                u.setNombre(rs.getString("Nombre"));
                u.setApellido(rs.getString("Apellido"));
                u.setCorreo(rs.getString("Correo"));
                u.setContrasena(rs.getString("Contrasena"));
                u.setTelefono(rs.getString("Telefono"));
                u.setDireccion(rs.getString("Direccion"));
                u.setIdR(rs.getInt("IdR"));
                u.setFoto(rs.getString("Foto"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuario por ID: " + e.getMessage());
        }
        return u;
    }

    // Actualizar usuario
    public void actualizarUsuario(Usuario u) {
        String sql = "UPDATE usuarios SET Nombre=?, Apellido=?, Correo=?, Contrasena=?, Telefono=?, Direccion=?, Foto=? WHERE IdU=?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContrasena());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getDireccion());
            ps.setString(7, u.getFoto());
            ps.setInt(8, u.getIdU());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Eliminar usuario
    public void EliminarUsuario(int IdU) {
        String sql = "DELETE FROM usuarios WHERE IdU=?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, IdU);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    // Obtener último ID insertado
    public int obtenerUltimoIdInsertado() throws SQLException, ClassNotFoundException {
        int id = 0;
        String sql = "SELECT MAX(IdU) AS IdU FROM usuarios";
        conn = cn.Conexion();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("IdU");
        }
        return id;
    }

    // Insertar en veterinario
    public void insertarVeterinario(int IdU, String especialidad) throws ClassNotFoundException {
        String sql = "INSERT INTO veterinario (Especialidad, IdU) VALUES (?, ?)";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, especialidad);
            ps.setInt(2, IdU);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertando veterinario: " + e.getMessage());
        }
    }

    // Obtener IdV del veterinario por IdU
    public int obtenerIdVeterinarioPorIdUsuario(int idU) throws ClassNotFoundException {
        int idV = 0;
        String sql = "SELECT IdV FROM veterinario WHERE IdU = ?";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idU);
            rs = ps.executeQuery();
            if (rs.next()) {
                idV = rs.getInt("IdV");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener IdV del veterinario: " + e.getMessage());
        }
        return idV;
    }

    // ✅ Listar todos los veterinarios
    // Lista todos los veterinarios con su IdV y nombre
    public List<Usuario> listarVeterinarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT v.IdV AS IdV, u.Nombre AS Nombre FROM veterinario v INNER JOIN usuarios u ON v.IdU = u.IdU";
        try {
            conn = cn.Conexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario v = new Usuario();
                v.setIdU(rs.getInt("IdV"));
                v.setNombre(rs.getString("Nombre"));
                lista.add(v);
            }
        } catch (Exception e) {
            System.out.println("Error listando veterinarios: " + e.getMessage());
        }
        return lista;
    }
}