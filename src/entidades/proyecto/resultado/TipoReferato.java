/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

/**
 *
 * @author franco
 */
public enum TipoReferato {
    
NO_DECLARADO ("No declarado"),
SI ("Si"),
NO ("No");

private String name ;

private TipoReferato(String name){
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
