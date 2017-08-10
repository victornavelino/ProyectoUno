/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.picto;

import facade.ConexionFacade;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author ruben
 */
public class ImportarExcel {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoUnoPU", ConexionFacade.PROPIEDADES);
    private static ImportarExcel instancia = null;
    private RendicionDeCuentas rendiciondecuentas;
    private Resumen resumen;
    private Honorarios honorarios;
    private Beca becas;
    private Equipamiento equipamiento;
    private MaterialesEInsumos materialeseinsumos;
    private ViajesViaticos viajesyviaticos;
    private Tecnicos tecnicos;
    private Publicaciones publicaciones;
    private AdministracionSubsidio administracion;
    private Bibliografia bibliografia;
    private List<Resumen> listaresumen = new ArrayList();
    private List<Honorarios> listahonorarios = new ArrayList();
    private List<Beca> listabeca = new ArrayList();
    private List<Equipamiento> listaequipamiento = new ArrayList();
    private List<MaterialesEInsumos> listamateriales = new ArrayList();
    private List<ViajesViaticos> listaviajesviaticos = new ArrayList();
    private List<Tecnicos> listatecnicos = new ArrayList();
    private List<Publicaciones> listapublicaciones = new ArrayList();
    private List<AdministracionSubsidio> listaadministracion = new ArrayList();
    private List<Bibliografia> listabibliografia = new ArrayList();
    private Workbook workbook;
    private Sheet sheet;

    protected ImportarExcel() {

    }

    public static ImportarExcel getInstance() {
        if (instancia == null) {
            createInstance();
        }
        return instancia;
    }

    private synchronized static void createInstance() {
        if (instancia == null) {
            instancia = new ImportarExcel();
        }
    }

    public RendicionDeCuentas getRendicionDeCuentas() {
        return rendiciondecuentas;
    }

