/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.economico;

import entidades.Resolucion;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name = "economico_resolucion_rectoral")
public class ResolucionRectoral extends ResolucionEconomico implements Serializable {   

    @Override
    public String toString() {
        return String.valueOf(super.getNumero())+"/"+super.getAnio();
    }
    
}
