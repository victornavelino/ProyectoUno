/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.persona.Evaluador;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Proyecto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "resolucion")
public class Resolucion implements Serializable {

    @ManyToMany(mappedBy = "resoluciones")
    private List<Proyecto> proyectos;
    @ManyToMany(mappedBy = "resoluciones")
    private List<Evaluador> evaluadores;
    @ManyToMany(mappedBy = "resoluciones")
    private List<Investigador> investigadores;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long numero;
    private int ano;
    private String descripcion;
    @OneToOne(fetch = FetchType.LAZY)
    private Documento documento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Evaluador> getEvaluadores() {
        return evaluadores;
    }

    public void setEvaluadores(List<Evaluador> evaluadors) {
        this.evaluadores = evaluadors;
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(List<Investigador> investigadors) {
        this.investigadores = investigadors;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof Resolucion)) {
            return false;
        }
        Resolucion other = (Resolucion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (numero != 0) ? String.valueOf(numero) + "/" + String.valueOf(ano) + " - " + descripcion : "" + String.valueOf(ano) + " - " + descripcion;
    }
}
