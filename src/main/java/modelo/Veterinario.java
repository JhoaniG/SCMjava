/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jhoan
 */
public class Veterinario {
    
   public int IdV;
   public String Especialidad;
   public int IdU;

    public Veterinario() {
    }

    public Veterinario(int IdV, String Especialidad, int IdU) {
        this.IdV = IdV;
        this.Especialidad = Especialidad;
        this.IdU = IdU;
    }

    public int getIdV() {
        return IdV;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public int getIdU() {
        return IdU;
    }

    public void setIdV(int IdV) {
        this.IdV = IdV;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    public void setIdU(int IdU) {
        this.IdU = IdU;
    }
   
   
    
}
