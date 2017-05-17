/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="financiacion")
@Inheritance(strategy=InheritanceType.JOINED)
public class Financiacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
      @OneToMany(cascade = CascadeType.ALL)
    private List<NotaExterna> notasExternas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<NotaInterna> notasInternas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FondosySaldos> retiros;

    public List<FondosySaldos> getRetiros() {
        return retiros;
    }

    public void setRetiros(List<FondosySaldos> retiros) {
        this.retiros = retiros;
    }
        
    public List<NotaExterna> getNotasExternas() {
        return notasExternas;
    }

    public void setNotasExternas(List<NotaExterna> notasExternas) {
        this.notasExternas = notasExternas;
    }

    public List<NotaInterna> getNotasInternas() {
        return notasInternas;
    }

    public void setNotasInternas(List<NotaInterna> notasInternas) {
        this.notasInternas = notasInternas;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof Financiacion)) {
            return false;
        }
        Financiacion other = (Financiacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getDescripcion();
    }
    
}
