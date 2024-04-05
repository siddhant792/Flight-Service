package services.servicesImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dao.Storage;
import models.Booking;
import models.Flight;
import models.Seat;
import models.User;
import services.FlightService;

public class FlightServiceImpl implements FlightService{
	
	@Override
	public List<Flight> searchFlights(String from, String to, int departDate, int paxCount) {
		Map<String, Flight> allFlights = Storage.getFlights();
		
		return allFlights.values().stream()
	        .filter(flight -> flight.getFrom().equals(from))
	        .filter(flight -> flight.getTo().equals(to))
	        .filter(flight -> flight.getDepartDate() == departDate)
	        .filter(flight -> flight.getAvailableSeats().size() >= paxCount)
	        .collect(Collectors.toList());
	}

	@Override
	public String bookFlight(String userId, String flightNumber, int departDate, String fareType, List<String> seats) {
		Flight flight = Storage.getFlight(flightNumber);
		User user = Storage.getUser(userId);
		if(!flight.getFareType().equals(fareType)) {
			return "Fare type mismatch";
		}
		
		double totalPrice = seats.size() * flight.getPrice();
		if(totalPrice > user.getFunds()) {
			return "Insufficient balance in wallet";
		}
		
		Set<String> requestedSeats = new HashSet<>(seats);
		List<Seat> availableFlightSeats = flight.getAvailableSeats();
		List<Seat> unbookedSeats = flight.getAvailableSeats().stream()
                .filter(availableSeat -> requestedSeats.contains(availableSeat.getSeatId()))
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());
		
		if(unbookedSeats.size() != seats.size()) {
			return "Some of the seats are already booked!";
		}
		
		// marking seats as booked
		for (String seatId : seats) {
            for (Seat availableSeat : availableFlightSeats) {
                if (availableSeat.getSeatId().equals(seatId)) {
                    availableSeat.setAvailable(false);
                    break;
                }
            }
        }
		
		// deduct amount from user
		user.setFunds(user.getFunds() - totalPrice);
//		
		Booking booking = Booking.builder().bookingId(String.valueOf(System.currentTimeMillis()))
				.flight(flight)
				.user(user)
				.bookedSeats(unbookedSeats).build();
		
		Storage.addBooking(booking);
		return "Booking Successful!";
	}

	@Override
	public boolean initilizeFlightData() {
		// sample data inserted		
		Storage.addFlight("123", Flight.builder()
                .flightNumber("123")
                .airline("6E")
                .from("DEL")
                .to("BLR")
                .departDate(2)
                .departTime(10)
                .arrivalTime(11)
                .price(2000)
                .fareType("F1")
                .availableSeats(Arrays.asList(new Seat("1b", true),
                                        new Seat("2c", true),
                                        new Seat("4e", true)))
                .build());
		
		Storage.addFlight("156", Flight.builder()
                .flightNumber("156")
                .airline("6E")
                .from("DEL")
                .to("BLR")
                .departDate(2)
                .departTime(14)
                .arrivalTime(15)
                .price(4000)
                .fareType("F2")
                .availableSeats(Arrays.asList(new Seat("4e", true)))
                .build());
		
		Storage.addFlight("234", Flight.builder()
                .flightNumber("234")
                .airline("6E")
                .from("DEL")
                .to("HYD")
                .departDate(2)
                .departTime(15)
                .arrivalTime(16)
                .price(1000)
                .fareType("F3")
                .availableSeats(Arrays.asList(new Seat("29a", true),
                                              new Seat("40e", true),
                                              new Seat("1e", true),
                                              new Seat("4e", true)))
                .build());
		
		Storage.addFlight("456", Flight.builder()
                .flightNumber("456")
                .airline("6E")
                .from("AMD")
                .to("CCU")
                .departDate(2)
                .departTime(18)
                .arrivalTime(22)
                .price(10000)
                .fareType("F5")
                .availableSeats(Arrays.asList(new Seat("7c", true),
                                              new Seat("7d", true),
                                              new Seat("12c", true),
                                              new Seat("5f", true),
                                              new Seat("8e", true),
                                              new Seat("7e", true),
                                              new Seat("4d", true),
                                              new Seat("3e", true),
                                              new Seat("4a", true),
                                              new Seat("10a", true)))
                .build());
		
		Storage.addFlight("987", Flight.builder()
                .flightNumber("987")
                .airline("SJ")
                .from("DEL")
                .to("BLR")
                .departDate(2)
                .departTime(11)
                .arrivalTime(16)
                .price(2500)
                .fareType("F11")
                .availableSeats(Arrays.asList(new Seat("12c", true),
                                              new Seat("5f", true),
                                              new Seat("4d", true),
                                              new Seat("3e", true),
                                              new Seat("4a", true),
                                              new Seat("10a", true)))
                .build());
		return true;
	}

}
