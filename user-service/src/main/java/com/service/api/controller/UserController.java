package com.service.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.api.entity.User;
import com.service.api.model.Bike;
import com.service.api.model.Car;
import com.service.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService servicio;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		List<User> users = servicio.getAll();
		
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(users);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") int id){
		User user = servicio.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);

	}
	
	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User newUser = servicio.save(user);
		return ResponseEntity.ok(newUser);
	}
	
	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
		User user = servicio.getUserById(userId);
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Car> cars = servicio.getCars(userId);
		return ResponseEntity.ok(cars);
		
	}
	
	@GetMapping("/bikes/{userId}")
	public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
		User user = servicio.getUserById(userId);
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Bike> bikes = servicio.getBikes(userId);
		return ResponseEntity.ok(bikes);
	}
	
	
	@PostMapping("/savecar/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car ){
		
		if (servicio.getUserById(userId) == null) {
			return ResponseEntity.notFound().build();
		}
		
		Car carNew =  servicio.saveCar(userId, car);
		return ResponseEntity.ok(car);
	}
	
	/**
	@PostMapping("/savebike/{userId}")
	public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike ){
		
		if (servicio.getUserById(userId) == null) {
			return ResponseEntity.notFound().build();
		}
		
		Bike bikeNew =  servicio.saveBike(userId, bike);
		return ResponseEntity.ok(bikeNew);
	}
	
	
	@GetMapping("/getAll/{userId}")
	public ResponseEntity<Map<String,Object>> getAllVehicles(@PathVariable("userId") int userId){
		
		Map<String,Object> result = servicio.getUserAndVehicles(userId);
		return ResponseEntity.ok(result);
		
	}
	*/

}
