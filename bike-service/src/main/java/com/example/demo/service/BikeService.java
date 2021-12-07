package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bike;
import com.example.demo.repository.BikeRepository;


@Service
public class BikeService {

	@Autowired
	BikeRepository repository;
	
	public List<Bike> getAll(){
		return repository.findAll();
	}
	
	public Bike getCarById(int id) {
		return repository.findById(id).get();
	}
	
	public Bike save(Bike bike) {
		return repository.save(bike);
	}
	
	public List<Bike> byUserId(int id){
		return repository.findByUserId(id);
	}
	
}
