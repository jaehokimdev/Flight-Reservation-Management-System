package sait.frms.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	private JList<Reservation> reservationsList;
	
	private DefaultListModel<Reservation> reservationModel;
	
	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
	}
	
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(getSouthNorthPanel(), BorderLayout.NORTH);
		panel.add(getSouthCenterPanel(), BorderLayout.CENTER);
		panel.add(getSouthSouthPanel(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel getSouthSouthPanel() {
		JPanel panel = new JPanel();
		JButton findReservationButton = new JButton("Find Reservations");
		panel.add(findReservationButton);
		return panel;
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel getSouthCenterPanel() 
	{
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(3,1);
		panel.setLayout(gridLayout);

		JLabel code = new JLabel("Code:");
		code.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField codetext = new JTextField();
		
		JLabel airline = new JLabel("Airline:");
		airline.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField airlinetext = new JTextField();
		
		JLabel name = new JLabel("Name");
		name.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField nametext = new JTextField();
		
		panel.setVisible(true);
		panel.add(code);
		panel.add(codetext);
		panel.add(airline);
		panel.add(airlinetext);
		panel.add(name);
		panel.add(nametext);
		
		return panel;
	}
	
	private JPanel getSouthNorthPanel() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Search", SwingConstants.CENTER);
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
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
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
	    reservationModel = new DefaultListModel<>();
	    reservationsList = new JList<>(reservationModel);
		
		// User can only select one item at a time.
	    reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		
		reservationsList.addListSelectionListener(new ListSelectionListener()
				{
				@Override
				public void valueChanged(ListSelectionEvent e) 
				{
				
				}
				});
		
	
		scrollPane.setPreferredSize(new Dimension(500,280));
		panel.setLayout(new BorderLayout());
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
		JButton reserveButton = new JButton("Update");
		panel.add(reserveButton);
		return panel;

	}

	private JPanel getCenterEastCenterPanel() 
	{
		String statuslist[] = {"Active", "Inactive"};
		
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(7,2);
		
		panel.setLayout(gridLayout);
		
		JLabel code = new JLabel("Code:");
		code.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField codeText = new JTextField(20);
		codeText.setEnabled(false);

		JLabel flight = new JLabel("Flight:");
		flight.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField flightText = new JTextField(20);
		flightText.setEnabled(false);

		JLabel airline = new JLabel("Airline:");
		airline.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField airlineText = new JTextField(20);
		airlineText.setEnabled(false);

		JLabel cost = new JLabel("Cost:");
		cost.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField costText = new JTextField(20);
		costText.setEnabled(false);

		JLabel name = new JLabel("Name:");
		name.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField nameText = new JTextField(20);
		nameText.setEnabled(false);

		JLabel citizenship = new JLabel("Citizenship:");
		citizenship.setFont(new Font("serif", Font.PLAIN, 13));
		JTextField citizenshipText = new JTextField(20);		
		
		JLabel status = new JLabel("Status:");
		status.setFont(new Font("serif", Font.PLAIN, 13));
		JComboBox statuscomboBox = new JComboBox(statuslist);
		
		panel.setVisible(true);
		panel.add(code);
		panel.add(codeText);
		panel.add(flight);
		panel.add(flightText);
		panel.add(airline);
		panel.add(airlineText);
		panel.add(cost);
		panel.add(costText);
		panel.add(name);
		panel.add(nameText);
		panel.add(citizenship);
		panel.add(citizenshipText);
		panel.add(status);
		panel.add(statuscomboBox);
		

		return panel;
	}

	private JPanel getCenterEastNorthPanel() {
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(title);
		return panel;
	}

	
}
