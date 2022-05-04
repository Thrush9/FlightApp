package com.flightapp.main.service;

import java.util.List;

import javax.validation.Valid;

import com.flightapp.main.exception.BookingNotFoundException;
import com.flightapp.main.model.Booking;
import com.flightapp.main.model.BookingRequest;
import com.flightapp.main.model.Passenger;
import com.flightapp.main.model.Seat;

public interface BookingService {

	public Booking bookinFlight(@Valid BookingRequest booking,Long flightId);
	
	public Booking getBookingDetailsByPNR(String pnr) throws BookingNotFoundException;
	
	public List<Booking> getBookingHistory(String emailId);

	public Booking cancelBooking(String pnr) throws BookingNotFoundException;

	public List<Passenger> getBookingPassengerDetails(Long bookingId) throws BookingNotFoundException;

	public List<Seat> getBookingSeatDetails(Long bookingId) throws BookingNotFoundException;

}
