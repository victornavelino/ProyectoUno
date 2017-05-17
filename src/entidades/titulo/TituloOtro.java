/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades.titulo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="titulootro")
public class TituloOtro extends Titulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "" + super.getDescripcion();
    }

}
