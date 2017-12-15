/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.editorial;

import entidades.Idioma;
import entidades.UnidadAcademica;
import entidades.persona.investigador.Investigador;
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
    @Lob
    private String autor;
    private String coautor;
    private String ISBN;
    @Lob
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
    private String summary;        
    @ManyToMany
    private List<Investigador> investigadores;
    private List<DonacionEditorial> donaciones;
    
    private int cantidadPaginas;
    @ManyToOne
    private Idioma idioma;
    private String referato;
    private String tipoCodigo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecibido;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAceptado;
    @OneToOne
    private UnidadAcademica unidadAcademica;
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getReferato() {
        return referato;
    }

    public void setReferato(String referato) {
        this.referato = referato;
    }

    public String getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Date getFechaAceptado() {
        return fechaAceptado;
    }

    public void setFechaAceptado(Date fechaAceptado) {
        this.fechaAceptado = fechaAceptado;
    }

    public UnidadAcademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }
 
    
    public List<DonacionEditorial> getDonaciones() {
        return donaciones;
    }

    public void setDonaciones(List<DonacionEditorial> donaciones) {
        this.donaciones = donaciones;
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(List<Investigador> investigadores) {
        this.investigadores = investigadores;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

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
