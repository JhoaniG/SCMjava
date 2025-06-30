package modelo;

/**
 *
 * @author jhoan
 */
public class ActividadFisica {
    public int IdF;
    public int IdM;
    public int IdV;
    public String Descripcion;
    public String TipoActividad;
    
    // Nuevos atributos para la vista
    public String nombreMascota;
    public String nombreVeterinario;

    public ActividadFisica() {
    }

    public ActividadFisica(int IdF, int IdM, int IdV, String Descripcion, String TipoActividad) {
        this.IdF = IdF;
        this.IdM = IdM;
        this.IdV = IdV;
        this.Descripcion = Descripcion;
        this.TipoActividad = TipoActividad;
    }

    // Constructor con los nuevos campos para la vista
    public ActividadFisica(int IdF, int IdM, int IdV, String Descripcion, String TipoActividad, String nombreMascota, String nombreVeterinario) {
        this.IdF = IdF;
        this.IdM = IdM;
        this.IdV = IdV;
        this.Descripcion = Descripcion;
        this.TipoActividad = TipoActividad;
        this.nombreMascota = nombreMascota;
        this.nombreVeterinario = nombreVeterinario;
    }
    
    public int getIdF() {
        return IdF;
    }

    public int getIdM() {
        return IdM;
    }

    public int getIdV() {
        return IdV;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getTipoActividad() {
        return TipoActividad;
    }

    public void setIdF(int IdF) {
        this.IdF = IdF;
    }

    public void setIdM(int IdM) {
        this.IdM = IdM;
    }

    public void setIdV(int IdV) {
        this.IdV = IdV;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setTipoActividad(String TipoActividad) {
        this.TipoActividad = TipoActividad;
    }

    // Nuevos getters y setters
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
}