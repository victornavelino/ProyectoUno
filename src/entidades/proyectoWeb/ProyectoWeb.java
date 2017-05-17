/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyectoWeb;

import entidades.Resolucion;
import entidades.TipoProyecto;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.economico.PagoEconomico;
import entidades.economico.Presupuesto;
import entidades.proyecto.CampoAplicacion;
import entidades.proyecto.EntidadConvenio;
import entidades.proyecto.EntidadEvaluadora;
import entidades.proyecto.Especialidad;
import entidades.proyecto.Evaluacion;
import entidades.proyecto.LineaInvestigacion;
import entidades.proyecto.ObjetivoSocioeconomico;
import entidades.proyecto.Participacion;
import entidades.proyecto.Programa;
import entidades.proyecto.Prorroga;
import entidades.proyecto.Proyecto;
import entidades.proyecto.SubDisciplinaCientifica;
import entidades.proyecto.UnidadInvestigacion;
import entidades.proyecto.resultado.Contrato;
import entidades.proyecto.resultado.FormacionRRHH;
import entidades.proyecto.resultado.Propiedad;
import entidades.proyecto.resultado.Publicacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "proyectoweb")
public class ProyectoWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Convocatoria convocatoria;

    //I. IDENTIFICACION DEL PROYECTO
    //1
    @Lob // Porque con String no solo no bastaba. Hay títulos re largos!
    private String titulo;

    //2
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaFinalizacion;
    
    //3
    private String codigoCarga;
    
    private String codigoSecyt;
    
    //4
    @Enumerated
    private TipoFinanciamiento tipoFinanciamiento;
    
    //5
    private Boolean participaEnPrograma;

    //se genera solo
    @Column(unique = true)
    private String codigo;

    //director y codirector
    @OneToMany(mappedBy = "proyectoWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipacionWeb> participacionesWeb;

    //5. UNIDAD 
    //dura unidadAcademica tiene una lista de unidadEjecutora
    @OneToOne
    private UnidadAcademica unidadAcademica;

    @OneToOne
    private UnidadEjecutora unidadEjecutora;

    private String unidadEjecutoraOtra;

    //seria localizacion del proyecto
    @OneToMany
    private List<UnidadInvestigacion> localizaciones;

    private String localizacionOtra;
    
    private double latitud;
    
    private double longitud;
    //6. LINEA PRIORITARIA
    @OneToOne
    private LineaInvestigacion lineaInvestigacion;
    
    
    private SectorPrioritario sectorPrioritario;
    
    private String sectorPrioritarioOtro;

    //7.CARACTERISTICAS
    //seria Area temática - Disciplina Científica - Subdisciplinas Científicas
    @OneToMany
    private List<SubDisciplinaCientifica> subDisciplinasCientificas;

    private String subDisciplinaCientificaOtra;

    @OneToMany
    private List<CampoAplicacion> camposAplicacion;

    @OneToOne
    private ObjetivoSocioeconomico objetivoSocioeconomico;

    //8. CATEGORIA
    private Boolean proyectoOrientado;

    private Boolean proyectoIniciacion;

    //9. TIPO DE ACTIVIDAD
    @OneToOne
    private TipoProyecto tipoProyecto;

    //II. Resumen del Proyecto
    @Lob
    private String resumen;

    private List<String> palabrasClaves;

    @Lob
    private String title;

    @Lob
    private String summary;

    private List<String> keywords;

    //III. Formulación General del Proyecto
    @OneToOne(cascade = CascadeType.ALL)
    private ProyectoWebFGP proyectoWebFGP;

    
    

    //VI: Presupuesto
    @OneToOne(mappedBy = "proyectoWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private PresupuestoWeb presupuestoWeb;

    @OneToOne(mappedBy = "proyectoWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private PresupuestoWebDetallado presupuestoWebAnioUno;

    @OneToOne(mappedBy = "proyectoWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private PresupuestoWebDetalladoDos presupuestoWebAnioDos;
    @OneToOne(mappedBy = "proyectoWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private PresupuestoWebDetalladoTres presupuestoWebAnioTres;
    @OneToOne(mappedBy = "proyectoWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private PresupuestoWebDetalladoCuatro presupuestoWebAnioCuatro;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RecursoSubsidio> recursosSubsidios;

    //VII: Archivos
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArchivoWeb> lstArchivoWeb;

    @Temporal(TemporalType.DATE)
    private Date fechaArchivo;

    //CONTROL
    private Boolean finalizado;

    private Boolean aprobado;

    @Lob
    private String observaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectorPrioritarioOtro() {
        return sectorPrioritarioOtro;
    }

    public void setSectorPrioritarioOtro(String sectorPrioritarioOtro) {
        this.sectorPrioritarioOtro = sectorPrioritarioOtro;
    }
    
    public SectorPrioritario getSectorPrioritario() {
        return sectorPrioritario;
    }

    public void setSectorPrioritario(SectorPrioritario sectorPrioritario) {
        this.sectorPrioritario = sectorPrioritario;
    }

    
    public String getCodigoCarga() {
        return codigoCarga;
    }

    public void setCodigoCarga(String codigoCarga) {
        this.codigoCarga = codigoCarga;
    }

    public String getCodigoSecyt() {
        return codigoSecyt;
    }

    public void setCodigoSecyt(String codigoSecyt) {
        this.codigoSecyt = codigoSecyt;
    }

    public TipoFinanciamiento getTipoFinanciamiento() {
        return tipoFinanciamiento;
    }

    public void setTipoFinanciamiento(TipoFinanciamiento tipoFinanciamiento) {
        this.tipoFinanciamiento = tipoFinanciamiento;
    }

    

    public Boolean getParticipaEnPrograma() {
        return participaEnPrograma;
    }

    public void setParticipaEnPrograma(Boolean participaEnPrograma) {
        this.participaEnPrograma = participaEnPrograma;
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
    

    public ProyectoWebFGP getProyectoWebFGP() {
        return proyectoWebFGP;
    }

    public void setProyectoWebFGP(ProyectoWebFGP proyectoWebFGP) {
        this.proyectoWebFGP = proyectoWebFGP;
    }
    
    public Date getFechaArchivo() {
        return fechaArchivo;
    }

    public void setFechaArchivo(Date fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public List<ArchivoWeb> getLstArchivoWeb() {
        return lstArchivoWeb;
    }

    public void setLstArchivoWeb(List<ArchivoWeb> lstArchivoWeb) {
        this.lstArchivoWeb = lstArchivoWeb;
    }

    public Boolean getProyectoOrientado() {
        return proyectoOrientado;
    }

    public void setProyectoOrientado(Boolean proyectoOrientado) {
        this.proyectoOrientado = proyectoOrientado;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public PresupuestoWebDetallado getPresupuestoWebAnioUno() {
        return presupuestoWebAnioUno;
    }

    public void setPresupuestoWebAnioUno(PresupuestoWebDetallado presupuestoWebAnioUno) {
        this.presupuestoWebAnioUno = presupuestoWebAnioUno;
    }

    public List<RecursoSubsidio> getRecursosSubsidios() {
        return recursosSubsidios;
    }

    public void setRecursosSubsidios(List<RecursoSubsidio> recursosSubsidios) {
        this.recursosSubsidios = recursosSubsidios;
    }

    public PresupuestoWeb getPresupuestoWeb() {
        return presupuestoWeb;
    }

    public void setPresupuestoWeb(PresupuestoWeb presupuestoWeb) {
        this.presupuestoWeb = presupuestoWeb;
    } 
    

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public List<String> getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(List<String> palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<SubDisciplinaCientifica> getSubDisciplinasCientificas() {
        return subDisciplinasCientificas;
    }

    public void setSubDisciplinasCientificas(List<SubDisciplinaCientifica> subDisciplinasCientificas) {
        this.subDisciplinasCientificas = subDisciplinasCientificas;
    }

    public List<UnidadInvestigacion> getLocalizaciones() {
        return localizaciones;
    }

    public void setLocalizaciones(List<UnidadInvestigacion> unidadesInvestigacion) {
        this.localizaciones = unidadesInvestigacion;
    }

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public List<ParticipacionWeb> getParticipacionesWeb() {
        return participacionesWeb;
    }

    public void setParticipacionesWeb(List<ParticipacionWeb> participacionesWeb) {
        this.participacionesWeb = participacionesWeb;
    }

    public UnidadAcademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public UnidadEjecutora getUnidadEjecutora() {
        return unidadEjecutora;
    }

    public void setUnidadEjecutora(UnidadEjecutora unidadEjecutora) {
        this.unidadEjecutora = unidadEjecutora;
    }

    public LineaInvestigacion getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }

    public List<CampoAplicacion> getCamposAplicacion() {
        return camposAplicacion;
    }

    public void setCamposAplicacion(List<CampoAplicacion> camposAplicacion) {
        this.camposAplicacion = camposAplicacion;
    }

    public ObjetivoSocioeconomico getObjetivoSocioeconomico() {
        return objetivoSocioeconomico;
    }

    public void setObjetivoSocioeconomico(ObjetivoSocioeconomico objetivoSocioeconomico) {
        this.objetivoSocioeconomico = objetivoSocioeconomico;
    }

    public TipoProyecto getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public String getUnidadEjecutoraOtra() {
        return unidadEjecutoraOtra;
    }

    public void setUnidadEjecutoraOtra(String unidadEjecutoraOtra) {
        this.unidadEjecutoraOtra = unidadEjecutoraOtra;
    }

    public String getLocalizacionOtra() {
        return localizacionOtra;
    }

    public void setLocalizacionOtra(String localizacionOtra) {
        this.localizacionOtra = localizacionOtra;
    }

    public String getSubDisciplinaCientificaOtra() {
        return subDisciplinaCientificaOtra;
    }

    public void setSubDisciplinaCientificaOtra(String subDisciplinaCientificaOtra) {
        this.subDisciplinaCientificaOtra = subDisciplinaCientificaOtra;
    }

    public Boolean getProyectoIniciacion() {
        return proyectoIniciacion;
    }

    public void setProyectoIniciacion(Boolean proyectoIniciacion) {
        this.proyectoIniciacion = proyectoIniciacion;
    }

    public PresupuestoWebDetalladoDos getPresupuestoWebAnioDos() {
        return presupuestoWebAnioDos;
    }

    public void setPresupuestoWebAnioDos(PresupuestoWebDetalladoDos presupuestoWebAnioDos) {
        this.presupuestoWebAnioDos = presupuestoWebAnioDos;
    }

    public PresupuestoWebDetalladoTres getPresupuestoWebAnioTres() {
        return presupuestoWebAnioTres;
    }

    public void setPresupuestoWebAnioTres(PresupuestoWebDetalladoTres presupuestoWebAnioTres) {
        this.presupuestoWebAnioTres = presupuestoWebAnioTres;
    }

    public PresupuestoWebDetalladoCuatro getPresupuestoWebAnioCuatro() {
        return presupuestoWebAnioCuatro;
    }

    public void setPresupuestoWebAnioCuatro(PresupuestoWebDetalladoCuatro presupuestoWebAnioCuatro) {
        this.presupuestoWebAnioCuatro = presupuestoWebAnioCuatro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof ProyectoWeb)) {
            return false;
        }
        ProyectoWeb other = (ProyectoWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titulo;
    }

}