package sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import sait.frms.problemdomain.Flight;

public class FlightManager {
	private ArrayList<Flight> flights;
	private ArrayList<String> airports;
	
	public static String WEEKDAY_ANY = "Any";
	public static String WEEKDAY_SUNDAY = "Sunday";
	public static String WEEKDAY_MONDAY = "Monday";
	public static String WEEKDAY_TUESDAY = "Tuesday";
	public static String WEEKDAY_WEDNESDAY = "Wednesday";
	public static String WEEKDAY_THURSDAY = "Thursday";
	public static String WEEKDAY_FRIDAY = "Friday";
	public static String WEEKDAY_SATURDAY = "Saturday";

	public FlightManager() throws FileNotFoundException 
	{
		flights = new ArrayList<>();
		airports = new ArrayList<>();
		populateFlights();
		populateAirports();
	}

	public ArrayList<Flight> getFlights() 
	{
		return flights;
	}
	public ArrayList<String> getAirports() 
	{
		return airports;
	}
	
	public String findAirportByCode(String code)
	{
		return code;
		
	}
	
	public Flight findFlightByCode(String code)
	{
		Flight findflightcode = null;
		for(int i = 0; i < flights.size(); i++) 
		{
			if (code.equals(flights.get(i).getCode()))
			{
				findflightcode = flights.get(i);
			}
		}
		return findflightcode;
	}
	
	public ArrayList<Flight> findFlights(String from, String to, String weekday)
	{
		ArrayList<Flight> findflights;
		findflights = new ArrayList<>();
		findflights.clear();
		for(int i = 0; i < flights.size(); i++) 
		{
			if (from.equals(flights.get(i).getFrom()) && to.equals(flights.get(i).getTo()) && weekday.equals(flights.get(i).getWeekday())) {
				findflights.add(flights.get(i));
			}else if (from.equals(flights.get(i).getFrom()) && to.equals(flights.get(i).getTo()) && weekday.equals("Any")) {
				findflights.add(flights.get(i));
			}
		}
		
		return findflights;
	}
	
	private void populateFlights() throws FileNotFoundException
	{
		Scanner file = new Scanner(new File("res/flights.csv"));

		while (file.hasNext())
		{
			String line = file.nextLine();
			String[] field = line.split(",");
			Flight flight = null;

			flight = new Flight(field[0], field[1], field[2], field[3], field[4], field[5], Integer.parseInt(field[6]), Double.parseDouble(field[7]));

			flights.add(flight);

		}
		file.close();
	}
	
	private void populateAirports() throws FileNotFoundException
	{
		Scanner file = new Scanner(new File("res/airports.csv"));

		while (file.hasNext())
		{
			String line = file.nextLine();
			String[] field = line.split(",");

			airports.add(field[0]);

		}
		file.close();
	}
}
