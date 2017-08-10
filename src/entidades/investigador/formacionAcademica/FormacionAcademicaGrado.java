/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.investigador.formacionAcademica;

import entidades.Universidad;
import entidades.persona.investigador.Investigador;
import entidades.titulo.TituloGrado;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "formacionacademicagrado")
public class FormacionAcademicaGrado extends FormacionAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    @OneToOne
    private TituloGrado tituloGrado;
    @OneToOne
    private Universidad universidad;

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public TituloGrado getTituloGrado() {
        return tituloGrado;
    }

    public void setTituloGrado(TituloGrado tituloGrado) {
        this.tituloGrado = tituloGrado;
    }

    @Override
    public String toString() {
        String salida = "";
        try {
            salida += tituloGrado.getDescripcion();
        } catch (Exception e) {
        }
        try {
            if (super.getAnoIngreso() == 0 && super.getAnoEgreso() == 0) {
                salida = tituloGrado.getDescripcion();
            } else {
                if (super.getAnoEgreso() != 0) {
                    salida += " - Egresado en " + super.getAnoEgreso();
                } else {
                    salida += " - Actualmente cursando";
                }
            }
        } catch (Exception e) {
        }
        return salida;
    }
}
