/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.NotaPagoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.NotaPago;
import entidades.becas.PostulacionBeca;
import includes.Comunes;
import includes.ExportarExcel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author huguito
 */
public class NotaPagoFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    NotaPagoJpaController pagoJpaController = new NotaPagoJpaController(emf);
    private static NotaPagoFacade instance = null;

    protected NotaPagoFacade() {
    }

    public static NotaPagoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new NotaPagoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(NotaPago pago) {
        new NotaPagoJpaController(emf).create(pago);
    }

    public void modificar(NotaPago pago) {
        try {
            new NotaPagoJpaController(emf).edit(pago);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NotaPagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NotaPagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    public void eliminar(long id) {
        try {
            new NotaPagoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NotaPagoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public NotaPago buscar(long id) {
        return new NotaPagoJpaController(emf).findNotaPago(id);
    }

    public List<NotaPago> getTodas(String text) {
        Query quBuscar = em.createQuery("SELECT n FROM NotaPago n WHERE n.motivo LIKE '%"
                + text + "%'");
       return quBuscar.getResultList();
    }
    
    public List<NotaPago> getTodas() {
        Query quBuscar = em.createQuery("SELECT p FROM NotaPago p");
        return quBuscar.getResultList();
    }
    public List<NotaPago> getPagosPostulante(PostulacionBeca postulacion) {
        EntityManagerFactory emfa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager ema = emfa.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT n FROM NotaPago n WHERE n.postulacionBeca =:postulacion");
        quBuscar.setParameter("postulacion", postulacion);
        return quBuscar.getResultList();
    }

  public void exportarAExcelPagos() {
        Comunes.ventanaCargando(this, "excelPagos", "Preparandose para Exportar a Excel", null);
    }
    
    public void excelPagos() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //Datos a escribir
        List<String> lista = new ArrayList<String>();
        lista.add("Nº|Codigo Incentivos|Beca|Postulante|Proyecto|Fecha|Nro Nota|"
                + "Motivo|Recibido Mesa de Entrada|Fecha Desembolso");

        for (NotaPago p :getTodas()) {
            StringBuilder stringBuider = new StringBuilder();
            stringBuider.append(p.getId());
            stringBuider.append("|");
            if (p.getPostulacionBeca().getProyecto() != null) {
                stringBuider.append(p.getPostulacionBeca().getProyecto().getCodigoIncentivos());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulacionBeca().getBeca() != null) {
                stringBuider.append(p.getPostulacionBeca().getBeca());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulacionBeca().getPostulante() != null) {
                stringBuider.append(p.getPostulacionBeca().getPostulante().toString());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getPostulacionBeca().getProyecto() != null) {
                stringBuider.append(p.getPostulacionBeca().getProyecto());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getFecha() != null) {
                stringBuider.append(formato.format(p.getFecha()));
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getNroNota() != null) {
                stringBuider.append(p.getNroNota());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getMotivo() != null) {
                stringBuider.append(p.getMotivo());
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getFechaDeRecibidoMesaDeEntrada() != null) {
                stringBuider.append(formato.format(p.getFechaDeRecibidoMesaDeEntrada() ));
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            if (p.getFechaDesembolso() != null) {
                stringBuider.append(formato.format(p.getFechaDesembolso()));
            } else {
                stringBuider.append(" ");
            }
            stringBuider.append("|");
            lista.add(stringBuider.toString());

        }
         // Generar el fichero
        new ExportarExcel().crearExcel(lista, "Pagos");

    }  
    
}