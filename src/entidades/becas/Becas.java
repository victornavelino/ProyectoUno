/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas;

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
 * @author huguito
 */
@Entity
@Table(name="becas")
public class Becas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigobeca;
    private String descripcion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechainicio, fechafinalizacion;
    private Integer duracion;
    private Integer cantidadpagos;
    private Integer montoporpago;
    private Integer cantidadbeneficiarios;
    private Boolean vigente;
    private Boolean paraalumnos;
    private Boolean paradocentes;
    private Boolean tieneproyecto;
    private Boolean tienedirector;
    private Boolean tieneposgrado;
    private Boolean tienecarrera;
    private String tipoBeca;

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) {
        this.tipoBeca = tipoBeca;
    }
    
    
    

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }
    

    public Integer getCantidadbeneficiarios() {
        return cantidadbeneficiarios;
    }

    public void setCantidadbeneficiarios(Integer cantidadbeneficiarios) {
        this.cantidadbeneficiarios = cantidadbeneficiarios;
    }

    public Integer getCantidadpagos() {
        return cantidadpagos;
    }

    public void setCantidadpagos(Integer cantidadpagos) {
        this.cantidadpagos = cantidadpagos;
    }

    public String getCodigobeca() {
        return codigobeca;
    }

    public void setCodigobeca(String codigobeca) {
        this.codigobeca = codigobeca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getFechafinalizacion() {
        return fechafinalizacion;
    }

    public void setFechafinalizacion(Date fechafinalizacion) {
        this.fechafinalizacion = fechafinalizacion;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Integer getMontoporpago() {
        return montoporpago;
    }

    public void setMontoporpago(Integer montoporpago) {
        this.montoporpago = montoporpago;
    }

    public Boolean getTienecarrera() {
        return tienecarrera;
    }

    public void setTienecarrera(Boolean tienecarrera) {
        this.tienecarrera = tienecarrera;
    }

    public Boolean getTienedirector() {
        return tienedirector;
    }

    public void setTienedirector(Boolean tienedirector) {
        this.tienedirector = tienedirector;
    }

    public Boolean getTieneposgrado() {
        return tieneposgrado;
    }

    public void setTieneposgrado(Boolean tieneposgrado) {
        this.tieneposgrado = tieneposgrado;
    }

    public Boolean getTieneproyecto() {
        return tieneproyecto;
    }

    public void setTieneproyecto(Boolean tieneproyecto) {
        this.tieneproyecto = tieneproyecto;
    }

    public Boolean getParaalumnos() {
        return paraalumnos;
    }

    public void setParaalumnos(Boolean paraalumnos) {
        this.paraalumnos = paraalumnos;
    }

    public Boolean getParadocentes() {
        return paradocentes;
    }

    public void setParadocentes(Boolean paradocentes) {
        this.paradocentes = paradocentes;
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
        if (!(object instanceof Becas)) {
            return false;
        }
        Becas other = (Becas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String vigencia;
        if(this.getVigente()){
            vigencia="(Vigente)";
        }else{
            vigencia="(no vigente)";
        }
        return descripcion+", "+vigencia;
    }
    
}
