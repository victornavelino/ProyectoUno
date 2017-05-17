/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.BecasJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.Becas;
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
public class BecasFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    BecasJpaController BecasJpaController = new BecasJpaController(emf);

    private static BecasFacade instance = null;
    
     protected BecasFacade() {
    }

        public static BecasFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BecasFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Becas beca) {
        new BecasJpaController(emf).create(beca);
    }

    public void modificar(Becas beca) {
        try {
            new BecasJpaController(emf).edit(beca);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BecasFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BecasFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new BecasJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BecasFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Becas> getTodosBecas() {
        Query quTodosbecas = em.createQuery("SELECT b FROM Becas b");
        return quTodosbecas.getResultList();
    }
    public List<Becas> getTodasBecasVigentes() {
        Query quTodosbecas = em.createQuery("SELECT b FROM Becas b WHERE b.vigente = 1");
        return quTodosbecas.getResultList();
    }

    public List<Becas> getBecas(String descripcion) {
        Query qutbb = em.createQuery("SELECT b FROM Becas b "
                + "WHERE b.vigente = 1 AND b.descripcion LIKE '%" + descripcion + "%'");
        return qutbb.getResultList();
    }
    public Becas getBeca(String descripcion) {
        Query qutbb = em.createQuery("SELECT b FROM Becas b "
                + "WHERE b.descripcion='"  + descripcion + "'");
        return (Becas)qutbb.getSingleResult();
    }
}
