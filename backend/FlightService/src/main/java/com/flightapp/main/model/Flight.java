package com.flightapp.main.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "flights",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "flightNo")
	})
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 10)
	private String flightNo;
	
	@NotBlank
	@Size(min = 3,max = 40)
	private String fromPlace;
	
	@NotBlank
	@Size(min = 3,max = 40)
	private String toPlace;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@NotBlank
	private String availability;
	
	@NotBlank
	private String instrument;
	
	@NotNull
	@Min(0)
	private Integer businessSeats;
	
	@NotNull
	@Min(0)
	private Integer nonBusinessSeats;
	
	@NotNull
	private Float cost;
	
	@NotNull
	@Min(5)
	private Integer totalRows;
	
	@NotNull
	private Boolean veg = true;
	
	private Boolean meal = false;
	
	@NotNull
	private Long airlinesId;
	
    public Flight () {
    	
    }

	public Flight(String flightNo, String from, String to, LocalDate startDate, LocalDate endDate, String availability, 
			 String instrument, Integer businessSeats, Integer nonBusinessSeats,Float cost,Integer totalRows, Boolean veg, Boolean meal) {
		super();
		this.flightNo = flightNo;
		this.fromPlace = from;
		this.toPlace = to;
		this.startDate = startDate;
		this.endDate = endDate;
		this.availability = availability;
		this.instrument = instrument;
		this.businessSeats = businessSeats;
		this.nonBusinessSeats = nonBusinessSeats;
		this.cost = cost;
		this.totalRows = totalRows;
		this.veg = veg;
		this.meal = meal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String from) {
		this.fromPlace = from;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String to) {
		this.toPlace = to;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public Integer getBusinessSeats() {
		return businessSeats;
	}

	public void setBusinessSeats(Integer businessSeats) {
		this.businessSeats = businessSeats;
	}

	public Integer getNonBusinessSeats() {
		return nonBusinessSeats;
	}

	public void setNonBusinessSeats(Integer nonBusinessSeats) {
		this.nonBusinessSeats = nonBusinessSeats;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer gettotalRows() {
		return totalRows;
	}

	public void settotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Boolean getveg() {
		return veg;
	}

	public void setveg(Boolean veg) {
		this.veg = veg;
	}

	public Boolean getMeal() {
		return meal;
	}

	public void setMeal(Boolean meal) {
		this.meal = meal;
	}

	public Long getAirlinesId() {
		return airlinesId;
	}

	public void setAirlinesId(Long airlinesId) {
		this.airlinesId = airlinesId;
	}
     
}
