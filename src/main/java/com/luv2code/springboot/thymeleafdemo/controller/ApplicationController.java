package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.ApplicationRepository;
import com.luv2code.springboot.thymeleafdemo.dao.JobRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PilotRepository;
import com.luv2code.springboot.thymeleafdemo.dao.StatusRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Application;
import com.luv2code.springboot.thymeleafdemo.entity.Job;
import com.luv2code.springboot.thymeleafdemo.entity.Pilot;
import com.luv2code.springboot.thymeleafdemo.service.DocStorageService;
import com.luv2code.springboot.thymeleafdemo.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Controller
@RequestMapping("/applications")
public class ApplicationController {

	// load application data

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private DocStorageService docStorageService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private EmailSenderService senderService;

	@Autowired
	private StatusRepository statusRepository;


	@GetMapping("/list")
	public String listApplications(Model theModel) {
		
		// add to the spring model
		theModel.addAttribute("applications", applicationRepository.findAll());
		
		return "list-applications";
	}


	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel , @RequestParam("jobId") int theId) {

		Job job  = jobRepository.getOne(theId);

		// This will bind the Form Data
		Application theApplication = new Application();
		theApplication.addJob(job);

		theModel.addAttribute("application", theApplication);
		theModel.addAttribute("jobId", theId);


		return "application-form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(Model theModel , @ModelAttribute("application") Application application ) {



		// getting the Application from the Repository Class
		List<Application> theApplications = applicationRepository.findByMobile(application.getMobile());


		// setting application as a model attribute to pre-populate the form
		theModel.addAttribute("theApplication", theApplications.get(0));

		theModel.addAttribute("docs", theApplications.get(0).getDocs());

		theModel.addAttribute("jobId", theApplications.get(0).getJobs().iterator().next().getId());


		// send over to our form
		return "application-update-form";
	}



	@PostMapping("/save")
	public String saveApplication(@ModelAttribute("application") Application theApplication , @RequestParam("files") MultipartFile[] files ,  @RequestParam("jobId") int theId) {


		Job job  = jobRepository.getOne(theId);
		theApplication.addJob(job);

		theApplication.setStatus(statusRepository.getOne(1));

		System.out.println("--------------- LOGS - saveApplication Method  - Added Job is " + theApplication.getJobs().toString());


		// saving the Application
		applicationRepository.save(theApplication);


		// Sending the Email
		senderService.sendEmail(theApplication.getEmail(), "Cinnamon Life Careers - Thank You for Registering" , "FOR GOD SO LOVED THE WORLD HE GAVE HIS ONE AND " +
				"ONLY SON THAT WHO EVER BELIEVES IN HIM WILL NOT PERISH BUT HAVE ETERNAL LIFE - JOHN 3:16 ♥" );

		for (MultipartFile file:files){
			docStorageService.saveFile(file , theApplication);
		}

		return "redirect:/";
	}



	//Update Application

	@PostMapping("/update")
	public String updateApplication(@ModelAttribute("application") Application theApplication , @RequestParam("files") MultipartFile[] files ,  @RequestParam("jobId") int theId , @RequestParam("prestatusId") int prestatusId ) {


		Job job  = jobRepository.getOne(theId);
		theApplication.addJob(job);


		System.out.println("--------------- LOGS - updateApplication Method  - Previous Status is " + prestatusId);


		// saving the Application
		applicationRepository.save(theApplication);


		// Sending the Email
		//senderService.sendEmail(theApplication.getEmail(), "Newly Saved" , "FOR GOD SO LOVED THE WORLD HE GAVE HIS ONE AND " +
				//"ONLY SON THAT WHO EVER BELIEVES IN HIM WILL NOT PERISH BUT HAVE ETERNAL LIFE - JOHN 3:16" );

		for (MultipartFile file:files){
			docStorageService.saveFile(file , theApplication);
		}

		return "redirect:/";
	}







	@GetMapping("/delete")
	public String delete(@RequestParam("applicationId") int theId , @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer){

		applicationRepository.deleteById(theId);

		return "redirect:/" + referrer;

	}

	@GetMapping("/track")
	public String trackApplication(Model theModel) {

		// This will bind the Form Data
		Application theApplication = new Application();

		theModel.addAttribute("application", theApplication);

		return "track-application";
	}


}









