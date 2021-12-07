package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	CarRepository repository;
	
	public List<Car> getAll(){
		return repository.findAll();
	}
	
	public Car getCarById(int id) {
		return repository.findById(id).get();
	}
	
	public Car save(Car car) {
		return repository.save(car);
	}
	
	public List<Car> byUserId(int id){
		return repository.findByUserId(id);
	}
	
}
