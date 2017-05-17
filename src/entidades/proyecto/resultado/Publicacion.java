/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Proyecto;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author franco
 */
@Inheritance(strategy= InheritanceType.JOINED)
@Entity
@Table(name = "resultado_publicacion")
public abstract class Publicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String titulo;
    @ManyToMany
    private List<Proyecto> proyectos;
    @ManyToMany
    private List<ProyectoVinculacion> proyectosVinculacion;

    
    @ManyToMany
    private List<Investigador> investigadores;

    
    public List<ProyectoVinculacion> getProyectosVinculacion() {
        return proyectosVinculacion;
    }

    public void setProyectosVinculacion(List<ProyectoVinculacion> proyectosVinculacion) {
        this.proyectosVinculacion = proyectosVinculacion;
    }
    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(List<Investigador> investigadores) {
        this.investigadores = investigadores;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.resultado.Publicacion[ id=" + id + " ]";
    }
    
}
