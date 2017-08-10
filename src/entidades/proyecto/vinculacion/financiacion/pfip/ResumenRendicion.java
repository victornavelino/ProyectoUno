/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="resumenrendicion")
public class ResumenRendicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroplanilla;
    private String rubro;
    private Float total;
    private Float afinanciarSCTIP;

    public Float getAfinanciarSCTIP() {
        return afinanciarSCTIP;
    }

    public void setAfinanciarSCTIP(Float afinanciarSCTIP) {
        this.afinanciarSCTIP = afinanciarSCTIP;
    }

    public String getNumeroplanilla() {
        return numeroplanilla;
    }

    public void setNumeroplanilla(String numeroplanilla) {
        this.numeroplanilla = numeroplanilla;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
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
        if (!(object instanceof ResumenRendicion)) {
            return false;
        }
        ResumenRendicion other = (ResumenRendicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.ResumenRendicion[ id=" + id + " ]";
    }
    
}
