/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.AgenciaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.vinculacion.Agencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Panchi
 */
public class AgenciaFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    AgenciaJpaController agenciaJpaController = new AgenciaJpaController(emf);
    private static AgenciaFacade instance = null;

    protected AgenciaFacade() {
    }

    public static AgenciaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new AgenciaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(Agencia agencia) {
        new AgenciaJpaController(emf).create(agencia);
    }
    
    public void modificacion(Agencia agencia) {        
        try {
            new AgenciaJpaController(emf).edit(agencia);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AgenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AgenciaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    public List<Agencia> agenciaxDescripcion(String agencia){
        Query quDes = em.createQuery("SELECT l FROM Agencia l "
                + "WHERE l.descripcion LIKE :des");
        quDes.setParameter("des", "%" + agencia + "%");
        return  quDes.getResultList();        
    }
    
    public Agencia buscar(long id) {
        return agenciaJpaController.findAgencia(id);
    }
    public List<Agencia> getTodosAgencia() {
        Query quTodasAgencia = em.createQuery("SELECT nf FROM Agencia nf ORDER BY nf.descripcion");
        return quTodasAgencia.getResultList();
    }
}
