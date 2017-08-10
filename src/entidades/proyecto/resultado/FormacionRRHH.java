/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

import entidades.proyecto.Proyecto;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author hugo
 */
@Entity
@Table(name = "resultado_formacionrrhh")
public class FormacionRRHH implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int becariosAlumnos;
    private int becariosGraduados;
    private int tesisDirigidas;
    private int tesisAprobadasMaestria;
    private int tesisAprobadasDoctorado;
    private int tesinasGradoAprobadas;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    private Proyecto proyecto;
@ManyToOne
    private ProyectoVinculacion proyectoVinculacion;

    public ProyectoVinculacion getProyectoVinculacion() {
        return proyectoVinculacion;
    }

    public void setProyectoVinculacion(ProyectoVinculacion proyectoVinculacion) {
        this.proyectoVinculacion = proyectoVinculacion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBecariosAlumnos() {
        return becariosAlumnos;
    }

    public void setBecariosAlumnos(int becariosAlumnos) {
        this.becariosAlumnos = becariosAlumnos;
    }

    public int getBecariosGraduados() {
        return becariosGraduados;
    }

    public void setBecariosGraduados(int becariosGraduados) {
        this.becariosGraduados = becariosGraduados;
    }

    public int getTesinasGradoAprobadas() {
        return tesinasGradoAprobadas;
    }

    public void setTesinasGradoAprobadas(int tesinasGradoAprobadas) {
        this.tesinasGradoAprobadas = tesinasGradoAprobadas;
    }

    public int getTesisAprobadasDoctorado() {
        return tesisAprobadasDoctorado;
    }

    public void setTesisAprobadasDoctorado(int tesisAprobadasDoctorado) {
        this.tesisAprobadasDoctorado = tesisAprobadasDoctorado;
    }

    public int getTesisAprobadasMaestria() {
        return tesisAprobadasMaestria;
    }

    public void setTesisAprobadasMaestria(int tesisAprobadasMaestria) {
        this.tesisAprobadasMaestria = tesisAprobadasMaestria;
    }

    public int getTesisDirigidas() {
        return tesisDirigidas;
    }

    public void setTesisDirigidas(int tesisDirigidas) {
        this.tesisDirigidas = tesisDirigidas;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
        if (!(object instanceof FormacionRRHH)) {
            return false;
        }
        FormacionRRHH other = (FormacionRRHH) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Becas Alumno: " + becariosAlumnos + " \n" + "Becas Graduados: "
                + becariosGraduados + " \n" + "Tesis Dirigidas: "
                + tesisDirigidas + " \n" + "Tesinas: " + tesinasGradoAprobadas
                + " \n" + "Tesis Maestria: " + tesisAprobadasMaestria + " \n"
                + "Tesis Doctorado: " + tesisAprobadasDoctorado;
    }
}
