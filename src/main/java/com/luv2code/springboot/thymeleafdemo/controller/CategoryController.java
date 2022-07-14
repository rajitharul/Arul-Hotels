package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.CategoryRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;


@Controller
@RequestMapping("/categories")
public class CategoryController {

	// load category data

	@Autowired
	private CategoryRepository categoryRepository;



	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// This will bind the Form Data
		Category theCategory = new Category();

		theModel.addAttribute("category", theCategory);

		return "category-form";
	}

	@PostMapping("/save")
	public RedirectView saveCategory(Category category,
									 @RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		category.setPhotos(fileName);

		Category savedCategory = categoryRepository.save(category);

		String uploadDir = "src/main/resources/static/img/category-photos/" + savedCategory.getId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		return new RedirectView("/admin/mainPanel", true);
	}




}









