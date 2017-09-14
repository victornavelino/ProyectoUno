/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.EntradasSalidas;

import entidades.Documento;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.swing.JOptionPane;

/**
 *
 * @author vouilloz
 */
@Entity
@Table(name = "entradasalida_correspondencia")
public class Correspondencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String remitente;
    private String destinantario;
    
 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRegistro;

    @OneToOne(fetch = FetchType.LAZY)
    private Documento documento; 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinantario() {
        return destinantario;
    }

    public void setDestinantario(String destinantario) {
        this.destinantario = destinantario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        if (!(object instanceof Correspondencia)) {
            return false;
        }
        Correspondencia other = (Correspondencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return "Remitente: " + remitente + " - Destinatario: " + destinantario + " - Fecha: " + new SimpleDateFormat("dd/MM/YYYY").format(fechaRegistro) + "";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error To String Correspondencia");
        }
        return"";
    }
    
}
