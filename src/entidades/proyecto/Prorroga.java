/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "prorroga")
public class Prorroga implements Serializable, Comparable<Prorroga> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
        if (!(object instanceof Prorroga)) {
            return false;
        }
        Prorroga other = (Prorroga) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        } catch (Exception ex) {
            return "";
        }
    }

    @Override
    public int compareTo(Prorroga o) {
        int compareTo = 0;
        try {
            compareTo = this.getFecha().compareTo(o.getFecha());
        } catch (Exception ex) {
            System.out.println("Error comparando");
        }
        return compareTo;
    }
}
