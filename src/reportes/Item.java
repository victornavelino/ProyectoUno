/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

/**
 *
 * @author ruben
 */
public class Item {
    private int año;
    private String nombre;
    private String descripcion;
    private Float montocontraparte;
    private Float montosubsidio;

       
    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getMontocontraparte() {
        return montocontraparte;
    }

    public void setMontocontraparte(Float montocontraparte) {
        this.montocontraparte = montocontraparte;
    }

    public Float getMontosubsidio() {
        return montosubsidio;
    }

    public void setMontosubsidio(Float montosubsidio) {
        this.montosubsidio = montosubsidio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
