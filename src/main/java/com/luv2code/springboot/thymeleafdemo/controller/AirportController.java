package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.AirportRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/airports")
public class AirportController {

	// load airport data

	@Autowired
	private AirportRepository airportRepository;


	@GetMapping("/list")
	public String listAirports(Model theModel) {
		
		// add to the spring model
		theModel.addAttribute("airports", airportRepository.findAll());
		
		return "list-airports";
	}


	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// This will bind the Form Data
		Airport theAirport = new Airport();

		theModel.addAttribute("airport", theAirport);

		return "airport-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("airportId") int theId,
									Model theModel) {

		// getting the Airport from the Repository Class
		Optional<Airport> theAirport = airportRepository.findById(theId);

		// setting airport as a model attribute to pre-populate the form
		theModel.addAttribute("airport", theAirport);

		// send over to our form
		return "airport-form";
	}



	@PostMapping("/save")
	public String saveAirport(@ModelAttribute("airport") Airport theAirport) {

		// saving the airport
		airportRepository.save(theAirport);

		return "redirect:/airports/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("airportId") int theId){

		airportRepository.deleteById(theId);

		return "redirect:/airports/list";

	}



}









