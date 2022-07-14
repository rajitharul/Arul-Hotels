package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.PilotRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Pilot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/pilots")
public class PilotController {

	// load pilot data

	@Autowired
	private PilotRepository pilotRepository;


	@GetMapping("/list")
	public String listPilots(Model theModel) {
		
		// add to the spring model
		theModel.addAttribute("pilots", pilotRepository.findAll());
		
		return "list-pilots";
	}


	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// This will bind the Form Data
		Pilot thePilot = new Pilot();

		theModel.addAttribute("pilot", thePilot);

		return "pilot-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("pilotId") int theId,
									Model theModel) {

		// getting the Pilot from the Repository Class
		Optional<Pilot> thePilot = pilotRepository.findById(theId);

		// setting Pilot as a model attribute to pre-populate the form
		theModel.addAttribute("pilot", thePilot);

		// send over to our form
		return "pilot-form";
	}



	@PostMapping("/save")
	public String savePilot(@ModelAttribute("pilot") Pilot thePilot) {

		// saving the Pilot
		pilotRepository.save(thePilot);

		return "redirect:/pilots/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("pilotId") int theId){

		pilotRepository.deleteById(theId);

		return "redirect:/pilots/list";

	}



}









