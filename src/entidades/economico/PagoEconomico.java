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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name = "economico_pago")
public class PagoEconomico implements Serializable {    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroExpediente;
    private int anioExpediente;
    @OneToOne(cascade= CascadeType.REMOVE)
    private ResolucionConsejoSuperior resolucionConsejoSuperior;
    @OneToOne(cascade= CascadeType.REMOVE)
    private ResolucionRectoral resolucionRectoral;
    @Column(scale=2, precision=12)
    private BigDecimal monto;   
    @OneToOne(cascade= CascadeType.REMOVE)
    private ResolucionSecyt ResolucionSecyt;
    @Lob    
    private String observacion;
    @OneToMany(mappedBy = "pago", cascade= CascadeType.REMOVE)    
    private List<Cobro> cobros;
    private String numeroOrdenPago;
    @OneToMany(mappedBy = "pagoEconomico")
    private List<Rendicion> rendiciones;
    @OneToOne(cascade= CascadeType.ALL)
    private Documento archivoResolucionConsejoSuperior;
    @OneToOne(cascade= CascadeType.ALL)
    private Documento archivoResolucionRectoral;
    @OneToOne(cascade= CascadeType.ALL)
    private Documento archivoResolucionSecyt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<Cobro> getCobros() {
        return cobros;
    }

    public void setCobros(List<Cobro> cobros) {
        this.cobros = cobros;
    }

    public entidades.economico.ResolucionConsejoSuperior getResolucionConsejoSuperior() {
        return resolucionConsejoSuperior;
    }

    public void setResolucionConsejoSuperior(entidades.economico.ResolucionConsejoSuperior resolucionConsejoSuperior) {
        this.resolucionConsejoSuperior = resolucionConsejoSuperior;
    }

    public entidades.economico.ResolucionRectoral getResolucionRectoral() {
        return resolucionRectoral;
    }

    public void setResolucionRectoral(entidades.economico.ResolucionRectoral resolucionRectoral) {
        this.resolucionRectoral = resolucionRectoral;
    }

    public int getAnioExpediente() {
        return anioExpediente;
    }

    public void setAnioExpediente(int anioExpediente) {
        this.anioExpediente = anioExpediente;
    }
    
    public entidades.economico.ResolucionSecyt getResolucionSecyt() {
        return ResolucionSecyt;
    }

    public void setResolucionSecyt(entidades.economico.ResolucionSecyt ResolucionSecyt) {
        this.ResolucionSecyt = ResolucionSecyt;
    }

    public String getNumeroOrdenPago() {
        return numeroOrdenPago;
    }

    public void setNumeroOrdenPago(String numeroOrdenPago) {
        this.numeroOrdenPago = numeroOrdenPago;
    }

    public List<Rendicion> getRendiciones() {
        return rendiciones;
    }

    public void setRendiciones(List<Rendicion> rendiciones) {
        this.rendiciones = rendiciones;
    }

    public Documento getArchivoResolucionConsejoSuperior() {
        return archivoResolucionConsejoSuperior;
    }

    public void setArchivoResolucionConsejoSuperior(Documento archivoResolucionConsejoSuperior) {
        this.archivoResolucionConsejoSuperior = archivoResolucionConsejoSuperior;
    }

    public Documento getArchivoResolucionRectoral() {
        return archivoResolucionRectoral;
    }

    public void setArchivoResolucionRectoral(Documento archivoResolucionRectoral) {
        this.archivoResolucionRectoral = archivoResolucionRectoral;
    }

    public Documento getArchivoResolucionSecyt() {
        return archivoResolucionSecyt;
    }

    public void setArchivoResolucionSecyt(Documento archivoResolucionSecyt) {
        this.archivoResolucionSecyt = archivoResolucionSecyt;
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
        if (!(object instanceof PagoEconomico)) {
            return false;
        }
        PagoEconomico other = (PagoEconomico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Expte:" + numeroExpediente + " - " + "$ " + monto;
    }
    
}
