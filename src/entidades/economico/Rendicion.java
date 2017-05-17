/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.economico;

import entidades.Documento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name = "economico_rendicion")
public class Rendicion implements Serializable {
    @ManyToOne
    private PagoEconomico pagoEconomico;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRendicion;
    @Column(scale=2, precision=12)
    private BigDecimal montoRendido;
    @Lob
    private String observacion;
    @OneToMany(cascade= CascadeType.ALL)
    private List<RendicionDetalle> rendicionDetalle;
    @OneToOne(cascade= CascadeType.REMOVE)
    private Documento archivoRendicion;
    private int anioRendido;
    private String libreDeuda;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRendicion() {
        return fechaRendicion;
    }

    public void setFechaRendicion(Date fechaRendicion) {
        this.fechaRendicion = fechaRendicion;
    }

    public BigDecimal getMontoRendido() {
        return montoRendido;
    }

    public void setMontoRendido(BigDecimal montoRendido) {
        this.montoRendido = montoRendido;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<RendicionDetalle> getRendicionDetalle() {
        return rendicionDetalle;
    }

    public void setRendicionDetalle(List<RendicionDetalle> rendicionDetalle) {
        this.rendicionDetalle = rendicionDetalle;
    }

    public Documento getArchivoRendicion() {
        return archivoRendicion;
    }

    public void setArchivoRendicion(Documento archivoRendicion) {
        this.archivoRendicion = archivoRendicion;
    }    

    public PagoEconomico getPagoEconomico() {
        return pagoEconomico;
    }

    public void setPagoEconomico(PagoEconomico pagoEconomico) {
        this.pagoEconomico = pagoEconomico;
    }

    public int getAnioRendido() {
        return anioRendido;
    }

    public void setAnioRendido(int anioRendido) {
        this.anioRendido = anioRendido;
    }

    public String getLibreDeuda() {
        return libreDeuda;
    }

    public void setLibreDeuda(String libreDeuda) {
        this.libreDeuda = libreDeuda;
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
        if (!(object instanceof Rendicion)) {
            return false;
        }
        Rendicion other = (Rendicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.economico.rendicion[ id=" + id + " ]";
    }
    
}
