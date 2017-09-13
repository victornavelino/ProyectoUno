/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas.evaluacion;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "becas_item_evaluar")
@NamedQueries({
    
    @NamedQuery(name="BecasItemEvaluar.findByDescripcion", 
            query="SELECT b FROM BecasItemEvaluar b WHERE TRIM(LOWER(b.descripcion)) = :descripcion"),
    
    @NamedQuery(name="BecasItemEvaluar.findByDescripcionID", 
            query="SELECT b FROM BecasItemEvaluar b WHERE TRIM(LOWER(b.descripcion)) = :descripcion AND b.id <> :id")
})
public class BecasItemEvaluar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof BecasItemEvaluar)) {
            return false;
        }
        BecasItemEvaluar other = (BecasItemEvaluar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return this.descripcion;

        
    }
    
}
