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
public class Cita {
    public int IdC;
    public int IdM;
    public int IdV;
    public Date FechaCita;
    public String MotivoCita;
    public String EstadoCita;
    public int IdD;

    public Cita() {
    }

    public Cita(int IdC, int IdM, int IdV, Date FechaCita, String MotivoCita, String EstadoCita, int IdD) {
        this.IdC = IdC;
        this.IdM = IdM;
        this.IdV = IdV;
        this.FechaCita = FechaCita;
        this.MotivoCita = MotivoCita;
        this.EstadoCita = EstadoCita;
        this.IdD = IdD;
    }

    public int getIdC() {
        return IdC;
    }

    public int getIdM() {
        return IdM;
    }

    public int getIdV() {
        return IdV;
    }

    public Date getFechaCita() {
        return FechaCita;
    }

    public String getMotivoCita() {
        return MotivoCita;
    }

    public String getEstadoCita() {
        return EstadoCita;
    }

    public int getIdD() {
        return IdD;
    }

    public void setIdC(int IdC) {
        this.IdC = IdC;
    }

    public void setIdM(int IdM) {
        this.IdM = IdM;
    }

    public void setIdV(int IdV) {
        this.IdV = IdV;
    }

    public void setFechaCita(Date FechaCita) {
        this.FechaCita = FechaCita;
    }

    public void setMotivoCita(String MotivoCita) {
        this.MotivoCita = MotivoCita;
    }

    public void setEstadoCita(String EstadoCita) {
        this.EstadoCita = EstadoCita;
    }

    public void setIdD(int IdD) {
        this.IdD = IdD;
    }
    
    
    
    
    
    
    
    
}
