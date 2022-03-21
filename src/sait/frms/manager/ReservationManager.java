package sait.frms.manager;

import java.util.ArrayList;

import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

public class ReservationManager {
	private ArrayList<Reservation> reservation;
	
	public ReservationManager() 
	{
		
	}
	
	/*
	 * public Reservation makeReservation(Flight flight, String name, String
	 * citizenship) { return Reservation;
	 * 
	 * }
	 */
	
	public ArrayList<Reservation> findReservations(String code, String airline, String name)
	{
		return reservation;
		
	}
	
	/*
	 * public Reservation findReservationByCode(String code) { return Reservation;
	 * 
	 * }
	 */
	
	public void persist() {
		
	}
	
	private int getAvailableSeats(Flight flight)
	{
		return 0;
		
	}
	
	private String generateReservationCode(Flight flight)
	{
		return null;
		
	}
	
	private void populateFromBinary()
	{
		
	}
}
