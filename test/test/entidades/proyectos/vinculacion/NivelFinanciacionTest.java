package test.entidades.proyectos.vinculacion;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import controladores.NivelFinanciacionJpaController;
import entidades.proyecto.vinculacion.Agencia;
import entidades.proyecto.vinculacion.Ministerio;
import org.junit.*;
import static org.junit.Assert.*;
import entidades.proyecto.vinculacion.NivelFinanciacion;
import entidades.proyecto.vinculacion.OtrasAgencias;
import facade.MinisterioFacade;
import facade.NivelFinanciacionFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Panchi
 */
public class NivelFinanciacionTest {

    public NivelFinanciacionTest() {
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
    public void insertar2() {
        OtrasAgencias o = new OtrasAgencias();
        o.setDescripcion("PICT");
        OtrasAgencias oa = new OtrasAgencias();
        oa.setDescripcion("PICTO");
//        OtrasAgencias ow = new OtrasAgencias();
//        ow.setDescripcion("PID");
//        OtrasAgencias oq = new OtrasAgencias();
//        oq.setDescripcion("PME");
//
//        OtrasAgencias oqs = new OtrasAgencias();
//        oqs.setDescripcion("PAE");
//        OtrasAgencias so = new OtrasAgencias();
//        so.setDescripcion("PRH");

//        OtrasAgencias ao = new OtrasAgencias();
//        ao.setDescripcion("PRAMIN");
//        OtrasAgencias aao = new OtrasAgencias();
//        aao.setDescripcion("PPL");
        Agencia a = new Agencia();
        a.setDescripcion("FONCYT");
        List<OtrasAgencias> lista = new ArrayList<>();
        lista.add(o);
        lista.add(oa);
//        lista.add(ow);
//        lista.add(oq);
//        lista.add(oqs);
//        lista.add(so);
//        lista.add(ao);
//        lista.add(aao);
        a.setOtrasAgencias(lista);
        Agencia aa = new Agencia();
        aa.setDescripcion("FONTAR");
        Agencia ab = new Agencia();
        ab.setDescripcion("FONSOFT");
        Agencia ac = new Agencia();
        ac.setDescripcion("FONARSE");

        Ministerio m = new Ministerio();
        m.setDescripcion("MINCyT");
        List<Agencia> lista2 = new ArrayList<>();
        lista2.add(a);
        lista2.add(aa);

        lista2.add(ab);

        lista2.add(ac);

        m.setAgencias(lista2);
        Ministerio m3 = new Ministerio();
        m3.setDescripcion("M. Educ");

        NivelFinanciacion n = new NivelFinanciacion();
        List<Ministerio> li = new ArrayList<>();
        li.add(m);
        li.add(m3);
        n.setDescripcion("Nacional");
        n.setMinisterios(li);
        NivelFinanciacionFacade.getInstance().alta(n);
        NivelFinanciacion n2 = new NivelFinanciacion();
        n2.setDescripcion("Provincia");
        NivelFinanciacionFacade.getInstance().alta(n2);

        Ministerio m2 = new Ministerio();
        m2.setDescripcion("Cooperados");
        Agencia ad = new Agencia();
        ad.setDescripcion("COFECYT");
        List<Agencia> lista3 = new ArrayList<>();
        lista3.add(ad);
        m2.setAgencias(lista3);
//
//        OtrasAgencias dso = new OtrasAgencias();
//        dso.setDescripcion("ASETUR");
        OtrasAgencias aso = new OtrasAgencias();
        aso.setDescripcion("DETEM");
        OtrasAgencias w = new OtrasAgencias();
        w.setDescripcion("PFIP");
//        OtrasAgencias oe = new OtrasAgencias();
//        oe.setDescripcion("PME");

        List<OtrasAgencias> lista4 = new ArrayList<>();
     //   lista4.add(dso);
        lista4.add(aso);
        lista4.add(w);
       // lista4.add(oe);
        ad.setOtrasAgencias(lista4);
        NivelFinanciacion n4 = new NivelFinanciacion();
        List<Ministerio> l = new ArrayList<>();
        l.add(m2);
        n4.setMinisterios(l);
        n4.setDescripcion("Cooperados");

        NivelFinanciacionFacade.getInstance().alta(n4);

    }

}
