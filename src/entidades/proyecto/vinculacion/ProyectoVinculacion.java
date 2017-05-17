/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion;

import entidades.Documento;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import entidades.Resolucion;
import entidades.TipoProyecto;
import entidades.UnidadAcademica;
import entidades.UnidadEjecutora;
import entidades.persona.Evaluador;
import entidades.proyecto.*;
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
 * @author Panchi
 */
@Entity
@Table(name="proyectovinculacion")
public class ProyectoVinculacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nroConvenio;
    @OneToOne
    private UnidadAcademica unidadAcademica;
    private int duracionTeorica;
    @Lob
    private String titulo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinalizacion;
    @OneToMany
    List<Prorroga> prorrogas;
    @OneToOne
    private LineaInvestigacion lineaInvestigacion;
    @OneToOne
    private UnidadEjecutora unidadEjecutora;
    @OneToOne
    private EntidadEvaluadora entidadEvaluadora;
    @OneToOne
    private Programa programa;
    @Lob
    private String resumen;
    private List<String> palabrasClaves;
    @Lob
    private String observaciones;
    @OneToMany
    private List<SubDisciplinaCientifica> subDisciplinasCientificas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ParticipacionVinculacion> participaciones;
    
    private String codigo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Financiacion> financiaciones;
    @Lob
    private String transferenciaConocimiento;

    @ManyToMany
    private List<Publicacion> publicaciones;
@ManyToMany(mappedBy = "proyectosVinculacion")
    private List<Contrato> contratos;
@OneToMany(mappedBy = "proyectoVinculacion")
    private List<FormacionRRHH> formacionRRHHs;
    @ManyToMany(mappedBy = "proyectosVinculacion")
    private List<Propiedad> propiedades;
    @OneToMany
    private List<Documento> archivosDigitales;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<NotaExterna> notasExternas;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<NotaInterna> notasInternas;

    private String resolucionMinisterial;
    private String resolucionRectoral;

    public String getResolucionMinisterial() {
        return resolucionMinisterial;
    }

    public void setResolucionMinisterial(String resolucionMinisterial) {
        this.resolucionMinisterial = resolucionMinisterial;
    }

    public String getResolucionRectoral() {
        return resolucionRectoral;
    }

    public void setResolucionRectoral(String resolucionRectoral) {
        this.resolucionRectoral = resolucionRectoral;
    }
    
    public List<NotaExterna> getNotasExternas() {
        return notasExternas;
    }

    public void setNotasExternas(List<NotaExterna> notasExternas) {
        this.notasExternas = notasExternas;
    }

    public List<NotaInterna> getNotasInternas() {
        return notasInternas;
    }

    public void setNotasInternas(List<NotaInterna> notasInternas) {
        this.notasInternas = notasInternas;
    }
    
    
    public List<Documento> getArchivosDigitales() {
        return archivosDigitales;
    }

    public void setArchivosDigitales(List<Documento> archivosDigitales) {
        this.archivosDigitales = archivosDigitales;
    }
    
    

    public List<FormacionRRHH> getFormacionRRHHs() {
        return formacionRRHHs;
    }

    public void setFormacionRRHHs(List<FormacionRRHH> formacionRRHHs) {
        this.formacionRRHHs = formacionRRHHs;
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }
    

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }


    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
    
    public String getTransferenciaConocimiento() {
        return transferenciaConocimiento;
    }

    public void setTransferenciaConocimiento(String transferenciaConocimiento) {
        this.transferenciaConocimiento = transferenciaConocimiento;
    }
    
    

    public List<Financiacion> getFinanciaciones() {
        return financiaciones;
    }

    public void setFinanciaciones(List<Financiacion> financiaciones) {
        this.financiaciones = financiaciones;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getDuracionTeorica() {
        return duracionTeorica;
    }

    public void setDuracionTeorica(int duracionTeorica) {
        this.duracionTeorica = duracionTeorica;
    }

    public EntidadEvaluadora getEntidadEvaluadora() {
        return entidadEvaluadora;
    }

    public void setEntidadEvaluadora(EntidadEvaluadora entidadEvaluadora) {
        this.entidadEvaluadora = entidadEvaluadora;
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

    public LineaInvestigacion getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }

    public String getNroConvenio() {
        return nroConvenio;
    }

    public void setNroConvenio(String nroConvenio) {
        this.nroConvenio = nroConvenio;
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

    public List<ParticipacionVinculacion> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<ParticipacionVinculacion> participaciones) {
        this.participaciones = participaciones;
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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public List<SubDisciplinaCientifica> getSubDisciplinasCientificas() {
        return subDisciplinasCientificas;
    }

    public void setSubDisciplinasCientificas(List<SubDisciplinaCientifica> subDisciplinasCientificas) {
        this.subDisciplinasCientificas = subDisciplinasCientificas;
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
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoVinculacion)) {
            return false;
        }
        ProyectoVinculacion other = (ProyectoVinculacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return '"'+this.getNroConvenio()+'"'+"    "+'"'+ this.getTitulo()+ '"'  ;
    }
}
