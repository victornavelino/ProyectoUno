/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="material")
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    private Integer cantidad;
    private Float costoUnitario;
    private Float costoTotal;
    private Float financiaM;
    private Float financiaC;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Float getFinanciaC() {
        return financiaC;
    }

    public void setFinanciaC(Float financiaC) {
        this.financiaC = financiaC;
    }

    public Float getFinanciaM() {
        return financiaM;
    }

    public void setFinanciaM(Float financiaM) {
        this.financiaM = financiaM;
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
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.Material[ id=" + id + " ]";
    }
    
}
