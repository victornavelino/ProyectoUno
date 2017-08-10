/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.persona.investigador;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author huguito
 */
@Entity
@Table(name="investigador_especializacion")
public class Especializacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Investigador investigador;
    private int año;
    @OneToOne
    private EspecialidadInvestigacion especialidadInvestigacion;
    @OneToOne
    private EspecialidadActividadAcademica especialidadActividadAcademica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
    
    public EspecialidadInvestigacion getEspecialidadInvestigacion() {
        return especialidadInvestigacion;
    }

    public void setEspecialidadInvestigacion(EspecialidadInvestigacion especialidadInvestigacion) {
        this.especialidadInvestigacion = especialidadInvestigacion;
    }

    public EspecialidadActividadAcademica getEspecialidadActividadAcademica() {
        return especialidadActividadAcademica;
    }

    public void setEspecialidadActividadAcademica(EspecialidadActividadAcademica especialidadActividadAcademica) {
        this.especialidadActividadAcademica = especialidadActividadAcademica;
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
        if (!(object instanceof Especializacion)) {
            return false;
        }
        Especializacion other = (Especializacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.persona.investigador.Especializacion[ id=" + id + " ]";
    }
}
