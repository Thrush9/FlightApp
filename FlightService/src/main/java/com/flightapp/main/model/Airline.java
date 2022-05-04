package com.flightapp.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "airlines",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "email") 
	})
public class Airline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String name;
	
	@NotBlank
	@Size(max = 50)
	private String poc;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	private String contact;
	    
	private String address;
	
	private Boolean enabled = true;
	
	public Airline() {
		
	}

	public Airline(String name, String poc, String email, String contact, String address) {
		super();
		this.name = name;
		this.poc = poc;
		this.email = email;
		this.contact = contact;
		this.address = address;
	}
	
	public Airline(String name, String poc, String email) {
		super();
		this.name = name;
		this.poc = poc;
		this.email = email;
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

	public String getPoc() {
		return poc;
	}

	public void setPoc(String poc) {
		this.poc = poc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
