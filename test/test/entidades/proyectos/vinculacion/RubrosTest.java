/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.entidades.proyectos.vinculacion;


import controladores.RubroJpaController;
import entidades.proyecto.vinculacion.financiacion.picto.Rubro;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruben
 */
public class RubrosTest {
    
    public RubrosTest() {
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
     @Test
     public void hello() {
         EntityManagerFactory emf=Persistence.createEntityManagerFactory("ProyectoUnoPU");
         EntityManager em=emf.createEntityManager();
         Rubro r1=new Rubro();
         r1.setNombre("Insumos");
         new RubroJpaController(emf).create(r1);
         Rubro r2 =new Rubro();
         r2.setNombre("Bibliografia");
         new RubroJpaController(emf).create(r2);
         Rubro r3 =new Rubro();
         r3.setNombre("Viajes y Viaticos");
         new RubroJpaController(emf).create(r3);
         Rubro r4 =new Rubro();
         r4.setNombre("Equipamiento");
         new RubroJpaController(emf).create(r4);
         Rubro r5 =new Rubro();
         r5.setNombre("Becas");
         new RubroJpaController(emf).create(r5);
         Rubro r6 =new Rubro();
         r6.setNombre("Gastos de administracion del subsidio");
         new RubroJpaController(emf).create(r6);
         Rubro r7 =new Rubro();
         r7.setNombre("Personal (Salarios)");
         new RubroJpaController(emf).create(r7);
     }
}
