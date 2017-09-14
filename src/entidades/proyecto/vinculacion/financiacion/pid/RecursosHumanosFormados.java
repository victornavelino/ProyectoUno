/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_rrhh_formados")
public class RecursosHumanosFormados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apellidoNombre;
    private String entidadFinanciadora;
    private String tipoFormacion;
    @Lob
    private String descripcion;

    public String getApellidoNombre() {
        return apellidoNombre;
    }

    public void setApellidoNombre(String apellidoNombre) {
        this.apellidoNombre = apellidoNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEntidadFinanciadora() {
        return entidadFinanciadora;
    }

    public void setEntidadFinanciadora(String entidadFinanciadora) {
        this.entidadFinanciadora = entidadFinanciadora;
    }

    public String getTipoFormacion() {
        return tipoFormacion;
    }

    public void setTipoFormacion(String tipoFormacion) {
        this.tipoFormacion = tipoFormacion;
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
        if (!(object instanceof RecursosHumanosFormados)) {
            return false;
        }
        RecursosHumanosFormados other = (RecursosHumanosFormados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return apellidoNombre;
    }
    
}
