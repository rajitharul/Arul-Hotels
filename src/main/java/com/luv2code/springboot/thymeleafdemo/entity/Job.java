package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="job")
public class Job {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name ;

	@Column(name="description")
	private String description;

	@Column(name="number_of_vacancies")
	private int numberOfVacancies;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;


	@ManyToMany(mappedBy="jobs")
	private Set<Application> applications;

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	public Job(int id, String name, String description, int numberOfVacancies, Category category, Set<Application> applications) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.numberOfVacancies = numberOfVacancies;
		this.category = category;
		this.applications = applications;
	}

	public Job(int id, String name, String description, int numberOfVacancies, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.numberOfVacancies = numberOfVacancies;
		this.category = category;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfVacancies() {
		return numberOfVacancies;
	}

	public void setNumberOfVacancies(int numberOfVacancies) {
		this.numberOfVacancies = numberOfVacancies;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

// define constructors

	public Job() {

	}

}











