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
import javax.persistence.Table;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "evaluacion_calificacion")
public class CalificacionWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * (ejemplo NA)*
     */
    private String nombre;
    /**
     * (ejemplo No Aprobado)*
     */
    private String descripcion;
    private int puntuacionDesde;
    private int puntacionHasta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntuacionDesde() {
        return puntuacionDesde;
    }

    public void setPuntuacionDesde(int puntuacionDesde) {
        this.puntuacionDesde = puntuacionDesde;
    }

    public int getPuntacionHasta() {
        return puntacionHasta;
    }

    public void setPuntacionHasta(int puntacionHasta) {
        this.puntacionHasta = puntacionHasta;
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
        if (!(object instanceof CalificacionWeb)) {
            return false;
        }
        CalificacionWeb other = (CalificacionWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
