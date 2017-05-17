/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.editorial;

import entidades.proyecto.Proyecto;
import entidades.proyecto.vinculacion.ParticipacionVinculacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name = "editorial_editorialCientifica")
public class EditorialCientifica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String titulo;
    private String autor;
    private String coautor;
    private String ISBN;
    private String resumen;
    @Temporal(javax.persistence.TemporalType.DATE)    
    private Date anioPublicacion;
    @OneToOne(cascade = CascadeType.ALL)
    private TipoPublicacion tipoPublicacion;
    @OneToOne(cascade = CascadeType.ALL)
    private ExpedienteEditorial expediente;
    @OneToOne(cascade = CascadeType.ALL)
    private Proyecto proyecto;
    private String menciones;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EvaluacionEditorial> evaluacionesEditorial;

    public List<EvaluacionEditorial> getEvaluacionesEditorial() {
        return evaluacionesEditorial;
    }

    public void setEvaluacionesEditorial(List<EvaluacionEditorial> evaluacionesEditorial) {
        this.evaluacionesEditorial = evaluacionesEditorial;
    }
    
    
    public String getMenciones() {
        return menciones;
    }

    public void setMenciones(String menciones) {
        this.menciones = menciones;
    }

    public ExpedienteEditorial getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteEditorial expediente) {
        this.expediente = expediente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCoautor() {
        return coautor;
    }

    public void setCoautor(String coautor) {
        this.coautor = coautor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Date getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Date anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
        if (!(object instanceof EditorialCientifica)) {
            return false;
        }
        EditorialCientifica other = (EditorialCientifica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getTitulo();
    }

}
