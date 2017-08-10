/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ResolucionEconomicoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Resolucion;
import entidades.economico.ResolucionEconomico;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
public class ResolucionEconomicoFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    ResolucionEconomicoJpaController economicoJpaController = new ResolucionEconomicoJpaController(emf);
    
    private static ResolucionEconomicoFacade instance = null;
    
    protected ResolucionEconomicoFacade(){        
    }
    
    public static ResolucionEconomicoFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ResolucionEconomicoFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public boolean existe(Resolucion resolucion) {
        boolean flag = false;
        Query quBuscarResolucion = em.createQuery("SELECT r FROM Resolucion r "
                + "WHERE r.numero=" + resolucion.getNumero() + " AND r.ano="
                + resolucion.getAno());
        List<Resolucion> resolucionesEncontradas = quBuscarResolucion.getResultList();
        if (!resolucionesEncontradas.isEmpty()) {
            flag = true;
        }
        return flag;
    }

    public void alta(ResolucionEconomico resolucionEconomico) {
        new ResolucionEconomicoJpaController(emf).create(resolucionEconomico);
    }

    public void modificar(ResolucionEconomico resolucionEconomico) {
        try {
            new ResolucionEconomicoJpaController(emf).edit(resolucionEconomico);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ResolucionEconomicoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ResolucionEconomicoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Resolucion> buscar(String codigo) {
        Query quBuscar = em.createQuery("SELECT r FROM Resolucion r WHERE r.descripcion "
                + "LIKE :codigo");
        quBuscar.setParameter("codigo", "%" + codigo + "%");
        return quBuscar.getResultList();
    }
}
