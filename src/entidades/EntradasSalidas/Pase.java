/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.EntradasSalidas;

import entidades.Documento;
import entidades.Resolucion;
import entidades.persona.investigador.Investigador;
import entidades.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Temporal;

/**
 *
 * @author vouilloz
 */
@Entity
@Table(name="entradasalida_pase")
public class Pase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tramite;
    private String movimiento;
    private String motivo;
    private boolean borrado;
    private Integer num_fojas;


    @OneToOne
    private Usuario receptor= null;
    
    @OneToOne
    private Investigador investigadorEmisor = null;

    @OneToOne
    private Investigador investigadorDestino = null;

    @OneToOne
    private Areas areaEmisor = null;

    @OneToOne
    private Areas areaDestino = null;
    
    @OneToOne
    private OtrosEmisoresReceptores otroEmisor = null;
    
    @OneToOne
    private OtrosEmisoresReceptores otroDestino = null;

    @OneToOne(fetch = FetchType.LAZY)
    private Resolucion resolucion = null;

    @OneToOne
    private Expediente expediente = null;

    @OneToOne
    private NotaEntradaSalida nota = null;
    
    @OneToOne
    private Correspondencia correspondencia = null;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Documento documento;    

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRegistro;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecepcion;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDestino;

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public int getNum_fojas() {
        return num_fojas;
    }

    public void setNum_fojas(int num_fojas) {
        this.num_fojas = num_fojas;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investigador getInvestigadorEmisor() {
        return investigadorEmisor;
    }

    public void setInvestigadorEmisor(Investigador investigadorEmisor) {
        this.investigadorEmisor = investigadorEmisor;
    }

    public Investigador getInvestigadorDestino() {
        return investigadorDestino;
    }

    public void setInvestigadorDestino(Investigador investigadorDestino) {
        this.investigadorDestino = investigadorDestino;
    }

    public Areas getAreaEmisor() {
        return areaEmisor;
    }

    public void setAreaEmisor(Areas areaEmisor) {
        this.areaEmisor = areaEmisor;
    }

    public Areas getAreaDestino() {
        return areaDestino;
    }

    public void setAreaDestino(Areas areaDestino) {
        this.areaDestino = areaDestino;
    }

    public Resolucion getResolucion() {
        return resolucion;
    }

    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public OtrosEmisoresReceptores getOtroEmisor() {
        return otroEmisor;
    }

    public void setOtroEmisor(OtrosEmisoresReceptores otroEmisor) {
        this.otroEmisor = otroEmisor;
    }

    public OtrosEmisoresReceptores getOtroDestino() {
        return otroDestino;
    }

    public void setOtroDestino(OtrosEmisoresReceptores otroDestino) {
        this.otroDestino = otroDestino;
    }
    
    

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaDestino() {
        return fechaDestino;
    }

    public void setFechaDestino(Date fechaDestino) {
        this.fechaDestino = fechaDestino;
    }

    public NotaEntradaSalida getNota() {
        return nota;
    }

    public void setNota(NotaEntradaSalida nota) {
        this.nota = nota;
    }

    public Correspondencia getCorrespondencia() {
        return correspondencia;
    }

    public void setCorrespondencia(Correspondencia correspondencia) {
        this.correspondencia = correspondencia;
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
        if (!(object instanceof Pase)) {
            return false;
        }
        Pase other = (Pase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EntradasSalidas.Pase[ id=" + id + " ]";
    }

}
