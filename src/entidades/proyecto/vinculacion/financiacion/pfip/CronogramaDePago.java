/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPfip;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="cronogramadepago")
public class CronogramaDePago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etapaDeCronograma;
   
    private Long financiacionId;

 
    private Float desembolso;
    @OneToOne
    private Etapa etapaAEjecutar;
    private Float costoEjecucion;
    private Float saldo;
    
    public Long getFinanciacionId() {
        return financiacionId;
    }

    public void setFinanciacionId(Long financiacionId) {
        this.financiacionId = financiacionId;
    }
 
   
    
       public String getEtapaDeCronograma() {
        return etapaDeCronograma;
    }

    public void setEtapaDeCronograma(String etapaDeCronograma) {
        this.etapaDeCronograma = etapaDeCronograma;
    }


    public Float getCostoEjecucion() {
        return costoEjecucion;
    }

    public void setCostoEjecucion(Float costoEjecucion) {
        this.costoEjecucion = costoEjecucion;
    }

    public Float getDesembolso() {
        return desembolso;
    }

    public void setDesembolso(Float desembolso) {
        this.desembolso = desembolso;
    }

   

    public Etapa getEtapaAEjecutar() {
        return etapaAEjecutar;
    }

    public void setEtapaAEjecutar(Etapa etapaAEjecutar) {
        this.etapaAEjecutar = etapaAEjecutar;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
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
        if (!(object instanceof CronogramaDePago)) {
            return false;
        }
        CronogramaDePago other = (CronogramaDePago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.CronogramaDePago[ id=" + id + " ]";
    }
    
}
