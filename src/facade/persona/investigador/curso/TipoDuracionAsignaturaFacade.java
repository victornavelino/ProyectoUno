/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.persona.investigador.curso;

import controladores.TipoDuracionAsignaturaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.curso.TipoDuracionAsignatura;
import facade.ConexionFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class TipoDuracionAsignaturaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static TipoDuracionAsignaturaFacade instance = null;

    protected TipoDuracionAsignaturaFacade() {
    }

    public static TipoDuracionAsignaturaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoDuracionAsignaturaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoDuracionAsignatura tipoDuracionAsignatura) {
        new TipoDuracionAsignaturaJpaController(emf).create(tipoDuracionAsignatura);
    }

    public void modificar(TipoDuracionAsignatura tipoDuracionAsignatura) {
        try {
            new TipoDuracionAsignaturaJpaController(emf).edit(tipoDuracionAsignatura);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoDuracionAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoDuracionAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoDuracionAsignatura buscar(long id) {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoDuracionAsignatura tt WHERE tt.id="
                + id);
        try {
            return (TipoDuracionAsignatura) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            TipoDuracionAsignatura tipoDuracionAsignatura = null;
            return tipoDuracionAsignatura;
        }

    }

    public List<TipoDuracionAsignatura> listarTipoDuracionAsignatura(String text) {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoDuracionAsignatura tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<TipoDuracionAsignatura> listarTodosTipoDuracionAsignatura() {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoDuracionAsignatura tt");
        return quBuscar.getResultList();
    }

    public List<TipoDuracionAsignatura> listarTodosTipoDuracionAsignaturaOrdenados() {
        Query quBuscar = em.createQuery("SELECT tt FROM TipoDuracionAsignatura tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public List<TipoDuracionAsignatura> filtrar(String descripcion) {
       EntityManager ema = emf.createEntityManager();
        Query quBuscar = ema.createQuery("SELECT tda From TipoDuracionAsignatura tda "
                + "WHERE tda.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            new TipoDuracionAsignaturaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoDuracionAsignaturaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
