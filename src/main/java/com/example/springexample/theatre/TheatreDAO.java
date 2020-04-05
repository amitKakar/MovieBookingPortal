package com.example.springexample.theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springexample.global.Globals;

@Service
public class TheatreDAO
{

	@Autowired
	private TheatreRepository theatreRepository;
	
	public void populateMapFromDB()
	{
		if( theatreRepository.tableSize() > 0 )
		{
			List<Theatre> theatreList = getAllTheatres();
			
			for(Theatre theatre : theatreList)
			{
				ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
				map.put(theatre.getName(), theatre.getAddress());
				Globals.theatreMap.put(map, theatre.getTheatreId());
				
			}
			
			//for (HashMap<String, String> name : Globals.theatreMap.keySet()) 
	           // System.out.println("key: " + name);
		}
	}
	
	public void addTheatre(Theatre theatre)
	{
		int currentTheatreId = Theatre.getCurrentTheatreId();
		
		if( currentTheatreId == 0 && theatreRepository.tableSize() > 0)
		{
			currentTheatreId = theatreRepository.currentTheatreId();
		}
		
		Theatre.setCurrentTheatreId(currentTheatreId + 1);
		theatre.setTheatreId(currentTheatreId + 1);
		
		if( Globals.theatreMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put(theatre.getName(), theatre.getAddress());
		Globals.theatreMap.put(map, theatre.getTheatreId());
		
		theatreRepository.save(theatre);
		
		
		
		/*City city = new City();
		city.setCityName(theatre.getCity());
		city.setTheatreId(theatre.getTheatreId());
		cityDAO.addCity(city);*/
		
	}
	
	public List<Theatre> getAllTheatres()
	{
		List<Theatre> theatreList=new ArrayList<>();
		theatreRepository.findAll().forEach(theatreList::add);
		return theatreList;
	}
	
	public Theatre getTheatreById(int theatreId)
	{
		Optional<Theatre> theatreContainer = theatreRepository.findById(theatreId);
		return theatreContainer.get();
	}
	
	public Theatre getTheatreByNameAndAddress(Theatre theatre)
	{
		if( Globals.theatreMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		 for (ConcurrentHashMap<String, String> name : Globals.theatreMap.keySet()) 
	            System.out.println("key: " + name);
		
		 ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put(theatre.getName(), theatre.getAddress());
		
		Integer theatreId = Globals.theatreMap.get(map);
		
		if(null!= theatreId)
		{
			return getTheatreById(theatreId);
		}
		
		return null;
	}
	
	@Transactional
	public void updateTheatre(String name, Theatre updatedTheatre)
	{
		if( Globals.theatreMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(name, updatedTheatre.getAddress());
		
		Integer theatreId = Globals.theatreMap.get(map);
		
		if(null!= theatreId)
		{
			theatreRepository.update(theatreId, updatedTheatre.getName(),
					updatedTheatre.getNumberOfScreens());
		}
		
		if(!name.equals(updatedTheatre.getName()))
		{
			Globals.theatreMap.remove(map);
			
			ConcurrentHashMap<String, String> newMap = new ConcurrentHashMap<String, String>();
			newMap.put(updatedTheatre.getName(), updatedTheatre.getAddress());
			
			Globals.theatreMap.put(newMap, theatreId);
			
		}
	}
	
	public void deleteTheatreById(int theatreId)
	{
		theatreRepository.deleteById(theatreId);
	}
	
	public void deleteTheatreByName(Theatre theatre)
	{
		if( Globals.theatreMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(theatre.getName(), theatre.getAddress());
		
		Integer theatreId = Globals.theatreMap.get(map);
		
		if(null!= theatreId)
		{
			Globals.theatreMap.remove(map);
			theatreRepository.deleteById(theatreId);
		}
	}
	
	public ArrayList<String> getAllCities()
	{
		return theatreRepository.getAllCities();
	}
	
	public ArrayList<Theatre> getAllTheatresByCityName(String city)
	{
		return theatreRepository.getAllTheatresByCityName(city);
	}
	
	public Theatre getTheatreByCityNameAndId(String city, int theatreId)
	{
		return theatreRepository.getTheatreByCityNameAndId(city, theatreId);
	}

	public ArrayList<Theatre> getAllTheatresByCityNameAndOperatorId(String city, int operatorId) {
		
		return theatreRepository.getTheatreByCityNameAndOperatorId(city, operatorId);
	}

	public ArrayList<String> getAllCitiesByOperator(int operatorId) {
		// TODO Auto-generated method stub
		return theatreRepository.getAllCitiesByOperator(operatorId);
	}
	
	public ArrayList<Theatre> getAllTheatresByOperator(int operatorId)
	{
		return theatreRepository.getAllTheatresByOperator(operatorId);
	}
	
}