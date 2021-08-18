package com.javacourse.project.hibernateAndJpa.restApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacourse.project.hibernateAndJpa.Business.ICityService;
import com.javacourse.project.hibernateAndJpa.Entities.City;

@RestController
@RequestMapping("/api") // bu bir standarttır api'ler böyle başlar
//Bunu class'ın tepesine koyarız çünkü bu operasyondaki her şey bununla başlar demek istiyoruz
public class CityController {

	//api 'mizin business katmanıyla bağlantısı vardı o yüzden
	private ICityService cityService;
	
	//CityService'i sadece Manager implement ediyor bunu otomatik bulması için autowired ekliyoruz yine
	@Autowired
	public CityController(ICityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("/cities")
	public List<City> get(){
		return cityService.getAll();
	}


	@PostMapping("/add")
	public void add(@RequestBody City city) {
		cityService.add(city);
	
	}
	
	@PostMapping("/update")
	public void update(@RequestBody City city) {
		cityService.update(city);
	
	}
	@PostMapping("/delete")
	public void delete(@RequestBody City city) {
		cityService.delete(city);
	
	}
	@GetMapping("/cities/{id}")
	public City getById(@PathVariable int id) {
		return cityService.getById(id);
	
	}
	
}
