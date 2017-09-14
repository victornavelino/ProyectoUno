/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.persona.investigador;

import entidades.DedicacionDocente;
import entidades.UnidadAcademica;
import entidades.Universidad;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author huguito
 */
@Entity
@Table(name="docencia")
public class Docencia implements Serializable {
    @ManyToOne
    private Investigador investigador;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Universidad universidad;
    @OneToOne
    private UnidadAcademica unidadAcademica;
    @OneToOne
    private DepartamentoDocente departamentoDocente;
    @OneToOne
    private CategoriaDocente categoriaDocente;
    @OneToOne
    private DedicacionDocente dedicacionDocente;
    @OneToOne
    private ModoObtencionCargo modoObtencionCargo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaObtencionCargo;
    private int horasDedicadasDocenciaPrimerCuatrimestre;
    private int semanasDedicadasDocenciaPrimerCuatrimestre;
    private int horasDedicadasDocenciaSegundoCuatrimestre;
    private int semanasDedicadasDocenciaSegundoCuatrimestre;
    private int año;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
    
    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public UnidadAcademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public DepartamentoDocente getDepartamentoDocente() {
        return departamentoDocente;
    }

    public void setDepartamentoDocente(DepartamentoDocente departamentoDocente) {
        this.departamentoDocente = departamentoDocente;
    }

    public CategoriaDocente getCategoriaDocente() {
        return categoriaDocente;
    }

    public void setCategoriaDocente(CategoriaDocente categoriaDocente) {
        this.categoriaDocente = categoriaDocente;
    }

    public DedicacionDocente getDedicacionDocente() {
        return dedicacionDocente;
    }

    public void setDedicacionDocente(DedicacionDocente dedicacionDocente) {
        this.dedicacionDocente = dedicacionDocente;
    }

    public ModoObtencionCargo getModoObtencionCargo() {
        return modoObtencionCargo;
    }

    public void setModoObtencionCargo(ModoObtencionCargo modoObtencionCargo) {
        this.modoObtencionCargo = modoObtencionCargo;
    }

    public Date getFechaObtencionCargo() {
        return fechaObtencionCargo;
    }

    public void setFechaObtencionCargo(Date fechaObtencionCargo) {
        this.fechaObtencionCargo = fechaObtencionCargo;
    }

    public int getHorasDedicadasDocenciaPrimerCuatrimestre() {
        return horasDedicadasDocenciaPrimerCuatrimestre;
    }

    public void setHorasDedicadasDocenciaPrimerCuatrimestre(int horasDedicadasDocenciaPrimerCuatrimestre) {
        this.horasDedicadasDocenciaPrimerCuatrimestre = horasDedicadasDocenciaPrimerCuatrimestre;
    }

    public int getSemanasDedicadasDocenciaPrimerCuatrimestre() {
        return semanasDedicadasDocenciaPrimerCuatrimestre;
    }

    public void setSemanasDedicadasDocenciaPrimerCuatrimestre(int semanasDedicadasDocenciaPrimerCuatrimestre) {
        this.semanasDedicadasDocenciaPrimerCuatrimestre = semanasDedicadasDocenciaPrimerCuatrimestre;
    }

    public int getHorasDedicadasDocenciaSegundoCuatrimestre() {
        return horasDedicadasDocenciaSegundoCuatrimestre;
    }

    public void setHorasDedicadasDocenciaSegundoCuatrimestre(int horasDedicadasDocenciaSegundoCuatrimestre) {
        this.horasDedicadasDocenciaSegundoCuatrimestre = horasDedicadasDocenciaSegundoCuatrimestre;
    }

    public int getSemanasDedicadasDocenciaSegundoCuatrimestre() {
        return semanasDedicadasDocenciaSegundoCuatrimestre;
    }

    public void setSemanasDedicadasDocenciaSegundoCuatrimestre(int semanasDedicadasDocenciaSegundoCuatrimestre) {
        this.semanasDedicadasDocenciaSegundoCuatrimestre = semanasDedicadasDocenciaSegundoCuatrimestre;
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
        if (!(object instanceof Docencia)) {
            return false;
        }
        Docencia other = (Docencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return categoriaDocente.getDescripcion()+" - " +unidadAcademica;
    }
    
}
