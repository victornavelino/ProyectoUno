/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas.evaluacion;

/**
 *
 * @author franco
 */
public enum BecasCalificacionGlobal {

    APROBADO("Aprobado"),
    DESAPROBADO("Desaprobado");
    private String name;

    private BecasCalificacionGlobal(String name) {
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
