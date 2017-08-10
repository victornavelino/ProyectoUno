/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas;

import entidades.Resolucion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author hugo
 */
@Entity
@Table(name="beca_pago")
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private PostulacionBeca postulacionBeca;
    @OneToOne
    private Resolucion resolucion;
    private String expediente;
    private float monto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String nroCheque;
    @OneToOne
    private NumPago numPago;
    private String observaciones;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getNroCheque() {
        return nroCheque;
    }

    public void setNroCheque(String nroCheque) {
        this.nroCheque = nroCheque;
    }

    public NumPago getNumPago() {
        return numPago;
    }

    public void setNumPago(NumPago numPago) {
        this.numPago = numPago;
    }

    public Resolucion getResolucion() {
        return resolucion;
    }

    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }

    public PostulacionBeca getPostulacionbeca() {
        return postulacionBeca;
    }

    public void setPostulacionbeca(PostulacionBeca postulacionbeca) {
        this.postulacionBeca = postulacionbeca;
    }
    
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.becas.Pago[ id=" + id + " ]";
    }
    
}
