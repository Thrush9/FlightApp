package com.flightapp.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightapp.main.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long>{

	@Query(nativeQuery = true, value = "SELECT s.id,s.name from seats s "
			+ "LEFT JOIN booking_seats bs on bs.seat_id = s.id" + " WHERE bs.booking_id = :bookingId")
	List<Seat> findSeatsByBookingId(@Param("bookingId") Long bookingId);

}
