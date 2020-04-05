package com.example.springexample.city;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CityController {

	@Autowired
	private CityDAO cityDAO;
	
	@PostMapping("/city")
	public void addCity(@Valid @RequestBody City city)
	{
		cityDAO.addCity(city);
	}
	
	@GetMapping("/cities")
	public List<City> getAllCities()
	{
		return cityDAO.getAllCities();
	}
	
	@GetMapping("/city/{cityId}")
	public City getCityById(@PathVariable(value="cityId") int cityId)
	{
		return cityDAO.getCityById(cityId);
	}
	
	@GetMapping("/city/{cityName}")
	public City getCityByName(@PathVariable(value="cityName") String cityName)
	{
		return cityDAO.getCityByName(cityName);
	}
	
	@PutMapping("/city/{cityName")
	public void updateCity(@PathVariable(value="cityName") String cityName, @Valid @RequestBody City updatedCity)
	{
		cityDAO.updateCity(cityName, updatedCity);
	}
	
	@DeleteMapping("/city/{cityId}")
	public void deleteShow(@PathVariable(value="cityId") int cityId)
	{
		cityDAO.deleteCity(cityId);
	}
	
/*	@GetMapping("/cityNames")
	public List<String>getCitiesName()
	{
		
	}*/
}
