package modelo;

import java.sql.Date;

public class Cita {
    private int IdC;
    private int IdM;
    private int IdV;
    private Date FechaCita;
    private String MotivoCita;
    private String EstadoCita;
    private int IdD;
    private String nombreMascota;
private String nombreDueno;
private String nombreVeterinario;

    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
    }
    


    // Getters & setters
    public int getIdC() { return IdC; }
    public void setIdC(int IdC) { this.IdC = IdC; }

    public int getIdM() { return IdM; }
    public void setIdM(int IdM) { this.IdM = IdM; }

    public int getIdV() { return IdV; }
    public void setIdV(int IdV) { this.IdV = IdV; }

    public Date getFechaCita() { return FechaCita; }
    public void setFechaCita(Date FechaCita) { this.FechaCita = FechaCita; }

    public String getMotivoCita() { return MotivoCita; }
    public void setMotivoCita(String MotivoCita) { this.MotivoCita = MotivoCita; }

    public String getEstadoCita() { return EstadoCita; }
    public void setEstadoCita(String EstadoCita) { this.EstadoCita = EstadoCita; }

    public int getIdD() { return IdD; }
    public void setIdD(int IdD) { this.IdD = IdD; }
}