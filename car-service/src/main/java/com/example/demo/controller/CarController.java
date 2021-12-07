package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	CarService servicio;
	
	@GetMapping
	public ResponseEntity<List<Car>> getAll(){
		List<Car> cars = servicio.getAll();
		
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(cars);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> getById(@PathVariable("id") int id){
		Car car = servicio.getCarById(id);
		if (car == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);

	}
	
	@PostMapping()
	public ResponseEntity<Car> saveUser(@RequestBody Car car){
		Car newCar = servicio.save(car);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/byuser/{userId}")
	public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId ){
		List<Car> cars = servicio.byUserId(userId);
		
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(cars);	
	}

	
}
