/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ResolucionJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Resolucion;
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
public class ResolucionFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();

    public boolean existe(Resolucion resolucion) {
        boolean flag = false;
        Query quBuscarResolucion = em.createQuery("SELECT r.id FROM Resolucion r "
                + "WHERE r.numero=" + resolucion.getNumero() + " AND r.ano="
                + resolucion.getAno());
        List resolucionesEncontradas = quBuscarResolucion.getResultList();
        if (!resolucionesEncontradas.isEmpty()) {
            flag = true;
        }
        return flag;
    }

    public void alta(Resolucion resolucion) {
        new ResolucionJpaController(emf).create(resolucion);
    }

    public void modificar(Resolucion resolucion) {
        try {
            new ResolucionJpaController(emf).edit(resolucion);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ResolucionFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ResolucionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Resolucion> buscar(String codigo) {
        Query quBuscar = em.createQuery("SELECT r FROM Resolucion r WHERE r.descripcion "
                + "LIKE :codigo");
        quBuscar.setMaxResults(100);
        quBuscar.setParameter("codigo", "%" + codigo + "%");
        return quBuscar.getResultList();
    }
}
