/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.economico;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name = "economico_bien_consumo")
public class BienConsumo implements Serializable, Comparable<BienConsumo> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(scale = 2, precision = 12)
    private BigDecimal valor;
    @Column(scale = 2, precision = 12)
    private BigDecimal valorA;
    private String anio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorA() {
        return valorA;
    }

    public void setValorA(BigDecimal valorA) {
        this.valorA = valorA;
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
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        BienConsumo other = (BienConsumo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int compareTo(BienConsumo gv) {
        int esteAnio = Integer.valueOf(this.anio);
        int elOtroAnio = Integer.valueOf(gv.getAnio());
        if (esteAnio < elOtroAnio) {
            return -1;
        } else if (esteAnio > elOtroAnio) {
            return 1;
        } else {
            return 0;
        }
    }
}
