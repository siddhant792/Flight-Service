package models;

import java.util.List;

import lombok.Builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Booking {
	private String bookingId;
	private User user;
	private Flight flight;
	List<Seat> bookedSeats;
}
