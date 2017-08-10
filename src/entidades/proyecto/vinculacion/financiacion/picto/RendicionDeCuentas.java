/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.picto;

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
@Table(name="rendiciondecuentas")
public class RendicionDeCuentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String codigo;
    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Resumen> resumen;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Honorarios> honorarios;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Beca> becas;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Equipamiento> equipamiento;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<MaterialesEInsumos> materialeseinsumos;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<ViajesViaticos> viajesviaticos;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Tecnicos> serviciostecnicos;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Publicaciones> publicaciones;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<AdministracionSubsidio> gastosadministracion;
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Bibliografia> bibliografia;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    

    public List<Beca> getBecas() {
        return becas;
    }

    public void setBecas(List<Beca> becas) {
        this.becas = becas;
    }

    public List<Bibliografia> getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(List<Bibliografia> bibliografia) {
        this.bibliografia = bibliografia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Equipamiento> getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(List<Equipamiento> equipamiento) {
        this.equipamiento = equipamiento;
    }

    public List<AdministracionSubsidio> getGastosadministracion() {
        return gastosadministracion;
    }

    public void setGastosadministracion(List<AdministracionSubsidio> gastosadministracion) {
        this.gastosadministracion = gastosadministracion;
    }

    public List<Honorarios> getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(List<Honorarios> honorarios) {
        this.honorarios = honorarios;
    }

    public List<MaterialesEInsumos> getMaterialeseinsumos() {
        return materialeseinsumos;
    }

    public void setMaterialeseinsumos(List<MaterialesEInsumos> materialeseinsumos) {
        this.materialeseinsumos = materialeseinsumos;
    }

    public List<Publicaciones> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicaciones> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Resumen> getResumen() {
        return resumen;
    }

    public void setResumen(List<Resumen> resumen) {
        this.resumen = resumen;
    }

    public List<Tecnicos> getServiciostecnicos() {
        return serviciostecnicos;
    }

    public void setServiciostecnicos(List<Tecnicos> serviciostecnicos) {
        this.serviciostecnicos = serviciostecnicos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<ViajesViaticos> getViajesviaticos() {
        return viajesviaticos;
    }

    public void setViajesviaticos(List<ViajesViaticos> viajesviaticos) {
        this.viajesviaticos = viajesviaticos;
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
        if (!(object instanceof RendicionDeCuentas)) {
            return false;
        }
        RendicionDeCuentas other = (RendicionDeCuentas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titulo+"    "+descripcion+"    "+codigo;
    }
    
}
