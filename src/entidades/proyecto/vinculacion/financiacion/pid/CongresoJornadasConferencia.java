/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

import entidades.proyecto.resultado.TipoEdicion;
import entidades.proyecto.resultado.TipoReferato;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.OneToMany;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_congreso_jornadas_conferencia")
@DiscriminatorValue("Congreso Jornada o Conferencia")
public class CongresoJornadasConferencia extends Produccion implements Serializable {
   
    private String estado;
    @Enumerated(EnumType.STRING)
    private TipoEdicion ambito;
    @Enumerated(EnumType.STRING)
    private TipoReferato referato;
    @OneToMany
    private List<Autor> autores;
    @Lob
    private String DescripcionesVarias;

    public String getDescripcionesVarias() {
        return DescripcionesVarias;
    }

    public void setDescripcionesVarias(String DescripcionesVarias) {
        this.DescripcionesVarias = DescripcionesVarias;
    }

    public TipoEdicion getAmbito() {
        return ambito;
    }

    public void setAmbito(TipoEdicion ambito) {
        this.ambito = ambito;
    }

    
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoReferato getReferato() {
        return referato;
    }

    public void setReferato(TipoReferato referato) {
        this.referato = referato;
    }

  
    
    

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pid.CongresoJornadasConferencia[ id=" + super.getId() + " ]";
    }
    
}
