/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author ruben
 */
public class ImportarRendicion {
    
    private static ImportarRendicion instancia=null;
    private RendicionPfipDetem rendicioncuenta;
    private ResumenRendicion resumen;
    private RecursosHumanos recursoshumanos;
    private Otros otros;
    private MaterialesInsumos materiales;
    private Consultorias consultorias;
    private BienesDeCapital bienesdecapital;
    private List<ResumenRendicion> listaresumen=new ArrayList();
    private List<RecursosHumanos> listarecursoshumanos=new ArrayList();
    private List<Otros> listaotros=new ArrayList();
    private List<MaterialesInsumos> listamateriales=new ArrayList();
    private List<Consultorias> listaconsultorias=new ArrayList();
    private List<BienesDeCapital> listabienes=new ArrayList();
    private Workbook workbook;
    private Sheet sheet;

    private synchronized static void createInstance() {
        if(instancia==null){
            instancia=new ImportarRendicion();
        }
    }
    
    protected ImportarRendicion(){
        
    }
    
    public static ImportarRendicion getInstance(){
        if(instancia==null){
            createInstance();
        }
        return instancia;
    }
    
    public RendicionPfipDetem getRendicionPfipDetem(){
        return rendicioncuenta;
    }
    
    public void importar(String rutaArchivo){
        boolean flag=true;
        InputStream in=null;
        try {
            in = new FileInputStream(rutaArchivo);
           
                workbook= Workbook.getWorkbook(in);
                sheet=workbook.getSheet(0);
                System.out.println(sheet.getRows());
                System.out.println(rutaArchivo);
                rendicioncuenta= new RendicionPfipDetem();
                Cell celdat= sheet.getCell(0  , 2);
                rendicioncuenta.setTitulo(celdat.getContents());
                Cell celdad= sheet.getCell(0 , 3);
                rendicioncuenta.setDescripcion(celdad.getContents());                
                importarResumen();
                rendicioncuenta.setResumen(listaresumen);
                importarRecursosHumanos();
                rendicioncuenta.setRecursoshumanos(listarecursoshumanos);
                importarOtros();
                rendicioncuenta.setOtros(listaotros);
                importarMateriales();
                rendicioncuenta.setMateriales(listamateriales);
                importarConsultorias();
                rendicioncuenta.setConsultorias(listaconsultorias);
                importarBienesDeCapital();
                rendicioncuenta.setBienesdecapital(listabienes);      
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
                flag=false;
            } catch (BiffException ex) {
                JOptionPane.showMessageDialog(null, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
                flag=false;
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Problemas con el archivo, revisar excel", "Error", JOptionPane.ERROR_MESSAGE);
                flag=false;
            }finally{
            try {
                in.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        if(flag)
        JOptionPane.showMessageDialog(null, "Carga exitosa", "carga", JOptionPane.INFORMATION_MESSAGE);
       
    }

    private void importarResumen() {
        for(int i=10 ; i<sheet.getRows() ; i++){
                    resumen= new ResumenRendicion();
                    Cell celda1= sheet.getCell(1 , i);
                    resumen.setNumeroplanilla(celda1.getContents());                   
                    Cell celda2= sheet.getCell(2 , i);                    
                    resumen.setRubro(celda2.getContents());                  
                    Cell celda3= sheet.getCell(3 , i);
                    if(!celda3.getContents().equals(""))
                    resumen.setTotal(Float.valueOf(quitarComas(celda3.getContents())));                                       
                    Cell celda4=sheet.getCell(4 , i);
                    if(!celda4.getContents().equals(""))
                    resumen.setAfinanciarSCTIP(Float.valueOf(quitarComas(celda4.getContents())));                                        
                    listaresumen.add(resumen);
                }
    }
    
    private String quitarComas(String entrada){
        String aux=entrada.replace(""+"$"+"", "");
        String d = aux.replace(".","");
        String e=d.replace(",", ".");
        //String f=e.trim();
        //if(f.contains("$")){
        
            String aux1=e.replace('"', ' ');
            String aux2=aux1.trim();
          //  return aux2;
        //}
             
        return aux2;
    }

    private void importarRecursosHumanos() {
        sheet=workbook.getSheet(2);
        for(int i=10 ; i<sheet.getRows() ; i++){
                    recursoshumanos= new RecursosHumanos();
                    Cell celda1= sheet.getCell(2 , i);
                    recursoshumanos.setProveedor(celda1.getContents());                   
                    Cell celda2= sheet.getCell(3 , i);                    
                    recursoshumanos.setConcepto(celda2.getContents());                  
                    Cell celda3= sheet.getCell(4 , i);                    
                    recursoshumanos.setNumerocomprobante(celda3.getContents());                                       
                    Cell celda4=sheet.getCell(5 , i);                    
                    recursoshumanos.setFecha(celda4.getContents());               
                    Cell celda5=sheet.getCell(6 , i);
                    if(!celda5.getContents().equals(""))
                    recursoshumanos.setTotal(Float.valueOf(quitarComas(celda5.getContents())));
                    Cell celda6=sheet.getCell(7 , i);
                    if(!celda6.getContents().equals(""))
                    recursoshumanos.setFinanciado(Float.valueOf(quitarComas(celda6.getContents())));                  
                    listarecursoshumanos.add(recursoshumanos);
                }
    }

    private void importarOtros() {
        sheet=workbook.getSheet(5);
        for(int i=10 ; i<sheet.getRows() ; i++){
                    otros= new Otros();
                    Cell celda1= sheet.getCell(2 , i);
                    otros.setProveedor(celda1.getContents());                   
                    Cell celda2= sheet.getCell(3 , i);                    
                    otros.setConcepto(celda2.getContents());                  
                    Cell celda3= sheet.getCell(4 , i);                    
                    otros.setNumerocomprobante(celda3.getContents());                                       
                    Cell celda4=sheet.getCell(5 , i);                    
                    otros.setFecha(celda4.getContents());               
                    Cell celda5=sheet.getCell(6 , i);
                    if(!celda5.getContents().equals(""))
                    otros.setTotal(Float.valueOf(quitarComas(celda5.getContents())));
                    Cell celda6=sheet.getCell(7 , i);
                    if(!celda6.getContents().equals(""))
                    otros.setFinanciado(Float.valueOf(quitarComas(celda6.getContents())));                  
                    listaotros.add(otros);
                }
    }

    private void importarMateriales() {
        sheet=workbook.getSheet(4);
        for(int i=10 ; i<sheet.getRows() ; i++){
                    materiales= new MaterialesInsumos();
                    Cell celda1= sheet.getCell(1 , i);
                    materiales.setProveedor(celda1.getContents());                   
                    Cell celda2= sheet.getCell(2 , i);                    
                    materiales.setConcepto(celda2.getContents());                  
                    Cell celda3= sheet.getCell(3 , i);                    
                    materiales.setNumerocomprobante(celda3.getContents());                                       
                    Cell celda4=sheet.getCell(4 , i);                    
                    materiales.setFecha(celda4.getContents());               
                    Cell celda5=sheet.getCell(5 , i);
                    if(!celda5.getContents().equals(""))
                    materiales.setTotal(Float.valueOf(quitarComas(celda5.getContents())));
                    Cell celda6=sheet.getCell(6 , i);
                    if(!celda6.getContents().equals(""))
                    materiales.setFinanciado(Float.valueOf(quitarComas(celda6.getContents())));                  
                    listamateriales.add(materiales);
                }
    }

    private void importarConsultorias() {
        sheet=workbook.getSheet(3);
        for(int i=10 ; i<sheet.getRows() ; i++){
                    consultorias= new Consultorias();
                    Cell celda1= sheet.getCell(1 , i);
                    consultorias.setProveedor(celda1.getContents());                   
                    Cell celda2= sheet.getCell(2 , i);                    
                    consultorias.setConcepto(celda2.getContents());                  
                    Cell celda3= sheet.getCell(3 , i);                    
                    consultorias.setNumerocomprobante(celda3.getContents());                                       
                    Cell celda4=sheet.getCell(4 , i);                    
                    consultorias.setFecha(celda4.getContents());               
                    Cell celda5=sheet.getCell(5 , i);
                    if(!celda5.getContents().equals(""))
                    consultorias.setTotal(Float.valueOf(quitarComas(celda5.getContents())));
                    Cell celda6=sheet.getCell(6 , i);
                    if(!celda6.getContents().equals(""))
                    consultorias.setFinanciado(Float.valueOf(quitarComas(celda6.getContents())));                  
                    listaconsultorias.add(consultorias);
                }
    }

    private void importarBienesDeCapital() {
        sheet=workbook.getSheet(1);
        for(int i=10 ; i<sheet.getRows() ; i++){
                    bienesdecapital= new BienesDeCapital();
                    Cell celda1= sheet.getCell(2 , i);
                    bienesdecapital.setProveedor(celda1.getContents());                   
                    Cell celda2= sheet.getCell(3 , i);                    
                    bienesdecapital.setConcepto(celda2.getContents());                  
                    Cell celda3= sheet.getCell(4 , i);                    
                    bienesdecapital.setNumerocomprobante(celda3.getContents());                                       
                    Cell celda4=sheet.getCell(5 , i);                    
                    bienesdecapital.setFecha(celda4.getContents());               
                    Cell celda5=sheet.getCell(6 , i);
                    if(!celda5.getContents().equals(""))
                    bienesdecapital.setTotal(Float.valueOf(quitarComas(celda5.getContents())));
                    Cell celda6=sheet.getCell(7 , i);
                    if(!celda6.getContents().equals(""))
                    bienesdecapital.setFinanciado(Float.valueOf(quitarComas(celda6.getContents())));                  
                    listabienes.add(bienesdecapital);
                }
    }
}
