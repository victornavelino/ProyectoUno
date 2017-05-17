/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

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
@Table(name="vinculacion_adoptante")
public class Adoptante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String relacionAdoptante;
    @Lob
    private String estadoTransferenciaAdoptante;

    public String getEstadoTransferenciaAdoptante() {
        return estadoTransferenciaAdoptante;
    }

    public void setEstadoTransferenciaAdoptante(String estadoTransferenciaAdoptante) {
        this.estadoTransferenciaAdoptante = estadoTransferenciaAdoptante;
    }

    public String getRelacionAdoptante() {
        return relacionAdoptante;
    }

    public void setRelacionAdoptante(String relacionAdoptante) {
        this.relacionAdoptante = relacionAdoptante;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adoptante)) {
            return false;
        }
        Adoptante other = (Adoptante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pid.Adoptante[ id=" + id + " ]";
    }
    
}
