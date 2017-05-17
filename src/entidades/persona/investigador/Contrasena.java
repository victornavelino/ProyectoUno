/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.persona.investigador;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author AFerSor
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Contrasena.findByInvestigador", query="SELECT c FROM Contrasena c WHERE c.investigador.persona.cuil = :cuil"),
    @NamedQuery(name="Contrasena.findByInvestigadorYcontrasena", query="SELECT c FROM Contrasena c WHERE c.investigador.persona.cuil = :cuil "
        + "AND c.contrasena =:contrasena"),
    @NamedQuery(name="Contrasena.updateContrasena", query="UPDATE Contrasena c SET c.contrasena = :contrasena WHERE c.investigador.persona.cuil = :cuil ")
})
@Table(name="investigador_contrasena")
public class Contrasena implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Investigador investigador;
    
    @Column(name="contrasena")
    private String contrasena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        if (!(object instanceof Contrasena)) {
            return false;
        }
        Contrasena other = (Contrasena) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.persona.investigador.Contrasena[ id=" + id + " ]";
    }
    
}
