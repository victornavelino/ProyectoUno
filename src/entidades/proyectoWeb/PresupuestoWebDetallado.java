/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyectoWeb;

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
 * @author diego
 */
@Entity
@Table(name = "proyectoweb_presupuesto_detallado")
public class PresupuestoWebDetallado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval=true)
    private List<BienConsumoWeb> bienConsumoWeb;
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval=true)
    private List<BienNoPersonalWeb> bienNoPersonalWeb;
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval=true)
    private List<BienUsoWeb> bienUsoWeb;
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval=true)
    private List<GastoViajeWeb> gastosViajeWeb;
    
    @OneToOne
    private ProyectoWeb proyectoWeb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BienConsumoWeb> getBienConsumoWeb() {
        return bienConsumoWeb;
    }

    public void setBienConsumoWeb(List<BienConsumoWeb> bienConsumoWeb) {
        this.bienConsumoWeb = bienConsumoWeb;
    }
    

    public List<BienNoPersonalWeb> getBienNoPersonalWeb() {
        return bienNoPersonalWeb;
    }

    public void setBienNoPersonalWeb(List<BienNoPersonalWeb> bienNoPersonalWeb) {
        this.bienNoPersonalWeb = bienNoPersonalWeb;
    }

    public List<BienUsoWeb> getBienUsoWeb() {
        return bienUsoWeb;
    }

    public void setBienUsoWeb(List<BienUsoWeb> bienUsoWeb) {
        this.bienUsoWeb = bienUsoWeb;
    }

    public List<GastoViajeWeb> getGastosViajeWeb() {
        return gastosViajeWeb;
    }

    public void setGastosViajeWeb(List<GastoViajeWeb> gastosViajeWeb) {
        this.gastosViajeWeb = gastosViajeWeb;
    }

    public ProyectoWeb getProyectoWeb() {
        return proyectoWeb;
    }

    public void setProyectoWeb(ProyectoWeb proyectoWeb) {
        this.proyectoWeb = proyectoWeb;
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
        if (!(object instanceof PresupuestoWebDetallado)) {
            return false;
        }
        PresupuestoWebDetallado other = (PresupuestoWebDetallado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyectoWeb.RN.PresupuestoWeb[ id=" + id + " ]";
    }
    
}
