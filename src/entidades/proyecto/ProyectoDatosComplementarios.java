/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto;

import entidades.localidad.Localidad;
import entidades.proyectoWeb.Convocatoria;
import entidades.proyectoWeb.SectorPrioritario;
import entidades.proyectoWeb.TipoFinanciamiento;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author hugo
 */
@Entity
@Table(name = "proyectodatoscomplementarios")
public class ProyectoDatosComplementarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Localidad localidad;
    @OneToOne
    private Proyecto proyecto;
    @ManyToOne
    private Convocatoria convocatoria;
    @Lob
    private String antecedentes;
    @Lob
    private String capacitacionFormacionRH;
    @Lob
    private String cronogramaTrabajo;
    private Boolean finalizado;
    private String localizacionOtra;
    @Lob
    private String metodologia;
    @Lob
    private String objetivoEspecificoHipotesis;
    @Lob
    private String objetivoGral;
    private Boolean proyectoIniciacion;
    private Boolean proyectoOrientado;
    @Lob
    private String relevanciaProblema;
    private String subDisciplinaCientificaOtra;
    private String unidadEjecutoraOtra;
    //NUEVOS DATOS
    private double latitud;
    private double longitud;
    private SectorPrioritario sectorPrioritario;
    private String sectorPrioritarioOtro;
    @Enumerated
    private TipoFinanciamiento tipoFinanciamiento;

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

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

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getCapacitacionFormacionRH() {
        return capacitacionFormacionRH;
    }

    public void setCapacitacionFormacionRH(String capacitacionFormacionRH) {
        this.capacitacionFormacionRH = capacitacionFormacionRH;
    }

    public String getCronogramaTrabajo() {
        return cronogramaTrabajo;
    }

    public void setCronogramaTrabajo(String cronogramaTrabajo) {
        this.cronogramaTrabajo = cronogramaTrabajo;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getLocalizacionOtra() {
        return localizacionOtra;
    }

    public void setLocalizacionOtra(String localizacionOtra) {
        this.localizacionOtra = localizacionOtra;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getObjetivoEspecificoHipotesis() {
        return objetivoEspecificoHipotesis;
    }

    public void setObjetivoEspecificoHipotesis(String objetivoEspecificoHipotesis) {
        this.objetivoEspecificoHipotesis = objetivoEspecificoHipotesis;
    }

    public String getObjetivoGral() {
        return objetivoGral;
    }

    public void setObjetivoGral(String objetivoGral) {
        this.objetivoGral = objetivoGral;
    }

    public Boolean getProyectoIniciacion() {
        return proyectoIniciacion;
    }

    public void setProyectoIniciacion(Boolean proyectoIniciacion) {
        this.proyectoIniciacion = proyectoIniciacion;
    }

    public Boolean getProyectoOrientado() {
        return proyectoOrientado;
    }

    public void setProyectoOrientado(Boolean proyectoOrientado) {
        this.proyectoOrientado = proyectoOrientado;
    }

    public String getRelevanciaProblema() {
        return relevanciaProblema;
    }

    public void setRelevanciaProblema(String relevanciaProblema) {
        this.relevanciaProblema = relevanciaProblema;
    }

    public String getSubDisciplinaCientificaOtra() {
        return subDisciplinaCientificaOtra;
    }

    public void setSubDisciplinaCientificaOtra(String subDisciplinaCientificaOtra) {
        this.subDisciplinaCientificaOtra = subDisciplinaCientificaOtra;
    }

    public String getUnidadEjecutoraOtra() {
        return unidadEjecutoraOtra;
    }

    public void setUnidadEjecutoraOtra(String unidadEjecutoraOtra) {
        this.unidadEjecutoraOtra = unidadEjecutoraOtra;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public SectorPrioritario getSectorPrioritario() {
        return sectorPrioritario;
    }

    public void setSectorPrioritario(SectorPrioritario sectorPrioritario) {
        this.sectorPrioritario = sectorPrioritario;
    }

    public String getSectorPrioritarioOtro() {
        return sectorPrioritarioOtro;
    }

    public void setSectorPrioritarioOtro(String sectorPrioritarioOtro) {
        this.sectorPrioritarioOtro = sectorPrioritarioOtro;
    }

    public TipoFinanciamiento getTipoFinanciamiento() {
        return tipoFinanciamiento;
    }

    public void setTipoFinanciamiento(TipoFinanciamiento tipoFinanciamiento) {
        this.tipoFinanciamiento = tipoFinanciamiento;
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
        if (!(object instanceof ProyectoDatosComplementarios)) {
            return false;
        }
        ProyectoDatosComplementarios other = (ProyectoDatosComplementarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.ProyectoDatosComplementarios[ id=" + id + " ]";
    }

}
