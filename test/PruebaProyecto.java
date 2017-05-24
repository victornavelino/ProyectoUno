/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import controladores.ProyectoJpaController;
import entidades.economico.BienUso;
import entidades.economico.PagoEconomico;
import entidades.proyecto.Proyecto;
import entidades.proyecto.vinculacion.NotaExterna;
import facade.ConexionFacade;
import facade.PagoEconomicoFacade;
import java.awt.Frame;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Ignore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Carlos
 */
public class PruebaProyecto {

    public PruebaProyecto() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void probarModificacionProyecto() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU");
//        Proyecto proyecto = new ProyectoJpaController(emf).findProyecto(1L);
//        diagProyecto proyectoModificacion = new diagProyecto(null, true, "Modificación", proyecto);
//        proyectoModificacion.setLocation(Comunes.centrarDialog(proyectoModificacion));
//        proyectoModificacion.setVisible(true);
    }

    @Ignore
    @Test
    public void probarAltaProyecto() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU");
//        diagProyecto proyectoAlta = new diagProyecto(null, true, "Alta");
//        proyectoAlta.setLocation(Comunes.centrarDialog(proyectoAlta));
//        proyectoAlta.setVisible(true);
    }
    
    @Test
    public void probarQueryPagos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU",ConexionFacade.PROPIEDADES);
        Proyecto p = new ProyectoJpaController(emf).findProyecto(505L);
        List<PagoEconomico> lista = p.getPagos();
        Comparator<BienUso> comparadorNe = new Comparator<BienUso>() {
        public int compare(BienUso a, BienUso b) {
            return (a.getAnio().compareTo(b.getAnio()));
            }
        };
        List<BienUso> bu = p.getPresupuesto().getBienUso();
        Collections.sort(bu,comparadorNe);
        for(BienUso b: bu){
            System.out.println(b.getAnio());
        }
        /*Map<Integer, BigDecimal> map = new HashMap<>();

        for(PagoEconomico pe : lista) {
            Integer name = pe.getAnioExpediente();
            System.out.println(name);
            BigDecimal sum = map.get(name);
            if (sum == null) {
                sum = new BigDecimal(0);
                map.put(name, sum);
            }
            
            map.put(name, sum.add(pe.getMonto()));
        }
        Iterator keys=map.keySet().iterator();
        while(keys.hasNext()){
           if(keys.hasNext()){
            System.out.println(map.get(keys.next()));
           }
        }
        System.out.println(map);
        System.out.println(map.values());
        System.out.println(new ArrayList<>(map.keySet()));*/
    }

}