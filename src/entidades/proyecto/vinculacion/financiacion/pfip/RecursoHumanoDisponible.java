/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="recursohumandodisponible")
public class RecursoHumanoDisponible implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreApellido;

   
    private String especialidad;
    private String funcion;
    
    private Float dedicacion;
    private Integer mesesParticipacion;
    private Float sueldo;
    private Float fondosDisponibles;

    public Float getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(Float dedicacion) {
        this.dedicacion = dedicacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
    public Float getFondosDisponibles() {
        return fondosDisponibles;
    }

    public void setFondosDisponibles(Float fondosDisponibles) {
        this.fondosDisponibles = fondosDisponibles;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Integer getMesesParticipacion() {
        return mesesParticipacion;
    }

    public void setMesesParticipacion(Integer mesesParticipacion) {
        this.mesesParticipacion = mesesParticipacion;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
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
        if (!(object instanceof RecursoHumanoDisponible)) {
            return false;
        }
        RecursoHumanoDisponible other = (RecursoHumanoDisponible) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.RecursoHumanoDisponible[ id=" + id + " ]";
    }
    
}
