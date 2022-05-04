package com.flightapp.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightapp.main.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	@Query(nativeQuery = true, value = "SELECT p.id,p.name,p.gender,p.age from passengers p "
			+ "LEFT JOIN booking_passengers bp on bp.passenger_id = p.id" + " WHERE bp.booking_id = :bookingId")
	List<Passenger> findPassengersByBookingId(@Param("bookingId") Long bookingId);

}
