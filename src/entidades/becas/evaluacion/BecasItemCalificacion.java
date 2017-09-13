/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas.evaluacion;

/**
 *
 * @author franco
 */
public enum BecasItemCalificacion{

    SI("Si"),
    NO("No");
    private String name;

    private BecasItemCalificacion(String name) {
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
