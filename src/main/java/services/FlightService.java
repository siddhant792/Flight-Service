package services;

import java.util.List;

import models.Flight;
import models.Seat;

public interface FlightService {
	boolean initilizeFlightData();
	List<Flight> searchFlights(String from, String to, int departDate, int paxCount);
    String bookFlight(String userId, String flightNumber, int departDate, String fareType, List<String> seats);
}
