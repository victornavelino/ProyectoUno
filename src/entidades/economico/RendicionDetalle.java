/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.economico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.omg.CORBA.DATA_CONVERSION;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name = "economico_rendicion_detalle")
public class RendicionDetalle implements Serializable, Comparable<RendicionDetalle> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(scale=2, precision=12)
    private BigDecimal precioUnitario;
    private int cantidadCompra;
    private String numeroActaPatrimonial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCompra;
    private String detalle;   
    private String proveedor;
    private String numeroComprobante;
    private String tipoBien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }   

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }    

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNumeroActaPatrimonial() {
        return numeroActaPatrimonial;
    }

    public void setNumeroActaPatrimonial(String numeroActaPatrimonial) {
        this.numeroActaPatrimonial = numeroActaPatrimonial;
    }   

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getTipoBien() {
        return tipoBien;
    }

    public void setTipoBien(String tipoBien) {
        this.tipoBien = tipoBien;
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
        if (!(object instanceof RendicionDetalle)) {
            return false;
        }
        RendicionDetalle other = (RendicionDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.economico.rendicionDetalle[ id=" + id + " ]";
    }

    @Override
    public int compareTo(RendicionDetalle rd) {             
        return this.tipoBien.compareTo(rd.tipoBien);       
    }
    
}
