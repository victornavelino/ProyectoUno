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
import javax.persistence.*;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_objetivos_y_resultados")
public class ObjetivosYResultados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tituloObjetivo;
    private double porcentajeCumplimiento;
    @Lob
    private String tareasRealizadas;
    @Lob    
    private String resultadosAlcanzados;

    public double getPorcentajeCumplimiento() {
        return porcentajeCumplimiento;
    }

    public void setPorcentajeCumplimiento(double porcentajeCumplimiento) {
        this.porcentajeCumplimiento = porcentajeCumplimiento;
    }

    public String getResultadosAlcanzados() {
        return resultadosAlcanzados;
    }

    public void setResultadosAlcanzados(String resultadosAlcanzados) {
        this.resultadosAlcanzados = resultadosAlcanzados;
    }

    public String getTareasRealizadas() {
        return tareasRealizadas;
    }

    public void setTareasRealizadas(String tareasRealizadas) {
        this.tareasRealizadas = tareasRealizadas;
    }

    public String getTituloObjetivo() {
        return tituloObjetivo;
    }

    public void setTituloObjetivo(String tituloObjetivo) {
        this.tituloObjetivo = tituloObjetivo;
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
        if (!(object instanceof ObjetivosYResultados)) {
            return false;
        }
        ObjetivosYResultados other = (ObjetivosYResultados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pid.ObjetivosYResultados[ id=" + id + " ]";
    }
    
}
