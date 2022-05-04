package com.flightapp.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.main.model.Booking;
import com.flightapp.main.model.BookingRequest;
import com.flightapp.main.model.Passenger;
import com.flightapp.main.model.Seat;
import com.flightapp.main.service.BookingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1.0/flight")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/booking/{flightId}")
	public ResponseEntity<?> bookingFlight(@Valid @RequestBody BookingRequest booking,@PathVariable Long flightId) {
		try {
			Booking added = bookingService.bookinFlight(booking,flightId);
			return new ResponseEntity<Booking>(added, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/ticket/{pnr}")
	public ResponseEntity<?> getBookingDetailsByPNR(@PathVariable String pnr) {
		try {
			Booking search = bookingService.getBookingDetailsByPNR(pnr);
			return new ResponseEntity<Booking>(search, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/booking/history/{emailId}")
	public ResponseEntity<?> getBookingHistory(@PathVariable String emailId) {
		try {
			List<Booking> bookingList = bookingService.getBookingHistory(emailId);
			return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@DeleteMapping("/booking/cancel/{pnr}")
	public ResponseEntity<?> cancelBooking(@PathVariable String pnr) {
		try {
			Booking removed = bookingService.cancelBooking(pnr);
			return new ResponseEntity<Booking>(removed, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/booking/passengers/{bookingId}")
	public ResponseEntity<?> getBookingPassengerDetails(@PathVariable Long bookingId) {
		try {
			List<Passenger> passengersList = bookingService.getBookingPassengerDetails(bookingId);
			return new ResponseEntity<List<Passenger>>(passengersList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/booking/seats/{bookingId}")
	public ResponseEntity<?> getBookingSeatDetails(@PathVariable Long bookingId) {
		try {
			List<Seat> seatsList = bookingService.getBookingSeatDetails(bookingId);
			return new ResponseEntity<List<Seat>>(seatsList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}

}
