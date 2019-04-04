package controller;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.BST;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.TablaHash;
import model.vo.LocationVO;
import model.vo.VODaylyStatistic;
import model.vo.VOGeographicLocation;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	public final static String mesEnero = "./data/Moving_Violations_Issued_in_January_2018.csv";

	public final static String mesFebrero = "./data/Moving_Violations_Issued_in_February_2018.csv";

	public final static String mesMarzo = "./data/Moving_Violations_Issued_in_March_2018.csv";

	public final static String mesAbril = "./data/Moving_Violations_Issued_in_April_2018.csv";

	public final static String mesMayo = "./data/Moving_Violations_Issued_in_May_2018.csv";

	public final static String mesJunio = "./data/Moving_Violations_Issued_in_June_2018.csv";

	public final static String mesJulio = "./data/Moving_Violations_Issued_in_July_2018.csv";

	public final static String mesAgosto = "./data/Moving_Violations_Issued_in_August_2018.csv";

	public final static String mesSeptiembre = "./data/Moving_Violations_Issued_in_September_2018.csv";

	public final static String mesOctubre = "./data/Moving_Violations_Issued_in_October_2018.csv";

	public final static String mesNomviembre = "./data/Moving_Violations_Issued_in_November_2018.csv";

	public final static String mesdiciembre= "./data/Moving_Violations_Issued_in_December_2018.csv";

	public static Double Xmin, Ymin, Xmax, Ymax;


	public Controller() {
		view = new MovingViolationsManagerView();

		Xmin=393185.8;
		Ymin=138316.9;

		Xmax=0.0;
		Ymax=0.0;
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
				view.printMessage("Ingrese el semestre (1 o 2)");
				int numeroCuatrimestre = sc.nextInt();
				int numCargados=controller.loadMovingViolations(numeroCuatrimestre);

				System.out.println("");
				System.out.println("El total de infracciones del semestre fue: "+numCargados);
				System.out.println("La zona geográfica Minimax es: ("+Xmin+","+Ymin+") y ("+Xmax+","+Ymax+")");
				break;

			case 1:
				view.printMessage("Ingrese el número N de franjas horarias deseadas");
				int N1 = sc.nextInt();
				controller.getNFranjas(N1);
				break;

			case 2:
				view.printMessage("Ingrese la coordenada x");
				double X= sc.nextDouble();
				view.printMessage("Ingrese la coordenada y");
				double Y=sc.nextDouble();
				TablaHash<Double,VOMovingViolations> r2 =controller.getMovingViolationsInXY(X,Y);
				view.printMovingViolationsReq2(r2);
				break;

			case 3:
				view.printMessage("Ingrese la fecha con hora inicial (Ej : 28/03/2017)");
				LocalDate fechaInicialReq3A = convertirFecha(sc.next());

				view.printMessage("Ingrese la fecha con hora final (Ej : 28/03/2017)");
				LocalDate fechaFinalReq3A = convertirFecha(sc.next());

				BST<String, VOMovingViolations> r3=	controller.getMovingViolationsInRange(fechaInicialReq3A,fechaFinalReq3A);
				view.printBST(r3);
				break;

			case 4:

				view.printMessage("Ingrese el número N de franjas horarias deseadas");
				int N4 = sc.nextInt();
				MaxColaPrioridad<VOMovingViolations> resultados4 = controller.getNTipos(N4);
				view.printPrioQueue(resultados4);
				break;

			case 5:
				//TODO
				break;

			case 6:

				//TODO
				break;

			case 7:

				//TODO
				break;

			case 8:
				//TODO
				break;

			case 9:
				/*
				view.printMessage("Ingrese la hora inicial (Ej: 23)");
				int horaInicial9 = sc.nextInt();

				view.printMessage("Ingrese la hora final (Ej: 23)");
				int horaFinal9 = sc.nextInt();*/
				//TODO
				view.printMessage("Ingrese el código");
				String code =sc.next();
				int resultado9 = controller.countMovingViolationsByCode(code);

				view.printMessage("Número de infracciones: " + resultado9);
				break;

			case 10:
				//TODO
				view.printMovingViolationsByHourReq10();
				break;
				/*
			case 13:
				view.printMessage("Ingrese la fecha inicial (Ej : 28/03/2017)");
				LocalDate fechaInicial11 = convertirFecha(sc.next());

				view.printMessage("Ingrese la fecha final (Ej : 28/03/2017)");
				LocalDate fechaFinal11 = convertirFecha(sc.next());

				double resultados11 = controller.totalDebt(fechaInicial11, fechaFinal11);
				view.printMessage("Deuda total "+ resultados11);
				break;

			case 12:	
				view.printTotalDebtbyMonthReq12();
				break;*/

			case 11:	
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
		System.out.println("");
		System.out.println("Se está cargando: "+movingViolationsFile);
		int numCargados=0;
		int linea = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(movingViolationsFile));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);


			for (CSVRecord csvRecord : csvParser) {
				linea++;
				if (linea == 1)
					continue;

				{
					// Accessing Values by Column Index
					String OBJECTID =  csvRecord.get(0);
					if(OBJECTID.contains(","))
						continue;
					int pOBJECTID=OBJECTID.equals("")?0:Integer.parseInt(OBJECTID);
					//String ROW_ = csvRecord.get(1);
					String LOCATION = csvRecord.get(2);

					String STREETSEGID = csvRecord.get(4);  
					String ADDRESS_ID = csvRecord.get(3).equals("")?STREETSEGID:csvRecord.get(3); 

					int pADDRESS_ID =ADDRESS_ID.equals("")?0:Integer.parseInt(ADDRESS_ID);
					int pSTREETSEGID=STREETSEGID.equals("")?0:Integer.parseInt(STREETSEGID);

					String XCOORD = csvRecord.get(5);
					String YCOORD = csvRecord.get(6);
					Double x = XCOORD.equals("")?0.0:Double.parseDouble(XCOORD);
					Double y = YCOORD.equals("")?0.0:Double.parseDouble(YCOORD);
					/*int n=0;
					if(n<20)
					{
						System.out.println(x+","+y);
						n++;
					}

					int i=0;
					int j=0;
					int k=0;
					int l=0;*/
					
					//Rectangulo Min y Max
					if(x>Xmax){Xmax=x;/*if(i<20){System.out.println("xmax");i++;}*/}
					if(y>Ymax){Ymax=y;/*if(j<20){System.out.println("ymax");j++;}*/}
					if(x<Xmin){Xmin=x;/*if(k<20){System.out.println("xmin");k++;}*/}
					if(y<Ymin){Ymin=y;/*if(l<20){System.out.println("ymin");l++;}*/}
					//String TICKETTYPE = csvRecord.get(7);

					String FINEAMT = csvRecord.get(8);
					String TOTALPAID = csvRecord.get(9);

					Double pFINEAMT=FINEAMT.equals("")?0:Double.parseDouble(FINEAMT);
					Double pTOTALPAID=TOTALPAID.equals("")?0:Double.parseDouble(TOTALPAID);
					/*String PENALTY1 = csvRecord.get(10);
					String PENALTY2 = csvRecord.get(11);*/					
					String ACCIDENTINDICATOR = csvRecord.get(12);
					boolean pACCIDENTINDICATOR=ACCIDENTINDICATOR.equalsIgnoreCase("Yes")?true:false;
					String TICKETISSUEDATE =  !otroAtributo ? csvRecord.get(13).toString() : csvRecord.get(14);
					String VIOLATIONDESC = !otroAtributo ? csvRecord.get(15).toString() :  csvRecord.get(16);
					String VIOLATIONCODE = !otroAtributo ? csvRecord.get(14).toString() :  csvRecord.get(15);
					//System.out.println(OBJECTID + "," + LOCATION +  "," + ADDRESS_ID + "," + STREETSEGID);
					VOMovingViolations newMoving = new VOMovingViolations(pOBJECTID,LOCATION,pADDRESS_ID,pSTREETSEGID,pFINEAMT,pTOTALPAID,pACCIDENTINDICATOR,TICKETISSUEDATE,VIOLATIONCODE,VIOLATIONDESC, x, y);
					numCargados++;
					//moving.add(listaa);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("AQUI - linea" + linea);
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getMessage());
		}
		System.out.println("El total de infracciones del mes fue: "+numCargados);
		return numCargados;
	}
	/**
	 *  Parte A 
	 */
	public MaxColaPrioridad<VOMovingViolations> getNFranjas(int N) {
		// TODO R1
		return null;
	}

	public TablaHash<Double,VOMovingViolations> getMovingViolationsInXY(double X,double Y)
	{
		// TODO R2
		return null;
	}

	public BST<String, VOMovingViolations> getMovingViolationsInRange(LocalDate fechaInicialReq3A,
			LocalDate fechaFinalReq3A) 
	{
		// TODO R3 		
		return null;
	}

	/**
	 * Parte B
	 */
	public MaxColaPrioridad<VOMovingViolations> getNTipos(int N) 
	{
		// TODO R4
		return null;
	}

	public BST<VOGeographicLocation,VOMovingViolations> getBSTViolationsInXY(double x, double y) 
	{
		// TODO R5
		return null;
	}

	public BST<Double, VOMovingViolations> getHoursFineInRange(LocalDate fechaInicial, LocalDate fechaFinal) 
	{
		// TODO R6
		return null;
	}

	/**
	 * Parte C
	 */

	public ArregloDinamico<LocationVO> getMovingViolationsByTotalPaid(String AddressID) 
	{
		// TODO R7
		return null;
	}

	public BST<String,VOMovingViolations> getMovingViolationsByHour(int horaInicial7, int horaFinal7) 
	{
		// TODO R8
		return null;
	}

	public BST<VOGeographicLocation, VOMovingViolations> getNGeoLocations(int N) {
		// TODO R9
		return null;
	}

	public int countMovingViolationsByCode(String ViolationCode) 
	{
		// TODO R10
		return 0;
	}
	


	/*
	public IQueue <VODaylyStatistic> getDailyStatistics () {
		return null;
	}

	public IStack <VOMovingViolations> nLastAccidents(int n) {
		return null;
	}*/
	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}


	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss"));
	}
}
