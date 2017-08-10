/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.EtapaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Resolucion;
import entidades.categorizacion.Categorizacion;
import entidades.categorizacion.Winsip;
import entidades.proyecto.vinculacion.financiacion.pfip.Etapa;
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
public class EtapaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EtapaJpaController etapaJpaController = new EtapaJpaController(emf);
    private static EtapaFacade instance = null;

    public EtapaFacade() {
    }

    public static EtapaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EtapaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Etapa etapa) {
        new EtapaJpaController(emf).create(etapa);
    }

    public void modificar(Etapa etapa) {
        try {
            new EtapaJpaController(emf).edit(etapa);
        } catch (Exception ex) {
            Logger.getLogger(EtapaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Long id) {
        try {
            new EtapaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EtapaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Etapa buscar(long id) {
        return new EtapaJpaController(emf).findEtapa(id);
    }

    public List<Etapa> getTodos() {
        return new EtapaJpaController(emf).findEtapaEntities();
    }


    public List<Etapa> getTodosEtapa() {
        Query quTodosEtapa = em.createQuery("SELECT i FROM Etapa i "
                + "");
        return quTodosEtapa.getResultList();
    }

}
