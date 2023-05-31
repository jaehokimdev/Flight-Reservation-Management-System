=== The project title ===
Assignment2 

=== Date ===
Thu Mar 24 2022

=== Author === 
Ryan Gu, Jaeho Kim

=== What program does ===

This program was developed to efficiently manage flight reservations to increase productivity and improve customer service.
An appropriate GUI is provided so that the end-users who use this program can recognize the functions more conveniently and intuitively.
The program also consists of two parts to manage and confirm flight reservations. 
It consists of a 'Flight' page where you can find and book a flight that meets the conditions, and a 'Reservation' page where you can confirm and edit your reservation after making a reservation.
To select a flight, you can find your flight by entering the desired conditions and then entering your personal information to proceed with the reservation. 
After the reservation is completed, you can confirm the reservation by entering personal information or flight information on the reservation page, and you can also modify it.

=== List of deficiencies if any ===

N/A

=== How to run the program ===

-Automatically done when the program is executed

1. Creating some stuffs for using function of program
 - Create an array list to store data
 - Create a scanner necessary for user input


2. load a file.
 - load a file from airports.scv, flight.scv file
 - Each type of code must be structured and called according to its specific format.
 - When loading a file, the program uses the randomAccessFile method to retrieve data more efficiently.

3. Outputting a GUI window that the user can select and input


-function of management system.

1. Find Filghts
 - user input the originating airport, the destination airport, and the day of week
 - In the filght'array list that retrieves data from the Flights.scv file, it checks for a list that matches the information entered and displays the information.
 - By using randomAccessFile method to retrieve data more efficiently.
 - user can checkout appropriate information on screen


2. Make Reservation
 - user input 'Full name' and 'Citizenship' for making reservation after checking and choosing appropriate flight information
 - This method should receive input arguments: a Flight object, the travelers name and citizenship


3. Find Reservation
 - search for an existing reservation that contains the specified reservation code, or airline or traveller’s full name
 - Show the list which matched reservation information with inputting infomation
 - Deu to loading data, also uses the randomAccessFile method for find proper information from data file

4. Update Reservation
 - user can select the list that user want to edit 
 - Edit just the name, citizenship, and status
 - After that,  click update button user can notice update by seeing update pop-up window

5. Save and close program. 
 - The persist method in the ReservationManager class saves all Reservation objects to a binary file on the hard drive.
