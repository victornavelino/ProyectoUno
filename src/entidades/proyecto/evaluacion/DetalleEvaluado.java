/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.evaluacion;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "evaluacion_detalle_evaluado")
public class DetalleEvaluado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne (optional=false)
    private ContenidoBloque contenidoBloque;
    @OneToOne
    private CalificacionWeb calificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContenidoBloque getContenidoBloque() {
        return contenidoBloque;
    }

    public void setContenidoBloque(ContenidoBloque contenidoBloque) {
        this.contenidoBloque = contenidoBloque;
    }

    public CalificacionWeb getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(CalificacionWeb calificacion) {
        this.calificacion = calificacion;
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
        if (!(object instanceof DetalleEvaluado)) {
            return false;
        }
        DetalleEvaluado other = (DetalleEvaluado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return contenidoBloque.getDescripcion();
        } catch (Exception ex) {
            return "-";
        }

    }
}
