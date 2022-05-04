package com.flightapp.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.main.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
 
	Optional<Booking> findByPNR(String PNR);
	
	List<Booking> findByEmail(String email);
}
