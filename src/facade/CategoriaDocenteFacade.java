/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.CategoriaDocenteJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.CategoriaDocente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class CategoriaDocenteFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static CategoriaDocenteFacade instance = null;

    protected CategoriaDocenteFacade() {
    }

    public static CategoriaDocenteFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new CategoriaDocenteFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(CategoriaDocente categoriaDocente) {
        new CategoriaDocenteJpaController(emf).create(categoriaDocente);
    }

    public void modificar(CategoriaDocente categoriaDocente) {
        try {
            new CategoriaDocenteJpaController(emf).edit(categoriaDocente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategoriaDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CategoriaDocente buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM CategoriaDocente tt WHERE tt.id="
                + id);
        try {
            return (CategoriaDocente) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            CategoriaDocente categoriaDocente = null;
            return categoriaDocente;
        }

    }

    public List<CategoriaDocente> listarCategoriaDocente(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM CategoriaDocente tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<CategoriaDocente> listarTodosCategoriaDocente() {
        Query quBuscar = em.createQuery("SELECT tt FROM CategoriaDocente tt");
        return quBuscar.getResultList();
    }

    public List<CategoriaDocente> listarTodosCategoriaDocenteOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM CategoriaDocente tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public List<CategoriaDocente> filtrar(String descripcion) {
        Query quBuscar = em.createQuery("SELECT cd From CategoriaDocente cd "
                + "WHERE cd.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            new CategoriaDocenteJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategoriaDocenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
