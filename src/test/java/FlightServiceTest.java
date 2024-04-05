import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Flight;
import models.User;
import services.FlightService;
import services.UserService;
import services.servicesImpl.FlightServiceImpl;
import services.servicesImpl.UserServiceImpl;

public class FlightServiceTest {
	private UserService userService;
    private FlightService flightService;

    @Before
    public void setUp() {
        flightService = new FlightServiceImpl();
        userService = new UserServiceImpl();
        flightService.initilizeFlightData();
    }

    @Test
    public void testSearchFlights() {
        // Test searchFlights with valid parameters
        List<Flight> flights = flightService.searchFlights("DEL", "BLR", 2, 3);
        assertTrue(!flights.isEmpty());

        // Test searchFlights with invalid parameters
        List<Flight> emptyFlights = flightService.searchFlights("XYZ", "ABC", 99, 99);
        assertTrue(emptyFlights.isEmpty());
    }

    @Test
    public void testBookFlight() {
        // Add a user
        User user = userService.addUser("u1", "Nitin", 5000);

        // Test booking a flight with valid parameters
        List<Flight> flights = flightService.searchFlights("DEL", "BLR", 2, 3);
        Flight flight = flights.get(0);
        String result = flightService.bookFlight("u1", flight.getFlightNumber(), 2, "F1",
                new ArrayList<>(Arrays.asList("1b", "2c")));
        assertEquals("Booking Successful!", result);

        // Test booking a flight with invalid parameters (e.g., insufficient funds)
        String invalidResult = flightService.bookFlight("u1", flight.getFlightNumber(), 2, "F1",
                new ArrayList<>(Arrays.asList("1b", "2c")));
        assertEquals("Insufficient balance in wallet", invalidResult);
    }
}