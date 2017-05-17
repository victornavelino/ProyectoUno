/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.persona.investigador.actividadConduccion;

import entidades.Cargo;
import entidades.UnidadAcademica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name="actividadconduccion")
public class ActividadConduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private CargoConduccion cargoConduccion;

    public CargoConduccion getCargoConduccion() {
        return cargoConduccion;
    }

    public void setCargoConduccion(CargoConduccion cargoConduccion) {
        this.cargoConduccion = cargoConduccion;
    }
    @OneToOne
    private DedicacionConduccion dedicacion;
    @OneToOne
    private UnidadAcademica dependencia;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaHasta;

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

    

    public DedicacionConduccion getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(DedicacionConduccion dedicacion) {
        this.dedicacion = dedicacion;
    }

    public UnidadAcademica getDependencia() {
        return dependencia;
    }

    public void setDependencia(UnidadAcademica dependencia) {
        this.dependencia = dependencia;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadConduccion)) {
            return false;
        }
        ActividadConduccion other = (ActividadConduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cargoConduccion.getDescripcion() + " en " + dependencia.getDescripcion();
    }

   


}
