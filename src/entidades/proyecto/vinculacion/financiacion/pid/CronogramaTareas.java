/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_cronograma_tareas")
public class CronogramaTareas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nombre;
    @Lob
    private String descripcionEstado;
    @Lob
    private String descripcionMetodologiaUsada;
    @Lob
    private String problemasYSoluciones;
    @Lob
    private String posiblesRiesgosEtapasFuturas;

    public String getDescripcionMetodologiaUsada() {
        return descripcionMetodologiaUsada;
    }

    public void setDescripcionMetodologiaUsada(String descripcionMetodologiaUsada) {
        this.descripcionMetodologiaUsada = descripcionMetodologiaUsada;
    }

    public String getPosiblesRiesgosEtapasFuturas() {
        return posiblesRiesgosEtapasFuturas;
    }

    public void setPosiblesRiesgosEtapasFuturas(String posiblesRiesgosEtapasFuturas) {
        this.posiblesRiesgosEtapasFuturas = posiblesRiesgosEtapasFuturas;
    }

    public String getProblemasYSoluciones() {
        return problemasYSoluciones;
    }

    public void setProblemasYSoluciones(String problemasYSoluciones) {
        this.problemasYSoluciones = problemasYSoluciones;
    }

    
    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
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
        if (!(object instanceof CronogramaTareas)) {
            return false;
        }
        CronogramaTareas other = (CronogramaTareas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pid.CronogramaTareas[ id=" + id + " ]";
    }
    
}
