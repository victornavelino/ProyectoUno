/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion;

import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.financiacion.pfip.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="vinculacion_financiacion_detem")
@DiscriminatorValue("Detem")
public class FinanciacionDetem extends Financiacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Etapa> etapas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BienDeCapital> bienesdecapital;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BienDeCapitalAAdquirir> bienesdecapitaladquirir;

    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Consultoria> consultorias;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Material> materiales;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RecursoHumanoDisponible> recursosHumanosDisponibles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CronogramaDePago> cronogramasdepagos;
    @OneToOne(cascade = CascadeType.ALL)
    private Convenio convenio;
   
    @OneToMany(cascade = CascadeType.ALL)
    private List<RecursoHumanoAdquirir> recursosHumanosAdquirir;


    
    @OneToMany(cascade = CascadeType.ALL)
    private List<OtroRecursoDisponible> otrosRecursoDisponibles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OtroRecursoAdquirir> otroRecursoAdquirir;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<RendicionPfipDetem> rendicioncuenta;

    public List<RendicionPfipDetem> getRendicioncuenta() {
        return rendicioncuenta;
    }

    public void setRendicioncuenta(List<RendicionPfipDetem> rendicioncuenta) {
        this.rendicioncuenta = rendicioncuenta;
    }
    
    

    public List<BienDeCapital> getBienesdecapital() {
        return bienesdecapital;
    }

    public void setBienesdecapital(List<BienDeCapital> bienesdecapital) {
        this.bienesdecapital = bienesdecapital;
    }

    public List<BienDeCapitalAAdquirir> getBienesdecapitaladquirir() {
        return bienesdecapitaladquirir;
    }

    public void setBienesdecapitaladquirir(List<BienDeCapitalAAdquirir> bienesdecapitaladquirir) {
        this.bienesdecapitaladquirir = bienesdecapitaladquirir;
    }

    public List<Consultoria> getConsultorias() {
        return consultorias;
    }

    public void setConsultorias(List<Consultoria> consultorias) {
        this.consultorias = consultorias;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public List<CronogramaDePago> getCronogramasdepagos() {
        return cronogramasdepagos;
    }

    public void setCronogramasdepagos(List<CronogramaDePago> cronogramasdepagos) {
        this.cronogramasdepagos = cronogramasdepagos;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }

    public List<OtroRecursoAdquirir> getOtroRecursoAdquirir() {
        return otroRecursoAdquirir;
    }

    public void setOtroRecursoAdquirir(List<OtroRecursoAdquirir> otroRecursoAdquirir) {
        this.otroRecursoAdquirir = otroRecursoAdquirir;
    }

    public List<OtroRecursoDisponible> getOtrosRecursoDisponibles() {
        return otrosRecursoDisponibles;
    }

    public void setOtrosRecursoDisponibles(List<OtroRecursoDisponible> otrosRecursoDisponibles) {
        this.otrosRecursoDisponibles = otrosRecursoDisponibles;
    }

    public List<RecursoHumanoAdquirir> getRecursosHumanosAdquirir() {
        return recursosHumanosAdquirir;
    }

    public void setRecursosHumanosAdquirir(List<RecursoHumanoAdquirir> recursosHumanosAdquirir) {
        this.recursosHumanosAdquirir = recursosHumanosAdquirir;
    }

    public List<RecursoHumanoDisponible> getRecursosHumanosDisponibles() {
        return recursosHumanosDisponibles;
    }

    public void setRecursosHumanosDisponibles(List<RecursoHumanoDisponible> recursosHumanosDisponibles) {
        this.recursosHumanosDisponibles = recursosHumanosDisponibles;
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
        if (!(object instanceof FinanciacionDetem)) {
            return false;
        }
        FinanciacionDetem other = (FinanciacionDetem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
    
}
