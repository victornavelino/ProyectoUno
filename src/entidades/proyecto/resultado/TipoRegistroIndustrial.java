/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

/**
 *
 * @author hugo
 */
public enum TipoRegistroIndustrial {
    
    
    NO_DECLARADO("No Declarado"),
    PATENTE_INVENCION("Pantente de Invención"),
    CERTIFICADOS_MODELO_UTILIDAD("Certificados de Modelos de Utilidad"),
    MARCA("Marca"),
    MODELO_INDUSTRIAL("Modelo Industrial"),
    DISENIO_INDUSTRIAL("Diseño Industrial"),
    TRANSFERENCIA_TECNOLOGIA("Transferencia de Tecnología");
    private String name;

    private TipoRegistroIndustrial(String name) {
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
