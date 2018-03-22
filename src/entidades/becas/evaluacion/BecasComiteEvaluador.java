/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas.evaluacion;

import entidades.persona.Evaluador;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "becas_comite_evaluador")
public class BecasComiteEvaluador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Evaluador evaluador;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private BecasEvaluacionWeb becasEvaluacionWeb;
    
    //si es true es el que puede entrar a la pagina a evaluar la beca
    private Boolean responsable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evaluador getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Evaluador evaluador) {
        this.evaluador = evaluador;
    }

    public BecasEvaluacionWeb getBecasEvaluacionWeb() {
        return becasEvaluacionWeb;
    }

    public void setBecasEvaluacionWeb(BecasEvaluacionWeb becasEvaluacionWeb) {
        this.becasEvaluacionWeb = becasEvaluacionWeb;
    }

    public Boolean getResponsable() {
        return responsable;
    }

    public void setResponsable(Boolean responsable) {
        this.responsable = responsable;
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
        if (!(object instanceof BecasComiteEvaluador)) {
            return false;
        }
        BecasComiteEvaluador other = (BecasComiteEvaluador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (this.responsable ? "R -       " : "           ")+ this.evaluador.toString();
    }  
}
