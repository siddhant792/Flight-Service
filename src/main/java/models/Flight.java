package models;

import java.util.List;

import lombok.Builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class Flight {
	private String flightNumber;
    private String airline;
    private String from;
    private String to;
    private int departDate;
    private double departTime;
    private double arrivalTime;
    private double price;
    private String fareType;
    private List<Seat> availableSeats;
}
