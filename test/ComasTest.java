/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Panchi
 */
public class ComasTest {

    public ComasTest() {
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
    public void hello() throws ParseException {

        String f = "3,555666";
        this.formatodecimal(f);
    }

    public float formatodecimal(String texto) throws ParseException {

        float valor = 0;

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        DecimalFormat formateador = new DecimalFormat("###,###", simbolos);
        Number numero = formateador.parse(texto);
        valor = numero.floatValue();
        System.out.println(valor);
        // Estas dos líneas se puede abreviar con
        // double valor = formateador.parse(texto).doubleValue();

        return valor;

    }
}
