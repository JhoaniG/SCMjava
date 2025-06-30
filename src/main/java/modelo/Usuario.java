/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jhoan
 */
public class Usuario {
    public int IdU;
    public String Nombre;
    public String Apellido;
    public String Correo;
    public String Contrasena;
    public String Telefono;
    public String Direccion;
    public int IdR;
    private String Foto;
    

    

    public Usuario() {
    }

    public Usuario(int IdU, String Nombre, String Apellido, String Correo, String Contrasena, String Telefono, String Direccion, int IdR) {
        this.IdU = IdU;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.IdR = IdR;
    }

    public String getFoto() {
        return Foto;
    }

    public int getIdU() {
        return IdU;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public int getIdR() {
        return IdR;
    }

    public void setIdU(int IdU) {
        this.IdU = IdU;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setIdR(int IdR) {
        this.IdR = IdR;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }
    
    
}
