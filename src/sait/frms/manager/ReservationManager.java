package sait.frms.manager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

public class ReservationManager {
	private ArrayList<Reservation> reservations;
	
	public ReservationManager()
	{
		this.reservations = new ArrayList<>();
	}
	
	/**
	 * Makes a reservation.
	 * @return Created reservation instance.
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException 
	{ 
		populateFromBinary();
		Reservation reservation = null;
		String reservationcode = generateReservationCode(flight);

		if (flight.getSeats() > 0) 
		{
			reservation = new Reservation(reservationcode, flight.getCode(), flight.getAirlineName(), name, citizenship, flight.getCostPerSeat(), true);
			reservations.add(reservation);
		}
		persist();
		
		return reservation;
	}
	 
	/**
	 * Finds reservations containing either reservation code or airline.
	 * @return Any matching Reservation objects.
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) throws IOException
	{
		populateFromBinary();
		ArrayList<Reservation> findreservation = new ArrayList<Reservation>();
		
		for (int i = 0; i < reservations.size(); i++) {
			
			if (!code.isEmpty() && reservations.get(i).getCode().contains(code))
			{
				findreservation.add(reservations.get(i));
			}else if (!airline.isEmpty() && reservations.get(i).getAirline().contains(airline))
			{
				findreservation.add(reservations.get(i));
			}else if (!name.isEmpty() && reservations.get(i).getName().contains(name))
			{
				findreservation.add(reservations.get(i));
			}
		}
		
		return findreservation;
		
	}
	
	/**
	 * Finds reservation with the exact reservation code.
	 * @return Reservation object or null if none found.
	 */
	public Reservation findReservationByCode(String code) 
	{ 	
		Reservation findreservationcode = null;
		for(int i = 0; i < reservations.size(); i++) 
		{
			if (code.equals(reservations.get(i).getCode()))
			{
				findreservationcode = reservations.get(i);
			}
		}
		
		return findreservationcode;
	}
	
	/**
	 * Saves Reservation objects to a random access file.
	 *
	 */
	public void persist() throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile("res/reservation.dat", "rw");
			
		for(int i = 0; i < reservations.size(); i++)
		{
			
		String reservationcode = String.format("%-5s", reservations.get(i).getCode());
		raf.writeUTF(reservationcode);
		
		String flightcode = String.format("%-7s", reservations.get(i).getFlightCode());
		raf.writeUTF(flightcode);
		
		String airline = String.format("%-20s", reservations.get(i).getAirline());
		raf.writeUTF(airline);
		
		String name = String.format("%-20s", reservations.get(i).getName());
		raf.writeUTF(name);
		
		String citizenship = String.format("%-20s", reservations.get(i).getCitizenship());
		raf.writeUTF(citizenship);
		
		raf.writeDouble(reservations.get(i).getCost());
		
		raf.writeBoolean(reservations.get(i).isActive());
		}
	}
	
	private int getAvailableSeats(Flight flight)
	{
		return 0;
		
	}
	
	
	/**
	 * Gets reservation code using a flight.
	 * @return Reservation code.
	 */
	private String generateReservationCode(Flight flight)
	{
		String reservationcode = null;
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int randomnumber = 1000 + random.nextInt(8999);

		if (flight.getFrom().substring(0, 1).equals("Y") && flight.getTo().substring(0, 1).equals("Y"))
		{
			reservationcode = "D" + randomnumber;
		}else if(!flight.getFrom().substring(0, 1).equals("Y") || !flight.getTo().substring(0, 1).equals("Y"))
		{
			reservationcode = "I" + randomnumber;
		}
		return reservationcode;
	}
	
	/**
	 * Populates reservations with Reservation objects from Random Access File.
	 * 
	 */
	private void populateFromBinary() throws IOException
	{
		this.reservations.clear();
		RandomAccessFile raf = new RandomAccessFile("res/reservation.dat", "rw");
		Reservation reservation = null;
		for (long position = 0; position < raf.length(); position += 91) {
			raf.seek(position);
			
			String code = raf.readUTF().trim();
			String flightcode = raf.readUTF().trim();
			String airline = raf.readUTF().trim();
			String name = raf.readUTF().trim();
			String citizenship = raf.readUTF().trim();
			double cost = raf.readDouble();
			boolean active = raf.readBoolean();
			
			
			reservation = new Reservation(code, flightcode, airline, name, citizenship, cost, active);
			if (reservation.isActive()) {
			reservations.add(reservation);
			}
		}
	}
}
