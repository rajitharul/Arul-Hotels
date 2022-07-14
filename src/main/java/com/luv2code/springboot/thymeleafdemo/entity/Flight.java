package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="flight")
public class Flight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="start_date")
	private Date startDate;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "plane_id", referencedColumnName = "id")
	private Plane plane;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "pilot_id", referencedColumnName = "id")
	private Pilot pilot;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "airport_id", referencedColumnName = "id")
	private Airport airport;

	public Flight(int id, String name, Date startDate, Plane plane, Pilot pilot, Airport airport) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.plane = plane;
		this.pilot = pilot;
		this.airport = airport;
	}


	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	@Override
	public String toString() {
		return "Flight{" +
				"id=" + id +
				", name='" + name + '\'' +
				", startDate=" + startDate +
				", plane=" + plane +
				'}';
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Flight(int id, String name, Date startDate, Plane plane) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.plane = plane;
	}

	public Flight() {

	}


}











