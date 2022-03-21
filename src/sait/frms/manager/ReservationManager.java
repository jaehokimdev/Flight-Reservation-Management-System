package sait.frms.manager;

import java.util.ArrayList;
import java.util.Random;

import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

public class ReservationManager {
	private ArrayList<Reservation> reservation;
	
	public ReservationManager() 
	{
		
	}
	
	
	public Reservation makeReservation(Flight flight, String name, String citizenship) 
	{ 
		Reservation reservations = null;
		String reservationcode = generateReservationCode(flight);
		if (flight.getSeats() > 0) 
		{
			reservations = new Reservation(reservationcode, flight.getCode(), flight.getAirlineName(), name, citizenship, flight.getCostPerSeat(), false);
		}
		
		return reservations;
	}
	 
	
	public ArrayList<Reservation> findReservations(String code, String airline, String name)
	{
		return reservation;
		
	}
	
	/*
	 * public Reservation findReservationByCode(String code) { return Reservation;
	 * 
	 * }
	 */
	
	public void persist()
	{
		// save reservation to DAT using random access file
		// reservation ==> reservation.dat
	}
	
	private int getAvailableSeats(Flight flight)
	{
		return 0;
		
	}
	
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
	
	private void populateFromBinary()
	{
		
	}
}
