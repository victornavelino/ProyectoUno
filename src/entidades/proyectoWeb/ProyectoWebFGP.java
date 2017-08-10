/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyectoWeb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "proyectoweb_fgp")
public class ProyectoWebFGP implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //III. Formulación General del Proyecto
    @Lob
    private String antecedentes;

    @Lob
    private String objetivoGral;

    @Lob
    private String objetivoEspecificoHipotesis;

    @Lob
    private String relevanciaProblema;

    @Lob
    private String resultadosPreliminares;

    @Lob
    private String metodologia;

    @Lob
    private String cronogramaTrabajo;

    @Lob
    private String justificacionImpactoProyecto;

    //V. Programa de capacitación y formación de recursos humanos.
    @Lob
    private String capacitacionFormacionRH;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getObjetivoGral() {
        return objetivoGral;
    }

    public void setObjetivoGral(String objetivoGral) {
        this.objetivoGral = objetivoGral;
    }

    public String getObjetivoEspecificoHipotesis() {
        return objetivoEspecificoHipotesis;
    }

    public void setObjetivoEspecificoHipotesis(String objetivoEspecificoHipotesis) {
        this.objetivoEspecificoHipotesis = objetivoEspecificoHipotesis;
    }

    public String getRelevanciaProblema() {
        return relevanciaProblema;
    }

    public void setRelevanciaProblema(String relevanciaProblema) {
        this.relevanciaProblema = relevanciaProblema;
    }

    public String getResultadosPreliminares() {
        return resultadosPreliminares;
    }

    public void setResultadosPreliminares(String resultadosPreliminares) {
        this.resultadosPreliminares = resultadosPreliminares;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getCronogramaTrabajo() {
        return cronogramaTrabajo;
    }

    public void setCronogramaTrabajo(String cronogramaTrabajo) {
        this.cronogramaTrabajo = cronogramaTrabajo;
    }

    public String getJustificacionImpactoProyecto() {
        return justificacionImpactoProyecto;
    }

    public void setJustificacionImpactoProyecto(String justificacionImpactoProyecto) {
        this.justificacionImpactoProyecto = justificacionImpactoProyecto;
    }

    public String getCapacitacionFormacionRH() {
        return capacitacionFormacionRH;
    }

    public void setCapacitacionFormacionRH(String capacitacionFormacionRH) {
        this.capacitacionFormacionRH = capacitacionFormacionRH;
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
        if (!(object instanceof ProyectoWebFGP)) {
            return false;
        }
        ProyectoWebFGP other = (ProyectoWebFGP) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyectoWeb.ProyectoWebCadena[ id=" + id + " ]";
    }
    
}
