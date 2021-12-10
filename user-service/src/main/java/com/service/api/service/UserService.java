package com.service.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.service.api.entity.User;
import com.service.api.feignclient.BikeFeignClient;
import com.service.api.feignclient.CarFeignClient;
import com.service.api.model.Bike;
import com.service.api.model.Car;
import com.service.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	RestTemplate rt;
	
	
	@Autowired
	CarFeignClient carFeignClient;
	
	
	@Autowired
	BikeFeignClient bikeFeignClient;
	

	
	public List<User> getAll() {
		return repository.findAll();
	}
	
	public User getUserById(int id) {
		return repository.findById(id).get();
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public List<Car> getCars(int id){
		//List<Car> cars = rt.getForObject("http://localhost:8002/car/byuser/"+id, List.class);
		List<Car> cars = rt.getForObject("http://car-service/car/byuser/"+id, List.class);
		return cars;
	}
	
	public List<Bike> getBikes(int id){
		//List<Bike> bikes = rt.getForObject("http://localhost:8003/bike/byuser/"+id, List.class);
		List<Bike> bikes = rt.getForObject("http://bike-service/bike/byuser/"+id, List.class);
		return bikes;
	}
	
	
	public Car saveCar(int userId, Car car) {
		car.setUserId(userId);
		Car carNew = carFeignClient.save(car);
		return carNew;	
	}
	
	
	public Bike saveBike(int userId, Bike bike) {
		bike.setUserId(userId);
		Bike bikeNew = bikeFeignClient.save(bike);
		return bikeNew;	
	}
	
	public Map<String, Object> getUserAndVehicles(int id){
		
		
		Map<String, Object> result = new HashMap<>();
		User user = repository.findById(id).orElse(null);
		
		if (user == null) {
			result.put("mensaje", "no existe el usuario");
			return result;
		}
		
		result.put("User", user);
		
		
		List<Car> cars = carFeignClient.getCars(id);
		if (cars.isEmpty()) {
			result.put("Cars", "ese user no tiene carros");
		}else {
			result.put("Cars", cars);
		}
		
		
		List<Bike> bikes = bikeFeignClient.getBikes(id);
		if (bikes.isEmpty()) {
			result.put("Bikes", "ese user no tiene bikes");
		}else {
			result.put("Bikes", cars);
		}
		
		
		return result;
		
	}
	
	
}
