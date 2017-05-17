/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion;

import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.financiacion.pid.AdministracionProyecto;
import entidades.proyecto.vinculacion.financiacion.pid.Adoptante;
import entidades.proyecto.vinculacion.financiacion.pid.ArticuloEnRevista;
import entidades.proyecto.vinculacion.financiacion.pid.BecariosAnpcyt;
import entidades.proyecto.vinculacion.financiacion.pid.CongresoJornadasConferencia;
import entidades.proyecto.vinculacion.financiacion.pid.CronogramaTareas;
import entidades.proyecto.vinculacion.financiacion.pid.LibroYCapitulo;
import entidades.proyecto.vinculacion.financiacion.pid.ObjetivosYResultados;
import entidades.proyecto.vinculacion.financiacion.pid.Patente;
import entidades.proyecto.vinculacion.financiacion.pid.PublicacionOtra;
import entidades.proyecto.vinculacion.financiacion.pid.RecursosHumanosFormados;
import entidades.proyecto.vinculacion.financiacion.pid.VinculacionOtrasEntidades;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_financiacion_pid")
@DiscriminatorValue("PID")
public class FinanciacionPid extends Financiacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int numeroIta;
    private int añoConvocatoria;
    private int numero;//numero convocatoria?
    private int duracion;//duracion en años
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    private String intitucionBeneficiaria;
    private String adoptanteNombre;
    private int integrantesGR;
    private double montoSubsidioPercibidoAlaFecha;
    private double porcentajeSubsidioPercibidoSobreTotalOtorgado;
    private double montoAporteAdoptantePercibidoAlaFecha;
    private double porcentajeAporteAdoptantePercibidoSobreTotal;
    @OneToMany
    private List<ObjetivosYResultados> objetivos;
    @OneToMany
    private List<CronogramaTareas> tareas;
    @OneToOne
    private Adoptante adoptante;
    @OneToMany
    private List<VinculacionOtrasEntidades> vinculos;
    @OneToMany
    private List<Patente> patentesProducidas;
    @OneToMany
    private List<ArticuloEnRevista> articulosPublicados;
    @OneToMany
    private List<LibroYCapitulo> LibrosYCapitulos;
    @OneToMany
    private List<CongresoJornadasConferencia> comunicaciones;
    @OneToMany
    private List<PublicacionOtra> otrasPublicaciones;
    @OneToMany
    private List<BecariosAnpcyt> listaBecarios;
    @OneToMany
    private List<RecursosHumanosFormados> listaRRHH;
    @OneToOne
    private AdministracionProyecto administracionDescripcion;

    public List<LibroYCapitulo> getLibrosYCapitulos() {
        return LibrosYCapitulos;
    }

    public void setLibrosYCapitulos(List<LibroYCapitulo> LibrosYCapitulos) {
        this.LibrosYCapitulos = LibrosYCapitulos;
    }

    public AdministracionProyecto getAdministracionDescripcion() {
        return administracionDescripcion;
    }

    public void setAdministracionDescripcion(AdministracionProyecto administracionDescripcion) {
        this.administracionDescripcion = administracionDescripcion;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;
    }

    public String getAdoptanteNombre() {
        return adoptanteNombre;
    }

    public void setAdoptanteNombre(String adoptanteNombre) {
        this.adoptanteNombre = adoptanteNombre;
    }

    public List<ArticuloEnRevista> getArticulosPublicados() {
        return articulosPublicados;
    }

    public void setArticulosPublicados(List<ArticuloEnRevista> articulosPublicados) {
        this.articulosPublicados = articulosPublicados;
    }

    public int getAñoConvocatoria() {
        return añoConvocatoria;
    }

    public void setAñoConvocatoria(int añoConvocatoria) {
        this.añoConvocatoria = añoConvocatoria;
    }

    public List<CongresoJornadasConferencia> getComunicaciones() {
        return comunicaciones;
    }

    public void setComunicaciones(List<CongresoJornadasConferencia> comunicaciones) {
        this.comunicaciones = comunicaciones;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getIntegrantesGR() {
        return integrantesGR;
    }

    public void setIntegrantesGR(int integrantesGR) {
        this.integrantesGR = integrantesGR;
    }

    public String getIntitucionBeneficiaria() {
        return intitucionBeneficiaria;
    }

    public void setIntitucionBeneficiaria(String intitucionBeneficiaria) {
        this.intitucionBeneficiaria = intitucionBeneficiaria;
    }

    public List<BecariosAnpcyt> getListaBecarios() {
        return listaBecarios;
    }

    public void setListaBecarios(List<BecariosAnpcyt> listaBecarios) {
        this.listaBecarios = listaBecarios;
    }

    public List<RecursosHumanosFormados> getListaRRHH() {
        return listaRRHH;
    }

    public void setListaRRHH(List<RecursosHumanosFormados> listaRRHH) {
        this.listaRRHH = listaRRHH;
    }

    public double getMontoAporteAdoptantePercibidoAlaFecha() {
        return montoAporteAdoptantePercibidoAlaFecha;
    }

    public void setMontoAporteAdoptantePercibidoAlaFecha(double montoAporteAdoptantePercibidoAlaFecha) {
        this.montoAporteAdoptantePercibidoAlaFecha = montoAporteAdoptantePercibidoAlaFecha;
    }

    public double getMontoSubsidioPercibidoAlaFecha() {
        return montoSubsidioPercibidoAlaFecha;
    }

    public void setMontoSubsidioPercibidoAlaFecha(double montoSubsidioPercibidoAlaFecha) {
        this.montoSubsidioPercibidoAlaFecha = montoSubsidioPercibidoAlaFecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumeroIta() {
        return numeroIta;
    }

    public void setNumeroIta(int numeroIta) {
        this.numeroIta = numeroIta;
    }

    public List<ObjetivosYResultados> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivosYResultados> objetivos) {
        this.objetivos = objetivos;
    }

    public List<PublicacionOtra> getOtrasPublicaciones() {
        return otrasPublicaciones;
    }

    public void setOtrasPublicaciones(List<PublicacionOtra> otrasPublicaciones) {
        this.otrasPublicaciones = otrasPublicaciones;
    }

    public List<Patente> getPatentesProducidas() {
        return patentesProducidas;
    }

    public void setPatentesProducidas(List<Patente> patentesProducidas) {
        this.patentesProducidas = patentesProducidas;
    }

    public double getPorcentajeAporteAdoptantePercibidoSobreTotal() {
        return porcentajeAporteAdoptantePercibidoSobreTotal;
    }

    public void setPorcentajeAporteAdoptantePercibidoSobreTotal(double porcentajeAporteAdoptantePercibidoSobreTotal) {
        this.porcentajeAporteAdoptantePercibidoSobreTotal = porcentajeAporteAdoptantePercibidoSobreTotal;
    }

    public double getPorcentajeSubsidioPercibidoSobreTotalOtorgado() {
        return porcentajeSubsidioPercibidoSobreTotalOtorgado;
    }

    public void setPorcentajeSubsidioPercibidoSobreTotalOtorgado(double porcentajeSubsidioPercibidoSobreTotalOtorgado) {
        this.porcentajeSubsidioPercibidoSobreTotalOtorgado = porcentajeSubsidioPercibidoSobreTotalOtorgado;
    }

    public List<CronogramaTareas> getTareas() {
        return tareas;
    }

    public void setTareas(List<CronogramaTareas> tareas) {
        this.tareas = tareas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<VinculacionOtrasEntidades> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<VinculacionOtrasEntidades> vinculos) {
        this.vinculos = vinculos;
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
        if (!(object instanceof FinanciacionPid)) {
            return false;
        }
        FinanciacionPid other = (FinanciacionPid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.FinanciacionPid[ id=" + id + " ]";
    }
    
}
