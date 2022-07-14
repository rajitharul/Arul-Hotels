package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.CabinCrewMemberRepository;
import com.luv2code.springboot.thymeleafdemo.entity.CabinCrewMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/cabincrewmembers")
public class CabinCrewMemberController {

	// load cabincrewmember data

	@Autowired
	private CabinCrewMemberRepository cabinCrewMemberRepository;


	@GetMapping("/list")
	public String listCabinCrewMembers(Model theModel) {
		
		theModel.addAttribute("cabinCrewMembers", cabinCrewMemberRepository.findAll());
		
		return "list-cabinCrewMembers";
	}


	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// This will bind the Form Data
		CabinCrewMember theCabinCrewMember = new CabinCrewMember();

		theModel.addAttribute("cabinCrewMember", theCabinCrewMember);

		return "cabinCrewMember-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("cabinCrewMemberId") int theId,
									Model theModel) {

		// getting the Airport from the Repository Class
		Optional<CabinCrewMember> theCabinCrewMember = cabinCrewMemberRepository.findById(theId);

		// setting airport as a model attribute to pre-populate the form
		theModel.addAttribute("cabinCrewMember", theCabinCrewMember);

		// send over to our form
		return "cabinCrewMember-form";
	}



	@PostMapping("/save")
	public String saveCabinCrewMember(@ModelAttribute("cabinCrewMember") CabinCrewMember theCabinCrewMember) {

		// saving the airport
		cabinCrewMemberRepository.save(theCabinCrewMember);

		return "redirect:/cabincrewmembers/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("cabinCrewMemberId") int theId){

		cabinCrewMemberRepository.deleteById(theId);

		return "redirect:/cabincrewmembers/list";

	}



}









