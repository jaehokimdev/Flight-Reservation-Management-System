package sait.frms.application;

import sait.frms.gui.MainWindow;
import sait.frms.manager.FlightManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import sait.frms.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
		
	}

}
