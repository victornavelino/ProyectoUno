/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Rol;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name = "participacionvinculacion")
public class ParticipacionVinculacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FECHADESDE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "FECHAHASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @JoinColumn(name = "INVESTIGADOR_ID", nullable = false)
    @OneToOne
    private Investigador investigador;
    @JoinColumn(name = "PROYECTOVINCULACION_ID", nullable = false)
    @OneToOne
    private ProyectoVinculacion proyectoVinculacion;
    @JoinColumn(name = "ROL_ID", nullable = false)
    @OneToOne
    private Rol rol;
    @Column(name = "DEDICACIONSEMANAL")
    private Integer dedicacionSemanal;
    private String resolucionAlta;
    private String resolucionBaja;
    
        public String getResolucionAlta() {
        return resolucionAlta;
    }

    public void setResolucionAlta(String resolucionAlta) {
        this.resolucionAlta = resolucionAlta;
    }

    public String getResolucionBaja() {
        return resolucionBaja;
    }

    public void setResolucionBaja(String resolucionBaja) {
        this.resolucionBaja = resolucionBaja;
    }
    
    public Integer getDedicacionSemanal() {
        return dedicacionSemanal;
    }

    public void setDedicacionSemanal(Integer dedicacionSemanal) {
        this.dedicacionSemanal = dedicacionSemanal;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public ProyectoVinculacion getProyectoVinculacion() {
        return proyectoVinculacion;
    }

    public void setProyectoVinculacion(ProyectoVinculacion proyectoVinculacion) {
        this.proyectoVinculacion = proyectoVinculacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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
        if (!(object instanceof ParticipacionVinculacion)) {
            return false;
        }
        ParticipacionVinculacion other = (ParticipacionVinculacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            String fechaDesd = "";
            String fechaHast = "";
            if (fechaDesde != null) {
                fechaDesd = new SimpleDateFormat("dd/MM/yyyy").format(fechaDesde);
            }
            if (fechaHasta != null) {
                fechaHast = new SimpleDateFormat("dd/MM/yyyy").format(fechaHasta);
            } else {
                fechaHast = "Actualidad";
            }

            return investigador.getPersona().toString() + " - " + rol + " - " + fechaDesd + " - " + fechaHast;
        } catch (Exception ex) {
            return "No se puede mostrar la participación";
        }

    }
}
