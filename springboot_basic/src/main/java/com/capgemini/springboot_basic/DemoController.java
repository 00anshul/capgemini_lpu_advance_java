package com.capgemini.springboot_basic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class DemoController {

	@PostMapping("/a")
	// @ResponseBody
	public List<String> hello() {
		return List.of("a", "b", "c", "d");
	}

	@PostMapping("/add")
	public String createplayer(@RequestBody Cricketer c) {
		System.out.println(c);
		return c.toString();
	}

	@PostMapping("/car")
	public Car addCar(@RequestBody Car car) {
		System.out.println(car);
		return car; // echoes back what you sent
	}
	
	@PostMapping("/persons")
	public List<Person> addPersons(@RequestBody List<Person> persons) {
		System.out.println(persons);
	    return persons;  // echoes back the list
	}

}
