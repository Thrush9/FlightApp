package com.flightapp.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightapp.main.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	Boolean existsByFlightNo(String flightNo);

	Optional<Flight> findByFlightNo(String flightNo);

	List<Flight> findByAirlinesId(Long airlinesId);

	@Query(nativeQuery = true, value = "SELECT f.id,f.airlines_id,f.availability,f.business_seats,f.cost,"+
		       "f.end_date,f.flight_no,f.from_place,f.instrument,f.meal,f.non_business_seats,f.start_date,f.to_place,f.total_rows,f.veg "
				+ " FROM flights f "
				+ " LEFT JOIN airlines a on a.id = f.airlines_id " 
	       + " WHERE a.enabled = 1 and f.airlines_id = :airlinesId and (f.from_place = :fromPlace or f.to_place = :toPlace) and f.start_date > :searchDate  ")
	List<Flight> searchFlightsUsingKeywords(@Param("airlinesId") Long airlinesId, @Param("fromPlace") String fromPlace,
			@Param("toPlace") String toPlace,@Param("searchDate") String searchDate);
	
	@Query(nativeQuery = true, value = "SELECT f.id,f.airlines_id,f.availability,f.business_seats,f.cost,"+
	       "f.end_date,f.flight_no,f.from_place,f.instrument,f.meal,f.non_business_seats,f.start_date,f.to_place,f.total_rows,f.veg "
			+ " FROM flights f "
			+ " LEFT JOIN airlines a on a.id = f.airlines_id "
			+ " WHERE a.enabled = 1")
	List<Flight> getFlightsforUser();
}
