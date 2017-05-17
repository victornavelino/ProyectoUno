/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.editorial;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author panchi
 */
@Entity
@Table(name="editorial_expedienteeditorial")
public class ExpedienteEditorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String iniciador;
    @OneToOne()
    private EditorialCientifica publicacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIniciador() {
        return iniciador;
    }

    public void setIniciador(String iniciador) {
        this.iniciador = iniciador;
    }

    public EditorialCientifica getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(EditorialCientifica publicacion) {
        this.publicacion = publicacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof ExpedienteEditorial)) {
            return false;
        }
        ExpedienteEditorial other = (ExpedienteEditorial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Código: " + this.getCodigo() + " Publicación: " + this.getPublicacion() + " Iniciador: " + this.getIniciador();
    }

}
