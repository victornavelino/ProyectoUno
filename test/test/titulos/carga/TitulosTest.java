/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.titulos.carga;

import entidades.Institucion;
import entidades.titulo.TituloGrado;
import entidades.titulo.TituloOtro;
import entidades.titulo.TituloPosgrado;
import facade.InstitucionFacade;
import facade.TituloFacade;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author franco
 */
public class TitulosTest {

    public TitulosTest() {
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
    public void carga() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://10.20.1.40/secyt", "secyt", "102030nose");
            Statement st = conexion.createStatement();
            
            
            ResultSet rs = st.executeQuery("SELECT DISTINCT institucion FROM titulos");


            while (rs.next()) {
                System.out.println("institucion = " + rs.getObject("institucion"));
                Institucion institucion = new Institucion();
                institucion.setDescripcion(rs.getObject("institucion").toString());
                InstitucionFacade.getInstance().alta(institucion);
            }
            rs.close();
//            
//            /*
//             * PREGRADO
//             */
//            ResultSet rs = st.executeQuery("SELECT DISTINCT  titulo FROM titulos WHERE nivel = 'PREGRADO'");
//
//
//            while (rs.next()) {
//                System.out.println("titulo pregrado = " + rs.getObject("titulo"));
//                TituloOtro tituloOtro = new TituloOtro();
//                tituloOtro.setDescripcion(rs.getObject("titulo").toString());
//                new TituloFacade().alta(tituloOtro);
//            }
//            rs.close();
//            /*
//             * GRADO
//             */
//            rs = st.executeQuery("SELECT DISTINCT titulo FROM titulos WHERE nivel = 'GRADO'");
//
//
//            while (rs.next()) {
//                System.out.println("titulo grado = " + rs.getObject("titulo"));
//                TituloGrado tituloGrado = new TituloGrado();
//                tituloGrado.setDescripcion(rs.getObject("titulo").toString());
//                new TituloFacade().alta(tituloGrado);
//
//            }
//            rs.close();
//            /*
//             * POSGRADO
//             */
//
//            rs = st.executeQuery("SELECT DISTINCT titulo FROM titulos WHERE nivel = 'POSGRADO'");
//
//
//            while (rs.next()) {
//                System.out.println("titulo posgrado = " + rs.getObject("titulo"));
//                TituloPosgrado tituloPosgrado = new TituloPosgrado();
//                tituloPosgrado.setDescripcion(rs.getObject("titulo").toString());
//                new TituloFacade().alta(tituloPosgrado);
//
//            }
//            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(TitulosTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TitulosTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
