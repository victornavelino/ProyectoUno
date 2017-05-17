/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pid;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.OneToMany;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="vinculacion_patente")
@DiscriminatorValue("Patente")
public class Patente extends Produccion implements Serializable {
    
    private String estado;
    private String numeroRegsitro;
    private String año;
    @OneToMany
    private List<Titular> titulares;
    private String denominacion;
    @Lob
    private String Descripcion;
    @OneToMany
    private List<Cita> citas;
    @OneToMany
    private List<DerechoObtentor> derechos;
    @OneToMany
    private List<Desarrollo> desarrollos;
    @OneToMany
    private List<Producto> productosDesarrollados;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<DerechoObtentor> getDerechos() {
        return derechos;
    }

    public void setDerechos(List<DerechoObtentor> derechos) {
        this.derechos = derechos;
    }

    public List<Desarrollo> getDesarrollos() {
        return desarrollos;
    }

    public void setDesarrollos(List<Desarrollo> desarrollos) {
        this.desarrollos = desarrollos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumeroRegsitro() {
        return numeroRegsitro;
    }

    public void setNumeroRegsitro(String numeroRegsitro) {
        this.numeroRegsitro = numeroRegsitro;
    }

    public List<Producto> getProductosDesarrollados() {
        return productosDesarrollados;
    }

    public void setProductosDesarrollados(List<Producto> productosDesarrollados) {
        this.productosDesarrollados = productosDesarrollados;
    }

    public List<Titular> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Titular> titulares) {
        this.titulares = titulares;
    }
       

    
  
    @Override
    public String toString() {
        return denominacion;
    }
    
}
