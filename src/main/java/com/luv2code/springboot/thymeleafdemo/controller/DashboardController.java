package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.*;
import com.luv2code.springboot.thymeleafdemo.entity.Airport;
import com.luv2code.springboot.thymeleafdemo.entity.Application;
import com.luv2code.springboot.thymeleafdemo.entity.Flight;
import com.luv2code.springboot.thymeleafdemo.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.Set;

@Controller
@RequestMapping("/")
public class DashboardController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private StatusRepository statusRepository;






	@GetMapping("/")
	public String openDashBoard(Model theModel) {

		theModel.addAttribute("categories", categoryRepository.findAll());


		return "dashboard-v2";
	}

	@GetMapping("/admin/mainPanel")
	public String openIndex() {
		return "main-panel";
	}


	@GetMapping("/admin/mainPanel/copyLink")
	public String copyLink() {
		String testString = "localhost:8080";
		StringSelection stringSelectionObj = new StringSelection(testString);
		Clipboard clipboardObj = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboardObj.setContents(stringSelectionObj, null);
		return "redirect:/admin/mainPanel";
	}


	@GetMapping("/admin/listApplications")
	public String listApplciations(Model theModel , String keywordCategory , String keywordJob , String keywordStatus , String filterBy) {


		List<Application> filteredApplications;

		if(keywordCategory == null && keywordJob == null){
			System.out.println("------------------------No Filter in Place-----------------------");

			 filteredApplications  = applicationRepository.findAll();
		}else {

			if (filterBy.equals("Job")){
				System.out.println("Filtering By Jobs ------------------------------");
				Set<Job> filteredJobs = jobRepository.findByName(keywordJob);
				filteredApplications = applicationRepository.findByJobsIn(filteredJobs);
			}else if (filterBy.equals("Status")){
				System.out.println("Filtering By Status ------------------------------");

				filteredApplications = applicationRepository.findByStatusId(Integer.parseInt(keywordStatus));

			}else {
				System.out.println("Filtering By None ------------------------------");

				filteredApplications  = applicationRepository.findAll();

			}

		}



		// add to the spring model
		theModel.addAttribute("applications",filteredApplications);
		theModel.addAttribute("categories", categoryRepository.findAll());
		theModel.addAttribute("jobs", jobRepository.findAll());
		theModel.addAttribute("statuses", statusRepository.findAll());


		theModel.addAttribute("filter", "");




		System.out.println(" ------------------- Keyword Category is    ------------- " + keywordCategory);

		System.out.println(" ------------------- keywordJob is    ------------- " + keywordJob);
		System.out.println(" ------------------- keywordStaus  is    ------------- " + keywordStatus);


		return "list-admin-applications";
	}


	@GetMapping("/admin/showApplication")
	public String showFormForUpdate(@RequestParam("applicationId") int theId,
									Model theModel) {


		Application theApplication = applicationRepository.getOne(theId);


		theModel.addAttribute("theApplication",theApplication);
		theModel.addAttribute("docs", theApplication.getDocs());

		theModel.addAttribute("statuses", statusRepository.findAll());

		theModel.addAttribute("jobId", theApplication.getJobs().iterator().next().getId());

		theModel.addAttribute("prestatusId", theApplication.getStatus().getId());


		// send over to our form
		return "application-admin-update-form";
	}




}








