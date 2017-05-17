/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.persona.investigador.curso;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "asignatura")
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoAsignatura;
    private String codigoAsignaturaNacion;
    @JoinColumn(nullable=false)
    @OneToOne
    private CarreraAsignatura carreraAsignatura;
    @OneToOne
    @JoinColumn(nullable=false)
    private FacultadAsignatura facultadAsignatura;
    @Column(nullable=false)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarreraAsignatura getCarreraAsignatura() {
        return carreraAsignatura;
    }

    public void setCarreraAsignatura(CarreraAsignatura carreraAsignatura) {
        this.carreraAsignatura = carreraAsignatura;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getCodigoAsignaturaNacion() {
        return codigoAsignaturaNacion;
    }

    public void setCodigoAsignaturaNacion(String codigoAsignaturaNacion) {
        this.codigoAsignaturaNacion = codigoAsignaturaNacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FacultadAsignatura getFacultadAsignatura() {
        return facultadAsignatura;
    }

    public void setFacultadAsignatura(FacultadAsignatura facultadAsignatura) {
        this.facultadAsignatura = facultadAsignatura;
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
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoAsignatura + " - " + descripcion + " - " + carreraAsignatura.toString();
    }
}
