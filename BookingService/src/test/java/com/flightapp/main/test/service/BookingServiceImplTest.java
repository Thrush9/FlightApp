package com.flightapp.main.test.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.flightapp.main.exception.BookingNotFoundException;
import com.flightapp.main.model.Booking;
import com.flightapp.main.model.BookingRequest;
import com.flightapp.main.model.Passenger;
import com.flightapp.main.model.Role;
import com.flightapp.main.model.Seat;
import com.flightapp.main.model.User;
import com.flightapp.main.repository.BookingRepository;
import com.flightapp.main.repository.PassengerRepository;
import com.flightapp.main.repository.SeatRepository;
import com.flightapp.main.repository.UserRepository;
import com.flightapp.main.service.BookingServiceImpl;
import com.flightapp.main.service.UserServiceImpl;

public class BookingServiceImplTest {
	
	@Mock
	BookingRepository bookingRepo;
	
	@Mock
	PassengerRepository passengerRepo;
	
	@Mock
	SeatRepository seatRepo;

    Booking booking;
	
	Passenger passenger;
	
	Seat seat;
	
	BookingRequest bookingRequest;
	
	List<Passenger> passengerList;
	
	List<Seat> seatList;

	@InjectMocks
	private BookingServiceImpl bookingServiceImpl;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		booking = new Booking();
		booking.setId(101L);
		booking.setName("Test");
		booking.setEmail("Test@gmail.com");
		booking.setFlightId(111111L);
		booking.setPNR("9876543210");
		booking.setTotalSeats(20);
		booking.setMeal(true);
		booking.setStatus(true);
		
		passenger = new Passenger();
		passenger.setName("TestPassenger");
		passenger.setGender("Male");
		passenger.setAge(25);
		
		seat = new Seat();
		seat.setName("TA");
		
		passengerList = new ArrayList<>();
		passengerList.add(passenger);
		
		seatList = new ArrayList<>();
		seatList.add(seat);
		
		booking.setPassengers(passengerList);
		booking.setSeats(seatList);
	}
	
	@Test
	public void saveBookingSuccessTest()  {
		when(bookingRepo.findById(101L)).thenReturn(Optional.empty());
		when(bookingRepo.save(Mockito.any(Booking.class))).thenReturn(booking);
		
		bookingRequest = new BookingRequest();
		bookingRequest.setEmail(booking.getEmail());
		bookingRequest.setName(booking.getName());
		bookingRequest.setTotalSeats(booking.getTotalSeats());
		bookingRequest.setMeal(booking.getMeal());
		List<String> passengers = new ArrayList<>();
		passengers.add("TestPassenger-Male-25");
		bookingRequest.setPassengers(passengers);
		List<String> seats =new ArrayList<>();
		seats.add("TA");
		bookingRequest.setSeats(seats);
		
		Mockito.when(bookingRepo.save(booking)).thenReturn(booking);
		Mockito.when(passengerRepo.save(passenger)).thenReturn(passenger);
		Mockito.when(seatRepo.save(seat)).thenReturn(seat);
		Booking status = bookingServiceImpl.bookinFlight(bookingRequest,111111L);
		assertThat(booking.getEmail(), is(status.getEmail()));

	}
	
	@Test
	public void getBookingDetailsByPNRSuccessTest() throws BookingNotFoundException  {
		Optional<Booking> optBooking = Optional.of(booking);
		Mockito.when(bookingRepo.findByPNR(booking.getPNR())).thenReturn(optBooking);
		Booking status = bookingServiceImpl.getBookingDetailsByPNR(booking.getPNR());
		assertThat(optBooking.get().getEmail(), is(status.getEmail()));
	}
	
	@Test
	public void getBookinghistorySuccessTest()  {
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		Mockito.when(bookingRepo.findByEmail(booking.getEmail())).thenReturn(bookingList);
		List<Booking>  status = bookingServiceImpl.getBookingHistory(booking.getEmail());
		assertThat(bookingList.size(), is(status.size()));
	}
	
	@Test
	public void getBookingPassengerDetailsSuccessTest()  {
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		Mockito.when(bookingRepo.findById(booking.getId())).thenReturn(Optional.of(booking));
		Mockito.when(passengerRepo.findPassengersByBookingId(booking.getId())).thenReturn(passengerList);
		List<Passenger> status = passengerRepo.findPassengersByBookingId(booking.getId());
		assertThat(passengerList.size(), is(status.size()));
	}
	
	@Test
	public void getBookingSeatDetailsSuccessTest()  {
		List<Seat> seatList = new ArrayList<>();
		seatList.add(seat);
		Mockito.when(bookingRepo.findById(booking.getId())).thenReturn(Optional.of(booking));
		Mockito.when(seatRepo.findSeatsByBookingId(booking.getId())).thenReturn(seatList);
		List<Seat> status = seatRepo.findSeatsByBookingId(booking.getId());
		assertThat(seatList.size(), is(status.size()));
	}

}
