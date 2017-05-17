/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion;

import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.financiacion.pfip.BienDeCapital;
import entidades.proyecto.vinculacion.financiacion.pfip.BienDeCapitalAAdquirir;
import entidades.proyecto.vinculacion.financiacion.pfip.Consultoria;
import entidades.proyecto.vinculacion.financiacion.pfip.Convenio;
import entidades.proyecto.vinculacion.financiacion.pfip.CronogramaDePago;
import entidades.proyecto.vinculacion.financiacion.pfip.Etapa;
import entidades.proyecto.vinculacion.financiacion.pfip.Material;
import entidades.proyecto.vinculacion.financiacion.pfip.OtroRecursoAdquirir;
import entidades.proyecto.vinculacion.financiacion.pfip.OtroRecursoDisponible;
import entidades.proyecto.vinculacion.financiacion.pfip.RecursoHumanoAdquirir;
import entidades.proyecto.vinculacion.financiacion.pfip.RecursoHumanoDisponible;
import entidades.proyecto.vinculacion.financiacion.pfip.RendicionPfipDetem;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="vinculacion_financiacion_generica")
public class FinanciacionGenerica extends Financiacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaTematica;
    private String categoria;
    private String institucionbeneficiaria;
    private String representanteinstben;
        @Lob
    private String institucionesproyecto;
        
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Consultoria> consultorias;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Material> materiales;
        @OneToOne(cascade = CascadeType.ALL)
    private Convenio convenio;
   
    @OneToMany(cascade = CascadeType.ALL)
    private List<RecursoHumanoDisponible> recursosHumanosDisponibles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CronogramaDePago> cronogramasdepagos;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RecursoHumanoAdquirir> recursosHumanosAdquirir;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OtroRecursoDisponible> otrosRecursoDisponibles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OtroRecursoAdquirir> otroRecursoAdquirir;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RendicionPfipDetem> rendicioncuenta;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Etapa> etapas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BienDeCapital> bienesdecapital;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BienDeCapitalAAdquirir> bienesdecapitaladquirir;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Consultoria> getConsultorias() {
        return consultorias;
    }

    public void setConsultorias(List<Consultoria> consultorias) {
        this.consultorias = consultorias;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public String getInstitucionesproyecto() {
        return institucionesproyecto;
    }

    public void setInstitucionesproyecto(String institucionesproyecto) {
        this.institucionesproyecto = institucionesproyecto;
    }


    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getInstitucionbeneficiaria() {
        return institucionbeneficiaria;
    }

    public void setInstitucionbeneficiaria(String institucionbeneficiaria) {
        this.institucionbeneficiaria = institucionbeneficiaria;
    }

    public String getRepresentanteinstben() {
        return representanteinstben;
    }

    public void setRepresentanteinstben(String representanteinstben) {
        this.representanteinstben = representanteinstben;
    }

    public List<RecursoHumanoDisponible> getRecursosHumanosDisponibles() {
        return recursosHumanosDisponibles;
    }

    public void setRecursosHumanosDisponibles(List<RecursoHumanoDisponible> recursosHumanosDisponibles) {
        this.recursosHumanosDisponibles = recursosHumanosDisponibles;
    }

    public List<CronogramaDePago> getCronogramasdepagos() {
        return cronogramasdepagos;
    }

    public void setCronogramasdepagos(List<CronogramaDePago> cronogramasdepagos) {
        this.cronogramasdepagos = cronogramasdepagos;
    }

    public List<RecursoHumanoAdquirir> getRecursosHumanosAdquirir() {
        return recursosHumanosAdquirir;
    }

    public void setRecursosHumanosAdquirir(List<RecursoHumanoAdquirir> recursosHumanosAdquirir) {
        this.recursosHumanosAdquirir = recursosHumanosAdquirir;
    }

    public List<OtroRecursoDisponible> getOtrosRecursoDisponibles() {
        return otrosRecursoDisponibles;
    }

    public void setOtrosRecursoDisponibles(List<OtroRecursoDisponible> otrosRecursoDisponibles) {
        this.otrosRecursoDisponibles = otrosRecursoDisponibles;
    }

    public List<OtroRecursoAdquirir> getOtroRecursoAdquirir() {
        return otroRecursoAdquirir;
    }

    public void setOtroRecursoAdquirir(List<OtroRecursoAdquirir> otroRecursoAdquirir) {
        this.otroRecursoAdquirir = otroRecursoAdquirir;
    }

    public List<RendicionPfipDetem> getRendicioncuenta() {
        return rendicioncuenta;
    }

    public void setRendicioncuenta(List<RendicionPfipDetem> rendicioncuenta) {
        this.rendicioncuenta = rendicioncuenta;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
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

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinanciacionGenerica)) {
            return false;
        }
        FinanciacionGenerica other = (FinanciacionGenerica) object;
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
