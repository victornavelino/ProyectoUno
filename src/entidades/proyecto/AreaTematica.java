/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.proyecto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name="areatematica")
public class AreaTematica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @OneToMany(mappedBy = "areaTematica")
    private List<DisciplinaCientifica> disciplinasCientificas;

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

    public List<DisciplinaCientifica> getDisciplinasCientificas() {
        return disciplinasCientificas;
    }

    public void setDisciplinasCientificas(List<DisciplinaCientifica> disciplinasCientificas) {
        this.disciplinasCientificas = disciplinasCientificas;
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
        if (!(object instanceof AreaTematica)) {
            return false;
        }
        AreaTematica other = (AreaTematica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
