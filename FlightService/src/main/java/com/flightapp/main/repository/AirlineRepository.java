package com.flightapp.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.main.model.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>{

	Boolean existsByName(String name);
	
	Optional<Airline> findByName(String name);
	
}
