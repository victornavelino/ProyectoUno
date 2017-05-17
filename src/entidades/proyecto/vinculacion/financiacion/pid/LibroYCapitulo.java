/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

import entidades.proyecto.resultado.TipoEdicion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.OneToMany;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_libro_y_capitulo")
@DiscriminatorValue("Libro y/o capitulo")
public class LibroYCapitulo extends Produccion implements Serializable {
 
    private Long id;
    private String estado;
    @Enumerated(EnumType.STRING)
    private TipoEdicion ambito;
    private String isbn;
    @OneToMany
    private List<Autor> autores;
    @Lob
    private String descripcionesVarias;

    public TipoEdicion getAmbito() {
        return ambito;
    }

    public void setAmbito(TipoEdicion ambito) {
        this.ambito = ambito;
    }

    

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getDescripcionesVarias() {
        return descripcionesVarias;
    }

    public void setDescripcionesVarias(String descripcionesVarias) {
        this.descripcionesVarias = descripcionesVarias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pid.LibroYCapitulo[ id=" + super.getId() + " ]";
    }
    
}
