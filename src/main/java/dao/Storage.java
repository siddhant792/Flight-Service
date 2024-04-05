package dao;

import java.util.HashMap;
import java.util.Map;

import models.Booking;
import models.Flight;
import models.User;

public class Storage {
	private static Map<String, Flight> flights;
	private static Map<String, User> users;
	private static Map<String, Booking> bookings;
	
	public Storage() {}
	
	public static Map<String, Flight> getFlights() {
		if(flights == null) {
			flights = new HashMap<>();
		}
		return flights;
	}
	
	public static void addFlight(String flightNumber, Flight flight) {
		if(flights == null) {
			flights = new HashMap<>();
		}
		flights.put(flightNumber, flight);
	}
	
	public static Flight getFlight(String flightId) {
		return flights.get(flightId);
	}
	
	public static User getUser(String userId) {
		return users.get(userId);
	}
	
	public static Map<String, User> getUsers() {
		if(users == null) {
			users = new HashMap<>();
		}
		return users;
	}
	
	public static void addUser(String userId, User user) {
		if(users == null) {
			users = new HashMap<>();
		}
		users.put(userId, user);
	}
	
	public static void addBooking(Booking booking) {
		if(bookings == null) {
			bookings = new HashMap<>();
		}
		bookings.put(booking.getBookingId(), booking);
	}
}
