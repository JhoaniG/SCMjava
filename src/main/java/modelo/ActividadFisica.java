/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public ActividadFisica() {
    }

    public ActividadFisica(int IdF, int IdM, int IdV, String Descripcion, String TipoActividad) {
        this.IdF = IdF;
        this.IdM = IdM;
        this.IdV = IdV;
        this.Descripcion = Descripcion;
        this.TipoActividad = TipoActividad;
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
    
    
    
    
    
}
