/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

/**
 *
 * @author franco
 */
public enum TipoEdicion {

NO_DECLARADO ("No declarado"),
NACIONAL ("Nacional"),
EXTRANJERA ("Extranjera");

private String name ;

private TipoEdicion(String name){
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