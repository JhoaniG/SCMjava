/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author jhoan
 */
public class Mascota {
    public int IdM;
    public int IdU;
    public String Nombre;
    public String Genero;
    public Date FechaNacimineto;
    public String Raza;
    public String Foto;

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }
    

    public Mascota() {
    }

    public Mascota(int IdM, int IdU, String Nombre, String Genero, Date FechaNacimineto, String Raza) {
        this.IdM = IdM;
        this.IdU = IdU;
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.FechaNacimineto = FechaNacimineto;
        this.Raza = Raza;
    }

    public int getIdM() {
        return IdM;
    }

    public int getIdU() {
        return IdU;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getGenero() {
        return Genero;
    }

    public Date getFechaNacimineto() {
        return FechaNacimineto;
    }

    public String getRaza() {
        return Raza;
    }

    public void setIdM(int IdM) {
        this.IdM = IdM;
    }

    public void setIdU(int IdU) {
        this.IdU = IdU;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public void setFechaNacimineto(Date FechaNacimineto) {
        this.FechaNacimineto = FechaNacimineto;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
