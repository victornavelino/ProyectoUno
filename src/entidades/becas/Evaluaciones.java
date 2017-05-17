/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas;

import entidades.Universidad;
import entidades.persona.investigador.Investigador;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author hugo
 */
@Entity
@Table(name="beca_evaluaciones")
public class Evaluaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private PostulacionBeca postulacionBeca;
    @OneToOne
    private EvaluacionEstado evaluacionEstado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @OneToOne
    private Investigador evaluador;
    @OneToOne
    private Universidad institucion;
    @OneToOne
    private Calificacion calificacionEnLetras;
    private float calificacionEnNumeros;
    @Lob
    private String observaciones;
     
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calificacion getCalificacionEnLetras() {
        return calificacionEnLetras;
    }

    public void setCalificacionEnLetras(Calificacion calificacionEnLetras) {
        this.calificacionEnLetras = calificacionEnLetras;
    }

    public float getCalificacionEnNumeros() {
        return calificacionEnNumeros;
    }

    public void setCalificacionEnNumeros(float calificacionEnNumeros) {
        this.calificacionEnNumeros = calificacionEnNumeros;
    }

    public PostulacionBeca getPostulacionBeca() {
        return postulacionBeca;
    }

    public void setPostulacionBeca(PostulacionBeca postulacionBeca) {
        this.postulacionBeca = postulacionBeca;
    }

       
    

    public EvaluacionEstado getEvaluacionEstado() {
        return evaluacionEstado;
    }

    public void setEvaluacionEstado(EvaluacionEstado evaluacionEstado) {
        this.evaluacionEstado = evaluacionEstado;
    }
    
    public Investigador getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Investigador evaluador) {
        this.evaluador = evaluador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Universidad getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Universidad institucion) {
        this.institucion = institucion;
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
        if (!(object instanceof Evaluaciones)) {
            return false;
        }
        Evaluaciones other = (Evaluaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.becas.Evaluaciones[ id=" + id + " ]";
    }
    
}
