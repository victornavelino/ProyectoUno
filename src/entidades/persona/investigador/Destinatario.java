/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.persona.investigador;

/**
 *
 * @author hugo
 */
public enum Destinatario {
    
    INVESTIGADOR ("Investigador"),
    AREASECYT ("AreaSecyt");
    
    private String name;

    public String getName() {
        return name;
    }
    
    private Destinatario(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
