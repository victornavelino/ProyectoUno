/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.categorizacion;

import entidades.Resolucion;
import entidades.proyecto.Proyecto;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ruben
 */
@Entity
@Table(name = "winsip")
@NamedQueries({
    @NamedQuery(name = "Winsip.findByProyecto",
            query = "SELECT w FROM Winsip w WHERE w.proyecto.id = :idProyecto")})
public class Winsip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Proyecto proyecto;
    private int año;
    private String lugarEvaluacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEvaluacion;
    @Enumerated(EnumType.STRING)
    private TipoResultadoEvaluacion evaluacionWinsip;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EvaluacionWinsipIntegrantes> evaluacionesIntegrantes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EvaluacionWinsip> evaluaciones;
    @OneToOne
    private Resolucion evaluacionProyecto;
    @OneToOne
    private Resolucion evaluacionIntegrantes;
    @Enumerated(EnumType.STRING)
    private TipoInforme tipoInforme;
    private boolean habilitado;

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public TipoInforme getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(TipoInforme tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public String getLugarEvaluacion() {
        return lugarEvaluacion;
    }

    public void setLugarEvaluacion(String lugarEvaluacion) {
        this.lugarEvaluacion = lugarEvaluacion;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public TipoResultadoEvaluacion getEvaluacionWinsip() {
        return evaluacionWinsip;
    }

    public void setEvaluacionWinsip(TipoResultadoEvaluacion evaluacionWinsip) {
        this.evaluacionWinsip = evaluacionWinsip;
    }

    public List<EvaluacionWinsipIntegrantes> getEvaluacionesIntegrantes() {
        return evaluacionesIntegrantes;
    }

    public void setEvaluacionesIntegrantes(List<EvaluacionWinsipIntegrantes> evaluacionesIntegrantes) {
        this.evaluacionesIntegrantes = evaluacionesIntegrantes;
    }

    public List<EvaluacionWinsip> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionWinsip> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public Resolucion getEvaluacionProyecto() {
        return evaluacionProyecto;
    }

    public void setEvaluacionProyecto(Resolucion evaluacionProyecto) {
        this.evaluacionProyecto = evaluacionProyecto;
    }

    public Resolucion getEvaluacionIntegrantes() {
        return evaluacionIntegrantes;
    }

    public void setEvaluacionIntegrantes(Resolucion evaluacionIntegrantes) {
        this.evaluacionIntegrantes = evaluacionIntegrantes;
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
        if (!(object instanceof Winsip)) {
            return false;
        }
        Winsip other = (Winsip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return "Winsip " + this.getAño();
        } catch (Exception ex) {
            return "winsip";
        }
    }

}
