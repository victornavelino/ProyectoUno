/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.categorizacion;

import entidades.Documento;
import entidades.Resolucion;
import entidades.UnidadAcademica;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name = "categorizacion")
public class Categorizacion implements Serializable, Comparable<Categorizacion> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Llamado llamado;
    @OneToOne(cascade = CascadeType.ALL)
    private CategoriaAnterior categoriaAnterior;
    @OneToOne(cascade = CascadeType.ALL)
    private CategoriaAsignada categoriaAsignada;
    @OneToOne(cascade = CascadeType.ALL)
    private CategoriaSolicitada categoriaSolicitada;
    private String condicion;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Documento> resoluciones;
    @ManyToOne
    private UnidadAcademica unidadAcademica;
    @ManyToOne
    private ComisionCategorizacion comision;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoriaAnterior getCategoriaAnterior() {
        return categoriaAnterior;
    }

    public void setCategoriaAnterior(CategoriaAnterior categoriaAnterior) {
        this.categoriaAnterior = categoriaAnterior;
    }

    public CategoriaAsignada getCategoriaAsignada() {
        return categoriaAsignada;
    }

    public void setCategoriaAsignada(CategoriaAsignada categoriaAsignada) {
        this.categoriaAsignada = categoriaAsignada;
    }

    public CategoriaSolicitada getCategoriaSolicitada() {
        return categoriaSolicitada;
    }

    public void setCategoriaSolicitada(CategoriaSolicitada categoriaSolicitada) {
        this.categoriaSolicitada = categoriaSolicitada;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Llamado getLlamado() {
        return llamado;
    }

    public void setLlamado(Llamado llamado) {
        this.llamado = llamado;
    }

    public ComisionCategorizacion getComision() {
        return comision;
    }

    public void setComision(ComisionCategorizacion comision) {
        this.comision = comision;
    }

    public UnidadAcademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<Documento> getResoluciones() {
        return resoluciones;
    }

    public void setResoluciones(List<Documento> resoluciones) {
        this.resoluciones = resoluciones;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorizacion)) {
            return false;
        }
        Categorizacion other = (Categorizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return llamado + " - Categoría: " + categoriaAsignada;
    }

    @Override
    public int compareTo(Categorizacion c) {
        int compareTo = 0;
        try {
            compareTo = this.llamado.getFecha().compareTo(c.getLlamado().getFecha());
        } catch (Exception ex) {
            System.out.println("Error comparando");
        }
        return compareTo;
    }
}
