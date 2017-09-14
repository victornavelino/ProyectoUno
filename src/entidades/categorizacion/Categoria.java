/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.categorizacion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name="categoria")
@Inheritance(strategy=InheritanceType.JOINED)
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @OneToOne
    private ValorCategoria valorCategoria;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCategoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ValorCategoria getValorCategoria() {
        return valorCategoria;
    }

    public void setValorCategoria(ValorCategoria valorCategoria) {
        this.valorCategoria = valorCategoria;
    }

    public Date getFechaCategoria() {
        return fechaCategoria;
    }

    public void setFechaCategoria(Date fechaCategoria) {
        this.fechaCategoria = fechaCategoria;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return valorCategoria.getDescripcion();
    }
    
}
