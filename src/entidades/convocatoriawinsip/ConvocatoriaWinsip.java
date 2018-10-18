/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.convocatoriawinsip;

import entidades.proyecto.Proyecto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hugo
 */
@Entity
@Table(name = "convocatoriawinsip")
public class ConvocatoriaWinsip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String Descripcion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date apertura;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date cierre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEvaluadaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEvaluadaFin;
    @OneToMany
    private List<Proyecto> proyectos;
    private Boolean habilitada;

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
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Date getApertura() {
        return apertura;
    }

    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }

    public Date getCierre() {
        return cierre;
    }

    public void setCierre(Date cierre) {
        this.cierre = cierre;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(Boolean habilitada) {
        this.habilitada = habilitada;
    }

    public Date getFechaEvaluadaInicio() {
        return fechaEvaluadaInicio;
    }

    public void setFechaEvaluadaInicio(Date fechaEvaluadaInicio) {
        this.fechaEvaluadaInicio = fechaEvaluadaInicio;
    }

    public Date getFechaEvaluadaFin() {
        return fechaEvaluadaFin;
    }

    public void setFechaEvaluadaFin(Date fechaEvaluadaFin) {
        this.fechaEvaluadaFin = fechaEvaluadaFin;
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
        if (!(object instanceof ConvocatoriaWinsip)) {
            return false;
        }
        ConvocatoriaWinsip other = (ConvocatoriaWinsip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.convocatoriawinsip.ConvocatoriaWinsip[ id=" + id + " ]";
    }

}
