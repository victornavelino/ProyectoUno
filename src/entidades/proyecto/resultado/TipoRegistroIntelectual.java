/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

/**
 *
 * @author hugo
 */
public enum TipoRegistroIntelectual {

    NO_DECLARADO("No Declarado"),
    LIBROS("Libros"),
    REVISTAS("Revistas"),
    SOFTWARE("Software"),
    MUSICA("Música"),
    LITERATURA_INEDITA("Literatura Inédita"),
    OTROS("Otros");
    private String name;

    private TipoRegistroIntelectual(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
