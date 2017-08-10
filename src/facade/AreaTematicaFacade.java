/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.AreaTematicaJpaController;
import controladores.DisciplinaCientificaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.AreaTematica;
import entidades.proyecto.DisciplinaCientifica;
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
public class AreaTematicaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    AreaTematicaJpaController areaTematicaJpaController = new AreaTematicaJpaController(emf);
    private static AreaTematicaFacade instance = null;

    protected AreaTematicaFacade() {
    }

    public static AreaTematicaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new AreaTematicaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(AreaTematica areaTematica) {
        areaTematicaJpaController.create(areaTematica);
    }

    public void modificar(AreaTematica areaTematica) {
        try {
            areaTematicaJpaController.edit(areaTematica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<AreaTematica> getTodas() {
        Query quTodasDisciplinas = em.createQuery("SELECT a FROM AreaTematica a ORDER BY a.descripcion");
        return quTodasDisciplinas.getResultList();
    }

    public void agregarDisciplinaCientifica(AreaTematica areaTematica, DisciplinaCientifica disciplinaCientifica) {
        System.out.println("ANTES=" + areaTematica.getDisciplinasCientificas().size());
        List<DisciplinaCientifica> disciplinaCientificas = areaTematica.getDisciplinasCientificas();
        disciplinaCientificas.add(disciplinaCientifica);
        areaTematica.setDisciplinasCientificas(disciplinaCientificas);
        System.out.println("DESPUES=" + areaTematica.getDisciplinasCientificas().size());
        try {
            new AreaTematicaJpaController(emf).edit(areaTematica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        disciplinaCientifica.setAreaTematica(areaTematica);
        try {
            new DisciplinaCientificaJpaController(emf).edit(disciplinaCientifica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<AreaTematica> getTodosAreaTematica() {
        Query quTodosAreaTematica = em.createQuery("SELECT at FROM AreaTematica at");
        return quTodosAreaTematica.getResultList();
    }

    public List<AreaTematica> getAreaTematica(String descripcion) {
        Query quAreaTematica = em.createQuery("SELECT at FROM AreaTematica at "
                + "WHERE at.descripcion LIKE '" + descripcion + "'");
        return quAreaTematica.getResultList();
    }

    public List<AreaTematica> filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT s FROM AreaTematica s "
                + "WHERE s.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            areaTematicaJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
