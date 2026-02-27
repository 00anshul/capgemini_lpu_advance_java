package com.spring_prac;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/car")
@RestController
public class DemoController {

	@Autowired // ← Injects CarRepository bean automatically
	private CarRepository carjpa;

	@PostMapping("/a")
	public List<String> hello() {
		return List.of("a", "b", "c", "d");
	}

	@PostMapping("/add")
	public String createPlayer(@RequestBody Cricketer c) {
		System.out.println(c);
		return c.toString();
	}

	@PostMapping("/car")
	public String createCar(@RequestBody Car c) {
		Car car1 = carjpa.save(c);
		return car1.toString();
	}

	@PostMapping("/persons")
	public List<Person> addPersons(@RequestBody List<Person> persons) {
		System.out.println(persons);
		return persons;
	}

	@GetMapping("/car/{id}")
	public Car getById(@PathVariable int id) {
		Optional<Car> option = carjpa.findById(id);

		if (option.isPresent()) {
			Car c = option.get();
			return c;
		} else {
			throw new DataNotExist() ;
		}

		// Modern shorthand alternative:
		// return option.map(Car::toString).orElse("Car not found");
	}

	@DeleteMapping("/delete-id/{id}")
	public boolean deleteCar(@PathVariable int id) {
		Optional<Car> option = carjpa.findById(id);
		if (option.isPresent()) {
			carjpa.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	@PutMapping("/updatecar-id/{id}")
	public boolean updateCar(@PathVariable int id,@RequestBody Car c) {
		Optional<Car> option = carjpa.findById(id);
		if (option.isPresent()) {
			Car car  = option.get();
			car.setBrand(c.getBrand());
			car.setModel(c.getModel());
			carjpa.save(car);
			return true;
		}
		else {
			return false;
		}
	}
	

	@PatchMapping("/updatecar-id/{id}")
	public boolean updateCarData(@PathVariable int id,@RequestBody Car c) {
		Optional<Car> option = carjpa.findById(id);
		if (option.isPresent()) {
			Car car  = option.get();
			if(c.getBrand()!=null) {
				car.setBrand(c.getBrand());
			}
			if(c.getModel()!=null) {
				car.setModel(c.getModel());
			}
			carjpa.save(car);
			return true;
		}
		else {
			return false;
		}
	}
	
	@GetMapping("/carprice/{price}")
	public Car getCarByPrice(@PathVariable double price) {
		return carjpa.getByPrice(price);
	}
	
	@DeleteMapping("/deletebybrand/{brand}")
	public  void deleteBrand(@PathVariable String  brand) {
		System.out.println("Deleting brand: " + brand);  
			 carjpa.deleteByBrand(brand);
	}
	


}

