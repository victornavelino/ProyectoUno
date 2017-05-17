/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.picto;

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
@Table(name="resumen")
public class Resumen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private Float aportefoncyt;
    private Float aporteinstituciones;
    private Float otrosaportes;
    private Float total;

    public Float getAportefoncyt() {
        return aportefoncyt;
    }

    public void setAportefoncyt(Float aportefoncyt) {
        this.aportefoncyt = aportefoncyt;
    }

    public Float getAporteinstituciones() {
        return aporteinstituciones;
    }

    public void setAporteinstituciones(Float aporteinstituciones) {
        this.aporteinstituciones = aporteinstituciones;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Float getOtrosaportes() {
        return otrosaportes;
    }

    public void setOtrosaportes(Float otrosaportes) {
        this.otrosaportes = otrosaportes;
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
        if (!(object instanceof Resumen)) {
            return false;
        }
        Resumen other = (Resumen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.picto.Resumen[ id=" + id + " ]";
    }
    
}
