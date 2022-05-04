package com.flightapp.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.main.exception.BookingNotFoundException;
import com.flightapp.main.model.Booking;
import com.flightapp.main.repository.BookingRepository;
import com.flightapp.main.repository.PassengerRepository;
import com.flightapp.main.repository.SeatRepository;
import com.flightapp.main.model.BookingRequest;
import com.flightapp.main.model.Passenger;
import com.flightapp.main.model.Seat;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	SeatRepository SeatRepository;

	@Override
	public Booking bookinFlight(BookingRequest booking,Long flightId) {
		
		List<Passenger> passengers = new ArrayList<>();
		List<Seat> seats = new ArrayList<>();
		
		Booking newBooking = new Booking(booking.getName(), booking.getEmail(), flightId, booking.getMeal(),booking.getTotalSeats());

		
		List<String> passengerStrings = booking.getPassengers();
		passengerStrings.forEach(string -> {
			Passenger passenger = this.createPassengerByString(string);
			passengers.add(passenger);
		});

		List<String> seatNames = booking.getSeats();
		seatNames.forEach(name -> {
			Seat added = this.createSeatByName(name);
			seats.add(added);
		});
		newBooking.setPassengers(passengers);
		newBooking.setSeats(seats);	
		
		Long number = (long) (Math.floor(Math.random() * (9 * Math.pow(10, 10 - 1))) + Math.pow(10, (10 - 1)));
		String pnr = String.valueOf(number);
		newBooking.setPNR(pnr);
		Booking saved = bookingRepository.save(newBooking);
		return saved;
	}
	
	private Passenger createPassengerByString(String str) {
		String[] strArray = str.split("-");
		String name = strArray[0];
		String gender = strArray[1];
		Integer age = Integer.parseInt(strArray[2]);
		Passenger passenger = new Passenger(name,gender,age);
		return passengerRepository.save(passenger);
		
	}
	
	private Seat createSeatByName(String name) {
		Seat seat = new Seat(name);
		return SeatRepository.save(seat);
	}
	
	@Override
	public Booking getBookingDetailsByPNR(String pnr) throws BookingNotFoundException {
		Optional<Booking> search = bookingRepository.findByPNR(pnr);
		if(search.isEmpty()) {
			throw new BookingNotFoundException("No Booking Found with this PNR Number");
		}
		return search.get();
	}
	
	@Override
	public List<Booking> getBookingHistory(String emailId) {
		return bookingRepository.findByEmail(emailId);
	}

	@Override
	public Booking cancelBooking(String pnr) throws BookingNotFoundException {
		Optional<Booking> search = bookingRepository.findByPNR(pnr);
		if(search.isEmpty()) {
			throw new BookingNotFoundException("No Booking Found with this PNR Number");
		}
		Booking target = search.get();
		target.setStatus(false);
		return bookingRepository.save(target);
	}

	@Override
	public List<Passenger> getBookingPassengerDetails(Long bookingId) throws BookingNotFoundException {
		Optional<Booking> search = bookingRepository.findById(bookingId);
		if(search.isEmpty()) {
			throw new BookingNotFoundException("No Booking Found with this BookingId");
		}
		Booking target = search.get();
		List<Passenger> passengersList = passengerRepository.findPassengersByBookingId(bookingId);
		return passengersList;
	}

	@Override
	public List<Seat> getBookingSeatDetails(Long bookingId) throws BookingNotFoundException {
		Optional<Booking> search = bookingRepository.findById(bookingId);
		if(search.isEmpty()) {
			throw new BookingNotFoundException("No Booking Found with this BookingId");
		}
		Booking target = search.get();
		List<Seat> seatsList = SeatRepository.findSeatsByBookingId(bookingId);
		return seatsList;
	}

}
