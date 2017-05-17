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
public enum TipoInforme {

    AVANCE("Avance"),
    FINAL("Final");

    private String name;

    private TipoInforme(String name) {
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
