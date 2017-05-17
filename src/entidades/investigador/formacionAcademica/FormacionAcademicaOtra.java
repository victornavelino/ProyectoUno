/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.investigador.formacionAcademica;

import entidades.Institucion;
import entidades.titulo.TituloOtro;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "formacionacademicaotra")
public class FormacionAcademicaOtra extends FormacionAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    @OneToOne
    private TituloOtro tituloOtro;
    @OneToOne
    private Institucion institucion;

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public TituloOtro getTituloOtro() {
        return tituloOtro;
    }

    public void setTituloOtro(TituloOtro tituloOtro) {
        this.tituloOtro = tituloOtro;
    }

    @Override
    public String toString() {
        String salida = "";
        try {
            salida += tituloOtro.getDescripcion();
        } catch (Exception e) {
        }
        try {
            if (super.getAnoIngreso() == 0 & super.getAnoEgreso() == 0) {
                salida = tituloOtro.getDescripcion();
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
