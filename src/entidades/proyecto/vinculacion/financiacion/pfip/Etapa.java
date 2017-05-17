/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import entidades.Documento;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name = "etapa")
public class Etapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nroEtapa;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    private Integer mesFin;
    private Float bienCapitalFM;
    private Float recHumFM;
    private Integer mesInicio;
    private Float consultoriasFM;
    private Float otrosRecursosFM;
    private Float materialesFM;
    private Float bienCapitalFC;
    private Float recHumFC;
    private List<Documento> documentos;


    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    private Float consultoriasFC;
    private Float otrosRecursosFC;
    private Float materialesFC;
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Etapa> etapa;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getNroEtapa() {
        return nroEtapa;
    }

    public void setNroEtapa(Integer nroEtapa) {
        this.nroEtapa = nroEtapa;
    }

    public List<Etapa> getEtapa() {
        return etapa;
    }

    public void setEtapa(List<Etapa> etapa) {
        this.etapa = etapa;
    }

    public Float getRecHumFC() {
        return recHumFC;
    }

    public void setRecHumFC(Float recHumFC) {
        this.recHumFC = recHumFC;
    }

    public Float getRecHumFM() {
        return recHumFM;
    }

    public void setRecHumFM(Float recHumFM) {
        this.recHumFM = recHumFM;
    }

    public Float getBienCapitalFC() {
        return bienCapitalFC;
    }

    public void setBienCapitalFC(Float bienCapitalFC) {
        this.bienCapitalFC = bienCapitalFC;
    }

    public Float getBienCapitalFM() {
        return bienCapitalFM;
    }

    public void setBienCapitalFM(Float bienCapitalFM) {
        this.bienCapitalFM = bienCapitalFM;
    }

    public Float getConsultoriasFC() {
        return consultoriasFC;
    }

    public void setConsultoriasFC(Float consultoriasFC) {
        this.consultoriasFC = consultoriasFC;
    }

    public Float getConsultoriasFM() {
        return consultoriasFM;
    }

    public void setConsultoriasFM(Float consultoriasFM) {
        this.consultoriasFM = consultoriasFM;
    }

    public Float getMaterialesFC() {
        return materialesFC;
    }

    public void setMaterialesFC(Float materialesFC) {
        this.materialesFC = materialesFC;
    }

    public Float getMaterialesFM() {
        return materialesFM;
    }

    public void setMaterialesFM(Float materialesFM) {
        this.materialesFM = materialesFM;
    }

    public Float getOtrosRecursosFC() {
        return otrosRecursosFC;
    }

    public void setOtrosRecursosFC(Float otrosRecursosFC) {
        this.otrosRecursosFC = otrosRecursosFC;
    }

    public Float getOtrosRecursosFM() {
        return otrosRecursosFM;
    }

    public void setOtrosRecursosFM(Float otrosRecursosFM) {
        this.otrosRecursosFM = otrosRecursosFM;
    }

    public Integer getMesFin() {
        return mesFin;
    }

    public void setMesFin(Integer mesFin) {
        this.mesFin = mesFin;
    }

    public Integer getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(Integer mesInicio) {
        this.mesInicio = mesInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Etapa)) {
            return false;
        }
        Etapa other = (Etapa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getInicio() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getFechaInicio());

        if (cal.get(Calendar.MONTH) == 11) {
            return "12" + "/" + cal.get(Calendar.YEAR);

        }
        cal.add(Calendar.MONTH, 1);
        return cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
    }

    public String getFin() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getFechaFin());
        if (cal.get(Calendar.MONTH) == 11) {
            return "12" + "/" + cal.get(Calendar.YEAR);

        }
        cal.add(Calendar.MONTH, 1);
        return cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return "Nro: " + this.getNroEtapa() + " Inicio: " + this.getInicio() + " - Fin: " + this.getFin();
    }
}
