package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.PlaneRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/planes")
public class PlaneController {

	// load plane data

	@Autowired
	private PlaneRepository planeRepository;


	@GetMapping("/list")
	public String listPlanes(Model theModel) {
		
		// add to the spring model
		theModel.addAttribute("planes", planeRepository.findAll());
		
		return "list-planes";
	}


	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// This will bind the Form Data
		Plane thePlane = new Plane();

		theModel.addAttribute("plane", thePlane);

		return "plane-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("planeId") int theId,
									Model theModel) {

		// getting the Plane from the Repository Class
		Optional<Plane> thePlane = planeRepository.findById(theId);

		// setting Plane as a model attribute to pre-populate the form
		theModel.addAttribute("plane", thePlane);

		// send over to our form
		return "plane-form";
	}



	@PostMapping("/save")
	public String savePlane(@ModelAttribute("plane") Plane thePlane) {

		// saving the Plane
		planeRepository.save(thePlane);

		return "redirect:/planes/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("planeId") int theId){

		planeRepository.deleteById(theId);

		return "redirect:/planes/list";

	}



}









