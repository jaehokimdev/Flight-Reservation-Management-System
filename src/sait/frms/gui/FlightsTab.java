package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.gui.FlightsTab.MyListSelectionListener;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase implements ActionListener
{
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
	private JList<Flight> flightsList;
	
	private DefaultListModel<Flight> flightsModel;
	
	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 * @throws FileNotFoundException 
	 */
	
	private String fromValue;
	private String toValue;
	private String dayValue;
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
				fromValue = fromlist.getSelectedItem().toString();
				toValue = tolist.getSelectedItem().toString();
				dayValue = daylist.getSelectedItem().toString();
				flightManager.findFlights(fromValue, toValue, dayValue);
				printflight();
				getCenterCenterPanel();
			}
			
		});
		panel.add(findflightButton);
		panel.setVisible(true);
		return panel;
	}

	private JPanel getSouthCenterPanel() throws FileNotFoundException {		
		flightManager = new FlightManager();
		String weekdaylist[] = {"Any", "Sunday", "Monday", "Tuesday", "Wednessday", "Thursday", "Friday", "Saturday"};

		
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(3,2);
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
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(getCenterCenterPanel(), BorderLayout.CENTER);
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
		panel.add(reserveButton);
		return panel;

	}

	private JPanel getCenterEastCenterPanel() {
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(7,2);
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
		
		panel.setSize(300,300);
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

	private JPanel getCenterCenterPanel() 
	{ 
    JPanel panel = new JPanel();
    flightsModel = new DefaultListModel<>();
    

	flightsList = new JList<>(flightsModel);
	
	// User can only select one item at a time.
	flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
	// Wrap JList in JScrollPane so it is scrollable.
	JScrollPane scrollPane = new JScrollPane(this.flightsList);
	
	flightsList.addListSelectionListener(new MyListSelectionListener());
	scrollPane.setPreferredSize(new Dimension(450,280));
	panel.add(scrollPane);
	
	return panel; 
	}
	
	public void printflight()
	{
		flightsModel.clear();
		for (int i = 0; i < flightManager.getfindFlight().size() ; i++)
	    {
	    	flightsModel.addElement(flightManager.getfindFlight().get(i));
	    }
	}


	public class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



	
}