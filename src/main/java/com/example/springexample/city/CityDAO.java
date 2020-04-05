package com.example.springexample.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springexample.global.Globals;


@Service
public class CityDAO {

	@Autowired
	private CityRepository cityRepository;
	
	public void populateMapFromDB()
	{
		if( cityRepository.tableSize() > 0 )
		{
			List<City> cityList = getAllCities();
			
			for(City city : cityList)
			{

				Globals.cityMap.put(city.getCityName(), city.getCityId());
				
			}
			
			for (ConcurrentHashMap<String, String> name : Globals.theatreMap.keySet()) 
	            System.out.println("key: " + name);
		}
	}
	
	public void addCity(City city)
	{
		if(Globals.cityMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		if(!Globals.cityMap.isEmpty())
		{
			if(null == Globals.cityMap.get(city.getCityName()))
			{
				cityRepository.save(city);
				Globals.cityMap.put(city.getCityName(), city.getCityId());
			}
		}
		else
		{
			cityRepository.save(city);
			Globals.cityMap.put(city.getCityName(), city.getCityId());
		}
		
		
	}
	
	public List<City> getAllCities()
	{
		List<City> cityList=new ArrayList<>();
		cityRepository.findAll().forEach(cityList::add);
		return cityList;
	}
	
	public City getCityById(int cityId)
	{
		Optional<City> cityContainer = cityRepository.findById(cityId);
		if(null!=cityContainer)
		{
			return cityContainer.get();
		}
		return null;
	}
	
	public City getCityByName(String cityName) {
		Integer cityId = Globals.cityMap.get(cityName);
		if(null != cityId)
		{
			return getCityById(cityId);
		}
		return null;
	}
	
	/*
	public Show getShowbyName(String name)
	{
		Integer showId = Globals.showMap.get(name);
		if(null != showId)
		{
			return getShowById(showId);
		}
		return null;
	}
	*/
	
	public void updateCity(String cityName, City updatedCity)
	{
		Integer cityId = Globals.cityMap.get(cityName);
		if(null != cityId)
		{
			cityRepository.save(updatedCity);
		}
	}
	
	public void deleteCity(int cityId)
	{
		cityRepository.deleteById(cityId);
	}
	
	public void deleteCityByName(String cityName)
	{
		Integer cityId = Globals.cityMap.get(cityName);
		if(null != cityId)
		{
			cityRepository.deleteById(cityId);
			Globals.cityMap.remove(cityId);
		}
	}
	
	/*public List<String> getCityName(int theatreId)
	{
		List<String>cities=new ArrayList<>();
		cities=cityRepository.getScreensByTheatreId(theatreId);
		return cities;
	}*/

}
