/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="consultoria")
public class Consultoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Integer mesInicio;
    private Integer mesFin;
        @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    private Float costoTotal;
    private Float financiaM;
    private String prueba;

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getMesFin() {
        return mesFin;
    }

    public void setMesFin(Integer mesFin) {
        this.mesFin = mesFin;
    }

    public Integer getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(Integer mesInicio) {
        this.mesInicio = mesInicio;
    }
    private Float financiaC;
    
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
        if (!(object instanceof Consultoria)) {
            return false;
        }
        Consultoria other = (Consultoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.Consultoria[ id=" + id + " ]";
    }
    
}
