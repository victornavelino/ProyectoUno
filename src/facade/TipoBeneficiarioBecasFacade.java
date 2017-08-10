/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.TipoBeneficiarioBecasJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.becas.TipoBeneficiarioBecas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author huguito
 */
public class TipoBeneficiarioBecasFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    TipoBeneficiarioBecasJpaController TipoBeneficiarioBecasJpaController = new TipoBeneficiarioBecasJpaController(emf);

    private static TipoBeneficiarioBecasFacade instance = null;
    
     protected TipoBeneficiarioBecasFacade() {
    }

        public static TipoBeneficiarioBecasFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new TipoBeneficiarioBecasFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(TipoBeneficiarioBecas tbb) {
        new TipoBeneficiarioBecasJpaController(emf).create(tbb);
    }

    public void modificar(TipoBeneficiarioBecas tbb) {
        try {
            new TipoBeneficiarioBecasJpaController(emf).edit(tbb);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoBeneficiarioBecasFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoBeneficiarioBecasFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(long id) {
        try {
            new TipoBeneficiarioBecasJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TipoBeneficiarioBecasFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TipoBeneficiarioBecas> getTodosTipoBeneficiarioBecas() {
        Query quTodostbb = em.createQuery("SELECT b FROM TipoBeneficiarioBecas b");
        return quTodostbb.getResultList();
    }

    public List<TipoBeneficiarioBecas> getTipoBeneficiarioBeca(String descripcion) {
        Query qutbb = em.createQuery("SELECT tb FROM TipoBeneficiarioBecas tb "
                + "WHERE tb.descripcion LIKE '" + descripcion + "'");
        return qutbb.getResultList();
    }

}
    

