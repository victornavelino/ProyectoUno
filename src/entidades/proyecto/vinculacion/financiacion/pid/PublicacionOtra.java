/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

import entidades.proyecto.resultado.TipoEdicion;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_publicacion_otra")
public class PublicacionOtra extends Produccion implements Serializable {
   
    private String estado;
    @Enumerated(EnumType.STRING)
    private TipoEdicion ambito;
    private String registro;
    @Lob
    private String descripcionesVarias;

    public TipoEdicion getAmbito() {
        return ambito;
    }

    public void setAmbito(TipoEdicion ambito) {
        this.ambito = ambito;
    }

        
    public String getDescripcionesVarias() {
        return descripcionesVarias;
    }

    public void setDescripcionesVarias(String descripcionesVarias) {
        this.descripcionesVarias = descripcionesVarias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
    
    

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pid.PublicacionOtra[ id=" + super.getId() + " ]";
    }
    
}
