/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PuestoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.usuario.Puesto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class PuestoFacade {
    // Atributos

    EntityManagerFactory entityManagerFactoryTelecentroJpa = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    PuestoJpaController puestoJpa = new PuestoJpaController(entityManagerFactoryTelecentroJpa);
    private static PuestoFacade instance = null;

    protected PuestoFacade() {
    }

    public static PuestoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PuestoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Puesto puesto) {
        puestoJpa.create(puesto);

    }

    public void modificar(Puesto puesto) {
        try {
            puestoJpa.edit(puesto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PuestoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PuestoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Long id) {
        try {
            puestoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PuestoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Puesto buscar(Long id) {
        Puesto puesto = puestoJpa.findPuesto(id);
        return puesto;
    }

    public Puesto buscar(String nombreHost) {
        EntityManager entityManagerTelecentroJpa = entityManagerFactoryTelecentroJpa.createEntityManager();
        Query quPuesto;
        List listPuestos;
        Puesto puesto = new Puesto();
        quPuesto = entityManagerTelecentroJpa.createQuery("SELECT p FROM "
                + "Puesto p WHERE p.nombreHost = '" + nombreHost + "'");
        listPuestos = quPuesto.getResultList();
        if (listPuestos.size() > 0) {
            puesto = (Puesto) listPuestos.get(0);
        }
        return puesto;
    }
}
