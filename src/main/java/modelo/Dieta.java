/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Dieta {
    private int IdDi;
    private int IdM;
    private int IdV;
    private String Descripcion;
    private String TipoDieta;

    // Campos auxiliares
    private String nombreMascota;
    private String nombreVeterinario;
    // CAMBIAR A PRIVATE
    private String Foto; // <--- ¡AQUÍ ESTÁ EL CAMBIO!

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
    }

    // Getters y Setters
    public int getIdDi() { return IdDi; }
    public void setIdDi(int idDi) { this.IdDi = idDi; }

    public int getIdM() { return IdM; }
    public void setIdM(int idM) { this.IdM = idM; }

    public int getIdV() { return IdV; }
    public void setIdV(int idV) { this.IdV = idV; }

    public String getDescripcion() { return Descripcion; }
    public void setDescripcion(String descripcion) { this.Descripcion = descripcion; }

    public String getTipoDieta() { return TipoDieta; }
    public void setTipoDieta(String tipoDieta) { this.TipoDieta = tipoDieta; }

  
  
 
}
