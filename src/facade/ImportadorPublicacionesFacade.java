/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import includes.ModeloTablaNoEditable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author franco
 */
public class ImportadorPublicacionesFacade {

    private static ImportadorPublicacionesFacade instance = null;
    JFileChooser fileChooser = new JFileChooser();
    File fichero;
    File carpeta;
    private int BUFFER = 1;

    protected ImportadorPublicacionesFacade() {
    }

    public static ImportadorPublicacionesFacade getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ImportadorPublicacionesFacade();
        }
    }

//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public File seleccionarArchivo() {
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Archivos ZIP de WINSIP", "zip", "ZIP");
        fileChooser.setFileFilter(filtroImagen);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            fichero = fileChooser.getSelectedFile();

        }
        return fichero;
    }

    public List<List<Object>> leerZip(String archivejo) {
        List<List<Object>> leerDBF = new ArrayList<>();
        try {
//InputStream a partir del archivo ZIP a leer

            FileInputStream fis = new FileInputStream(fichero.getAbsolutePath());
//Obtenemos el checksum
            CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
            try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum))) {
                ZipEntry entry;
                //Iteramos por el contenido del ZIP
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().equals(archivejo)) { //hice el ejemplo solo para uno.. 
                        System.out.println("Extrayendo del ZIP: " + entry);
                        int count;
                        byte data[] = new byte[BUFFER];
                        // Escribimos los archivos en la ubicación deseada
                        File archivo = File.createTempFile("tmp", entry.getName());
                        archivo.deleteOnExit();
                        FileOutputStream fos = new FileOutputStream(archivo);
                        try (BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER)) {
                            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                                dest.write(data, 0, count);
                            }
                            dest.flush();
                        }
                        leerDBF = leerDBF(archivo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return leerDBF;
    }

    private List<List<Object>> leerDBF(File archivo) {
        List<Object> registros = new ArrayList<>();
        List<List<Object>> matriz = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(archivo)) {
            DBFReader reader = new DBFReader(inputStream);
            // get the field count if you want for some reasons like the following //
            int numberOfFields = reader.getFieldCount(); // use this count to fetch all field information //             if required // 
            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i); // do something with it if you want // refer the JavaDoc API reference for more details // 
                registros.add(field.getName());
                //nombre campo
            }
            matriz.add(registros);
            // Now, lets us start reading the rows //
            Object[] rowObjects;
            while ((rowObjects = reader.nextRecord()) != null) {
                registros = new ArrayList<>();
                for (int i = 0; i < rowObjects.length; i++) {
                    registros.add(rowObjects[i]);
                }
                matriz.add(registros);
            }

        } catch (DBFException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return matriz;

    }
}
