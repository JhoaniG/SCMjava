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
public class DiagnosticoDueno {
    public int IdD;
    public int IdM;
    public int IdV;
    public Date FechaDiagnostico;
    public String Observaciones;
    public String NombreM;

    public DiagnosticoDueno() {
    }

    public DiagnosticoDueno(int IdD, int IdM, int IdV, Date FechaDiagnostico, String Observaciones, String NombreM) {
        this.IdD = IdD;
        this.IdM = IdM;
        this.IdV = IdV;
        this.FechaDiagnostico = FechaDiagnostico;
        this.Observaciones = Observaciones;
        this.NombreM = NombreM;
    }

    public int getIdD() {
        return IdD;
    }

    public int getIdM() {
        return IdM;
    }

    public int getIdV() {
        return IdV;
    }

    public Date getFechaDiagnostico() {
        return FechaDiagnostico;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public String getNombreM() {
        return NombreM;
    }

    public void setIdD(int IdD) {
        this.IdD = IdD;
    }

    public void setIdM(int IdM) {
        this.IdM = IdM;
    }

    public void setIdV(int IdV) {
        this.IdV = IdV;
    }

    public void setFechaDiagnostico(Date FechaDiagnostico) {
        this.FechaDiagnostico = FechaDiagnostico;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public void setNombreM(String NombreM) {
        this.NombreM = NombreM;
    }
    
    
    
    
    
}
