/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controladores.DisciplinaCientificaJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.proyecto.AreaTematica;
import entidades.proyecto.DisciplinaCientifica;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Carlos
 */
public class DisciplinaCientificaFacade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();
    DisciplinaCientificaJpaController disciplinaCientificaJpaController = new DisciplinaCientificaJpaController(emf);

    private static DisciplinaCientificaFacade instance = null;

    protected DisciplinaCientificaFacade() {
    }

        public static DisciplinaCientificaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new DisciplinaCientificaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(DisciplinaCientifica disciplinaCientifica) {
        disciplinaCientificaJpaController.create(disciplinaCientifica);
    }

    public void modificar(DisciplinaCientifica disciplinaCientifica) {
        try {
            disciplinaCientificaJpaController.edit(disciplinaCientifica);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DisciplinaCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DisciplinaCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<DisciplinaCientifica> getTodas() {
        Query quTodasDisciplinas = em.createQuery("SELECT dc FROM DisciplinaCientifica dc");
        return quTodasDisciplinas.getResultList();
    }

    public DisciplinaCientifica get(int codigo) {
        Query quTodasDisciplinas = em.createQuery("SELECT dc FROM DisciplinaCientifica dc "
                + "WHERE dc.codigo=:codigo");
        quTodasDisciplinas.setParameter("codigo", codigo);
        List<DisciplinaCientifica> disciplinasCientificasEncontradas = quTodasDisciplinas.getResultList();
        if(!disciplinasCientificasEncontradas.isEmpty()) {
            return disciplinasCientificasEncontradas.get(0);
        } else {
            return null;
        }
    }

    public List<DisciplinaCientifica> getSinAreaTematica() {
        Query quDisciplinasSinAreaTematica = em.createQuery("SELECT dc FROM DisciplinaCientifica dc "
                + "WHERE dc.areaTematica IS NULL");
        return quDisciplinasSinAreaTematica.getResultList();
    }

        public List<DisciplinaCientifica> getTodosDisciplinaCientifica() {
        Query quTodosDisciplinaCientifica = em.createQuery("SELECT dc FROM DisciplinaCientifica dc");
        return quTodosDisciplinaCientifica.getResultList();
    }

    public List<DisciplinaCientifica> getDisciplinaCientifica(String descripcion) {
        Query quDisciplinaCientifica = em.createQuery("SELECT dc FROM DisciplinaCientifica dc "
                + "WHERE dc.descripcion LIKE '" + descripcion + "'");
        return quDisciplinaCientifica.getResultList();
    }

    public List<DisciplinaCientifica> filtrar(String descripcion) {
      Query quBuscar = em.createQuery("SELECT s FROM DisciplinaCientifica s "
                + "WHERE s.descripcion LIKE '%" + descripcion + "%'");
        return quBuscar.getResultList();   
    }

    public void eliminar(Long id) {
        try {
            disciplinaCientificaJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DisciplinaCientificaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<DisciplinaCientifica> getDisciplinaCientifica(AreaTematica areaTematica) {
         Query quBuscar = em.createQuery("SELECT s FROM DisciplinaCientifica s "
                + "WHERE s.areaTematica=:areaTematica");
        quBuscar.setParameter("areaTematica", areaTematica);
        return quBuscar.getResultList(); 
    }

    

}
