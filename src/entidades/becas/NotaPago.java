/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author huguito
 */
@Entity
@Table(name="beca_notapago")
public class NotaPago implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String nroNota;
    private String motivo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeRecibidoMesaDeEntrada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesembolso;
    @ManyToOne
    private PostulacionBeca postulacionBeca;
    
    public PostulacionBeca getPostulacionBeca() {
        return postulacionBeca;
    }

    public void setPostulacionBeca(PostulacionBeca postulacionBeca) {
        this.postulacionBeca = postulacionBeca;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNroNota() {
        return nroNota;
    }

    public void setNroNota(String nroNota) {
        this.nroNota = nroNota;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaDeRecibidoMesaDeEntrada() {
        return fechaDeRecibidoMesaDeEntrada;
    }

    public void setFechaDeRecibidoMesaDeEntrada(Date fechaDeRecibidoMesaDeEntrada) {
        this.fechaDeRecibidoMesaDeEntrada = fechaDeRecibidoMesaDeEntrada;
    }

    
    public Date getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(Date fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
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
        if (!(object instanceof NotaPago)) {
            return false;
        }
        NotaPago other = (NotaPago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.becas.NotaPago[ id=" + id + " ]";
    }
    
}
