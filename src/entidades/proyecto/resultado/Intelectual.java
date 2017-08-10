/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author hugo
 */
@Inheritance(strategy= InheritanceType.JOINED)
@Entity
@Table(name="resultado_propintelectual")
public class Intelectual extends Propiedad implements Serializable {
    @OneToMany(mappedBy="intelectual")
    
    @Enumerated(EnumType.STRING)
    private TipoRegistroIntelectual tipoRegistroIntelectual;
    private String titulares;
    private String nroRegistro;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String pais;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(String nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public TipoRegistroIntelectual getTipoRegistroIntelectual() {
        return tipoRegistroIntelectual;
    }

    public void setTipoRegistroIntelectual(TipoRegistroIntelectual tipoRegistroIntelectual) {
        this.tipoRegistroIntelectual = tipoRegistroIntelectual;
    }

    public String getTitulares() {
        return titulares;
    }

    public void setTitulares(String titulares) {
        this.titulares = titulares;
    }

    @Override
    public String toString() {
        return super.getTitulo();
    }
     

    

    
    
    
}
