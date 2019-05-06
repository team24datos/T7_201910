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
import model.vo.Counter;
import model.vo.LocationVO;
import model.vo.VODaylyStatistic;
import model.vo.VOGeographicLocation;
import model.vo.VOIntersections;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import model.vo.VOWay;
import view.MovingViolationsManagerView;

public class Controller {

	// Atributos ------------------------------------------------------------------
	
	/** Vista del controlador */
	private MovingViolationsManagerView view;
	
	/** Grafo donde se almacena toda la red vial de Washington con arcos de tipo highway */
	private Grafo grafo;
	
	// Constructor -------------------------------------------------------------------
	
	/**
	 * Construye el controlador 
	 */
	public Controller() 
	{
		view = new MovingViolationsManagerView();
		grafo= new Grafo<Long,VOIntersections,VOWay>();
	}


	public void run(String args[]) {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		Counter contador = new Counter();
		grafo = contador.load(args);

		while(!fin)
		{
			
			
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				System.out.println("Ya se carg� el grafo desde xlm");
				break;

			case 1:
				System.out.println("Guarda el grafo en un Json");
				controller.toJson();
				
				break;

			case 2:
				System.out.println("Carga del grafo desde un Json");
				String rutaInt= "./data//WashingtonVertices.json";
				String rutaWay= "./data/";
				controller.loadIntersectionsJson(rutaInt);
				controller.loadWaysJson(rutaWay);
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
