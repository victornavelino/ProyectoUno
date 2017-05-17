/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion;

import entidades.proyecto.vinculacion.Financiacion;
import entidades.proyecto.vinculacion.financiacion.picto.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_financiacion_pict")
public class FinanciacionPict extends Financiacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaTematica;
    private String categoria;
    private int duracion;
    private String institucionbeneficiaria;
    private String representanteinstben;
    @Lob
    private String institucionesproyecto;
    @OneToMany(cascade= CascadeType.ALL)
    private List<GrupoResponsable> gr;
    @OneToMany(cascade= CascadeType.ALL)
    private List<GrupoColaborador> gc;
    @OneToMany(cascade= CascadeType.ALL)
    private List<Impacto> impacto;
    @OneToMany(cascade= CascadeType.ALL)
    private List<Item> items;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<RendicionDeCuentas> rendiciondecuentas;

    public List<RendicionDeCuentas> getRendiciondecuentas() {
        return rendiciondecuentas;
    }

    public void setRendiciondecuentas(List<RendicionDeCuentas> rendiciondecuentas) {
        this.rendiciondecuentas = rendiciondecuentas;
    }
    
    

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<GrupoColaborador> getGc() {
        return gc;
    }

    public void setGc(List<GrupoColaborador> gc) {
        this.gc = gc;
    }

    public List<GrupoResponsable> getGr() {
        return gr;
    }

    public void setGr(List<GrupoResponsable> gr) {
        this.gr = gr;
    }

    public List<Impacto> getImpacto() {
        return impacto;
    }

    public void setImpacto(List<Impacto> impacto) {
        this.impacto = impacto;
    }

    public String getInstitucionbeneficiaria() {
        return institucionbeneficiaria;
    }

    public void setInstitucionbeneficiaria(String institucionbeneficiaria) {
        this.institucionbeneficiaria = institucionbeneficiaria;
    }

    public String getInstitucionesproyecto() {
        return institucionesproyecto;
    }

    public void setInstitucionesproyecto(String institucionesproyecto) {
        this.institucionesproyecto = institucionesproyecto;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getRepresentanteinstben() {
        return representanteinstben;
    }

    public void setRepresentanteinstben(String representanteinstben) {
        this.representanteinstben = representanteinstben;
    }
    

    public Long getId() {
        return super.getId();
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
        if (!(object instanceof FinanciacionPict)) {
            return false;
        }
        FinanciacionPict other = (FinanciacionPict) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.getDescripcion();
    }
    
}
