/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.PlantillaMensajeJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.persona.investigador.PlantillaMensaje;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hugo
 */
public class PlantillaMensajeFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    private static PlantillaMensajeFacade instance = null;

    protected PlantillaMensajeFacade() {
    }

    public static PlantillaMensajeFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new PlantillaMensajeFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(PlantillaMensaje plantillaMensaje) throws Exception {
        new PlantillaMensajeJpaController(emf).create(plantillaMensaje);
    }

    public void eliminar(PlantillaMensaje plantillaMensaje) throws Exception {
        try {
            new PlantillaMensajeJpaController(emf).destroy(plantillaMensaje.getId());
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al eliminar el borrador");
        }
    }

    public void modificar(PlantillaMensaje plantillaMensaje) throws Exception {
        try {
            new PlantillaMensajeJpaController(emf).edit(plantillaMensaje);
        } catch (NonexistentEntityException ex) {
            throw new Exception("Se ha producido un error al modificar el borrador");
        } catch (Exception ex) {
            throw new Exception("Se ha producido un error al modificar el borrador");
        }
    }

    public PlantillaMensaje buscar(Long id) throws Exception {
        emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        return new PlantillaMensajeJpaController(emf).findPlantillaMensaje(id);
    }

    public List<PlantillaMensaje> listar() throws Exception {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query quMensaje = entityManager.createQuery("SELECT m FROM PlantillaMensaje m ORDER BY m.fecha DESC");
        return quMensaje.getResultList();
    }

}
