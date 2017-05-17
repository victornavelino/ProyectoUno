/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ModoObtencionCargoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.ModoObtencionCargo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author carlos
 */
public class ModoObtencionCargoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    private static ModoObtencionCargoFacade instance = null;

    protected ModoObtencionCargoFacade() {
    }

    public static ModoObtencionCargoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ModoObtencionCargoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ModoObtencionCargo modoObtencionCargo) {
        new ModoObtencionCargoJpaController(emf).create(modoObtencionCargo);
    }

    public ModoObtencionCargo buscar(long id) {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT tt FROM ModoObtencionCargo tt WHERE tt.id="
                + id);
        try {
            return (ModoObtencionCargo) quBuscar.getSingleResult();
        } catch (NoResultException ex) {
            ModoObtencionCargo modoObtencionCargo = null;
            return modoObtencionCargo;
        }

    }

    public List<ModoObtencionCargo> listarModoObtencionCargo(String text) {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT tt FROM ModoObtencionCargo tt WHERE tt.descripcion LIKE '%"
                + text + "%'");
        return quBuscar.getResultList();
    }

    public List<ModoObtencionCargo> listarTodosModoObtencionCargo() {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT tt FROM ModoObtencionCargo tt");
        return quBuscar.getResultList();
    }

    public List<ModoObtencionCargo> listarTodosModoObtencionCargoOrdenados() {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT tt FROM ModoObtencionCargo tt ORDER BY tt.descripcion");
        return quBuscar.getResultList();
    }

    public void modificar(ModoObtencionCargo modoObtencionCargo) {
        try {
            new ModoObtencionCargoJpaController(emf).edit(modoObtencionCargo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModoObtencionCargoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModoObtencionCargoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ModoObtencionCargo> filtrar(String descripcion) {
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT cd From ModoObtencionCargo cd "
                + "WHERE cd.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();
    }

    public void eliminar(Long id) {
        try {
            new ModoObtencionCargoJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModoObtencionCargoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
