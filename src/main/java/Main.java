import services.FlightService;
import services.UserService;
import services.servicesImpl.FlightServiceImpl;
import services.servicesImpl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.Flight;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		FlightService flightService = new FlightServiceImpl();
		UserService userService = new UserServiceImpl();
		flightService.initilizeFlightData();
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            String[] parts = input.split("\\s+");
            if (input.startsWith("ADDUSER")) {
            	User u = userService.addUser(parts[1], parts[2], Double.parseDouble(parts[3]));
            	System.out.println("User has been created successfully: " + u.getName() + ", " + u.getFunds());
            } else if (input.startsWith("SEARCHFLIGHT")) {
            	System.out.println("Available Flights: ");
            	List<Flight> list = flightService.searchFlights(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            	for(Flight e: list) {
            		System.out.println(e);
            	}
            } else if (input.startsWith("BOOK")) {
            	List<String> seatIds = new ArrayList<>();
            	for (int i = 5; i < parts.length ; i++) seatIds.add(parts[i]);
            	System.out.println(flightService.bookFlight(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], seatIds));
            }
            if(input.isEmpty()) break;
            System.out.println();
		}
	}

}
