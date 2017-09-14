/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.SubDisciplinaCientificaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.DisciplinaCientifica;
import entidades.proyecto.SubDisciplinaCientifica;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class SubDisciplinaCientificaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    SubDisciplinaCientificaJpaController subDisciplinaCientificaJpaController = new SubDisciplinaCientificaJpaController(emf);
    private static SubDisciplinaCientificaFacade instance = null;

    protected SubDisciplinaCientificaFacade() {
    }

    public static SubDisciplinaCientificaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new SubDisciplinaCientificaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(SubDisciplinaCientifica subDisciplinaCientifica) {
        subDisciplinaCientificaJpaController.create(subDisciplinaCientifica);
    }

    public void modificar(SubDisciplinaCientifica subDisciplinaCientifica) {
        try {
            subDisciplinaCientificaJpaController.edit(subDisciplinaCientifica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SubDisciplinaCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SubDisciplinaCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SubDisciplinaCientifica> getTodas() {
        Query quTodasSubDisciplinas = em.createQuery("SELECT sdc FROM SubDisciplinaCientifica sdc");
        return quTodasSubDisciplinas.getResultList();
    }

    public List<SubDisciplinaCientifica> getTodosSubDisciplinaCientifica() {
        Query quTodosSubDisciplinaCientifica = em.createQuery("SELECT dc FROM SubDisciplinaCientifica dc");
        return quTodosSubDisciplinaCientifica.getResultList();
    }

    public List<SubDisciplinaCientifica> getSubDisciplinaCientifica(String descripcion) {
        Query quSubDisciplinaCientifica = em.createQuery("SELECT dc FROM SubDisciplinaCientifica dc "
                + "WHERE dc.descripcion LIKE '" + descripcion + "'");
        return quSubDisciplinaCientifica.getResultList();
    }

   public void eliminar(long id) {
        try {
            new SubDisciplinaCientificaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CampoAplicacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT s FROM SubDisciplinaCientifica s "
                + "WHERE s.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList(); 
    }
    public List<SubDisciplinaCientifica> getSubDisciplinas(DisciplinaCientifica disciplinaCientifica) {
        Query quBuscar = em.createQuery("SELECT s FROM SubDisciplinaCientifica s "
                + "WHERE s.disciplinaCientifica=:disciplinaCientifica");
        quBuscar.setParameter("disciplinaCientifica", disciplinaCientifica);
        return quBuscar.getResultList(); 
    }
}
