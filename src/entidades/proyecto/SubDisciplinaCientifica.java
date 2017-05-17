/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.proyecto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name="subdisciplinacientifica")
public class SubDisciplinaCientifica implements Serializable {
    @ManyToOne
    private DisciplinaCientifica disciplinaCientifica;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int codigo;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DisciplinaCientifica getDisciplinaCientifica() {
        return disciplinaCientifica;
    }

    public void setDisciplinaCientifica(DisciplinaCientifica disciplinaCientifica) {
        this.disciplinaCientifica = disciplinaCientifica;
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
        if (!(object instanceof SubDisciplinaCientifica)) {
            return false;
        }
        SubDisciplinaCientifica other = (SubDisciplinaCientifica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(codigo) + " - " + descripcion;
    }

}
