package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name="plane")
public class Plane {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="country_of_manufacture")
	private String countryOfManufacture;

	@Column(name="model")
	private String model;

	@Column(name="engine_capacity")
	private double engineCapacity;

	@Column(name="make_year")
	private Date makeYear;

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	@OneToMany(mappedBy="plane")
	private Set<Flight> flights;


	@Override
	public String toString() {
		return "Plane{" +
				"id=" + id +
				", name='" + name + '\'' +
				", countryOfManufacture='" + countryOfManufacture + '\'' +
				", model='" + model + '\'' +
				", engineCapacity=" + engineCapacity +
				", makeYear=" + makeYear +
				'}';
	}

	public Plane(int id, String name, String countryOfManufacture, String model, double engineCapacity, Date makeYear) {
		this.id = id;
		this.name = name;
		this.countryOfManufacture = countryOfManufacture;
		this.model = model;
		this.engineCapacity = engineCapacity;
		this.makeYear = makeYear;
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

	public String getCountryOfManufacture() {
		return countryOfManufacture;
	}

	public void setCountryOfManufacture(String countryOfManufacture) {
		this.countryOfManufacture = countryOfManufacture;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(double engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public Date getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(Date makeYear) {
		this.makeYear = makeYear;
	}

	public Plane() {

	}


}











