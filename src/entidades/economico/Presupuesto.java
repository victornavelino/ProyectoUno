/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.economico;

import entidades.proyecto.Proyecto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name = "economico_presupuesto")
public class Presupuesto implements Serializable {
    @OneToOne
    private Proyecto proyecto;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade= CascadeType.ALL)
    private List<BienConsumo> bienConsumo;
    @OneToMany(cascade= CascadeType.ALL)
    private List<BienNoPersonal> bienNoPersonal;
    @OneToMany(cascade= CascadeType.ALL)
    private List<BienUso> bienUso;
    @OneToMany(cascade= CascadeType.ALL)
    private List<GastoViaje> gastosViaje;
    @OneToMany(mappedBy = "presupuesto")
    private List<ModificacionPresupuesto> modificacionPresupuesto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<BienConsumo> getBienConsumo() {
        return bienConsumo;
    }

    public void setBienConsumo(List<BienConsumo> bienConsumo) {
        this.bienConsumo = bienConsumo;
    }

    public List<BienNoPersonal> getBienNoPersonal() {
        return bienNoPersonal;
    }

    public void setBienNoPersonal(List<BienNoPersonal> bienNoPersonal) {
        this.bienNoPersonal = bienNoPersonal;
    }

    public List<BienUso> getBienUso() {
        return bienUso;
    }

    public void setBienUso(List<BienUso> bienUso) {
        this.bienUso = bienUso;
    }

    public List<GastoViaje> getGastosViaje() {
        return gastosViaje;
    }

    public void setGastosViaje(List<GastoViaje> gastosViaje) {
        this.gastosViaje = gastosViaje;
    }

    public List<ModificacionPresupuesto> getModificacionPresupuesto() {
        return modificacionPresupuesto;
    }

    public void setModificacionPresupuesto(List<ModificacionPresupuesto> modificacionPresupuesto) {
        this.modificacionPresupuesto = modificacionPresupuesto;
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
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.economico.presupuesto[ id=" + id + " ]";
    }
    
}
