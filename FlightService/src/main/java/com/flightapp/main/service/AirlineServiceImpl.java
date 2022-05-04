package com.flightapp.main.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.main.exception.AirlinesAlreadyExistsException;
import com.flightapp.main.exception.AirlinesNotFoundException;
import com.flightapp.main.model.Airline;
import com.flightapp.main.repository.AirlineRepository;

@Service
public class AirlineServiceImpl implements AirlineService{

	@Autowired
	AirlineRepository airlineRepository;
	
	@Override
	public Airline registerAirlines(Airline airline) throws AirlinesAlreadyExistsException {
		Boolean exists = airlineRepository.existsByName(airline.getName());
		if(exists) {
			throw new AirlinesAlreadyExistsException("Airlines already exists with provided Name");
		}
		Airline added = airlineRepository.save(airline);
		return added;
	}

	@Override
	public Airline toggleAirlinesAvailability(String name) throws AirlinesNotFoundException {
		Optional<Airline> search = airlineRepository.findByName(name);
		if(search.isEmpty()) {
			throw new AirlinesNotFoundException("Airlines doesn't exists with provided Name");
		}
		Airline airline = search.get();
		Boolean availability = airline.getEnabled();
		airline.setEnabled(!availability);
		Airline modified = airlineRepository.save(airline);
		return modified;
	}

	@Override
	public List<Airline> fetchAllAirlines() {
		return airlineRepository.findAll();
	}

	@Override
	public Airline fetchSpecificAirlines(Long id) throws AirlinesNotFoundException {
		Optional<Airline> search = airlineRepository.findById(id);
		if(search.isEmpty()) {
			throw new AirlinesNotFoundException("Airlines doesn't exists with provided Id");
		}
		return search.get();
	}

}
