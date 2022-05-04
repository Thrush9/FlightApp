package com.flightapp.main.service;

import java.util.List;

import com.flightapp.main.exception.AirlinesAlreadyExistsException;
import com.flightapp.main.exception.AirlinesNotFoundException;
import com.flightapp.main.model.Airline;

public interface AirlineService {
	
	public Airline registerAirlines(Airline airline) throws AirlinesAlreadyExistsException;

	public Airline toggleAirlinesAvailability(String name) throws AirlinesNotFoundException;

	public List<Airline> fetchAllAirlines();

	public Airline fetchSpecificAirlines(Long id) throws AirlinesNotFoundException;;


}
