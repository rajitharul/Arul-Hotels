package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.AirportRepository;
import com.luv2code.springboot.thymeleafdemo.dao.FlightRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PilotRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PlaneRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Airport;
import com.luv2code.springboot.thymeleafdemo.entity.Flight;
import com.luv2code.springboot.thymeleafdemo.entity.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/flights")
public class FlightController {

	// load flight data

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private PlaneRepository planeRepository;

	@Autowired
	private PilotRepository pilotRepository;

	@Autowired
	private AirportRepository airportRepository;


	@GetMapping("/list")
	public String listFlights(Model theModel) {
		
		// add to the spring model
		theModel.addAttribute("flights", flightRepository.findAll());
		
		return "list-flights";
	}


	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// This will bind the Form Data
		Flight theFlight = new Flight();


		theModel.addAttribute("flight", theFlight);
		theModel.addAttribute("planes", planeRepository.findAll());
		theModel.addAttribute("pilots", pilotRepository.findAll());
		theModel.addAttribute("airports", airportRepository.findAll());


		return "flight-form";
	}



	@PostMapping("/save")
	public String saveFlight(@ModelAttribute("flight") Flight theFlight) {

		// saving the flight

		flightRepository.save(theFlight);

		return "redirect:/flights/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("flightId") int theId,
									Model theModel) {

		// getting the Flight from the Repository Class
		Optional<Flight> theFlight = flightRepository.findById(theId);

		// setting flight
		theModel.addAttribute("flight", theFlight);

		theModel.addAttribute("planes", planeRepository.findAll());
		theModel.addAttribute("pilots", pilotRepository.findAll());
		theModel.addAttribute("airports", airportRepository.findAll());


		// send over to our form
		return "flight-form";
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("flightId") int theId){

		flightRepository.deleteById(theId);

		return "redirect:/flights/list";

	}



}









