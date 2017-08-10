/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.investigador.formacionAcademica;

import entidades.Universidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="formacionacademica")
@Inheritance(strategy= InheritanceType.JOINED)
public class FormacionAcademica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private int anoIngreso;
    private int anoEgreso;
    private boolean finalizado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnoEgreso() {
        return anoEgreso;
    }

    public void setAnoEgreso(int anoEgreso) {
        this.anoEgreso = anoEgreso;
    }

    public int getAnoIngreso() {
        return anoIngreso;
    }

    public void setAnoIngreso(int anoIngreso) {
        this.anoIngreso = anoIngreso;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
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
        if (!(object instanceof FormacionAcademica)) {
            return false;
        }
        FormacionAcademica other = (FormacionAcademica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.investigador.formacionAcademica.FormacionAcademica[ id=" + id + " ]";
    }
    
}
