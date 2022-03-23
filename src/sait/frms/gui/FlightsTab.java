package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase implements ActionListener {
	/**
	 * Instance of flight manager.
	 */
//	private FlightManager flightManager;
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<String> flightsList;

	private DefaultListModel<String> flightsModel;
	
	private ArrayList<Flight> findflight;
	
	private Flight selectedFlight;

	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 * @throws FileNotFoundException
	 */

	private JComboBox fromlist;
	private JComboBox tolist;
	private JComboBox daylist;
	JTextField flightText;
	JTextField airlineText;
	JTextField dayText;
	JTextField timeText;
	JTextField costText;
	JTextField nameText;
	JTextField citizenshipText;

	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) throws FileNotFoundException {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
	}

	private JPanel createSouthPanel() throws FileNotFoundException {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.add(getSouthNorthPanel(), BorderLayout.NORTH);
		panel.add(getSouthCenterPanel(), BorderLayout.CENTER);
		panel.add(getSouthSouthPanel(), BorderLayout.SOUTH);

		return panel;
	}

	private JPanel getSouthSouthPanel() {
		JPanel panel = new JPanel();
		JButton findflightButton = new JButton("Find Flights");
		findflightButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String fromValue = fromlist.getSelectedItem().toString();
				String toValue = tolist.getSelectedItem().toString();
				String dayValue = daylist.getSelectedItem().toString();
				findflight = flightManager.findFlights(fromValue, toValue, dayValue);
				printflight();
			}

		});
		panel.add(findflightButton);
		panel.setVisible(true);
		return panel;
	}

	private JPanel getSouthCenterPanel() throws FileNotFoundException {
		String weekdaylist[] = { FlightManager.WEEKDAY_ANY, FlightManager.WEEKDAY_SUNDAY, FlightManager.WEEKDAY_MONDAY,
				FlightManager.WEEKDAY_TUESDAY, FlightManager.WEEKDAY_WEDNESDAY, FlightManager.WEEKDAY_THURSDAY,
				FlightManager.WEEKDAY_FRIDAY, FlightManager.WEEKDAY_SATURDAY };

		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(3, 2);
		panel.setLayout(gridLayout);
		JLabel from = new JLabel("From:");
		from.setFont(new Font("serif", Font.PLAIN, 13));
		fromlist = new JComboBox(flightManager.getAirports().toArray());

		JLabel to = new JLabel("To:");
		to.setFont(new Font("serif", Font.PLAIN, 13));
		tolist = new JComboBox(flightManager.getAirports().toArray());

		JLabel day = new JLabel("Day:");
		day.setFont(new Font("serif", Font.PLAIN, 13));
		daylist = new JComboBox(weekdaylist);

		panel.setVisible(true);
		panel.add(from);
		panel.add(fromlist);
		panel.add(to);
		panel.add(tolist);
		panel.add(day);
		panel.add(daylist);

		return panel;
	}

	private JPanel getSouthNorthPanel() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new ListSelectionListener(){
	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedFlightCode = flightsList.getSelectedValue().substring(0, 7);
				selectedFlight = flightManager.findFlightByCode(selectedFlightCode);
				flightText.setText(selectedFlight.getCode());
				airlineText.setText(selectedFlight.getAirlineName());
				dayText.setText(selectedFlight.getWeekday());
				timeText.setText(selectedFlight.getTime());
				costText.setText(String.valueOf("$" + selectedFlight.getCostPerSeat()));
			}
				
		});
		
		scrollPane.setPreferredSize(new Dimension(450, 280));
		panel.add(scrollPane);
		panel.add(getCenterEastPanel(), BorderLayout.EAST);

		return panel;
	}

	private JPanel getCenterEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.add(getCenterEastNorthPanel(), BorderLayout.NORTH);
		panel.add(getCenterEastCenterPanel(), BorderLayout.CENTER);
		panel.add(getCenterEastSouthPanel(), BorderLayout.SOUTH);

		return panel;
	}

	private JPanel getCenterEastSouthPanel() {
		JPanel panel = new JPanel();
		JButton reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (nameText.getText() == null || citizenshipText.getText() == null)
				{
					JOptionPane.showMessageDialog(null, "Please type name and citizenship");
				}else
				{
					Reservation reservation;
					try {
						reservation = reservationManager.makeReservation(selectedFlight, nameText.getText(), citizenshipText.getText());
						String reservationcode = reservation.getCode();
						JOptionPane.showMessageDialog(null, "Reservation created. Your code is " + reservationcode);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}	
				
		});
		panel.add(reserveButton);
		return panel;

	}

	private JPanel getCenterEastCenterPanel() {
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(7, 2);
		panel.setLayout(gridLayout);

		JLabel flight = new JLabel("Flight:");
		flight.setFont(new Font("serif", Font.PLAIN, 13));
		flightText = new JTextField(20);
		flightText.setEnabled(false);

		JLabel airline = new JLabel("Airline:");
		airline.setFont(new Font("serif", Font.PLAIN, 13));
		airlineText = new JTextField(20);
		airlineText.setEnabled(false);

		JLabel day = new JLabel("Day:");
		day.setFont(new Font("serif", Font.PLAIN, 13));
		dayText = new JTextField(20);
		dayText.setEnabled(false);

		JLabel time = new JLabel("Time:");
		time.setFont(new Font("serif", Font.PLAIN, 13));
		timeText = new JTextField(20);
		timeText.setEnabled(false);

		JLabel cost = new JLabel("Cost:");
		cost.setFont(new Font("serif", Font.PLAIN, 13));
		costText = new JTextField(20);
		costText.setEnabled(false);

		JLabel name = new JLabel("Name:");
		name.setFont(new Font("serif", Font.PLAIN, 13));
		nameText = new JTextField(20);

		JLabel citizenship = new JLabel("Citizenship:");
		citizenship.setFont(new Font("serif", Font.PLAIN, 13));
		citizenshipText = new JTextField(20);

		panel.add(flight);
		panel.add(flightText);
		panel.add(airline);
		panel.add(airlineText);
		panel.add(day);
		panel.add(dayText);
		panel.add(time);
		panel.add(timeText);
		panel.add(cost);
		panel.add(costText);
		panel.add(name);
		panel.add(nameText);
		panel.add(citizenship);
		panel.add(citizenshipText);

		panel.setSize(300, 300);
		panel.setVisible(true);

		return panel;
	}

	private JPanel getCenterEastNorthPanel() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(title);
		return panel;
	}

	private void printflight() {
		
		flightsModel.clear();
		for (int i = 0; i < findflight.size(); i++) {
			flightsModel.addElement(findflight.get(i).toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}