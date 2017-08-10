/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;


import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ruben
 */
public class Maestro {
    
    private String areatematica;
    private String categoria;
    private int duracion;
    private String institucionbeneficiaria;
    
       
    public String getAreatematica() {
        return areatematica;
    }

    public void setAreatematica(String areatematica) {
        this.areatematica = areatematica;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getInstitucionbeneficiaria() {
        return institucionbeneficiaria;
    }

    public void setInstitucionbeneficiaria(String institucionbeneficiaria) {
        this.institucionbeneficiaria = institucionbeneficiaria;
    }

    
}
