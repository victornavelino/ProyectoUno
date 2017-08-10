/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DocumentacionBecaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.DocumentacionBeca;
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
public class DocumentacionBecaFacade {
     
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    DocumentacionBecaJpaController DocumentacionBecasJpaController = new DocumentacionBecaJpaController(emf);

    private static DocumentacionBecaFacade instance = null;
    
     protected DocumentacionBecaFacade() {
    }

        public static DocumentacionBecaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DocumentacionBecaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DocumentacionBeca dbeca) {
        new DocumentacionBecaJpaController(emf).create(dbeca);
    }

    public void modificar(DocumentacionBeca dbeca) {
        try {
            new DocumentacionBecaJpaController(emf).edit(dbeca);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DocumentacionBecaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DocumentacionBecaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new DocumentacionBecaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DocumentacionBecaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<DocumentacionBeca> getTodosDocumentacionBecas() {
        Query quTodosdoc = em.createQuery("SELECT d FROM documentacionbeca d");
        return quTodosdoc.getResultList();
    }

    public List<DocumentacionBeca> getDocumentacionBecas(String descripcion) {
        Query qutbb = em.createQuery("SELECT d FROM documentacionbeca d "
                + "WHERE d.descripcion LIKE '%" + descripcion + "%'");
        return qutbb.getResultList();
    }
    
}
