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
@Table(name="recursohumanoadquirir")
public class RecursoHumanoAdquirir implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private String especialidad;
  private String funcion;
    
    private Float dedicacion;
    private Integer mesesParticipacion;
    private Float costoTotalMensual;

   
    private Float costoTotal;

    private Float financiaM;
    private Float financiaC;
    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Float getCostoTotalMensual() {
        return costoTotalMensual;
    }

    public void setCostoTotalMensual(Float costoTotalMensual) {
        this.costoTotalMensual = costoTotalMensual;
    }

    public Float getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(Float dedicacion) {
        this.dedicacion = dedicacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Integer getMesesParticipacion() {
        return mesesParticipacion;
    }

    public void setMesesParticipacion(Integer mesesParticipacion) {
        this.mesesParticipacion = mesesParticipacion;
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
        if (!(object instanceof RecursoHumanoAdquirir)) {
            return false;
        }
        RecursoHumanoAdquirir other = (RecursoHumanoAdquirir) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.RecursoHumanoAdquirir[ id=" + id + " ]";
    }
    
}
