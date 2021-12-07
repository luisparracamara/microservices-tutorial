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

import com.example.demo.entity.Bike;
import com.example.demo.service.BikeService;


@RestController
@RequestMapping("/bike")
public class BikeController {

	@Autowired
	BikeService servicio;
	
	@GetMapping
	public ResponseEntity<List<Bike>> getAll(){
		List<Bike> bikes = servicio.getAll();
		
		if (bikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(bikes);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bike> getById(@PathVariable("id") int id){
		Bike bike = servicio.getCarById(id);
		if (bike == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bike);

	}
	
	@PostMapping()
	public ResponseEntity<Bike> saveUser(@RequestBody Bike bike){
		Bike newCar = servicio.save(bike);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/byuser/{userId}")
	public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId ){
		List<Bike> cars = servicio.byUserId(userId);
		
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(cars);	
	}

	
}
