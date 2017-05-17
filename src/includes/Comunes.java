/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package includes;

import facade.ConexionFacade;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author carlos Esta clase contine los metodos comunes a las clases
 */
public class Comunes {

    public static String calcularHost() throws UnknownHostException {
        InetAddress addres = Inet4Address.getLocalHost();
        String dir = addres.getHostName();
        return dir;
    }

    public static String calcularFechaHora() {
        SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH:mm:ss", new Locale("es_ES"));
        Date fechaDate = Comunes.obtenerFechaActualDesdeDB();
        String fecha = formateador.format(fechaDate);
        return fecha;
    }

    /**
     * Añadir dias/meses/años,... a un date
     *
     * @param _date
     * @param _field
     * @param _amount
     * @return Date
     */
    public static Date addDate(java.util.Date _date, int _field, int _amount) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(_date);
        fecha.add(_field, _amount);
        return fecha.getTime();
    }

    public static Date restarDate(java.util.Date _date, int _field, int _amount) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(_date);
        fecha.add(_field, -_amount);
        return fecha.getTime();
    }

    public static String mayusculizar(JTextField textField) {
        textField.setText(textField.getText().toUpperCase());
        return textField.getText();
    }

    public static boolean validarTextFieldMail(JTextField textField) {
        EmailValidator validadorEmail = EmailValidator.getInstance();
        return validadorEmail.isValid(textField.getText());
    }

    public static boolean validarTextFieldCuit(JTextField textField) {
        boolean flag = false;
        Pattern patron = Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9]" + "[0-9][0-9][0-9][0-9]");
        Matcher encaja = patron.matcher(textField.getText());
        if (encaja.matches()) {
            flag = true;
        } else {
            patron = Pattern.compile("[0-9][0-9]-[0-9][0-9][0-9][0-9][0-9]" + "[0-9][0-9][0-9]-[0-9]");
            encaja = patron.matcher(textField.getText());
            if (encaja.matches()) {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean validarEmail(JTextField textField) {
        String patron = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(textField.getText());
        return matcher.matches();

    }

    public static boolean validarEmail(String text) {
        String patron = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();

    }

    public static String colocarGuionesAlCuit(JTextField textField) {
        String salida = "";
        Pattern patron = Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9]" + "[0-9][0-9][0-9][0-9]");
        Matcher encaja = patron.matcher(textField.getText());
        if (encaja.matches()) {
            salida += textField.getText().substring(0, 2) + "-";
            salida += textField.getText().substring(2, 10) + "-";
            salida += textField.getText().substring(10);
        } else {
            patron = Pattern.compile("[0-9][0-9]-[0-9][0-9][0-9][0-9][0-9]" + "[0-9][0-9][0-9]-[0-9]");
            encaja = patron.matcher(textField.getText());
            if (encaja.matches()) {
                salida += textField.getText();
            }
        }
        return salida;
    }

    public static boolean validarTextFieldDni(JTextField textField) {
        boolean flag = false;
        Pattern patron = Pattern.compile("[0-9]{6,9}");
        Matcher encaja = patron.matcher(textField.getText());
        if (encaja.matches()) {
            flag = true;
            System.out.println("Con " + textField.getText().length() + " digitos");
        } else {
            patron = Pattern.compile("[0-9][0-9].[0-9][0-9][0-9].[0-9][0-9]" + "[0-9]");
            encaja = patron.matcher(textField.getText());
            if (encaja.matches()) {
                flag = true;
            } else {
                patron = Pattern.compile("[0-9].[0-9][0-9][0-9].[0-9][0-9]" + "[0-9]");
                encaja = patron.matcher(textField.getText());
                if (encaja.matches()) {
                    flag = true;
                } else {
                    patron = Pattern.compile("[0-9][0-9][0-9].[0-9][0-9]" + "[0-9]");
                    encaja = patron.matcher(textField.getText());
                    if (encaja.matches()) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public static String colocarPuntosAlDni(JTextField textField) {
        String salida = "";
        Pattern patron = Pattern.compile("[0-9]{8}");
        Matcher encaja = patron.matcher(textField.getText());
        if (encaja.matches()) {
            salida += textField.getText().substring(0, 2) + ".";
            salida += textField.getText().substring(2, 5) + ".";
            salida += textField.getText().substring(5);
            System.out.println("Adentro!!! " + textField.getText());
        } else {
            patron = Pattern.compile("[0-9]{9}");
            encaja = patron.matcher(textField.getText());
            if (encaja.matches()) {
                salida += textField.getText().substring(0, 3) + ".";
                salida += textField.getText().substring(3, 6) + ".";
                salida += textField.getText().substring(6);
                System.out.println("Adentro!!! " + textField.getText());
            } else {
                patron = Pattern.compile("[0-9][0-9].[0-9][0-9][0-9].[0-9][0-9]" + "[0-9]");
                encaja = patron.matcher(textField.getText());
                if (encaja.matches()) {
                    salida += textField.getText();
                } else {
                    patron = Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9]" + "[0-9]");
                    encaja = patron.matcher(textField.getText());
                    if (encaja.matches()) {
                        salida += textField.getText().substring(0, 1) + ".";
                        salida += textField.getText().substring(1, 4) + ".";
                        salida += textField.getText().substring(4);
                        System.out.println("Adentro!!! " + textField.getText());
                    } else {
                        patron = Pattern.compile("[0-9].[0-9][0-9][0-9].[0-9][0-9]" + "[0-9]");
                        encaja = patron.matcher(textField.getText());
                        if (encaja.matches()) {
                            salida += textField.getText();
                        } else {
                            patron = Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9]");
                            encaja = patron.matcher(textField.getText());
                            if (encaja.matches()) {
                                salida += textField.getText().substring(0, 3) + ".";
                                salida += textField.getText().substring(3);
                                System.out.println("Adentro!!! " + textField.getText());
                            } else {
                                patron = Pattern.compile("[0-9][0-9][0-9].[0-9][0-9]" + "[0-9]");
                                encaja = patron.matcher(textField.getText());
                                if (encaja.matches()) {
                                    salida += textField.getText();
                                }
                            }
                        }
                    }
                }
            }
        }
        return salida;
    }

    public static Long validarStringALong(String valor) {
        long valorDevuelto = (long) 0.0;
        try {
            valorDevuelto = Long.parseLong(valor);
            return valorDevuelto;
        } catch (NumberFormatException nfe) {
            return valorDevuelto;
        }
    }

    public static Integer validarStringAInt(String valor) {
        Integer valorDevuelto = (Integer) 0;
        try {
            valorDevuelto = Integer.parseInt(valor);
            return valorDevuelto;
        } catch (NumberFormatException nfe) {
            return valorDevuelto;
        }
    }

    public static boolean validarInteger(String cadena) {
        IntegerValidator integerValidator = IntegerValidator.getInstance();
        return integerValidator.isValid(cadena);
    }

    public static boolean validarBigDecimal(String cadena) {
        BigDecimalValidator bigDecimalValidator = BigDecimalValidator.getInstance();
        return bigDecimalValidator.isValid(cadena, Locale.US);
    }

    public static Date obtenerFechaActualDesdeDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("SELECT NOW()");
        return (Date) q.getSingleResult();
    }

    public static Date getFechaSinHora(Date date) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        return calendario.getTime();
    }

    public static Point centrarDialog(JDialog diag) {
        Point punto = new Point();
        Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d2 = diag.getSize();
        punto.setLocation((int) ((d1.getWidth() / 2) - (d2.getWidth() / 2)),
                (int) ((d1.getHeight() / 2) - (d2.getHeight() / 2)));
        return punto;
    }

    public static int calcularEdad(Calendar fechaNac) {
        Calendar today = Calendar.getInstance();
        int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        //Si está en ese año pero todavía no los ha cumplido
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1; //no aparecían los dos guiones del postincremento :|
        }
        return diffYear;
    }

    public static String getExtension(File archivo) {
        String nombreConExtension = archivo.getName();
        int dotPos = nombreConExtension.lastIndexOf(".");
        String extension = nombreConExtension.substring(dotPos);
        return extension;
    }

    public static void cargarJList(JList jList, List lista) {
        DefaultListModel modeloLista = new DefaultListModel();
        for (Object objeto : lista) {
            modeloLista.addElement(objeto);
        }
        jList.setModel(modeloLista);
    }

    public static void cargarJCombo(JComboBox jCombo, List lista) {
        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        for (Object objeto : lista) {
            modeloCombo.addElement(objeto);
        }
        jCombo.setModel(modeloCombo);
    }

    public static void cargarJComboConBlanco(JComboBox jCombo, List lista) {
        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        modeloCombo.addElement("--Seleccione--");
        for (Object objeto : lista) {
            modeloCombo.addElement(objeto);
        }
        jCombo.setModel(modeloCombo);
    }

    public static void desactivarComponentesPanel(JPanel panel) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            panel.getComponent(i).setEnabled(false);
        }
    }

    public static void activarComponentesPanel(JPanel panel) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            panel.getComponent(i).setEnabled(true);
        }
    }

    public static void limitarTextField(JTextField textField, int maximo) {
        textField.setDocument(new limitarMaximoDocument(maximo));
    }

    public static void autoAjusteTextArea(JTextArea area) {
        // Para que se partan automáticamente las líneas al llegar al final 
        area.setLineWrap(true);

// Para que el partido se haga respetando las palabras. Sólo se parte la 
// línea en los espacios entre palabras. 
        area.setWrapStyleWord(true);
    }

    public static void ventanaCargando(final Object entidad, final String nombreMetodo, final String titulo, final Object[] parametros) {
        SwingWorker worker = new SwingWorker() {

            diagCargando cargando = new diagCargando(null, false);

            @Override
            protected Object doInBackground() throws Exception {
                cargando.setTitle(titulo);
                cargando.setAlwaysOnTop(true);
                cargando.pack();
                cargando.setLocation(100, 100);
                cargando.setVisible(true);
                Method method = entidad.getClass().getMethod(nombreMetodo);
                method.invoke(entidad, parametros);
                return true;
            }

            @Override
            protected void done() {
                try {

                    if (get().equals(Boolean.TRUE)) {
                        cargando.dispose();
                    }
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, " 1 Se ha producido un error, disculpe las molestias\n" + ex);
                } catch (ExecutionException ex) {
                    JOptionPane.showMessageDialog(null, " 2 Se ha producido un error, disculpe las molestias\n" + ex);

                }
            }
        };
        worker.execute();

    }

    public static void mensajeError(Object obj, String mensaje) {
        int opcion = JOptionPane.showOptionDialog(null, obj, mensaje, 2, JOptionPane.ERROR_MESSAGE, null, new String[]{"Cerrar Programa", "Copiar Error"}, null);
        switch (opcion) {
            case 0:
                System.exit(0);
                break;
            case 1:
                Clipboard portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection texto = new StringSelection(obj.toString());
                portapapeles.setContents(texto, texto);
                break;
        }
    }

    public static void validarjxdatepicker(JXDatePicker jxfecha) {
        jxfecha.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fech = sdf.format(jxfecha.getDate());
        if (!validarFecha(fech)) {
            JOptionPane.showMessageDialog(null, "Formato incorrecto!! debe ser DD/MM/AAAA ");
            jxfecha.setDate(new Date());

        }

    }

    public static boolean validarFecha(String fecha) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (fecha.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

        dateFormat.setLenient(false);

        try {
            dateFormat.parse(fecha.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static void formatearJXdatePicker(JXDatePicker datePicker) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        datePicker.setFormats(formato);
    }

    public static float formatodecimal(String texto) throws ParseException {

        float valor = 0;

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        //  try {
        DecimalFormat formateador = new DecimalFormat("###,###", simbolos);
        Number numero = formateador.parse(texto);
        valor = numero.floatValue();
//        } catch (ParseException e) {
//            JOptionPane.showMessageDialog(null, "Numeros mal formados", "Error", JOptionPane.ERROR_MESSAGE);
//        }//System.out.println(valor);
        // Estas dos líneas se puede abreviar con
        // double valor = formateador.parse(texto).doubleValue();

        return valor;

    }

    //permite ingresar solo numeros
    //soloNumeros(JTextField, evento de tecla)
    public static void soloNumeros(JTextField t, java.awt.event.KeyEvent evt) {
        char caracter = evt.getKeyChar();
        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) && caracter != '.') {
            evt.consume();  // ignorar el evento de teclado
        }
    }

    public static void soloNumerosSinComa(JTextField t, java.awt.event.KeyEvent evt) {
        char caracter = evt.getKeyChar();
        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }

    public static void ocultarColumnasJTable(JTable tbl, int columna[]) {
        for (int i = 0; i < columna.length; i++) {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setPreferredWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setResizable(false);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setPreferredWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setResizable(false);
        }
    }

