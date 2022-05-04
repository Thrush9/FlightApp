package com.flightapp.main.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "bookings", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "PNR")
})
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotNull
	private Long flightId;
	
	private String PNR;
	
	@NotNull
	private Integer totalSeats;
	
	@NotNull
	private Boolean meal;
	
	private Boolean status = true;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "booking_seats", 
				joinColumns = @JoinColumn(name = "booking_id"), 
				inverseJoinColumns = @JoinColumn(name = "seat_id"))
	private List<Seat> seats;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "booking_passengers", 
				joinColumns = @JoinColumn(name = "booking_id"), 
				inverseJoinColumns = @JoinColumn(name = "passenger_id"))
	private List<Passenger> passengers = new ArrayList<>();
	
	public Booking() {
		
	}
	
	public Booking(String name,String email, Long flightId, Boolean meal,Integer totalSeats) {
		super();
		this.name = name;
		this.email = email;
		this.flightId = flightId;
		this.meal = meal;
		this.totalSeats = totalSeats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	
	public String getPNR() {
		return PNR;
	}

	public void setPNR(String pNR) {
		PNR = pNR;
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", name=" + name + ", email=" + email + ", flightId=" + flightId + ", PNR=" + PNR
				+ ", totalSeats=" + totalSeats + ", meal=" + meal + ", status=" + status + ", seats=" + seats
				+ ", passengers=" + passengers + "]";
	}
	
	
}
