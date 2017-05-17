/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.economico;

import entidades.Documento;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Walter
 */
@Entity
@Table(name = "economico_modificacion_presupuesto")
public class ModificacionPresupuesto implements Serializable {
    @ManyToOne
    private Presupuesto presupuesto;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int anioModificado;
    @Column(scale=2, precision=12)
    private BigDecimal montoBU;
    @Column(scale=2, precision=12)
    private BigDecimal montoBC;
    @Column(scale=2, precision=12)
    private BigDecimal montoGV;
    @Column(scale=2, precision=12)
    private BigDecimal montoBNP;
    @Lob
    private String observacion;
    @OneToOne(cascade= CascadeType.REMOVE)
    private Documento archivoModificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnioModificado() {
        return anioModificado;
    }

    public void setAnioModificado(int anioModificado) {
        this.anioModificado = anioModificado;
    }

    public BigDecimal getMontoBU() {
        return montoBU;
    }

    public void setMontoBU(BigDecimal montoBU) {
        this.montoBU = montoBU;
    }

    public BigDecimal getMontoBC() {
        return montoBC;
    }

    public void setMontoBC(BigDecimal montoBC) {
        this.montoBC = montoBC;
    }

    public BigDecimal getMontoGV() {
        return montoGV;
    }

    public void setMontoGV(BigDecimal montoGV) {
        this.montoGV = montoGV;
    }

    public BigDecimal getMontoBNP() {
        return montoBNP;
    }

    public void setMontoBNP(BigDecimal montoBNP) {
        this.montoBNP = montoBNP;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Documento getArchivoModificacion() {
        return archivoModificacion;
    }

    public void setArchivoModificacion(Documento archivoModificacion) {
        this.archivoModificacion = archivoModificacion;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
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
        if (!(object instanceof ModificacionPresupuesto)) {
            return false;
        }
        ModificacionPresupuesto other = (ModificacionPresupuesto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.economico.ModificacionPresupuesto[ id=" + id + " ]";
    }
    
}
