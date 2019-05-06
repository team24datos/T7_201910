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
		System.out.println("---------------------Proyecto 3----------------------");
		System.out.println("0. Carga del grafo desde xlm");
		System.out.println("1. Guarda el grafo en un Json");
		System.out.println("2. Carga del grafo desde un Json");
		System.out.println("3. Salir");
		System.out.println("Digite el número de opción para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
}
