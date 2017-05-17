/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "resultado_capitulo_libro")
public class CapituloLibro extends Libro implements Serializable {

    @ManyToOne
    private Libro libro;
    private String nombreLibro;

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }
    

    @Override
    public String toString() {
        return nombreLibro;
    }
}
