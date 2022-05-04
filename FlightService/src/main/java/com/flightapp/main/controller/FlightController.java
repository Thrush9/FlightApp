package com.flightapp.main.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.flightapp.main.model.Airline;
import com.flightapp.main.model.Flight;
import com.flightapp.main.service.AirlineService;
import com.flightapp.main.service.FlightService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1.0/flight")
public class FlightController {
	
	@Autowired
	AirlineService airlineService;
	
	@Autowired
	FlightService flightService;
	
	@PostMapping("/airline/register")
	public ResponseEntity<?> registerAirlines(@Valid @RequestBody Airline airline) {
		try {
			Airline added = airlineService.registerAirlines(airline);
			return new ResponseEntity<Airline>(added, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@PostMapping("/airline/toggleAvailability/{name}")
	public ResponseEntity<?> toggleAvailability(@PathVariable String name) {
		try {
			Airline target = airlineService.toggleAirlinesAvailability(name);
			return new ResponseEntity<Airline>(target, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/airline/fetchAllAirlines")
	public ResponseEntity<?> fetchAllAirlines() {
		try {
			List<Airline> airlinesList = airlineService.fetchAllAirlines();
			return new ResponseEntity<List<Airline>>(airlinesList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/airline/fetchSpecificAirlines/{id}")
	public ResponseEntity<?> fetchSpecificAirlines(@PathVariable Long id) {
		try {
			Airline target = airlineService.fetchSpecificAirlines(id);
			return new ResponseEntity<Airline>(target, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@PostMapping("/airline/inventory/add")
	public ResponseEntity<?> scheduleFlight(@Valid @RequestBody Flight flight) {
		try {
			Flight added = flightService.scheduleFlight(flight);
			return new ResponseEntity<Flight>(added, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/fetchAllFlights")
	public ResponseEntity<?> fetchAllFlights() {
		try {
			List<Flight> flightsList = flightService.fetchAllFlights();
			return new ResponseEntity<List<Flight>>(flightsList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/fetchSpecificFlight/{id}")
	public ResponseEntity<?> fetchSpecificFlight(@PathVariable Long id) {
		try {
			Flight target = flightService.fetchSpecificflight(id);
			return new ResponseEntity<Flight>(target, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/fetchFlightByAirlines/{id}")
	public ResponseEntity<?> fetchFlightByAirlines(@PathVariable Long id) {
		try {
			List<Flight>  targetList = flightService.fetchFlightByAirlines(id);
			return new ResponseEntity<List<Flight>>(targetList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/fetchAllFlightsForUser")
	public ResponseEntity<?> fetchAllFlightsForUser() {
		try {
			List<Flight> flightsList = flightService.fetchAllFlightsForUser();
			return new ResponseEntity<List<Flight>>(flightsList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}

	
	@GetMapping("/search")
	public ResponseEntity<?> searchFlights(@RequestParam(name = "airlines") Long airlinesId,@RequestParam(name = "source") String source,
			@RequestParam(name = "destination") String destination,@RequestParam(name = "searchDate") String searchDate ) {
		try {
			List<Flight>  targetList = flightService.searchFlights(airlinesId,source,destination,searchDate);
			return new ResponseEntity<List<Flight>>(targetList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
}
