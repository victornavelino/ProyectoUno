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
public enum SectorPrioritario {
    
    Tic ("Tic"),
    Nanotecnología ("Nanotecnología"),
    Energia ("Energia"),
    Agroindustria ("Agroindustria"),
    Salud_Humana ("Salud Humana"),
    Ninguno ("Ninguno");

    private String name;
    

private SectorPrioritario(String name){
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
