/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.becas.evaluacion;

import entidades.Documento;
import entidades.becas.Becas;
import entidades.becas.PostulacionBeca;
import entidades.persona.Evaluador;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author franco
 */
@Entity
@Table(name = "becas_evaluacion_web")
@NamedQueries({
    @NamedQuery(name = "BecasEvaluacionWeb.findById", query = "SELECT c FROM BecasEvaluacionWeb c WHERE c.id = :idBecasEvaluacionWeb"),
    
    @NamedQuery(name="BecasEvaluacionWeb.findByPostulacionBeca", 
            query="SELECT pr FROM BecasEvaluacionWeb pr WHERE pr.postulacionBeca.id = :idPostulacionBeca"),
    
    @NamedQuery(name="BecasEvaluacionWeb.findByPostulacionBecaID", 
            query="SELECT pr FROM BecasEvaluacionWeb pr WHERE pr.postulacionBeca.id = :idPostulacionBeca "
            + "AND pr.id <> :id")
})
public class BecasEvaluacionWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private PostulacionBeca postulacionBeca;

    @OneToMany(mappedBy = "becasEvaluacionWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BecasComiteEvaluador> becasComitesEvaluadores;
    
    @OneToMany(mappedBy = "becasEvaluacionWeb", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BecasEvaluacionWebBecasItemEvaluar> becasEvaluacionWebBecasItemEvaluar;

    
    @Enumerated(EnumType.STRING)
    private BecasCalificacionGlobal becasCalificacionGlobal;
    
    private String sugerencias;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BecasEvaluacionWebBecasItemEvaluar> getBecasEvaluacionWebBecasItemEvaluar() {
        return becasEvaluacionWebBecasItemEvaluar;
    }

    public void setBecasEvaluacionWebBecasItemEvaluar(List<BecasEvaluacionWebBecasItemEvaluar> becasEvaluacionWebBecasItemEvaluar) {
        this.becasEvaluacionWebBecasItemEvaluar = becasEvaluacionWebBecasItemEvaluar;
    }
    
    public List<BecasComiteEvaluador> getBecasComitesEvaluadores() {
        return becasComitesEvaluadores;
    }

    public void setBecasComitesEvaluadores(List<BecasComiteEvaluador> becasComitesEvaluadores) {
        this.becasComitesEvaluadores = becasComitesEvaluadores;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    
    

    public PostulacionBeca getPostulacionBeca() {
        return postulacionBeca;
    }

    public void setPostulacionBeca(PostulacionBeca postulacionBeca) {
        this.postulacionBeca = postulacionBeca;
    }


    public BecasCalificacionGlobal getBecasCalificacionGlobal() {
        return becasCalificacionGlobal;
    }

    public void setBecasCalificacionGlobal(BecasCalificacionGlobal becasCalificacionGlobal) {
        this.becasCalificacionGlobal = becasCalificacionGlobal;
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
        if (!(object instanceof BecasEvaluacionWeb)) {
            return false;
        }
        BecasEvaluacionWeb other = (BecasEvaluacionWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.postulacionBeca.toString();
    }
}
