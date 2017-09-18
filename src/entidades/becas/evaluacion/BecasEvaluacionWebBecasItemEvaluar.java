/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas.evaluacion;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "becas_evaluacionweb_becas_item_evaluar")
public class BecasEvaluacionWebBecasItemEvaluar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private BecasEvaluacionWeb becasEvaluacionWeb;
    
    @ManyToOne
    private BecasItemEvaluar becasItemEvaluar;
    
    private BecasItemCalificacion becasItemCalificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BecasEvaluacionWeb getBecasEvaluacionWeb() {
        return becasEvaluacionWeb;
    }

    public void setBecasEvaluacionWeb(BecasEvaluacionWeb becasEvaluacionWeb) {
        this.becasEvaluacionWeb = becasEvaluacionWeb;
    }

    public BecasItemEvaluar getBecasItemEvaluar() {
        return becasItemEvaluar;
    }

    public void setBecasItemEvaluar(BecasItemEvaluar becasItemEvaluar) {
        this.becasItemEvaluar = becasItemEvaluar;
    }

    public BecasItemCalificacion getBecasItemCalificacion() {
        return becasItemCalificacion;
    }

    public void setBecasItemCalificacion(BecasItemCalificacion becasItemCalificacion) {
        this.becasItemCalificacion = becasItemCalificacion;
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
        if (!(object instanceof BecasEvaluacionWebBecasItemEvaluar)) {
            return false;
        }
        BecasEvaluacionWebBecasItemEvaluar other = (BecasEvaluacionWebBecasItemEvaluar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.becasItemEvaluar.toString();
    }
    
}
