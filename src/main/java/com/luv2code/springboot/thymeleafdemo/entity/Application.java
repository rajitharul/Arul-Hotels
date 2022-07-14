package com.luv2code.springboot.thymeleafdemo.entity;

import com.luv2code.springboot.thymeleafdemo.dao.StatusRepository;
import com.luv2code.springboot.thymeleafdemo.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="application")
public class Application {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="firstName")
	private String firstName;

	@Column(name="lastName")
	private String lastName;

	@Column(name="email")
	private String email;

	@OneToMany(mappedBy="application")
	private Set<Doc> docs;


	@Column(name="mobile")
	private int mobile;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	private Status status;


	@ManyToMany
	@JoinTable(
			name = "applications_jobs",
			joinColumns = @JoinColumn(name = "application_id"),
			inverseJoinColumns = @JoinColumn(name = "job_id"))
	private Set<Job> jobs;

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}


	//convenienve method to add a job

	public void addJob(Job job){

			if (jobs == null){
					jobs  = new HashSet<>();
				this.jobs.add(job);

			}else{
				this.jobs.add(job);

			}


	}


	public Application(int id, String firstName, String lastName, String email, Set<Doc> docs, int mobile, Status status, Set<Job> jobs) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.docs = docs;
		this.mobile = mobile;
		this.status = status;
		this.jobs = jobs;
	}

	public Application(int id, String firstName, String lastName, String email, Set<Doc> docs, int mobile, Status status) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.docs = docs;
		this.mobile = mobile;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Application(int id, String firstName, String lastName, String email, Set<Doc> docs, int mobile) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.docs = docs;
		this.mobile = mobile;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Doc> getDocs() {
		return docs;
	}

	public void setDocs(Set<Doc> docs) {
		this.docs = docs;
	}


	// define constructors

	public Application(int id, String firstName, String lastName, String email, Set<Doc> docs) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.docs = docs;
	}

	public Application() {

	}

	@Override
	public String toString() {
		return "Application{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", docs=" + docs +
				'}';
	}
}











