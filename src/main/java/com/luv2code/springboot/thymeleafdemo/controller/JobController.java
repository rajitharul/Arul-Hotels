package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.CategoryRepository;
import com.luv2code.springboot.thymeleafdemo.dao.JobRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PilotRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Job;
import com.luv2code.springboot.thymeleafdemo.entity.Pilot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/jobs")
public class JobController {

	// load Job data

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/list")
	public String listJobs(Model theModel) {

		// this is the admin listing of the Jobss

		// add to the spring model
		theModel.addAttribute("jobs", jobRepository.findAll());
		
		return "list-jobs";
	}

	@GetMapping("/vacancyList")
	public String vacancyListJobs(@RequestParam("categoryId") int theId ,Model theModel) {

		// add to the spring model
		theModel.addAttribute("jobs", categoryRepository.findById(theId).get().getJobs());

		return "list-apply-jobs-v2";
	}



	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {


		Job theJob = new Job();

		theModel.addAttribute("job", theJob);

		theModel.addAttribute("categories", categoryRepository.findAll());

		return "job-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("jobId") int theId,
									Model theModel) {

		// getting the Job from the Repository Class
		Optional<Job> theJob = jobRepository.findById(theId);

		// setting Pilot as a model attribute to pre-populate the form
		theModel.addAttribute("job", theJob);

		theModel.addAttribute("categories", categoryRepository.findAll());


		// send over to our form
		return "job-form";
	}



	@PostMapping("/save")
	public String saveJob(@ModelAttribute("job") Job theJob) {

		// saving the Job
		jobRepository.save(theJob);

		return "redirect:/jobs/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("jobId") int theId){

		jobRepository.deleteById(theId);

		return "redirect:/jobs/list";

	}



}









