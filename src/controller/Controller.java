package controller;

import java.io.FileReader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.data_structures.ArregloDinamico;
import model.data_structures.BST;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.RedBlackBST;
import model.data_structures.TablaHash;
import model.vo.LocationVO;
import model.vo.VODaylyStatistic;
import model.vo.VOGeographicLocation;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	public final static String mesEnero = "./data/Moving_Violations_Issued_in_January_2018.json";

	public final static String mesFebrero = "./data/Moving_Violations_Issued_in_February_2018.json";

	public final static String mesMarzo = "./data/Moving_Violations_Issued_in_March_2018.json";

	public final static String mesAbril = "./data/Moving_Violations_Issued_in_April_2018.json";

	public final static String mesMayo = "./data/Moving_Violations_Issued_in_May_2018.json";

	public final static String mesJunio = "./data/Moving_Violations_Issued_in_June_2018.json";

	public final static String mesJulio = "./data/Moving_Violations_Issued_in_July_2018.json";

	public final static String mesAgosto = "./data/Moving_Violations_Issued_in_August_2018.json";

	public final static String mesSeptiembre = "./data/Moving_Violations_Issued_in_September_2018.json";

	public final static String mesOctubre = "./data/Moving_Violations_Issued_in_October_2018.json";

	public final static String mesNomviembre = "./data/Moving_Violations_Issued_in_November_2018.json";

	public final static String mesdiciembre= "./data/Moving_Violations_Issued_in_December_2018.json";

	public static Double Xmin, Ymin, Xmax, Ymax;

	public RedBlackBST<Integer, VOMovingViolations> arbolbalanceado;

	public Controller() {
		view = new MovingViolationsManagerView();

		Xmin=393185.8;
		Ymin=138316.9;

		Xmax=0.0;
		Ymax=0.0;
		
		arbolbalanceado = new RedBlackBST<Integer,VOMovingViolations>();
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
				System.out.println("Ingrese el semestre (1 o 2)");
				int numeroCuatrimestre = sc.nextInt();
				int numCargados=controller.loadMovingViolations(numeroCuatrimestre);

				System.out.println("");
				System.out.println("El total de infracciones del semestre fue: "+numCargados);
				int height =arbolbalanceado.height();
				System.out.println("La altura del arbol fue: "+ height);
				//System.out.println("La zona geogr�fica Minimax es: ("+Xmin+","+Ymin+") y ("+Xmax+","+Ymax+")");
				break;

			case 1:
				System.out.println("Ingrese el OBJECT_ID de la infracción de la cual quiere obtener la información");
				int objectid = sc.nextInt();
				VOMovingViolations infraccion = controller.buscarInfraccion(objectid);
				System.out.println(infraccion.toString());
				
				break;

			case 2:
				System.out.println("Ingrese el OBJECT_ID mínimo");
				int min= sc.nextInt();
				System.out.println("Ingrese el OBJECT_ID máximo");
				int max=sc.nextInt();
				break;
			 
			case 3:	
				fin=true;
				sc.close();
				break;
			}
		}

	}


	public int loadMovingViolations(int numeroSemestre) 
	{
		int numCargados=0;
		if(numeroSemestre==1)
		{
			numCargados+= loadMovingViolationsXMes(mesEnero, false);
			numCargados+= loadMovingViolationsXMes(mesFebrero, false);
			numCargados+= loadMovingViolationsXMes(mesMarzo, false);
			numCargados+= loadMovingViolationsXMes(mesAbril, false);
			numCargados+= loadMovingViolationsXMes(mesMayo, false);
			numCargados+= loadMovingViolationsXMes(mesJunio, false);
		}

		else if(numeroSemestre==2)
		{
			numCargados+= loadMovingViolationsXMes(mesJulio, false);
			numCargados+= loadMovingViolationsXMes(mesAgosto, false);
			numCargados+= loadMovingViolationsXMes(mesSeptiembre, false);
			numCargados+= loadMovingViolationsXMes(mesOctubre, true);
			numCargados+= loadMovingViolationsXMes(mesNomviembre, true);
			numCargados+= loadMovingViolationsXMes(mesdiciembre, true);					

		}
		return numCargados;
	}

	public int loadMovingViolationsXMes(String movingViolationsFile, boolean otroAtributo) {
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
				arbolbalanceado.put(OBJECTID, newVO);
				
				numCargados++;
				
				//System.out.println("Agrego en moving");
				
				/*movLP.put(""+ADDRESS_ID, newVO);
				System.out.println("Agrego en LC");
				movSC.put(""+ADDRESS_ID, newVO);
				System.out.println("Agrego en SC");*/
				
				
			}
			
			System.out.println("El tamaño del árbol después de cargar los datos es : " + arbolbalanceado.size());
			System.out.println("El elemento máximo es: " + arbolbalanceado.max() + ". y el elemento mínimo es: " + arbolbalanceado.min());

		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getMessage());
		}
		System.out.println("El n�mero de datos cargados en este mes fue: "+numCargados);
		System.out.println("");
		return numCargados;

	}
	
	/**
	 * Método que utiliza el árbol balanceado donde está contenida la información para buscar un elemento específico
	 * @param pObjectId El object id de la infracción que corresponde a la llave de búsqueda del árbol. 
	 * @return La información de la infracción. 
	 */
	public VOMovingViolations buscarInfraccion(int pObjectId) {
		
		return arbolbalanceado.get(pObjectId);
	}
}
