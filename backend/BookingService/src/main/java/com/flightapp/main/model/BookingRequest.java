package com.flightapp.main.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookingRequest {
	
	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotNull
	private Integer totalSeats;
	
	@NotNull
	private Boolean meal;
	
	private List<String> seats;
	
	private List<String> passengers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Boolean getMeal() {
		return meal;
	}

	public void setMeal(Boolean meal) {
		this.meal = meal;
	}

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	public List<String> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<String> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "BookingRequest [name=" + name + ", email=" + email + ", totalSeats=" + totalSeats + ", meal=" + meal
				+ ", seats=" + seats + ", passengers=" + passengers + "]";
	}
	
	
}