    public void importar(String rutaArchivo) {
        boolean flag = true;
        InputStream in = null;
        try {
            in = new FileInputStream(rutaArchivo);

            workbook = Workbook.getWorkbook(in);
            sheet = workbook.getSheet(0);
            System.out.println(sheet.getRows());
            System.out.println(rutaArchivo);
            rendiciondecuentas = new RendicionDeCuentas();
            Cell celdat = sheet.getCell(0, 0);
            rendiciondecuentas.setTitulo(celdat.getContents());
            Cell celdad = sheet.getCell(2, 0);
            rendiciondecuentas.setDescripcion(celdad.getContents());
            Cell celdac = sheet.getCell(0, 1);
            rendiciondecuentas.setCodigo(celdac.getContents());
            importarResumen();
            rendiciondecuentas.setResumen(listaresumen);
            importarHonorarios();
            rendiciondecuentas.setHonorarios(listahonorarios);
            importarBecas();
            rendiciondecuentas.setBecas(listabeca);
            importarEquipamiento();
            rendiciondecuentas.setEquipamiento(listaequipamiento);
            importarMaterialesEInsumos();
            rendiciondecuentas.setMaterialeseinsumos(listamateriales);
            importarViajesViaticos();
            rendiciondecuentas.setViajesviaticos(listaviajesviaticos);
            importarTecnicos();
            rendiciondecuentas.setServiciostecnicos(listatecnicos);
            importarPublicaciones();
            rendiciondecuentas.setPublicaciones(listapublicaciones);
            importarAdministracionSubsidio();
            rendiciondecuentas.setGastosadministracion(listaadministracion);
            importarBibliografia();
            rendiciondecuentas.setBibliografia(listabibliografia);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
            flag = false;
        } catch (BiffException ex) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
            flag = false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
            flag = false;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        if (flag) {
            JOptionPane.showMessageDialog(null, "Carga exitosa", "carga", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void importarResumen() {
        for (int i = 8; i < sheet.getRows(); i++) {
            resumen = new Resumen();
            Cell celda1 = sheet.getCell(0, i);
            resumen.setItem(celda1.getContents());
            Cell celda2 = sheet.getCell(1, i);
            if (!celda2.getContents().equals("")) {
                resumen.setAportefoncyt(Float.valueOf(quitarComas(celda2.getContents())));
            }
            Cell celda3 = sheet.getCell(2, i);
            if (!celda3.getContents().equals("")) {
                resumen.setAporteinstituciones(Float.valueOf(quitarComas(celda3.getContents())));
            }
            Cell celda4 = sheet.getCell(3, i);
            if (!celda4.getContents().equals("")) {
                resumen.setOtrosaportes(Float.valueOf(quitarComas(celda4.getContents())));
            }
            Cell celda5 = sheet.getCell(4, i);
            if (!celda5.getContents().equals("")) {
                resumen.setTotal(Float.valueOf(quitarComas(celda5.getContents())));
            }
            listaresumen.add(resumen);
        }
    }

    private void importarHonorarios() {
        sheet = workbook.getSheet(1);
        for (int i = 8; i < sheet.getRows(); i++) {
            honorarios = new Honorarios();
            Cell celda1 = sheet.getCell(1, i);
            honorarios.setNombreyapellido(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            honorarios.setFecha(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            if (!celda3.getContents().equals("")) {
                honorarios.setAporteinstitucion(Float.valueOf(quitarComas(celda3.getContents())));
            }
            Cell celda4 = sheet.getCell(4, i);
            if (!celda4.getContents().equals("")) {
                honorarios.setOtrosaportes(Float.valueOf(quitarComas(celda4.getContents())));
            }
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                honorarios.setTotal(Float.valueOf(quitarComas(celda5.getContents())));
            }
            listahonorarios.add(honorarios);
        }
    }

    private void importarBecas() {
        sheet = workbook.getSheet(2);
        for (int i = 8; i < sheet.getRows(); i++) {
            becas = new Beca();
            Cell celda1 = sheet.getCell(1, i);
            becas.setNombreapellido(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            becas.setFecha(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            if (!celda3.getContents().equals("")) {
                becas.setAportefoncyt(Float.valueOf(quitarComas(celda3.getContents())));
            }
            Cell celda4 = sheet.getCell(4, i);
            if (!celda4.getContents().equals("")) {
                becas.setAporteinstitucion(Float.valueOf(quitarComas(celda4.getContents())));
            }
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                becas.setOtrosaportes(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                becas.setTotal(Float.valueOf(quitarComas(celda6.getContents())));
            }
//                    System.out.println(celda6.getContents());
//                     System.out.println(quitarComas(celda6.getContents()));
            listabeca.add(becas);
        }
    }

    private void importarEquipamiento() {
        sheet = workbook.getSheet(3);
        for (int i = 8; i < sheet.getRows(); i++) {
            equipamiento = new Equipamiento();
            Cell celda1 = sheet.getCell(1, i);
            equipamiento.setDatosequipamiento(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            equipamiento.setDatosproveedor(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            equipamiento.setNumerofactura(celda3.getContents());
            Cell celda4 = sheet.getCell(4, i);
            equipamiento.setFecha(celda4.getContents());
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                equipamiento.setAportefoncyt(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                equipamiento.setAporteinstitucion(Float.valueOf(quitarComas(celda6.getContents())));
            }
            Cell celda7 = sheet.getCell(7, i);
            if (!celda7.getContents().equals("")) {
                equipamiento.setOtrosaportes(Float.valueOf(quitarComas(celda7.getContents())));
            }
            listaequipamiento.add(equipamiento);
        }
    }

    private void importarMaterialesEInsumos() {
        sheet = workbook.getSheet(4);
        for (int i = 8; i < sheet.getRows(); i++) {
            materialeseinsumos = new MaterialesEInsumos();
            Cell celda1 = sheet.getCell(1, i);
            materialeseinsumos.setDescripcion(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            materialeseinsumos.setDatosproveedor(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            materialeseinsumos.setNumerofactura(celda3.getContents());
            Cell celda4 = sheet.getCell(4, i);
            materialeseinsumos.setFecha(celda4.getContents());
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                materialeseinsumos.setAportefoncyt(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                materialeseinsumos.setAporteinstitucion(Float.valueOf(quitarComas(celda6.getContents())));
            }
            Cell celda7 = sheet.getCell(7, i);
            if (!celda7.getContents().equals("")) {
                materialeseinsumos.setOtrosaportes(Float.valueOf(quitarComas(celda7.getContents())));
            }
            listamateriales.add(materialeseinsumos);
        }
    }

    private void importarViajesViaticos() {
        sheet = workbook.getSheet(5);
        for (int i = 8; i < sheet.getRows(); i++) {
            viajesyviaticos = new ViajesViaticos();
            Cell celda1 = sheet.getCell(1, i);
            viajesyviaticos.setNombreapellido(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            viajesyviaticos.setDestino(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            viajesyviaticos.setFecha(celda3.getContents());
            Cell celda4 = sheet.getCell(4, i);
            if (!celda4.getContents().equals("")) {
                viajesyviaticos.setAportefoncyt(Float.valueOf(quitarComas(celda4.getContents())));
            }
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                viajesyviaticos.setAporteinstitucion(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                viajesyviaticos.setOtrosaportes(Float.valueOf(quitarComas(celda6.getContents())));
            }
            listaviajesviaticos.add(viajesyviaticos);
        }
    }

    private void importarTecnicos() {
        sheet = workbook.getSheet(6);
        for (int i = 8; i < sheet.getRows(); i++) {
            tecnicos = new Tecnicos();
            Cell celda1 = sheet.getCell(1, i);
            tecnicos.setDescripcion(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            tecnicos.setDatosproveedor(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            tecnicos.setNumerofactura(celda3.getContents());
            Cell celda4 = sheet.getCell(4, i);
            tecnicos.setFecha(celda4.getContents());
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                tecnicos.setAportefoncyt(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                tecnicos.setAporteinstitucion(Float.valueOf(quitarComas(celda6.getContents())));
            }
            Cell celda7 = sheet.getCell(7, i);
            if (!celda7.getContents().equals("")) {
                tecnicos.setOtrosaportes(Float.valueOf(quitarComas(celda7.getContents())));
            }
            listatecnicos.add(tecnicos);
        }
    }

    private void importarPublicaciones() {
        sheet = workbook.getSheet(7);
        for (int i = 8; i < sheet.getRows(); i++) {
            publicaciones = new Publicaciones();
            Cell celda1 = sheet.getCell(1, i);
            publicaciones.setDescripcion(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            publicaciones.setDatosproveedor(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            publicaciones.setNumerofactura(celda3.getContents());
            Cell celda4 = sheet.getCell(4, i);
            publicaciones.setFecha(celda4.getContents());
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                publicaciones.setAportefoncyt(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                publicaciones.setAporteinstitucion(Float.valueOf(quitarComas(celda6.getContents())));
            }
            Cell celda7 = sheet.getCell(7, i);
            if (!celda7.getContents().equals("")) {
                publicaciones.setOtrosaportes(Float.valueOf(quitarComas(celda7.getContents())));
            }
            listapublicaciones.add(publicaciones);
        }
    }

    private void importarAdministracionSubsidio() {
        sheet = workbook.getSheet(8);
        for (int i = 8; i < sheet.getRows(); i++) {
            administracion = new AdministracionSubsidio();
            Cell celda1 = sheet.getCell(1, i);
            administracion.setNumeroproyecto(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            administracion.setIb(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            administracion.setNumerofactura(celda3.getContents());
            Cell celda4 = sheet.getCell(4, i);
            administracion.setFecha(celda4.getContents());
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                administracion.setAportefoncyt(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                administracion.setTotal(Float.valueOf(quitarComas(celda6.getContents())));
            }
            listaadministracion.add(administracion);
        }
    }

    private void importarBibliografia() {
        sheet = workbook.getSheet(9);
        for (int i = 8; i < sheet.getRows(); i++) {
            bibliografia = new Bibliografia();
            Cell celda1 = sheet.getCell(1, i);
            bibliografia.setDescripcion(celda1.getContents());
            Cell celda2 = sheet.getCell(2, i);
            bibliografia.setDatosproveedor(celda2.getContents());
            Cell celda3 = sheet.getCell(3, i);
            bibliografia.setNumerofactura(celda3.getContents());
            Cell celda4 = sheet.getCell(4, i);
            bibliografia.setFecha(celda4.getContents());
            Cell celda5 = sheet.getCell(5, i);
            if (!celda5.getContents().equals("")) {
                bibliografia.setAportefoncyt(Float.valueOf(quitarComas(celda5.getContents())));
            }
            Cell celda6 = sheet.getCell(6, i);
            if (!celda6.getContents().equals("")) {
                bibliografia.setAporteinstitucion(Float.valueOf(quitarComas(celda6.getContents())));
            }
            Cell celda7 = sheet.getCell(7, i);
            if (!celda7.getContents().equals("")) {
                bibliografia.setOtrosaportes(Float.valueOf(quitarComas(celda7.getContents())));
            }
            Cell celda8 = sheet.getCell(8, i);
            if (!celda8.getContents().equals("")) {
                bibliografia.setTotal(Float.valueOf(quitarComas(celda8.getContents())));
            }
            listabibliografia.add(bibliografia);
        }
    }

    public String quitarComas(String entrada) {
        String aux = entrada.replace("" + "$" + "", "");
        String d = aux.replace(".", "");
        String e = d.replace(",", ".");
        //String f=e.trim();
        //if(f.contains("$")){

        String aux1 = e.replace('"', ' ');
        String aux2 = aux1.trim();
        //  return aux2;
        //}

        return aux2;
    }

}
