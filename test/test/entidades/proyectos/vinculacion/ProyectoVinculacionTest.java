/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.entidades.proyectos.vinculacion;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.Participacion;
import entidades.proyecto.vinculacion.ParticipacionVinculacion;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import entidades.proyecto.vinculacion.financiacion.FinanciacionPICTO;
import entidades.usuario.Usuario;
import facade.ProyectoVinculacionFacade;
import facade.proyecto.vinculacion.PictoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.*;
import static org.junit.Assert.*;
import vista.proyectos.vinculacion.financiacion.diagDetem;
import vista.proyectos.vinculacion.financiacion.diagPfip;
import vista.proyectos.vinculacion.financiacion.diagPicto;

/**
 *
 * @author Panchi
 */
public class ProyectoVinculacionTest {

    public ProyectoVinculacionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    
    public void h() {

        diagDetem dialog = new diagDetem(new javax.swing.JFrame(), true, new String(), new Usuario());


        dialog.setVisible(true);

    }

    public void hello3() {
        ProyectoVinculacion p = ProyectoVinculacionFacade.getInstance().getUltimoProyectoVinculacion();
        List<Investigador> lista = ProyectoVinculacionFacade.getInstance().getTodosIntegrantes(p);
        Investigador i = lista.get(1);
        ProyectoVinculacionFacade.getInstance().eliminarTodasParticipacionVinculaciones(i, p);
        List<ParticipacionVinculacion> listacomprobacion = ProyectoVinculacionFacade.getInstance().getUltimoProyectoVinculacion().getParticipaciones();
        for (ParticipacionVinculacion l : listacomprobacion) {
            System.out.println(l);
        }
        List<Investigador> integrantesDentro = ProyectoVinculacionFacade.getInstance().getTodosIntegrantes(p);
        for (Investigador i2 : integrantesDentro) {
            System.out.println(i2);
        }

    }

    public void hello() {
        ProyectoVinculacion p = ProyectoVinculacionFacade.getInstance().getUltimoProyectoVinculacion();
        List<ParticipacionVinculacion> participacionesfinales = p.getParticipaciones();
        for (ParticipacionVinculacion parti : participacionesfinales) {
            // System.out.println(parti);
        }
        List<Investigador> lista = ProyectoVinculacionFacade.getInstance().getTodosIntegrantes(p);
        Investigador i = lista.get(2);
        System.out.println(i);
        System.out.println("--------------------");
        List<ParticipacionVinculacion> participaciones = ProyectoVinculacionFacade.getInstance().getParticipacionVinculaciones(i, p);
        for (ParticipacionVinculacion parti : participaciones) {
            //   System.out.println(parti);
        }
        List<ParticipacionVinculacion> listaPruebaFinal = new ArrayList<ParticipacionVinculacion>();
        List<ParticipacionVinculacion> listaFinal = new ArrayList<ParticipacionVinculacion>();
        for (ParticipacionVinculacion p3 : participacionesfinales) {
            System.out.println(p3 + "" + p3.getId());
            for (ParticipacionVinculacion p2 : participaciones) {
                System.out.println(p2 + "" + p2.getId());
                if (!p3.equals(p2)) {
                    System.out.println("SI");
                    listaPruebaFinal.add(p3);
                }
            }
        }

    }

    public void hello2() {
        ProyectoVinculacion p = ProyectoVinculacionFacade.getInstance().buscarPorCodigo("112233");
        System.out.println(p);
    }
    //filtrado por tipo de financiacion.
    
    public void hola(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("ProyectoUnoPU");
        EntityManager em=emf.createEntityManager();
        Query q= em.createQuery("select p from ProyectoVinculacion p, Financiacion f where f.descripcion=:var and f member of p.financiaciones");
        q.setParameter("var","PICTO");
        List<ProyectoVinculacion> lista=q.getResultList();
        for(ProyectoVinculacion p:lista){
            System.out.println(p.getTitulo());
        }
        
    }
    @Test
    @Ignore
    public void hola3(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("ProyectoUnoPU");
        EntityManager em=emf.createEntityManager();
        Query q= em.createQuery("select p from ProyectoVinculacion p, in (p.participaciones) par "
                + "   where par.investigador.persona.apellido like :var or par.investigador.persona.nombre like :var"
                );
        q.setParameter("var", "%Machado%");
        List<ProyectoVinculacion> lista=q.getResultList();
        for(ProyectoVinculacion p:lista){
            
            System.out.println(p.getTitulo());
        }
        
    }
    
     @Test
    public void hola4(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("ProyectoUnoPU");
        EntityManager em=emf.createEntityManager();
        Query q= em.createQuery("select distinct p from ProyectoVinculacion p, in (p.subDisciplinasCientificas) sub "
                + " where sub.disciplinaCientifica.areaTematica.descripcion like :var  " );
        q.setParameter("var", "%agronomia%");
        List<ProyectoVinculacion> lista=q.getResultList();
        for(ProyectoVinculacion p:lista){
            
            System.out.println(p.getTitulo());
        }
        
    }
   
    public void hola2(){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("ProyectoUnoPU");
        EntityManager em=emf.createEntityManager();
        FinanciacionPICTO picto=PictoFacade.getInstance().getPicto(15l);
        System.out.println(picto.getAreaTematica());
        Query q=em.createQuery("select p from ProyectoVinculacion p,Financiacion f where :f member of p.financiaciones");
        q.setParameter("f", picto);
        ProyectoVinculacion proyecto=(ProyectoVinculacion) q.getSingleResult();
        System.out.println(proyecto.getTitulo());
        diagPicto dialogo=new diagPicto(null, true, "Modificacion", new Usuario(), picto, proyecto);
        dialogo.setVisible(true);
    }
}
