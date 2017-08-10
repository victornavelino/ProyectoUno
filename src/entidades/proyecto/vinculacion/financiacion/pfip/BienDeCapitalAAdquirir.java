/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="biendecapitalaadquirir")
public class BienDeCapitalAAdquirir  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Float costo;
    private String destino;
    private Float parteMincyt;
    private Float parteContraparte;

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Float getParteContraparte() {
        return parteContraparte;
    }

    public void setParteContraparte(Float parteContraparte) {
        this.parteContraparte = parteContraparte;
    }

    public Float getParteMincyt() {
        return parteMincyt;
    }

    public void setParteMincyt(Float parteMincyt) {
        this.parteMincyt = parteMincyt;
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
        if (!(object instanceof BienDeCapitalAAdquirir)) {
            return false;
        }
        BienDeCapitalAAdquirir other = (BienDeCapitalAAdquirir) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.BienDeCapitalAAdquirir[ id=" + id + " ]";
    }
    
}
