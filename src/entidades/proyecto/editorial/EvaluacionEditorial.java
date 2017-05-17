/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.editorial;

import entidades.proyecto.EvaluacionIndividual;
import entidades.proyecto.Proyecto;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author francisco
 */
@Entity
@Table(name ="editorial_evaluacioneditorial")
public class EvaluacionEditorial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @OneToMany (cascade = CascadeType.ALL)
    private List<EvaluacionIndividual> evaluacionIndividual;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<EvaluacionIndividual> getEvaluacionIndividual() {
        return evaluacionIndividual;
    }

    public void setEvaluacionIndividual(List<EvaluacionIndividual> evaluacionIndividual) {
        this.evaluacionIndividual = evaluacionIndividual;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionEditorial)) {
            return false;
        }
        EvaluacionEditorial other = (EvaluacionEditorial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
               
        List<EvaluacionIndividual> evaluaciones = this.getEvaluacionIndividual();
        String msg = new String();
        for(EvaluacionIndividual e : evaluaciones){
            msg = msg + e.getNota() + " - ";
        }
        msg = msg + day + "/" + month + "/" + year;
        return msg;
    }
    
}
