package com.flightapp.main.service;

import java.util.List;

import com.flightapp.main.exception.FlightAlreadyExistsException;
import com.flightapp.main.exception.FlightNotFoundException;
import com.flightapp.main.model.Flight;

public interface FlightService {

	public Flight scheduleFlight(Flight flight) throws FlightAlreadyExistsException;

	public List<Flight> fetchAllFlights();

	public Flight fetchSpecificflight(Long id) throws FlightNotFoundException;

	public List<Flight>  fetchFlightByAirlines(Long id);
	
	public List<Flight> fetchAllFlightsForUser();

	public List<Flight> searchFlights(Long airlinesId, String source, String destination, String searchDate);


}
