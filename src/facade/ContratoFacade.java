/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ContratoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.Proyecto;
import entidades.proyecto.resultado.Contrato;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author franco
 */
public class ContratoFacade {
     private static ContratoFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);

    protected ContratoFacade() {
    }

    public static ContratoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ContratoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Contrato contrato) {
        new ContratoJpaController(emf).create(contrato);
    }

    public void modificar(Contrato contrato) {
        try {
            new ContratoJpaController(emf).edit(contrato);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AreaTematicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Contrato> getTodas() {
        return new ContratoJpaController(emf).findContratoEntities();
    }
      public List<Contrato> getContratosProyecto(ProyectoVinculacion proyectoVinculacion){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT c FROM Contrato c, IN (c.proyectosVinculacion) pr WHERE  pr = :proyectoVinculacion");
        quBuscar.setParameter("proyectoVinculacion", proyectoVinculacion);
        return quBuscar.getResultList();
        
    }
    public List<Contrato> getContratosProyecto(Proyecto proyecto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query quBuscar = em.createQuery("SELECT c FROM Contrato c, IN (c.proyectos) pr WHERE  pr = :proyecto");
        quBuscar.setParameter("proyecto", proyecto);
        return quBuscar.getResultList();
        
    }

    public Contrato buscar(Long id) {
        return new ContratoJpaController(emf).findContrato(id);
    }

    public void eliminar(Long id) {
        try{
        new ContratoJpaController(emf).destroy(id);
        }catch(NonexistentEntityException ex) {
            Logger.getLogger(ContratoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
