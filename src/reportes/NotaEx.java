/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.util.Date;

/**
 *
 * @author ruben
 */
public class NotaEx {
    
    private Date fecha;
    private String nronota;
    private String motivo;
    private Date fechaentrada;
    private Date fechaaprovacion;
    private Date fechadesembolso;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaaprovacion() {
        return fechaaprovacion;
    }

    public void setFechaaprovacion(Date fechaaprovacion) {
        this.fechaaprovacion = fechaaprovacion;
    }

    public Date getFechadesembolso() {
        return fechadesembolso;
    }

    public void setFechadesembolso(Date fechadesembolso) {
        this.fechadesembolso = fechadesembolso;
    }

    public Date getFechaentrada() {
        return fechaentrada;
    }

    public void setFechaentrada(Date fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNronota() {
        return nronota;
    }

    public void setNronota(String nronota) {
        this.nronota = nronota;
    }
    
    
}
