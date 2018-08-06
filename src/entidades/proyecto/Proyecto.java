/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto;

import entidades.Resolucion;
import entidades.TipoProyecto;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.economico.PagoEconomico;
import entidades.economico.Presupuesto;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable {
    @OneToOne(mappedBy = "proyecto", cascade = CascadeType.REMOVE)
    private ProyectoDatosComplementarios proyectoDatosComplementarios;
    @OneToMany(mappedBy = "proyecto")
    private List<FormacionRRHH> formacionRRHHs;
    @ManyToMany(mappedBy = "proyectos")
    private List<Contrato> contratos;
    @ManyToMany(mappedBy = "proyectos")
    private List<Publicacion> publicaciones;
    @ManyToMany(mappedBy = "proyectos")
    private List<Propiedad> propiedades;
    @ManyToOne
    private Proyecto proyectoComplementarioPadre;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String codigo;
    @Column(unique = true)
    private String codigoIncentivos;
    @OneToOne
    private UnidadAcademica unidadAcademica;
    @OneToMany
    private List<Evaluacion> evaluaciones;
    @OneToMany(mappedBy = "proyectoComplementarioPadre")
    private List<Proyecto> proyectosComplementarios;
    @OneToOne
    private TipoProyecto tipoProyecto;
    @OneToOne
    private ObjetivoSocioeconomico objetivoSocioeconomico;
    private int duracionTeorica;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinalizacion;
    @Lob
    private String resumen;
    @OneToOne
    private UnidadEjecutora unidadEjecutora;
    //@ElementCollection
    private List<String> palabrasClaves;
    @Lob
    private String summary;
    @Lob
    private String title;
    private List<String> keywords;
    @OneToOne
    private EntidadEvaluadora entidadEvaluadora;
    @OneToMany
    private List<EntidadConvenio> conveniosCon;
    @OneToMany
    private List<Especialidad> especialidades;
    @OneToOne
    private Programa programa;
    private int personalApoyo;
    @OneToMany
    private List<UnidadInvestigacion> unidadesInvestigacion;
    @OneToMany
    private List<CampoAplicacion> camposAplicacion;
    @OneToOne
    private LineaInvestigacion lineaInvestigacion;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Resolucion> resoluciones;
    @OneToOne
    private Resolucion documentoWinsip;
    @Lob // Porque con String no solo no bastaba. Hay títulos re largos!
    private String titulo;
    @OneToMany
    List<Prorroga> prorrogas;
    @Lob
    private String observaciones;
    @OneToMany
    private List<Participacion> participaciones;
    @OneToMany
    private List<SubDisciplinaCientifica> subDisciplinasCientificas;
    /// Agregados por WB   
    @OneToOne(mappedBy = "proyecto")
    private Presupuesto presupuesto;
    @OneToMany
    private List<PagoEconomico> pagos;
    @OneToOne
    private EntidadAcreditadora entidadAcreditadora;
    @OneToOne
    private EntidadFinanciadora entidadFinanciadora;

    public EntidadAcreditadora getEntidadAcreditadora() {
        return entidadAcreditadora;
    }

    public void setEntidadAcreditadora(EntidadAcreditadora entidadAcreditadora) {
        this.entidadAcreditadora = entidadAcreditadora;
    }

    public EntidadFinanciadora getEntidadFinanciadora() {
        return entidadFinanciadora;
    }

    public void setEntidadFinanciadora(EntidadFinanciadora entidadFinanciadora) {
        this.entidadFinanciadora = entidadFinanciadora;
    }
    

    /// Fin WB
    public ProyectoDatosComplementarios getProyectoDatosComplementarios() {
        return proyectoDatosComplementarios;
    }

    public Resolucion getDocumentoWinsip() {
        return documentoWinsip;
    }

    public void setDocumentoWinsip(Resolucion documentoWinsip) {
        this.documentoWinsip = documentoWinsip;
    }

    public void setProyectoDatosComplementarios(ProyectoDatosComplementarios proyectoDatosComplementarios) {
        this.proyectoDatosComplementarios = proyectoDatosComplementarios;
    }
   
    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<PagoEconomico> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoEconomico> pagos) {
        this.pagos = pagos;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoIncentivos() {
        return codigoIncentivos;
    }

    public void setCodigoIncentivos(String codigoIncentivos) {
        this.codigoIncentivos = codigoIncentivos;
    }

    public int getDuracionTeorica() {
        return duracionTeorica;
    }

    public void setDuracionTeorica(int duracionTeorica) {
        this.duracionTeorica = duracionTeorica;
    }

    public ObjetivoSocioeconomico getObjetivoSocioeconomico() {
        return objetivoSocioeconomico;
    }

    public void setObjetivoSocioeconomico(ObjetivoSocioeconomico objetivoSocioeconomico) {
        this.objetivoSocioeconomico = objetivoSocioeconomico;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<FormacionRRHH> getFormacionRRHHs() {
        return formacionRRHHs;
    }

    public void setFormacionRRHHs(List<FormacionRRHH> formacionRRHHs) {
        this.formacionRRHHs = formacionRRHHs;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public EntidadEvaluadora getEntidadEvaluadora() {
        return entidadEvaluadora;
    }

    public void setEntidadEvaluadora(EntidadEvaluadora entidadEvaluadora) {
        this.entidadEvaluadora = entidadEvaluadora;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public LineaInvestigacion getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<String> getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(List<String> palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<Participacion> participaciones) {
        this.participaciones = participaciones;
    }

    public int getPersonalApoyo() {
        return personalApoyo;
    }

    public void setPersonalApoyo(int personalApoyo) {
        this.personalApoyo = personalApoyo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public List<Prorroga> getProrrogas() {
        return prorrogas;
    }

    public void setProrrogas(List<Prorroga> prorrogas) {
        this.prorrogas = prorrogas;
    }

    public Proyecto getProyectoComplementarioPadre() {
        return proyectoComplementarioPadre;
    }

    public void setProyectoComplementarioPadre(Proyecto proyectoComplementarioPadre) {
        this.proyectoComplementarioPadre = proyectoComplementarioPadre;
    }

    public List<Proyecto> getProyectosComplementarios() {
        return proyectosComplementarios;
    }

    public void setProyectosComplementarios(List<Proyecto> proyectosComplementarios) {
        this.proyectosComplementarios = proyectosComplementarios;
    }

    public List<Resolucion> getResoluciones() {
        return resoluciones;
    }

    public void setResoluciones(List<Resolucion> resoluciones) {
        this.resoluciones = resoluciones;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public TipoProyecto getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public List<CampoAplicacion> getCamposAplicacion() {
        return camposAplicacion;
    }

    public void setCamposAplicacion(List<CampoAplicacion> camposAplicacion) {
        this.camposAplicacion = camposAplicacion;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<UnidadInvestigacion> getUnidadesInvestigacion() {
        return unidadesInvestigacion;
    }

    public void setUnidadesInvestigacion(List<UnidadInvestigacion> unidadesInvestigacion) {
        this.unidadesInvestigacion = unidadesInvestigacion;
    }

    public List<EntidadConvenio> getConveniosCon() {
        return conveniosCon;
    }

    public void setConveniosCon(List<EntidadConvenio> conveniosCon) {
        this.conveniosCon = conveniosCon;
    }

    public List<SubDisciplinaCientifica> getSubDisciplinasCientificas() {
        return subDisciplinasCientificas;
    }

    public void setSubDisciplinasCientificas(List<SubDisciplinaCientifica> subDisciplinasCientificas) {
        this.subDisciplinasCientificas = subDisciplinasCientificas;
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
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (codigoIncentivos == null) {
            if (codigo == null) {
                return titulo;
            } else {
                return codigo + " " + titulo;
            }
        } else {
            return codigoIncentivos + " " + titulo;
        }
    }

    
}
