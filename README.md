## What project is it?

This program was developed to efficiently manage flight reservations to increase productivity and improve customer service.
An appropriate GUI is provided so that the end-users who use this program can recognize the functions more conveniently and intuitively.
The program also consists of two parts to manage and confirm flight reservations. 
It consists of a 'Flight' page where you can find and book a flight that meets the conditions, and a 'Reservation' page where you can confirm and edit your reservation after making a reservation.
To select a flight, you can find your flight by entering the desired conditions and then entering your personal information to proceed with the reservation. 
After the reservation is completed, you can confirm the reservation by entering personal information or flight information on the reservation page, and you can also modify it.

## Languages and Tools

<p align="left"> <a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://git-scm.com/" target="_blank">

## How to run the program

-Automatically done when the program is executed

1. Creating some stuffs for using function of program
 - Create an array list to store data
 - Create a scanner necessary for user input


2. load a file.
 - load a file from airports.scv, flight.scv file
 - Each type of code must be structured and called according to its specific format.
 - When loading a file, the program uses the randomAccessFile method to retrieve data more efficiently.

3. Outputting a GUI window that the user can select and input


## Function of management system.

1. Find Filghts
 - user input the originating airport, the destination airport, and the day of week
 - In the filght'array list that retrieves data from the Flights.scv file, it checks for a list that matches the information entered and displays the information.
 - By using randomAccessFile method to retrieve data more efficiently.
 - user can checkout appropriate information on screen
 
<img width="1000" alt="1" src="https://github.com/jaehokimdev/Flight-Reservation-Management-System/assets/101899896/6ac39779-befb-4c7e-bede-36bc1367095a">

2. Make Reservation
 - user input 'Full name' and 'Citizenship' for making reservation after checking and choosing appropriate flight information
 - This method should receive input arguments: a Flight object, the travelers name and citizenship
 
<img width="1000" alt="2" src="https://github.com/jaehokimdev/Flight-Reservation-Management-System/assets/101899896/c9d29fd9-872c-47cf-b8da-ccf63d6d8984">

<img width="1000" alt="3" src="https://github.com/jaehokimdev/Flight-Reservation-Management-System/assets/101899896/4dd540d0-9d0a-4c47-95a3-6df97f96d92e">

3. Find Reservation
 - search for an existing reservation that contains the specified reservation code, or airline or traveller’s full name
 - Show the list which matched reservation information with inputting infomation
 - Deu to loading data, also uses the randomAccessFile method for find proper information from data file

<img width="1000" alt="4" src="https://github.com/jaehokimdev/Flight-Reservation-Management-System/assets/101899896/899e4161-5b3a-47cc-8312-0b6ecfeaf4fb">

<img width="1000" alt="5" src="https://github.com/jaehokimdev/Flight-Reservation-Management-System/assets/101899896/7da64b52-5602-449e-b909-d89205d16870">

4. Update Reservation
 - user can select the list that user want to edit 
 - Edit just the name, citizenship, and status
 - After that,  click update button user can notice update by seeing update pop-up window

<img width="1000" alt="6" src="https://github.com/jaehokimdev/Flight-Reservation-Management-System/assets/101899896/91fc77ad-ebf5-4921-a0bf-62f95dc95808">

<img width="1000" alt="7" src="https://github.com/jaehokimdev/Flight-Reservation-Management-System/assets/101899896/124f4c54-9192-4184-b972-58eb6219a816">

5. Save and close program. 
 - The persist method in the ReservationManager class saves all Reservation objects to a binary file on the hard drive.




