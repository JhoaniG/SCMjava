/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jhoan
 */
public class Dieta {
    public int IdDi;
    public int IdM;
    public int IdV;
    public String Descripcion;
    public String TipoDieta;

    public Dieta(int IdDi, int IdM, int IdV, String Descripcion, String TipoDieta) {
        this.IdDi = IdDi;
        this.IdM = IdM;
        this.IdV = IdV;
        this.Descripcion = Descripcion;
        this.TipoDieta = TipoDieta;
    }

    public int getIdDi() {
        return IdDi;
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

    public String getTipoDieta() {
        return TipoDieta;
    }

    public void setIdDi(int IdDi) {
        this.IdDi = IdDi;
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

    public void setTipoDieta(String TipoDieta) {
        this.TipoDieta = TipoDieta;
    }
    
    
}
