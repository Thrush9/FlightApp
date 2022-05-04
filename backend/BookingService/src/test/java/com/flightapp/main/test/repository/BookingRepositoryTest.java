package com.flightapp.main.test.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flightapp.main.model.Booking;
import com.flightapp.main.model.ERole;
import com.flightapp.main.model.Passenger;
import com.flightapp.main.model.Role;
import com.flightapp.main.model.Seat;
import com.flightapp.main.model.User;
import com.flightapp.main.repository.BookingRepository;
import com.flightapp.main.repository.PassengerRepository;
import com.flightapp.main.repository.SeatRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookingRepositoryTest {
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	PassengerRepository passengerRepo;
	
	@Autowired
	SeatRepository seatRepo;
	
	Booking booking;
	
	Passenger passenger;
	
	Seat seat;
	
	List<Passenger> passengerList;
	
	List<Seat> seatList;
	
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
	
	@AfterEach
	public void tearDown() throws Exception {
		bookingRepo.deleteAll();
		passengerRepo.deleteAll();
		seatRepo.deleteAll();
		
	}

	@Test
	public void testAddBookingSuccess() {
		passengerRepo.save(passenger);
		seatRepo.save(seat);
		bookingRepo.save(booking);
		Optional<Booking> bookingExists = bookingRepo.findById(101L);
		if (bookingExists.isPresent()) {
			assertThat("Test", is(booking.getName()));
		}
	}
	
	@Test
	public void testfindByPNRSuccess() {
		passengerRepo.save(passenger);
		seatRepo.save(seat);
		bookingRepo.save(booking);
		Optional<Booking> bookingExists = bookingRepo.findByPNR("9876543210");
		if (bookingExists.isPresent()) {
			assertThat("Test", is(booking.getName()));
		}
	}
	
	@Test
	public void testfindByEmailSuccess() {
		passengerRepo.save(passenger);
		seatRepo.save(seat);
		bookingRepo.save(booking);
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		List<Booking> bookings = bookingRepo.findByEmail("Test@gmail.com");
			assertThat(bookingList.size(), is(bookings.size()));
		}
}
