/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.vinculacion.Financiacion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */

@Entity
@Table(name="vinculacion_financiacion_prh")
@DiscriminatorValue("PRH")
public class FinanciacionPrh extends Financiacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Investigador responsable;
//    private List<FinanciacionPfip> listapfip;
//    private List<FinanciacionPict> listapict;
//    private List<FinanciacionPidri> listapidri;
//    private List<FinanciacionPfdt> listapfdt;
    //private List<FinanciacionPme> listapme;
    
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
        if (!(object instanceof FinanciacionPrh)) {
            return false;
        }
        FinanciacionPrh other = (FinanciacionPrh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.FinanciacionPrh[ id=" + id + " ]";
    }
    
}
