/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyectoWeb;

import entidades.economico.BienConsumo;
import entidades.economico.BienNoPersonal;
import entidades.economico.BienUso;
import entidades.economico.GastoViaje;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "proyectoweb_presupuesto")
public class PresupuestoWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BienConsumo> bienConsumo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BienNoPersonal> bienNoPersonal;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BienUso> bienUso;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GastoViaje> gastosViaje;
    @OneToOne
    private ProyectoWeb proyectoWeb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BienConsumo> getBienConsumo() {
        return bienConsumo;
    }

    public void setBienConsumo(List<BienConsumo> bienConsumo) {
        this.bienConsumo = bienConsumo;
    }

    public List<BienNoPersonal> getBienNoPersonal() {
        return bienNoPersonal;
    }

    public void setBienNoPersonal(List<BienNoPersonal> bienNoPersonal) {
        this.bienNoPersonal = bienNoPersonal;
    }

    public List<BienUso> getBienUso() {
        return bienUso;
    }

    public void setBienUso(List<BienUso> bienUso) {
        this.bienUso = bienUso;
    }

    public List<GastoViaje> getGastosViaje() {
        return gastosViaje;
    }

    public void setGastosViaje(List<GastoViaje> gastosViaje) {
        this.gastosViaje = gastosViaje;
    }

    public ProyectoWeb getProyectoWeb() {
        return proyectoWeb;
    }

    public void setProyectoWeb(ProyectoWeb proyectoWeb) {
        this.proyectoWeb = proyectoWeb;
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
        if (!(object instanceof PresupuestoWeb)) {
            return false;
        }
        PresupuestoWeb other = (PresupuestoWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String salida = "";
        try {
            Collections.sort(bienUso);
        } catch (Exception ex) {
        }
        try {
            Collections.sort(bienConsumo);
        } catch (Exception ex) {
        }
        try {
            Collections.sort(bienNoPersonal);
        } catch (Exception ex) {
        }
        try {
            Collections.sort(gastosViaje);
        } catch (Exception ex) {
        }
        try {
            Calendar calIni1 = Calendar.getInstance();
            Calendar calFin1 = Calendar.getInstance();
            calIni1.setTime(proyectoWeb.getFechaInicio());
            calFin1.setTime(proyectoWeb.getFechaFinalizacion());
            calFin1.add(Calendar.DATE, 1);
            Long dif1 = calFin1.getTimeInMillis() - calIni1.getTimeInMillis();
            long difDia1 = (calFin1.get(Calendar.DATE) - calIni1.get(Calendar.DATE))
                    + (calFin1.get(Calendar.MONTH) - calIni1.get(Calendar.MONTH))
                    + (calFin1.get(Calendar.YEAR) - calIni1.get(Calendar.YEAR));
            salida += "<table><tr>";
            salida += "<th>Presupuesto</th>";

            if (difDia1 == 2) {
                salida += "<th>Año 1</th>";
                salida += "<th>Año 2</th>";
            } else {
                salida += "<th>Año 1</th>";
                salida += "<th>Año 2</th>";
                salida += "<th>Año 3</th>";
                salida += "<th>Año 4</th>";
            }
            salida += "<th>Total</th>";
            salida += " </tr><tr>";
            salida += "<td>Bienes de Capital</td>";
            BigDecimal totalUso = BigDecimal.ZERO;
            BigDecimal totalConsumo = BigDecimal.ZERO;
            BigDecimal totalGastosViaje = BigDecimal.ZERO;
            BigDecimal totalNoPersonal = BigDecimal.ZERO;
            BigDecimal total1 = BigDecimal.ZERO;
            BigDecimal total2 = BigDecimal.ZERO;
            BigDecimal total3 = BigDecimal.ZERO;
            BigDecimal total4 = BigDecimal.ZERO;
            BigDecimal total = BigDecimal.ZERO;
            int i = 0;

            for (BienUso bc : bienUso) {
                salida += "<td>$ " + bc.getValor() + "</td>";
                totalUso = totalUso.add(bc.getValor());
                switch (i) {
                    case 0:
                        total1 = total1.add(bc.getValor());
                        break;
                    case 1:
                        total2 = total2.add(bc.getValor());
                        break;
                    case 2:
                        total3 = total3.add(bc.getValor());
                        break;
                    case 3:
                        total4 = total4.add(bc.getValor());
                        break;
                }
                i++;
            }
            salida += "<td>$ " + totalUso + "</td>";
            salida += " </tr><tr>";
            salida += "<td>Bienes de Consumo</td>";
            i = 0;
            for (BienConsumo bc : bienConsumo) {
                salida += "<td>$ " + bc.getValor() + "</td>";
                totalConsumo = totalConsumo.add(bc.getValor());
                switch (i) {
                    case 0:
                        total1 = total1.add(bc.getValor());
                        break;
                    case 1:
                        total2 = total2.add(bc.getValor());
                        break;
                    case 2:
                        total3 = total3.add(bc.getValor());
                        break;
                    case 3:
                        total4 = total4.add(bc.getValor());
                        break;
                }
                i++;
            }
            salida += "<td>$ " + totalConsumo + "</td>";
            salida += " </tr><tr>";
            salida += "<td>Gastos de Viaje</td>";
            i = 0;
            for (GastoViaje bc : gastosViaje) {
                salida += "<td>$ " + bc.getValor() + "</td>";
                totalGastosViaje = totalGastosViaje.add(bc.getValor());
                switch (i) {
                    case 0:
                        total1 = total1.add(bc.getValor());
                        break;
                    case 1:
                        total2 = total2.add(bc.getValor());
                        break;
                    case 2:
                        total3 = total3.add(bc.getValor());
                        break;
                    case 3:
                        total4 = total4.add(bc.getValor());
                        break;
                }
                i++;
            }
            salida += "<td>$ " + totalGastosViaje + "</td>";
            salida += " </tr><tr>";
            salida += "<td>Servicios No Personales</td>";
            i = 0;
            for (BienNoPersonal bc : bienNoPersonal) {

                salida += "<td>$ " + bc.getValor() + "</td>";
                totalNoPersonal = totalNoPersonal.add(bc.getValor());
                switch (i) {
                    case 0:
                        total1 = total1.add(bc.getValor());
                        break;
                    case 1:
                        total2 = total2.add(bc.getValor());
                        break;
                    case 2:
                        total3 = total3.add(bc.getValor());
                        break;
                    case 3:
                        total4 = total4.add(bc.getValor());
                        break;
                }
                i++;
            }
            salida += "<td>$ " + totalNoPersonal + "</td>";

            salida += " </tr><tr>";
            salida += "<td>Total</td>";
            if (difDia1 == 2) {
                salida += "<td>$ " + total1 + "</td>";
                salida += "<td>$ " + total2 + "</td>";
            } else {
                salida += "<td>$ " + total1 + "</td>";
                salida += "<td>$ " + total2 + "</td>";
                salida += "<td>$ " + total3 + "</td>";
                salida += "<td>$ " + total4 + "</td>";
            }
            salida += "<td>$ " + total1.add(total2).add(total3).add(total4) + "</td>";
            salida += "</tr></table>";


        } catch (Exception ex) {
        }
        return salida;
    }
}
