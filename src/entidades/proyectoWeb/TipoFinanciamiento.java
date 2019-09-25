/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyectoWeb;

/**
 *
 * @author diego
 */
public enum TipoFinanciamiento {
    
    SECyTUNCA("SECyT-UNCA"),
    Externo("Externo"),
    Secyt_Y_Externo("SECyT y Externo"),
    No("No");

    private String name;
    

private TipoFinanciamiento(String name){
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
