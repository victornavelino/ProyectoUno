/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.evaluacion;

/**
 *
 * @author franco
 */
public enum CalificacionGlobal {

    APROBADO("Aprobado"),
    APROBADO_CON_MODIFICACIONES("Aprobado con Modificaciones"),
    DESAPROBADO("Desaprobado"),;
    private String name;

    private CalificacionGlobal(String name) {
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
