/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.picto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="equipamiento")
public class Equipamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String datosequipamiento;
    private String datosproveedor;
    private String numerofactura;
    private String fecha;
    private Float aportefoncyt;
    private Float aporteinstitucion;
    private Float otrosaportes;

    public Float getAportefoncyt() {
        return aportefoncyt;
    }

    public void setAportefoncyt(Float aportefoncyt) {
        this.aportefoncyt = aportefoncyt;
    }

    public Float getAporteinstitucion() {
        return aporteinstitucion;
    }

    public void setAporteinstitucion(Float aporteinstitucion) {
        this.aporteinstitucion = aporteinstitucion;
    }

    public String getDatosequipamiento() {
        return datosequipamiento;
    }

    public void setDatosequipamiento(String datosequipamiento) {
        this.datosequipamiento = datosequipamiento;
    }

    public String getDatosproveedor() {
        return datosproveedor;
    }

    public void setDatosproveedor(String datosproveedor) {
        this.datosproveedor = datosproveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Float getOtrosaportes() {
        return otrosaportes;
    }

    public void setOtrosaportes(Float otrosaportes) {
        this.otrosaportes = otrosaportes;
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
        if (!(object instanceof Equipamiento)) {
            return false;
        }
        Equipamiento other = (Equipamiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return datosequipamiento;
    }
    
}
