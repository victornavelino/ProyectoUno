/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import entidades.proyecto.vinculacion.financiacion.pfip.Etapa;

/**
 *
 * @author Panchi
 */
public class Desembolso {
    private Long id;
    private String etapaDeCronograma;

    private Etapa etapaAEjecutar;
    private Float costoEjecucion;
    private Float saldo;
    private Float control;
 
    private Float desembolso;

    public Float getControl() {
        return control;
    }

    public void setControl(Float control) {
        this.control = control;
    }

    public Float getCostoEjecucion() {
        return costoEjecucion;
    }

    public void setCostoEjecucion(Float costoEjecucion) {
        this.costoEjecucion = costoEjecucion;
    }

    public Float getDesembolso() {
        return desembolso;
    }

    public void setDesembolso(Float desembolso) {
        this.desembolso = desembolso;
    }

    public Etapa getEtapaAEjecutar() {
        return etapaAEjecutar;
    }

    public void setEtapaAEjecutar(Etapa etapaAEjecutar) {
        this.etapaAEjecutar = etapaAEjecutar;
    }

    public String getEtapaDeCronograma() {
        return etapaDeCronograma;
    }

    public void setEtapaDeCronograma(String etapaDeCronograma) {
        this.etapaDeCronograma = etapaDeCronograma;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    
}
