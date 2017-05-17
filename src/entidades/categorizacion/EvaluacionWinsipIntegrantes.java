/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.categorizacion;

import entidades.persona.investigador.Investigador;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ruben
 */
@Entity
@Table(name = "winsip_evaluacion_integrantes")
public class EvaluacionWinsipIntegrantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Investigador investigador;
    @Enumerated(EnumType.STRING)
    private TipoResultadoInforme informe;
    @Enumerated(EnumType.STRING)
    private TipoResultadoEvaluacion evaluacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public TipoResultadoInforme getInforme() {
        return informe;
    }

    public void setInforme(TipoResultadoInforme informe) {
        this.informe = informe;
    }

    public TipoResultadoEvaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(TipoResultadoEvaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof EvaluacionWinsipIntegrantes)) {
            return false;
        }
        EvaluacionWinsipIntegrantes other = (EvaluacionWinsipIntegrantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return investigador.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
