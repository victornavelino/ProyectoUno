/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.EntradasSalidas;

import controladores.NotaEntradaSalidaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.EntradasSalidas.NotaEntradaSalida;
import facade.ConexionFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vouilloz
 */
public class NotaEntradaSalidaFacade {

    private static NotaEntradaSalidaFacade instance = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);

    protected NotaEntradaSalidaFacade() {
    }

    public static NotaEntradaSalidaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new NotaEntradaSalidaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(NotaEntradaSalida nota) {
        new NotaEntradaSalidaJpaController(emf).create(nota);
    }

    public void modificar(NotaEntradaSalida nota) {
        try {
            new NotaEntradaSalidaJpaController(emf).edit(nota);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NotaEntradaSalidaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NotaEntradaSalidaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<NotaEntradaSalida> getTodas() {
        return new NotaEntradaSalidaJpaController(emf).findNotaEntradaSalidaEntities();
    }

    public NotaEntradaSalida buscar(Long id) {
        return new NotaEntradaSalidaJpaController(emf).findNotaEntradaSalida(id);
    }

    public void eliminar(Long id) {
        try {
            new NotaEntradaSalidaJpaController(emf).destroy(id);
        } catch (NonexistentEntityException ex) {
            //Logger.getLogger(NotaEntradaSalidaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        // emf.close();
    }

    public List<NotaEntradaSalida> listaNotas() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT n FROM NotaEntradaSalida n ORDER BY n.id DESC");
        return q.getResultList();

    }

    public boolean buscarNumeroNota(String numero) {
        try {
            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT n FROM NotaEntradaSalida n WHERE n.numero = '" + numero + "'");
            q.getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*   public List<NotaEntradaSalida> buscar(String nombre) {
     EntityManager em = emf.createEntityManager();

     Query q = em.createQuery("SELECT n FROM NotaEntradaSalida n "
     + "WHERE n.nombre LIKE '%" + nombre + "%'");
     return q.getResultList();
     }

     public List<Expediente> buscarExp(String letra, String numero) {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
     + "WHERE e.letra= '" + letra + "'AND e.numero='" + numero + "'");
     return quBuscarOrg.getResultList();
     }

     public List<Expediente> buscarPorAño(int año) {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
     + "WHERE e.anio='" + año + "'");
     return quBuscarOrg.getResultList();
     }

     public List<Expediente> busquedaPorLetra(String letra) {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
     + "WHERE e.letra LIKE '%" + letra + "%'");
     return quBuscarOrg.getResultList();
     }

     public List<Expediente> busquedaPorNumero(String numero) {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
     + "WHERE e.numero LIKE '%" + numero + "%'");
     return quBuscarOrg.getResultList();
     }

     public List<Expediente> getExpedientes() {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e");
     return quBuscarOrg.getResultList();
     }

     public List<Pase> getPasesEntrada() {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='entrada'");
     return quBuscarOrg.getResultList();
     }

     public List<Pase> getPasesSalida() {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT p FROM Pase p WHERE p.movimiento='salida'");
     return quBuscarOrg.getResultList();
     }

     public List<Expediente> busquedaPorInciador(String iniciador) {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
     + "WHERE e.iniciador LIKE '%" + iniciador + "%'");
     return quBuscarOrg.getResultList();
     }

     public List<Expediente> busquedaPorEstracto(String estracto) {
     EntityManager em = emf.createEntityManager();

     Query quBuscarOrg = em.createQuery("SELECT e FROM Expediente e "
     + "WHERE e.estracto LIKE '%" + estracto + "%'");
     return quBuscarOrg.getResultList();
     }*/
}
