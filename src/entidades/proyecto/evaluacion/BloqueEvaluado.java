/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.evaluacion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "evaluacion_bloque_evaluado")
public class BloqueEvaluado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Bloque bloque;
    @OneToMany
    private List<DetalleEvaluado> detallesEvaluados;
    @OneToOne
    private CalificacionWeb calificacion;
    @OneToMany(mappedBy = "bloqueEvaluado",cascade = CascadeType.REMOVE)
    private List<MensajeEvaluacion> mensajes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public List<DetalleEvaluado> getDetallesEvaluados() {
        return detallesEvaluados;
    }

    public void setDetallesEvaluados(List<DetalleEvaluado> detallesEvaluados) {
        this.detallesEvaluados = detallesEvaluados;
    }

    public CalificacionWeb getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(CalificacionWeb calificacion) {
        this.calificacion = calificacion;
    }

    public List<MensajeEvaluacion> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajeEvaluacion> mensajes) {
        this.mensajes = mensajes;
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
        if (!(object instanceof BloqueEvaluado)) {
            return false;
        }
        BloqueEvaluado other = (BloqueEvaluado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return bloque.getDescripcion();
        } catch (Exception ex) {
            return "-";
        }
    }
}
