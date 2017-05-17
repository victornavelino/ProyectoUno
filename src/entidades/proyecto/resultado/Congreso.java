/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "resultado_congreso")
public class Congreso extends Publicacion implements Serializable {

    String nombreEvento;
    @Enumerated(EnumType.STRING)
    TipoEdicion caracter;
    String lugar;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date fecha;
    @Enumerated(EnumType.STRING)
    TipoReferato publicadoEnActas;
    @Enumerated(EnumType.STRING)
    TipoReferato referato;
    String volumen;
    String pagina;
    String anio;

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public TipoEdicion getCaracter() {
        return caracter;
    }

    public void setCaracter(TipoEdicion caracter) {
        this.caracter = caracter;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public TipoReferato getPublicadoEnActas() {
        return publicadoEnActas;
    }

    public void setPublicadoEnActas(TipoReferato publicadoEnActas) {
        this.publicadoEnActas = publicadoEnActas;
    }

    public TipoReferato getReferato() {
        return referato;
    }

    public void setReferato(TipoReferato referato) {
        this.referato = referato;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    @Override
    public String toString() {
        String strFechaContrato = "";
        strFechaContrato = new SimpleDateFormat("dd/MM/YYYY").format(fecha);
        return strFechaContrato + "\n " + super.getTitulo() + "\n " + nombreEvento 
                + "\n " + lugar;
    }
}
