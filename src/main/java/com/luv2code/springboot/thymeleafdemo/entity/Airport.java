package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="airport")
public class Airport {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="country")
	private String country;

	@Column(name="city")
	private String city;

	@Column(name="registration_cost")
	private double registrationCost;

	@OneToMany(mappedBy="airport")
	private Set<Flight> flights;

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Airport{" +
				"id=" + id +
				", name='" + name + '\'' +
				", country='" + country + '\'' +
				", city='" + city + '\'' +
				", registrationCost=" + registrationCost +
				'}';
	}

	public Airport(int id, String name, String country, String city, double registrationCost) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.registrationCost = registrationCost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getRegistrationCost() {
		return registrationCost;
	}

	public void setRegistrationCost(double registrationCost) {
		this.registrationCost = registrationCost;
	}

// define constructors

	public Airport() {

	}


}











