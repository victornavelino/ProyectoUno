/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package includes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.XML;
/**
 *
 * @author penniser
 */
public class CvarWsClient {
    
    private String urlBase = "http://168.83.4.6:8080/services/curriculum/";
    
    
  public String getXml(String parametros) {
            String xml="";
	  try {
 
		URL url = new URL(urlBase+parametros);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		//conn.setRequestProperty("Accept", "application/json");
 
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		String output="";
		System.out.println("Output from Server .... \n");
		output = br.readLine();
                System.out.println(output);
                if(output.equals("[]")){
                    System.out.println("salida array vacio"+xml);
                    return xml;
                }else if(output.startsWith("[{")){
                    JSONArray json = new JSONArray(output);
                    xml = XML.toString(json);
                    xml = xml.replace("<array>", "").replace("</array>", "");
                    System.out.println("salida array "+xml);
                }else{
                    JSONObject json = new JSONObject(output);
                    xml = XML.toString(json);
                    System.out.println("salida objeto"+xml);
                }
                System.out.println(xml);
		conn.disconnect();
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  } catch (JSONException ex) {
            Logger.getLogger(CvarWsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          return xml;
 
	}
  
  public String consultarIdentificacion(String parametros){
      String xml = getXml(parametros);
      xml = "<identificacion>"+xml+"</identificacion>";
      return xml;
  }
  
  public String consultarDireccionResidencial(String parametros){
      String xml = getXml(parametros);
      xml = "<direccion>"+xml+"</direccion>";
      return xml;
  }
  
  public String armarDatosPersonales(String cuil){
      String identificacion = consultarIdentificacion("datosPersonales/identificacion/"+cuil);
      String  direccion = consultarDireccionResidencial("datosPersonales/direccionResidencial/"+cuil);
      return "<datosPersonales>"+identificacion+direccion+"</datosPersonales>";
  }
  
  public String consultarDireccionLaboral(String parametros){
      String xml = getXml(parametros);
      xml = "<direccionLaboral>"+xml+"</direccionLaboral>";
      return xml;
  }
  
  public String consultarExperticia(String parametros){
      String xml = getXml(parametros);
      xml = "<experticia>"+xml+"</experticia>";
      return xml;
  }
  
  public String consultarUltimoAcceso(String parametros){
      String xml = getXml(parametros);
      xml = "<ultimoAcceso>"+xml+"</ultimoAcceso>";
      return xml;
  }
  
  public String consultarFormacionBasica(String parametros){
      String xml = getXml(parametros);
      xml = "<formacionBasica>"+xml+"</formacionBasica>";
      return xml;
  }
  
  public String consultarformacionMedia(String parametros){
      String xml = getXml(parametros);
      xml = "<formacionMedia>"+xml+"</formacionMedia>";
      return xml;
  }
  
  public String consultarformacionTerciariaNoUniversitaria(String parametros){
      String xml = getXml(parametros);
      xml = "<terciariaNoUniversitaria>"+xml+"</terciariaNoUniversitaria>";
      return xml;
  }
  
  public String consultarFormacionGrados(String parametros){
      String xml = getXml(parametros);
      xml = "<grados>"+xml+"</grados>";
      return xml;
  }
  
  public String consultarEspecializaciones(String parametros){
      String xml = getXml(parametros);
      xml = "<especializaciones>"+xml+"</especializaciones>";
      return xml;
  }
  
  public String consultarMaestrias(String parametros){
      String xml = getXml(parametros);
      xml = "<maestrias>"+xml+"</maestrias>";
      return xml;
  }
  
  public String consultarDoctorados(String parametros){
      String xml = getXml(parametros);
      xml = "<doctorados>"+xml+"</doctorados>";
      return xml;
  }
  
  public String armarFormacionAcademica(String cuil){
      String formacionBasica = consultarFormacionBasica("formacionAcademica/basica/"+cuil);
      String formacionMedia = consultarformacionMedia("formacionAcademica/media/"+cuil);
      String formacionTerNoUni = consultarformacionTerciariaNoUniversitaria("formacionAcademica/terciariaNoUniversitaria/"+cuil);
      String grados = consultarFormacionGrados("formacionAcademica/grados/"+cuil);
      String especializaciones = consultarEspecializaciones("formacionAcademica/especializaciones/"+cuil);
      String maestrias = consultarMaestrias("formacionAcademica/maestrias/"+cuil);
      String doctorados = consultarDoctorados("formacionAcademica/doctorados/"+cuil);
      return "<formacionAcademica>"+formacionBasica+formacionMedia+formacionTerNoUni+grados
              +especializaciones+maestrias+doctorados+"</formacionAcademica>";
  }
  
  public String consultarFormComEspOrgSal(String parametros){
      String xml = getXml(parametros);
      xml = "<espacialidadesOrganismoSalud>"+xml+"</espacialidadesOrganismoSalud>";
      return xml;
  }
  
  public String consultarFormComPosdoctorado(String parametros){
      String xml = getXml(parametros);
      xml = "<posdoctorados>"+xml+"</posdoctorados>";
      return xml;
  }
  
  public String consultarFormComCursosPosgEspe(String parametros){
      String xml = getXml(parametros);
      xml = "<cursosPostgradoEspecializaciones>"+xml+"</cursosPostgradoEspecializaciones>";
      return xml;
  }
  
  public String consultarFormComIdiomas(String parametros){
      String xml = getXml(parametros);
      xml = "<idiomas>"+xml+"</idiomas>";
      return xml;
  }
  
  public String armarFormacionComplementaria(String cuil){
      String  espacialOrgSal = consultarFormComEspOrgSal("formacionComplementaria/espacialidadOrganismoSalud/"+cuil);
      String posdoctodado = consultarFormComPosdoctorado("formacionComplementaria/posdoctorado/"+cuil);
      String curPosDoc = consultarFormComCursosPosgEspe("formacionComplementaria/cursosPostgradoEspecializaciones/"+cuil);
      String idiomas = consultarFormComIdiomas("formacionComplementaria/idiomas/"+cuil);
      return "<formacionComplementaria>"+espacialOrgSal+posdoctodado+curPosDoc+
              idiomas+"</formacionComplementaria>";
  }
  
  public String consultarCargosOrganismosCyT(String parametros){
      String xml = getXml(parametros);
      xml = "<cargosOrganismosCyT>"+xml+"</cargosOrganismosCyT>";
      return xml;
  }
  
  public String consultarCategorizacionesIncentivos(String parametros){
      String xml = getXml(parametros);
      xml = "<categorizacionesIncentivos>"+xml+"</categorizacionesIncentivos>";
      return xml;
  }
  public String consultarCargosOtroTipoInstitucion(String parametros){
      String xml = getXml(parametros);
      xml = "<cargosOtroTipoInstitucion>"+xml+"</cargosOtroTipoInstitucion>";
      return xml;
  }
  
  public String armarCargosID(String cuil){
      String cargosOrgCyT = consultarCargosOrganismosCyT("cargosDocencia/cargosOrganismosCyT/"+cuil);
      String categoIncen = consultarCategorizacionesIncentivos("cargosDocencia/categorizacionesIncentivos/"+cuil);
      String cargosOtroTipo = consultarCargosOtroTipoInstitucion("cargosDocencia/cargosOtroTipoInstitucion/"+cuil);
      return "<cargosID>"+cargosOrgCyT+categoIncen+cargosOtroTipo+"</cargosID>";
  }
  
  public String consultarCargosSuperiorPosgrado(String parametros){
      String xml = getXml(parametros);
      xml = "<superiorPosgrado>"+xml+"</superiorPosgrado>";
      return xml;
  }
  public String consultarCargosTerciario(String parametros){
      String xml = getXml(parametros);
      xml = "<terciario>"+xml+"</terciario>";
      return xml;
  }
  public String consultarCargosBasicaYMedia(String parametros){
      String xml = getXml(parametros);
      xml = "<basicaYMedia>"+xml+"</basicaYMedia>";
      return xml;
  }
  public String consultarCargosPosgradoYExtraCurr(String parametros){
      String xml = getXml(parametros);
      xml = "<posgradoYExtraCurriculares>"+xml+"</posgradoYExtraCurriculares>";
      return xml;
  }
  
  public String armarCargosDocencia(String cuil){
      String cargosSupPos = consultarCargosSuperiorPosgrado("cargosDocencia/superiorPosgrado/"+cuil);
      String cargosTerciario = consultarCargosTerciario("cargosDocencia/terciario/"+cuil);
      String cargosBasYMed = consultarCargosBasicaYMedia("cargosDocencia/basicaYMedia/"+cuil);
      String cargosPosExtraCurr = consultarCargosPosgradoYExtraCurr("cargosDocencia/posgradoYExtraCurriculares/"+cuil);
      return "<cargosDocencia>"+cargosSupPos+cargosTerciario+cargosBasYMed+cargosPosExtraCurr
              +"</cargosDocencia>";
  }
  
  public String consultarCargosGestionInstitu(String parametros){
      String xml = getXml(parametros);
      xml = "<cargosGestionInstitucional>"+xml+"</cargosGestionInstitucional>";
      return xml;
  }
  
  public String consultarCargosOtrosCargos(String parametros){
      String xml = getXml(parametros);
      xml = "<otrosCargos>"+xml+"</otrosCargos>";
      return xml;
  }
  
  public String armarOtrosCargos(String cuil){
      String cargosGestioninsti = consultarCargosGestionInstitu("cargos/cargosGestionInstitucional/"+cuil);
      String otrosCargos = consultarCargosOtrosCargos("cargos/otrosCargos/"+cuil);
      return "<cargos>"+cargosGestioninsti+otrosCargos+armarCargosDocencia(cuil)
              +armarCargosID(cuil)+"</cargos>";
  }
  
  public String consultarProduccionTecnologicaConProp(String parametros){
      String xml = getXml(parametros);
      xml = "<conPropiedadIntelectual>"+xml+"</conPropiedadIntelectual>";
      return xml;
  }
  public String consultarProduccionTecnologicaSinProp(String parametros){
      String xml = getXml(parametros);
      xml = "<sinPropiedadIntelectual>"+xml+"</sinPropiedadIntelectual>";
      return xml;
  }
  public String consultarProduccionTecnologicaServiciosCT(String parametros){
      String xml = getXml(parametros);
      xml = "<serviciosCT>"+xml+"</serviciosCT>";
      return xml;
  }
  public String consultarProduccionTecnologicaInfTecn(String parametros){
      String xml = getXml(parametros);
      xml = "<informeTecnico>"+xml+"</informeTecnico>";
      return xml;
  }
  
  public String armarProduccionTecnologica(String cuil){
      String prodTecConProp = consultarProduccionTecnologicaConProp("produccion/tecnologica/conPropiedadIntelectual/"+cuil);
      String prodTecSinProp = consultarProduccionTecnologicaSinProp("produccion/tecnologica/sinPropiedadIntelectual/"+cuil);
      String prodTecSer = consultarProduccionTecnologicaServiciosCT("produccion/tecnologica/serviciosCT/"+cuil);
      String prodTecInfTec = consultarProduccionTecnologicaInfTecn("produccion/tecnologica/informeTecnico/"+cuil);
      return "<tecnologica>"+prodTecConProp+prodTecSinProp+prodTecSer+prodTecInfTec
              +"</tecnologica>";
  }
  
  public String consultarProduccionCientificaArticulos(String parametros){
      String xml = getXml(parametros);
      xml = "<articulos>"+xml+"</articulos>";
      return xml;
  }
  public String consultarProduccionCientificaLibros(String parametros){
      String xml = getXml(parametros);
      xml = "<libros>"+xml+"</libros>";
      return xml;
  }
  public String consultarProduccionCientificaParteLibros(String parametros){
      String xml = getXml(parametros);
      xml = "<partesDeLibros>"+xml+"</partesDeLibros>";
      return xml;
  }
  public String consultarProduccionCientificaEventosPub(String parametros){
      String xml = getXml(parametros);
      xml = "<trabajosEnEventosPublicados>"+xml+"</trabajosEnEventosPublicados>";
      return xml;
  }
  public String consultarProduccionCientificaEventosNoPublicados(String parametros){
      String xml = getXml(parametros);
      xml = "<trabajosEnEventosNoPublicados>"+xml+"</trabajosEnEventosNoPublicados>";
      return xml;
  }
  public String consultarProduccionCientificaTesis(String parametros){
      String xml = getXml(parametros);
      xml = "<tesis>"+xml+"</tesis>";
      return xml;
  }
  public String consultarProduccionCientificaOtra(String parametros){
      String xml = getXml(parametros);
      xml = "<otra>"+xml+"</otra>";
      return xml;
  }
  
  public String armarProduccionCientifica(String cuil){
      String articulos = consultarProduccionCientificaArticulos("produccion/cientifica/articulos/"+cuil);
      String libros = consultarProduccionCientificaLibros("produccion/cientifica/libros/"+cuil);
      String partesDeLibros = consultarProduccionCientificaParteLibros("produccion/cientifica/partesDeLibros/"+cuil);
      String publicados = consultarProduccionCientificaEventosPub("produccion/cientifica/trabajosEnEventosPublicados/"+cuil);
      String noPublicados = consultarProduccionCientificaEventosNoPublicados("produccion/cientifica/trabajosEnEventosNoPublicados/"+cuil);
      String tesis = consultarProduccionCientificaTesis("produccion/cientifica/tesis/"+cuil);
      String otra = consultarProduccionCientificaOtra("produccion/cientifica/otra/"+cuil);
      return "<cientifica>"+articulos+libros+partesDeLibros+publicados+noPublicados
              +tesis+otra+"</cientifica>";
  }
  
  public String consultarProduccionArtisticaMusical(String parametros){
      String xml = getXml(parametros);
      xml = "<musical>"+xml+"</musical>";
      return xml;
  }
  public String consultarProduccionArtisticaVisual(String parametros){
      String xml = getXml(parametros);
      xml = "<visual>"+xml+"</visual>";
      return xml;
  }
  public String consultarProduccionArtisticaAudioVisual(String parametros){
      String xml = getXml(parametros);
      xml = "<audioVisual>"+xml+"</audioVisual>";
      return xml;
  }
  public String consultarProduccionArtisticaTeatral(String parametros){
      String xml = getXml(parametros);
      xml = "<teatral>"+xml+"</teatral>";
      return xml;
  }
  public String consultarProduccionArtisticaNarrativa(String parametros){
      String xml = getXml(parametros);
      xml = "<narrativa>"+xml+"</narrativa>";
      return xml;
  }
  public String consultarProduccionArtisticaDramaPoEns(String parametros){
      String xml = getXml(parametros);
      xml = "<dramaPoesiaEnsayo>"+xml+"</dramaPoesiaEnsayo>";
      return xml;
  }
  public String consultarProduccionArtisticaGuion(String parametros){
      String xml = getXml(parametros);
      xml = "<guion>"+xml+"</guion>";
      return xml;
  }
  public String consultarProduccionArtisticaOtraLiteraria(String parametros){
      String xml = getXml(parametros);
      xml = "<otraLiteraria>"+xml+"</otraLiteraria>";
      return xml;
  }
  public String consultarProduccionArtisticaOtra(String parametros){
      String xml = getXml(parametros);
      xml = "<otra>"+xml+"</otra>";
      return xml;
  }
  
  public String armarProduccionArtistica(String cuil){
      String musical = consultarProduccionArtisticaMusical("produccion/artistica/musical/"+cuil);
      String visual = consultarProduccionArtisticaVisual("produccion/artistica/visual/"+cuil);
      String audioVisual = consultarProduccionArtisticaAudioVisual("produccion/artistica/audioVisual/"+cuil);
      String teatral = consultarProduccionArtisticaTeatral("produccion/artistica/teatral/"+cuil);
      String narrativa = consultarProduccionArtisticaNarrativa("produccion/artistica/narrativa/"+cuil);
      String dramapoesiaensayo = consultarProduccionArtisticaDramaPoEns("produccion/artistica/dramaPoesiaEnsayo/"+cuil);
      String guion = consultarProduccionArtisticaGuion("produccion/artistica/guion"+cuil);
      String otraLiteraria = consultarProduccionArtisticaOtraLiteraria("produccion/artistica/otraLiteraria/"+cuil);
      String otra = consultarProduccionArtisticaOtra("produccion/artistica/otra/"+cuil);
      return "<artistica>"+musical+visual+audioVisual+teatral+narrativa+dramapoesiaensayo
              +guion+otraLiteraria+otra+"</artistica>";
  }
  
  public String armarProduccion(String cuil){
      return "<produccion>"+armarProduccionArtistica(cuil)+armarProduccionCientifica(cuil)
              +armarProduccionTecnologica(cuil)+"</produccion>";
  }
  
  public String armarFinal(String cuil){
      String encabezado= "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
      String xmlfinal =encabezado+"<datos>"+armarDatosPersonales(cuil)
              +armarFormacionAcademica(cuil)
              +armarFormacionComplementaria(cuil)
              +armarOtrosCargos(cuil)
              +"</datos>";
      System.out.println(xmlfinal);
      return xmlfinal;
  }
   
}
