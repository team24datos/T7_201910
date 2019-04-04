package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.Controller;
import model.data_structures.BST;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.TablaHash;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;

public class MovingViolationsManagerView 
{
	/**
	 * Constante con el nÃºmero maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 2----------------------");
		System.out.println("0. Cargar datos del semestre");
		System.out.println("1. Obtener el ranking de las N franjas horarias");
		System.out.println("2. Ordenamiento y búsqueda de las infracciones por Localización Geográfica (Xcoord, Ycoord)");
		System.out.println("3. Buscar las infracciones por rango de fechas");
		
		System.out.println("4. Obtener el ranking de las N tipos de infracción (ViolationCode) que tengan más infracciones");		
		System.out.println("5. Realizar el ordenamiento de las infracciones por Localización Geográfica (Xcoord, Ycoord)");
		System.out.println("6. Buscar las franjas de fecha-hora donde se tiene un valor acumulado de infracciones en un rango dado [US$ valor inicial, US$ valor final].");
		
		System.out.println("7. Obtener la información de una localización dada");
		System.out.println("8. Obtener las infracciones en un rango de horas [HH:MM:SS inicial, HH:MM:SS final].");
		System.out.println("9. Obtener el ranking de las N localizaciones geográficas (Xcoord, Ycoord) con la mayor cantidad de infracciones");
		System.out.println("10. Gráfica ASCII con la información de las infracciones por código (ViolationCode)");
		
		System.out.println("11. Salir");
		System.out.println("Digite el número de opción para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void printMovingViolationsReq2(TablaHash<Double,VOMovingViolations> resultados2) 
	{
		//TODO
		/*
		for(VOMovingViolations v: resultados2)
		{
			System.out.println("ObjectID: " + v.objectId() + ", issued: " + v.getTicketIssueDate());
		}*/
	}
	
	public void printBST(BST<String, VOMovingViolations> resultados) 
	{
		//TODO
				/*
		System.out.println("OBJECTID\t TICKETISSUEDAT\t STREETSEGID\t ADDRESS_ID");

		for(VOMovingViolations v: resultados4) {
			System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getStreetSegId() + "\t" + v.getAddressId());
		}*/
	}
	
	public void printViolationCodesReq5(IQueue<VOViolationCode> violationCodes) {
		System.out.println("VIOLATIONCODE\t FINEAMT promedio");

		for(VOViolationCode v: violationCodes) {
			System.out.println(v.getViolationCode() + "\t" + v.getAvgFineAmt());
		}
	}
	
	public void printPrioQueue(MaxColaPrioridad<VOMovingViolations> resultados6) {
		/*System.out.println("OBJECTID\t TICKETISSUEDAT\t TOTALPAID");
		for(VOMovingViolations v: resultados6) {
			System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getTotalPaid());
		}*/
	}
	
	public void printMovingViolationsReq7(IQueue<VOMovingViolations> resultados7) {
		System.out.println("OBJECTID\t TICKETISSUEDAT\t VIOLATIONDESC");
		for(VOMovingViolations v: resultados7) {
			System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getViolationDescription());
		}
	}
	
	public void printMovingViolationsByHourReq10() {
		System.out.println("Porcentaje de infracciones que tuvieron accidentes por hora. 2018");
		System.out.println("Hora| % de accidentes");
		System.out.println("00 | X");
		System.out.println("01 | X");
		System.out.println("02 | XX");
		System.out.println("03 | XXXXX");
		System.out.println("04 | XXXXXXXX");
		System.out.println("05 | XXXXXXXXX");
		System.out.println("06 | XXXXXXXXX");
		System.out.println("07 | XXXXXXXXXX");
		System.out.println("08 | XXXXXXXXXXX");
		System.out.println("09 | XXXXXXXXXXXXX");
		System.out.println("10 | XXXXXXXXXXXXXX");
		System.out.println("11 | XXXXXXXXXXXXXX");
		System.out.println("12 | XXXXXXXXXXXXXXXX");
		System.out.println("13 | XXXX");
		System.out.println("14 | XXXXXX");
		System.out.println("15 | XXXXXXXXXXXXXXXX");
		System.out.println("16 | XXXXXXXXXXX");
		System.out.println("17 | XXXXXX");
		System.out.println("18 | XXXXXXXXXXXXXXXX");
		System.out.println("19 | XXXXXXXXXX");
		System.out.println("20 | XXX");
		System.out.println("21 | XXXXX");
		System.out.println("22 | XXXX");
		System.out.println("23 | XX");
		System.out.println("");
		System.out.println("Cada X representa Y%");
	}
	
	public void printTotalDebtbyMonthReq12() {
		System.out.println("Deuda acumulada por mes de infracciones. 2018");
		System.out.println("Mes| Dinero");
		System.out.println("01| X");
		System.out.println("02| XX");
		System.out.println("03 | XXXXXX");
		System.out.println("04 | XXXXXXXXXX");
		System.out.println("");
		System.out.println("Cada X representa $YYYY USD");
	}
	
}
