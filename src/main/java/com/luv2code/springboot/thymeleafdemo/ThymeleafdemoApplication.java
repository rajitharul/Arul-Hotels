package com.luv2code.springboot.thymeleafdemo;

import com.luv2code.springboot.thymeleafdemo.entity.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ThymeleafdemoApplication {

	public static void main(String[] args) {


		//SpringApplication.run(ThymeleafdemoApplication.class, args);

//SpringApplication.run (Application.class, args)

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ThymeleafdemoApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);


	}

}