//permite ingresar solo letras
//soloTexto(JTextField, evento de tecla)
    public static void soloTexto(JTextField t, java.awt.event.KeyEvent evt) {
        char caracter = evt.getKeyChar();
        //verificar que la tecla pulsada sea una letra
        if (!((caracter > 64 && caracter < 91) || (caracter > 96 && caracter < 123))) {
            evt.consume();  // ignorar el evento de teclado
        }
    }

//permite ingresar una cierta cantidad de caracteres
//cantidadCarateres(JTextField, evento, cant.caracteres a ingresar)
    public static void cantidadCarateres(JTextField t, java.awt.event.KeyEvent evt, int ca) {
        //contamos los caracteres escritos - no debe pasar de ca
        if (t.getText().length() == ca) {
            evt.consume();
            //JOptionPane.showMessageDialog(null, "sdfkjshsdhsdkj");
        }
    }

    public static void definirAnchoColumnas(JTable tabla, int[] anchos) {

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    public static void setEnableContainer(Container c, boolean band) {

        Component[] components = c.getComponents();
        c.setEnabled(band);
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(band);

            if (components[i] instanceof Container) {
                setEnableContainer((Container) components[i], band);
            }

        }
    }

    public static void setOcultarColumnasJTable(JTable tbl, int columna) {

        tbl.getColumnModel().getColumn(columna).setMaxWidth(0);
        tbl.getColumnModel().getColumn(columna).setMinWidth(0);
        tbl.getTableHeader().getColumnModel().getColumn(columna).setMaxWidth(0);
        tbl.getTableHeader().getColumnModel().getColumn(columna).setMinWidth(0);
    }

    public static void configurarTabla(JTable tbl) {
        JViewport scroll = (JViewport) tbl.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tbl.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = 1 / 100;
                    break;
                case 1:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    anchoColumna = (5 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
            tbl.setColumnModel(modeloColumna);
        }
        tbl.getTableHeader().setBackground(java.awt.Color.WHITE);
        tbl.getTableHeader().setForeground(Color.BLACK);
        tbl.getTableHeader().setFont(new java.awt.Font("Dialog",
                java.awt.Font.PLAIN, 14));
        //Si le queremos cambiar el tamaño a la tablita
        tbl.setFont(new java.awt.Font("Dialog",
                java.awt.Font.PLAIN, 14));
    }
}
