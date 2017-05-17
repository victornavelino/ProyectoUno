/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.DepartamentoDocenteJpaController;

import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.DepartamentoDocente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class DepartamentoDocenteFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static DepartamentoDocenteFacade instance = null;

    protected DepartamentoDocenteFacade() {
    }

    public static DepartamentoDocenteFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DepartamentoDocenteFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DepartamentoDocente departamentoDocente) {
        new DepartamentoDocenteJpaController(emf).create(departamentoDocente);
    }
    public void modificar(DepartamentoDocente departamentoDocente){
        try {
            new DepartamentoDocenteJpaController(emf).edit(departamentoDocente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DepartamentoDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DepartamentoDocente buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM DepartamentoDocente tt WHERE tt.id="
                + id);
        try {
            return (DepartamentoDocente) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            DepartamentoDocente departamentoDocente = null;
            return departamentoDocente;
        }

    }

    public List<DepartamentoDocente> listarDepartamentoDocente(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM DepartamentoDocente tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<DepartamentoDocente> listarTodosDepartamentoDocente() {
        Query quBuscar = em.createQuery("SELECT tt FROM DepartamentoDocente tt");
        return quBuscar.getResultList();
    }

    public List<DepartamentoDocente> listarTodosDepartamentoDocenteOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM DepartamentoDocente tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public List<DepartamentoDocente> filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT d FROM DepartamentoDocente d "
                + "WHERE d.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList(); 
    }

    public void eliminar(Long id) {
        try {
            new DepartamentoDocenteJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DepartamentoDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   }
