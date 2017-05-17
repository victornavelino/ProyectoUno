/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.categorizacion;

import entidades.proyecto.resultado.*;

/**
 *
 * @author franco
 */
public enum TipoResultadoInforme {

    NO_SATISFACTORIO("No satisfactorio"),
    SATISFACTORIO("Satisfactorio");

    private String name;

    private TipoResultadoInforme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
//para llenar combo box
        return name;
    }

}
