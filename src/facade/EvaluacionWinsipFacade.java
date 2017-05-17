/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.EvaluacionWinsipJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.categorizacion.EvaluacionWinsip;
import entidades.proyecto.Proyecto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hugo
 */
public class EvaluacionWinsipFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    EvaluacionWinsipJpaController EvaluacionWinsipJpaController = new EvaluacionWinsipJpaController(emf);
    private static EvaluacionWinsipFacade instance = null;

    protected EvaluacionWinsipFacade() {
    }

    public static EvaluacionWinsipFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new EvaluacionWinsipFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(EvaluacionWinsip evaluacionWinsip) {
        new EvaluacionWinsipJpaController(emf).create(evaluacionWinsip);
    }

    public void modificar(EvaluacionWinsip evaluacionWinsip) {
        try {
            new EvaluacionWinsipJpaController(emf).edit(evaluacionWinsip);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionWinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EvaluacionWinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new EvaluacionWinsipJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EvaluacionWinsipFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EvaluacionWinsip buscar(long id) {
        Query quBuscar = em.createQuery("SELECT c FROM EvaluacionWinsip c WHERE "
                + "c.id=" + id);
        return (EvaluacionWinsip) quBuscar.getSingleResult();
    }

    public List<EvaluacionWinsip> listar(Proyecto proyecto) {
        Query quBuscar = em.createQuery("SELECT w FROM EvaluacionWinsip w WHERE "
                + "w.proyecto=:proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        try {
            return quBuscar.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public EvaluacionWinsip buscarEvaluacion(long id) {
        return new EvaluacionWinsipJpaController(emf).findEvaluacionWinsip(id);
    }
}
