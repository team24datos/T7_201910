package controller;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import model.data_structures.ArregloDinamico;
import model.data_structures.BST;
import model.data_structures.Grafo;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.RedBlackBST;
import model.data_structures.TablaHash;
import model.vo.LocationVO;
import model.vo.VODaylyStatistic;
import model.vo.VOGeographicLocation;
import model.vo.VOIntersections;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import model.vo.VOWay;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;
	private Grafo grafo;
	

	public Controller() 
	{
		view = new MovingViolationsManagerView();
		grafo= new Grafo<Integer,VOIntersections,VOWay>();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				System.out.println("Carga del grafo desde un Json");
				String rutaInt= "./data//WashingtonVertices.json";
				String rutaWay= "./data/";
				controller.loadIntersectionsJson(rutaInt);
				controller.loadWaysJson(rutaWay);
				break;

			case 1:
				;
				
				break;

			case 2:
				
				break;
			 
			case 3:	
				fin=true;
				sc.close();
				break;
			}
		}

	}


	public void loadIntersectionsJson(String ruta) 
	{
		int numCargados=0;
		JsonParser parser = new JsonParser();
		try 
		{
			Reader reader = Files.newBufferedReader(Paths.get(ruta));
			JsonArray arreglo = (JsonArray)parser.parse(new FileReader(ruta));
			for(int i=0; arreglo != null && i < arreglo.size(); i++)
			{
				JsonObject objeto = (JsonObject)arreglo.get(i);
				//------------------------------------
				//------ Lectura de atributos de la interseccion
				//------------------------------------
				int ID=0;
				JsonElement elementoID = objeto.get("ID");
				if(elementoID!=null && !elementoID.isJsonNull())
				{
					ID=elementoID.getAsInt();
					//System.out.print("a");
				}
				double LAT=0;
				JsonElement elementoLAT = objeto.get("LAT");
				if(elementoLAT!=null && !elementoLAT.isJsonNull())
				{
					LAT=elementoLAT.getAsDouble();
					//System.out.print("b");
				}
				double LON=0;
				JsonElement elementoLON = objeto.get("LON");
				if(elementoLON!=null && !elementoLON.isJsonNull())
				{
					LON=elementoLON.getAsDouble();
					//System.out.print("c");
				}
				VOIntersections nuevaInter= new VOIntersections(ID, LAT, LON);
				numCargados++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getMessage());
		}
	}
	public void loadWaysJson(String ruta) 
	{
		int numCargados=0;
		JsonParser parser = new JsonParser();
		try 
		{
			Reader reader = Files.newBufferedReader(Paths.get(ruta));
			JsonArray arreglo = (JsonArray)parser.parse(new FileReader(ruta));
			for(int i=0; arreglo != null && i < arreglo.size(); i++)
			{
				JsonObject objeto = (JsonObject)arreglo.get(i);
				//------------------------------------
				//------ Lectura de atributos del Way
				//------------------------------------
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getMessage());
		}
	}

	public void loadMovingViolationsXMes(String movingViolationsFile, boolean otroAtributo) {
		int numCargados=0;
		JsonParser parser = new JsonParser();
		try 
		{
			System.out.println("Mes:" + movingViolationsFile);
			Reader reader = Files.newBufferedReader(Paths.get(movingViolationsFile));
			JsonArray arreglo = (JsonArray)parser.parse(new FileReader(movingViolationsFile));
			
			System.out.println("Num Elementos:" + arreglo.size());
			for(int i=0; arreglo != null && i < arreglo.size(); i++)
			{
				JsonObject objeto = (JsonObject)arreglo.get(i);
				//------------------------------------
				//------ Lectura de atributos de la infracci�n
				//------------------------------------
				//VOMovingViolations newVO = new VOMovingViolations(LOCATION,Double.parseDouble(FINEAMT), TICKETISSUEDATE, ACCIDENTINDICATOR, VIOLATIONDESC, 
				//VIOLATIONCODE,STREETSEGID,ADDRESS_ID, Integer.parseInt(OBJECTID), Double.parseDouble(TOTALPAID), PENALTY1 , PENALTY2 ) ;
				int OBJECTID=0;
				JsonElement elementoID = objeto.get("OBJECTID");
				if(elementoID!=null && !elementoID.isJsonNull())
				{
					OBJECTID=elementoID.getAsInt();
					//System.out.print("a");
				}
				
				String LOCATION="";
				JsonElement LOCATIONelemento = objeto.get("LOCATION");
				if(LOCATIONelemento!=null && !LOCATIONelemento.isJsonNull())
				{
					LOCATION=LOCATIONelemento.getAsString();
					//System.out.print("b");
				}
				
				int ADDRESS_ID = 0;
				JsonElement ADDRESS_IDelemento = objeto.get("ADDRESS_ID");
				if(ADDRESS_IDelemento!=null && !ADDRESS_IDelemento.isJsonNull())
				{
					//System.out.println("["+ADDRESS_IDelemento.getAsString()+"]");
					ADDRESS_ID=ADDRESS_IDelemento.getAsInt();
					//System.out.print("c");
				}
				
				int STREETSEGID = 0;
				JsonElement STREETSEGIDelemento = objeto.get("STREETSEGID");
				if(STREETSEGIDelemento!=null && !STREETSEGIDelemento.isJsonNull())
				{
					STREETSEGID=STREETSEGIDelemento.getAsInt();
					//System.out.print("d");
				}

				double FINEAMT = 0.0;
				JsonElement FINEAMTelemento = objeto.get("FINEAMT");
				if(FINEAMTelemento!=null && !FINEAMTelemento.isJsonNull())
				{
					FINEAMT=FINEAMTelemento.getAsDouble();
					//System.out.print("e");
				}
				
				double TOTALPAID = 0.0;
				JsonElement TOTALPAIDelemento = objeto.get("TOTALPAID");
				if(TOTALPAIDelemento!=null && !TOTALPAIDelemento.isJsonNull())
				{
					TOTALPAID=TOTALPAIDelemento.getAsDouble();
					//System.out.print("f");
				}
				
				double PENALTY1 = 0.0;
				JsonElement PENALTY1elemento = objeto.get("PENALTY1");
				if(PENALTY1elemento!=null && !PENALTY1elemento.isJsonNull())
				{
					PENALTY1=PENALTY1elemento.getAsDouble();
					//System.out.print("g");
				}
				
				boolean ACCIDENTINDICATOR=false;
				JsonElement ACCIDENTINDICATORelemento = objeto.get("ACCIDENTINDICATOR");
				if(ACCIDENTINDICATORelemento!=null && !ACCIDENTINDICATORelemento.isJsonNull())
				{
					ACCIDENTINDICATOR=ACCIDENTINDICATORelemento.getAsString()=="Yes"?true:false;
					//System.out.print("h");
				}
				
				String TICKETISSUEDATE="";
				JsonElement TICKETISSUEDATEelemento = objeto.get("TICKETISSUEDATE");
				if(TICKETISSUEDATEelemento!=null && !TICKETISSUEDATEelemento.isJsonNull())
				{
					TICKETISSUEDATE=TICKETISSUEDATEelemento.getAsString();
					//System.out.print("i");
				}
				
				String VIOLATIONCODE="";
				JsonElement VIOLATIONCODEelemento = objeto.get("VIOLATIONCODE");
				if(VIOLATIONCODEelemento!=null && !VIOLATIONCODEelemento.isJsonNull())
				{
					VIOLATIONCODE=VIOLATIONCODEelemento.getAsString();
					//System.out.print("j");
				}
				
				String VIOLATIONDESC="";
				JsonElement VIOLATIONDESCelemento = objeto.get("VIOLATIONDESC");
				if(VIOLATIONDESCelemento!=null && !VIOLATIONDESCelemento.isJsonNull())
				{
					VIOLATIONDESC=VIOLATIONDESCelemento.getAsString();
					//System.out.print("k");
				}
				//System.out.println("Linea : "+i);
				VOMovingViolations newVO = new VOMovingViolations(OBJECTID, LOCATION, ADDRESS_ID, STREETSEGID, FINEAMT, TOTALPAID, PENALTY1, ACCIDENTINDICATOR, TICKETISSUEDATE, VIOLATIONCODE, VIOLATIONDESC);
				//System.out.println("cre� el objeto");
				//moving.add(newVO);
				
				
				numCargados++;
				
				//System.out.println("Agrego en moving");
				
				/*movLP.put(""+ADDRESS_ID, newVO);
				System.out.println("Agrego en LC");
				movSC.put(""+ADDRESS_ID, newVO);
				System.out.println("Agrego en SC");*/
				
				
			}
			
			
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getMessage());
		}

	}
	
	
	private void toJson()
	{
		JsonWriter writer;
		try
		{
			writer = new JsonWriter(new FileWriter("./data/WashingtonVertices.json"));
			writer.beginObject();
			//
			// VERTICES
			//
			writer.name("Vertices");
			writer.beginArray();
			Iterator<VOIntersections>  itVertices=grafo.iteratorVertices();
			
			while(itVertices.hasNext())
			{
				VOIntersections actual= itVertices.next();
				writer.beginObject();
				writer.name("ID").value(actual.getId());
				writer.name("LAT").value(actual.getLat());
				writer.name("LON").value(actual.getLon());
				//DUDA!! adyacentes??
				writer.endObject();				
			}
			writer.endArray();
			writer.endObject();
			//
			// ARCOS
			//
			writer.name("Arcos");
			writer.beginArray();
			//Corregir de acuerdo a Santiago
			Iterator<VOMovingViolations> itArcos;
			//TODO
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Fallo porque: "+e.getMessage());
		}
	}
	
}
