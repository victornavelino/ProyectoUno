/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

/**
 *
 * @author franco
 */
public enum TipoContrato {

NO_DECLARADO ("No Declarado"),
INVESTIGACION_DESARROLLO("De Investigación y Desarrollo"),
TRANSFERENCIA_TECNOLOGICA("De Transferencia Tecnológica"),
ASISTENCIA_TECNICA ("De Asistencia Técnica"),
TRANSFERENCIA_CONOCIMIENTOS ("De Transferencia de Conocimientos")
;

private String name ;

private TipoContrato(String name){
this.name = name;
}

public String getName() {
return name;
}

@Override
public String toString(){
//para llenar combo box
return name;
}


} 