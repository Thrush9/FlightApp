package com.flightapp.main.test.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flightapp.main.controller.BookingController;
import com.flightapp.main.model.Booking;
import com.flightapp.main.model.ERole;
import com.flightapp.main.model.Passenger;
import com.flightapp.main.model.Role;
import com.flightapp.main.model.Seat;
import com.flightapp.main.model.User;
import com.flightapp.main.service.BookingServiceImpl;
import com.flightapp.main.service.UserServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookingControllerTest {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private MockMvc mockMvc;
	
    private Booking booking;
	private Passenger passenger;
	private Seat seat;
	
	@MockBean
	private BookingServiceImpl bookingService;
	
	
	@InjectMocks
	private BookingController bookingController;
	
	private List<Booking> bookingList;
	private List<Passenger> passengerList;
	private List<Seat> seatList;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
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
		
		bookingList = new ArrayList<>();
		bookingList.add(booking);
	}

	@Test
	public void getBookingDetailsByPNRSuccess() throws Exception {
		when(bookingService.getBookingDetailsByPNR(booking.getPNR())).thenReturn(booking);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/flight/ticket/9876543210")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getBookingHistorySuccess() throws Exception {
		when(bookingService.getBookingHistory(booking.getEmail())).thenReturn(bookingList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/flight/booking/history/Test@gmail.com")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getpassengerDetailsSuccess() throws Exception {
		when(bookingService.getBookingPassengerDetails(booking.getId())).thenReturn(passengerList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/flight/booking/passengers/101")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getBookingSeatDetailsSuccess() throws Exception {
		when(bookingService.getBookingSeatDetails(booking.getId())).thenReturn(seatList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/flight/booking/seats/101")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}


	private static String asJsonString(final Object obj) {
		try {
			ObjectMapper objmapper = new ObjectMapper();
			objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objmapper.registerModule(new JavaTimeModule());
			return objmapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
