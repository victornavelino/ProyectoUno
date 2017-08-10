/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.Temporal;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_becarios_anpcyt")
public class BecariosAnpcyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apellido;
    private String nombres;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    private String tipoBeca;
    private String nombreCarreraOTesisEnCurso;
    private String institucionDondeCursa;
    private String acreditada;
    private String tipo;
    @Lob
    private String materiasAprobadasDescripcion;
    private String avanceCarrera;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinEstimada;
    @Lob
    private String actividadesDescripcion;
    @Lob
    private String publicacionesODesarrollosDescripcion;

    public String getAcreditada() {
        return acreditada;
    }

    public void setAcreditada(String acreditada) {
        this.acreditada = acreditada;
    }

    public String getActividadesDescripcion() {
        return actividadesDescripcion;
    }

    public void setActividadesDescripcion(String actividadesDescripcion) {
        this.actividadesDescripcion = actividadesDescripcion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAvanceCarrera() {
        return avanceCarrera;
    }

    public void setAvanceCarrera(String avanceCarrera) {
        this.avanceCarrera = avanceCarrera;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(Date fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getInstitucionDondeCursa() {
        return institucionDondeCursa;
    }

    public void setInstitucionDondeCursa(String institucionDondeCursa) {
        this.institucionDondeCursa = institucionDondeCursa;
    }

    public String getMateriasAprobadasDescripcion() {
        return materiasAprobadasDescripcion;
    }

    public void setMateriasAprobadasDescripcion(String materiasAprobadasDescripcion) {
        this.materiasAprobadasDescripcion = materiasAprobadasDescripcion;
    }

    public String getNombreCarreraOTesisEnCurso() {
        return nombreCarreraOTesisEnCurso;
    }

    public void setNombreCarreraOTesisEnCurso(String nombreCarreraOTesisEnCurso) {
        this.nombreCarreraOTesisEnCurso = nombreCarreraOTesisEnCurso;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPublicacionesODesarrollosDescripcion() {
        return publicacionesODesarrollosDescripcion;
    }

    public void setPublicacionesODesarrollosDescripcion(String publicacionesODesarrollosDescripcion) {
        this.publicacionesODesarrollosDescripcion = publicacionesODesarrollosDescripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) {
        this.tipoBeca = tipoBeca;
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
        if (!(object instanceof BecariosAnpcyt)) {
            return false;
        }
        BecariosAnpcyt other = (BecariosAnpcyt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return apellido+nombres;
    }
    
}
