 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas;

import entidades.Resolucion;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Proyecto;
import entidades.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author navemilio
 */
@Entity
@Table(name = "beca_postulacionbeca")
public class PostulacionBeca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Becas beca;
    @OneToOne
    private Investigador postulante;
    @OneToOne
    private Proyecto proyecto;
    @OneToMany
    private List<Investigador> asesores;
    @OneToMany(mappedBy = "postulacionBeca")
    private List<Evaluaciones> evaluaciones;
    @OneToMany(mappedBy = "postulacionBeca")
    private List<Pago> pago;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaalta;
    @OneToOne
    private Usuario usuario;
    @OneToMany
    private List<Resolucion> documentacion;
    private String Plandetrabajo;
    @OneToOne
    private BecaPostulanteEstado postulanteestado;
    @OneToOne
    private BecaInforme informefinal;
    @OneToOne
    private BecaInforme informeavance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaBecaInformeAvance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaBecaInformeFinal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAdjudicacionPostulante;
    @OneToMany(mappedBy = "postulacionBeca")
    private List<Observacion> observaciones;
    @OneToMany(mappedBy = "postulacionBeca")
    private List<NotaPago> notaPago;

    public List<NotaPago> getNotaPago() {
        return notaPago;
    }

    public void setNotaPago(List<NotaPago> notaPago) {
        this.notaPago = notaPago;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaBecaInformeAvance() {
        return fechaBecaInformeAvance;
    }

    public void setFechaBecaInformeAvance(Date fechaBecaInformeAvance) {
        this.fechaBecaInformeAvance = fechaBecaInformeAvance;
    }

    public Date getFechaBecaInformeFinal() {
        return fechaBecaInformeFinal;
    }

    public void setFechaBecaInformeFinal(Date fechaBecaInformeFinal) {
        this.fechaBecaInformeFinal = fechaBecaInformeFinal;
    }

    public Date getFechaAdjudicacionPostulante() {
        return fechaAdjudicacionPostulante;
    }

    public void setFechaAdjudicacionPostulante(Date fechaAdjudicacionPostulante) {
        this.fechaAdjudicacionPostulante = fechaAdjudicacionPostulante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlandetrabajo() {
        return Plandetrabajo;
    }

    public void setPlandetrabajo(String Plandetrabajo) {
        this.Plandetrabajo = Plandetrabajo;
    }

    public BecaInforme getInformefinal() {
        return informefinal;
    }

    public void setInformefinal(BecaInforme informefinal) {
        this.informefinal = informefinal;
    }

    public BecaInforme getInformeavance() {
        return informeavance;
    }

    public void setInformeavance(BecaInforme informeavance) {
        this.informeavance = informeavance;
    }

    public BecaPostulanteEstado getPostulanteestado() {
        return postulanteestado;
    }

    public void setPostulanteestado(BecaPostulanteEstado postulanteestado) {
        this.postulanteestado = postulanteestado;
    }

    public List<Resolucion> getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(List<Resolucion> documentacion) {
        this.documentacion = documentacion;
    }

    public List<Investigador> getAsesores() {
        return asesores;
    }

    public void setAsesores(List<Investigador> asesores) {
        this.asesores = asesores;
    }

    public List<Evaluaciones> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluaciones> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<Pago> getPago() {
        return pago;
    }

    public void setPago(List<Pago> pago) {
        this.pago = pago;
    }

    public Becas getBeca() {
        return beca;
    }

    public void setBeca(Becas beca) {
        this.beca = beca;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Investigador getPostulante() {
        return postulante;
    }

    public void setPostulante(Investigador postulante) {
        this.postulante = postulante;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof PostulacionBeca)) {
            return false;
        }
        PostulacionBeca other = (PostulacionBeca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return postulante.toString();
    }
}
