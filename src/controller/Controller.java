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
import model.vo.Counter;
import model.vo.LocationVO;
import model.vo.VODaylyStatistic;
import model.vo.VOGeographicLocation;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	public Controller() 
	{
		view = new MovingViolationsManagerView();
	}

	public void run(String args[]) {
		
		Controller controller = new Controller();
		Counter.load(args);
	}
	
}
