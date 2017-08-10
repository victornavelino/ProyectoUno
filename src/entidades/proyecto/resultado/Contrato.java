/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

import entidades.proyecto.Proyecto;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "resultado_contrato")
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    TipoContrato tipoContrato;
    String destinatarios;
    @Lob
    String objeto;
    @Column(precision = 10, scale = 2)
    BigDecimal montoObtenido;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date fechaContrato;
    @ManyToMany
    private List<Proyecto> proyectos;
    @ManyToMany
    private List<ProyectoVinculacion> proyectosVinculacion;

    public List<ProyectoVinculacion> getProyectosVinculacion() {
        return proyectosVinculacion;
    }

    public void setProyectosVinculacion(List<ProyectoVinculacion> proyectosVinculacion) {
        this.proyectosVinculacion = proyectosVinculacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public BigDecimal getMontoObtenido() {
        return montoObtenido;
    }

    public void setMontoObtenido(BigDecimal montoObtenido) {
        this.montoObtenido = montoObtenido;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String strFechaContrato = "";
        strFechaContrato = new SimpleDateFormat("dd/MM/YYYY").format(fechaContrato);
        return strFechaContrato + " \n" + objeto + " \n" + destinatarios + " \n" + tipoContrato + " \n" + montoObtenido;
    }
}
