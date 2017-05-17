/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyectoWeb;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Rol;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "proyectoweb_participacion")
public class ParticipacionWeb implements Serializable, Comparable<ParticipacionWeb> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "FECHADESDE")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    
    @Column(name = "FECHAHASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    
    @JoinColumn(name = "INVESTIGADOR_ID")
    @OneToOne
    private Investigador investigador;
    
    @JoinColumn(name = "PROYECTO_ID")
    @ManyToOne
    private ProyectoWeb proyectoWeb;
    
    @JoinColumn(name = "ROL_ID")
    @OneToOne
    private Rol rol;
    
    @Column(name = "DEDICACIONSEMANAL")
    private Integer dedicacionSemanal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProyectoWeb getProyectoWeb() {
        return proyectoWeb;
    }

    public void setProyectoWeb(ProyectoWeb proyectoWeb) {
        this.proyectoWeb = proyectoWeb;
    }

    

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Integer getDedicacionSemanal() {
        return dedicacionSemanal;
    }

    public void setDedicacionSemanal(Integer dedicacionSemanal) {
        this.dedicacionSemanal = dedicacionSemanal;
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
        if (!(object instanceof ParticipacionWeb)) {
            return false;
        }
        ParticipacionWeb other = (ParticipacionWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         try {
            return investigador.getPersona().getApellido() + ", "
                    + investigador.getPersona().getNombre() + " - " + investigador.getPersona().getDocumentoIdentidad() + " - " + investigador.getPersona().getDomicilio();

        } catch (Exception ex) {
            return "";
        }
    }
    
    @Override
    public int compareTo(ParticipacionWeb pw) {
        
        Long idRol = this.getRol().getId();
        if (idRol < pw.getRol().getId()) {
            return -1;
        } 
            
        return 1;

    }
}
