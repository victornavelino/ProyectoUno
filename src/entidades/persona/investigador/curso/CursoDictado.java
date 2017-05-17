/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.persona.investigador.curso;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "cursodictado")
public class CursoDictado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Asignatura asignatura;
    private String duracionAnual;
    @OneToOne
    private TipoDuracionAsignatura tipoDuracionAsignatura;
    @OneToOne
    private TipoAsignatura tipoAsignatura;
    private int horasSemanalesDictadoClases;
    private int semanasDuracion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public TipoDuracionAsignatura getTipoDuracionAsignatura() {
        return tipoDuracionAsignatura;
    }

    public void setTipoDuracionAsignatura(TipoDuracionAsignatura tipoDuracionAsignatura) {
        this.tipoDuracionAsignatura = tipoDuracionAsignatura;
    }

    public String getDuracionAnual() {
        return duracionAnual;
    }

    public void setDuracionAnual(String duracionAnual) {
        this.duracionAnual = duracionAnual;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public int getHorasSemanalesDictadoClases() {
        return horasSemanalesDictadoClases;
    }

    public void setHorasSemanalesDictadoClases(int horasSemanalesDictadoClases) {
        this.horasSemanalesDictadoClases = horasSemanalesDictadoClases;
    }

    public int getSemanasDuracion() {
        return semanasDuracion;
    }

    public void setSemanasDuracion(int semanasDuracion) {
        this.semanasDuracion = semanasDuracion;
    }

    public TipoAsignatura getTipoAsignatura() {
        return tipoAsignatura;
    }

    public void setTipoAsignatura(TipoAsignatura tipoAsignatura) {
        this.tipoAsignatura = tipoAsignatura;
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
        if (!(object instanceof CursoDictado)) {
            return false;
        }
        CursoDictado other = (CursoDictado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return asignatura.toString() + ": " + horasSemanalesDictadoClases + " hs semanales";
    }
}
