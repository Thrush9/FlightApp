package com.flightapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.main.exception.FlightAlreadyExistsException;
import com.flightapp.main.exception.FlightNotFoundException;
import com.flightapp.main.model.Airline;
import com.flightapp.main.model.Flight;
import com.flightapp.main.repository.AirlineRepository;
import com.flightapp.main.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AirlineRepository airlineRepository;

	@Override
	public Flight scheduleFlight(Flight flight) throws FlightAlreadyExistsException {
		Boolean exists = flightRepository.existsByFlightNo(flight.getFlightNo());
		if(exists) {
			throw new FlightAlreadyExistsException("Flight already exists with provided FlightNo");
		}
		Flight added = flightRepository.save(flight);
		return added;
	}

	@Override
	public List<Flight> fetchAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Flight fetchSpecificflight(Long id) throws FlightNotFoundException {
		Optional<Flight> search = flightRepository.findById(id);
		if(search.isEmpty()) {
			throw new FlightNotFoundException("Flight doesn't exists with provided Id");
		}
		return search.get();
	}

	@Override
	public List<Flight> fetchFlightByAirlines(Long id) {
		return flightRepository.findByAirlinesId(id);
	}

	@Override
	public List<Flight> searchFlights(Long airlinesId, String source, String destination,String searchDate) {
		return flightRepository.searchFlightsUsingKeywords(airlinesId, source, destination,searchDate);
	}

	@Override
	public List<Flight> fetchAllFlightsForUser() {
		return flightRepository.getFlightsforUser();
	}
	
	

}
