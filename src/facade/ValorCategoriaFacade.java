/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ValorCategoriaJpaController;
import entidades.categorizacion.ValorCategoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author wbivanco
 */
public class ValorCategoriaFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
    EntityManager em = emf.createEntityManager();        
    private static ValorCategoriaFacade instance = null;

    protected ValorCategoriaFacade() {
    }

    public static ValorCategoriaFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ValorCategoriaFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void alta(ValorCategoria valorCategoria) {
        new ValorCategoriaJpaController(emf).create(valorCategoria);
    }
    
     public List<ValorCategoria> listarTodosValorCategoria(){
        Query quValores = em.createQuery("SELECT v FROM ValorCategoria v");
        return quValores.getResultList();
    }
        
    public boolean descripcionExistente(String descripcion){
        Query quDes = em.createQuery("SELECT v FROM ValorCategoria v WHERE "
                + "v.descripcion=:descri");
        quDes.setParameter("descri", descripcion);        
        List<ValorCategoria> listaValor = quDes.getResultList();
        if(listaValor.isEmpty()){
            return true;
        }else{
            return false;
        }
    }            
     
}
