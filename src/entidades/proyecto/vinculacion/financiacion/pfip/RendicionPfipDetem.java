/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="rendicionpfipdetem")
public class RendicionPfipDetem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    @OneToMany (cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<ResumenRendicion> resumen;
    @OneToMany (cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<BienesDeCapital> bienesdecapital;
    @OneToMany (cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<RecursosHumanos> recursoshumanos;
    @OneToMany (cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Consultorias> consultorias;
    @OneToMany (cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Otros> otros;
    @OneToMany (cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<MaterialesInsumos> materiales;

    public List<BienesDeCapital> getBienesdecapital() {
        return bienesdecapital;
    }

    public void setBienesdecapital(List<BienesDeCapital> bienesdecapital) {
        this.bienesdecapital = bienesdecapital;
    }

    public List<Consultorias> getConsultorias() {
        return consultorias;
    }

    public void setConsultorias(List<Consultorias> consultorias) {
        this.consultorias = consultorias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<MaterialesInsumos> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<MaterialesInsumos> materiales) {
        this.materiales = materiales;
    }

    public List<Otros> getOtros() {
        return otros;
    }

    public void setOtros(List<Otros> otros) {
        this.otros = otros;
    }

    public List<RecursosHumanos> getRecursoshumanos() {
        return recursoshumanos;
    }

    public void setRecursoshumanos(List<RecursosHumanos> recursoshumanos) {
        this.recursoshumanos = recursoshumanos;
    }

    public List<ResumenRendicion> getResumen() {
        return resumen;
    }

    public void setResumen(List<ResumenRendicion> resumen) {
        this.resumen = resumen;
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
        if (!(object instanceof RendicionPfipDetem)) {
            return false;
        }
        RendicionPfipDetem other = (RendicionPfipDetem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titulo+"     "+descripcion;
    }
    
}
