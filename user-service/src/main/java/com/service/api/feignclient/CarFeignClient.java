package com.service.api.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.service.api.model.Car;

//@FeignClient(name = "car-service", url = "http://localhost:8002")
@FeignClient(name = "car-service")

public interface CarFeignClient {
	
	//@PostMapping
	@PostMapping(value = "/car")
	Car save(@RequestBody Car car);
	
	@GetMapping("/car/byuser/{userId}")
	List<Car> getCars(@PathVariable("userId") int userId);
	

	

}
