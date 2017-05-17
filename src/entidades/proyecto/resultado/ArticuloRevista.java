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
 * @author franco
 */
@Entity
@Table(name = "resultado_articulo_revista")
public class ArticuloRevista extends Publicacion implements Serializable {

    String nombreRevista;
    String volumen;
    int paginaInicial;
    int paginaFinal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEnviado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAceptado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPublicado;
    @Enumerated(EnumType.STRING)
    private TipoReferato referato;
    @Enumerated(EnumType.STRING)
    private TipoEdicion ambitoPublicacion;
    private String ISBN;
    private String anioEdicion;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public TipoEdicion getAmbitoPublicacion() {
        return ambitoPublicacion;
    }

    public void setAmbitoPublicacion(TipoEdicion ambitoPublicacion) {
        this.ambitoPublicacion = ambitoPublicacion;
    }

    public String getAnioEdicion() {
        return anioEdicion;
    }

    public void setAnioEdicion(String anioEdicion) {
        this.anioEdicion = anioEdicion;
    }

    public Date getFechaAceptado() {
        return fechaAceptado;
    }

    public void setFechaAceptado(Date fechaAceptado) {
        this.fechaAceptado = fechaAceptado;
    }

    public Date getFechaEnviado() {
        return fechaEnviado;
    }

    public void setFechaEnviado(Date fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }

    public Date getFechaPublicado() {
        return fechaPublicado;
    }

    public void setFechaPublicado(Date fechaPublicado) {
        this.fechaPublicado = fechaPublicado;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public int getPaginaFinal() {
        return paginaFinal;
    }

    public void setPaginaFinal(int paginaFinal) {
        this.paginaFinal = paginaFinal;
    }

    public int getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(int paginaInicial) {
        this.paginaInicial = paginaInicial;
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
        return "entidades.proyecto.resultado.ArticuloRevista[ id= ]";
    }
}
