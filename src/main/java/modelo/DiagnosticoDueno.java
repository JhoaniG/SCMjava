package modelo;

import java.sql.Date;

public class DiagnosticoDueno {

    private int IdD;
    private int IdM;
    private int IdV;
    private Date FechaDiagnostico;
    private String Observaciones;
    private String NombreM;            // Nombre de la mascota
    private String NombreDueno;        // ✅ Nombre del dueño
    private String NombreVeterinario;  // ✅ Nombre del veterinario

    public DiagnosticoDueno() {
    }

    public DiagnosticoDueno(int IdD, int IdM, int IdV, Date FechaDiagnostico, String Observaciones, String NombreM, String NombreDueno, String NombreVeterinario) {
        this.IdD = IdD;
        this.IdM = IdM;
        this.IdV = IdV;
        this.FechaDiagnostico = FechaDiagnostico;
        this.Observaciones = Observaciones;
        this.NombreM = NombreM;
        this.NombreDueno = NombreDueno;
        this.NombreVeterinario = NombreVeterinario;
    }

    // Getters
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

    public String getNombreDueno() {
        return NombreDueno;
    }

    public String getNombreVeterinario() {
        return NombreVeterinario;
    }

    // Setters
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

    public void setNombreDueno(String NombreDueno) {
        this.NombreDueno = NombreDueno;
    }

    public void setNombreVeterinario(String NombreVeterinario) {
        this.NombreVeterinario = NombreVeterinario;
    }
}

