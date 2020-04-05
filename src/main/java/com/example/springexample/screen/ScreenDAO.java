package com.example.springexample.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springexample.global.Globals;
import com.example.springexample.seat.SeatDAO;

@Service
public class ScreenDAO {
	
	@Autowired
	ScreenRepository screenRepository;
	
	@Autowired
	SeatDAO seatDAO;
	
	public void populateMapFromDB()
	{
		
		if( screenRepository.tableSize() > 0 )
		{
			List<Screen> screenList = getAllScreens();
			
			for(Screen screen : screenList)
			{
				ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
				map.put(screen.getTheatreId(), screen.getName());
				
				Globals.screenMap.put(map, screen.getScreenId());
			}
			
			for (ConcurrentHashMap<Integer, String> name : Globals.screenMap.keySet()) 
	            System.out.println("key: " + name);
		}
	}
	
	
	/*public JSONObject verifyJSON(String inputString) {
		JSONObject temp;
		try 
		{
			temp = new JSONObject(inputString);
		} 
		catch (JSONException e) 
		{
			temp = null;
			e.printStackTrace();

		}
		return temp;
	}*/

	public void addScreen(Screen screen) throws JSONException
	{

		if (Globals.screenMap.isEmpty()) {
			populateMapFromDB();
		}
		//JSONObject screenJsonObject = verifyJSON(screen.getJsonData());
		
		JSONObject screenJsonObject = new JSONObject(screen.getJsonData());
		
		int currentScreenId = Screen.getCurrentScreenId();
		
		if( currentScreenId == 0 && screenRepository.tableSize() > 0)
		{
			currentScreenId = screenRepository.currentScreenId();
		}
		
		Screen.setCurrentScreenId(currentScreenId);
		
		//creating a new screen object to store in DB
		Screen screenEntity = new Screen();
		
		
		
		int theatreId;
		String name;
		int numberOfSeats;
		JSONObject nameJson;
		JSONObject seatJson;
		
		
		theatreId = screenJsonObject.getInt("theatreId");

		nameJson = screenJsonObject.getJSONObject("screenName");
		seatJson = screenJsonObject.getJSONObject("numberOfSeats");

		Iterator<String> keys = nameJson.keys();

		while (keys.hasNext()) 
		{
			String key = keys.next();
			name = (String) nameJson.get(key);
			numberOfSeats = (int) seatJson.getInt(key);

			screenEntity.setTheatreId(theatreId);
			screenEntity.setScreenId(++currentScreenId);
			screenEntity.setName(name);
			screenEntity.setNumberOfSeats(numberOfSeats);

			

			ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();
			map.put(screenEntity.getTheatreId(), screenEntity.getName());
			Globals.screenMap.put(map, screenEntity.getScreenId());
			
			screenRepository.save(screenEntity);
			
			/*Seat seat = new Seat();
			seat.setScreenId(screenEntity.getScreenId());
			seat.setSeatClass("gold");
			seat.setStatus("A");
			seatDAO.addSeat(seat, numberOfSeats);*/
			
		}
		Screen.setCurrentScreenId(currentScreenId);
		
	}
	
	public List<Screen> getAllScreens()
	{
		List<Screen> screenList=new ArrayList<>();
		screenRepository.findAll().forEach(screenList::add);
		return screenList;
	}
	
	public Screen getScreenById(int screenId)
	{
		Optional<Screen> screenContainer = screenRepository.findById(screenId);
		return screenContainer.get();
	}
	
	//TODO add  functionality to retrieve screen by theatreName and screenName
	public Screen getScreen(int theatreId, String name)
	{
		if( Globals.screenMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(theatreId, name);
		
		Integer screenId = Globals.theatreMap.get(map);
		if(null!= screenId)
		{
			return getScreenById(screenId);
		}
		
		return null;
	}
	
	////TODO add  functionality to update screen by theatreName and screenName
	@Transactional
	public void update(int theatreId, String name, Screen updatedScreen)
	{
		if( Globals.screenMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(theatreId, name);
		
		Integer screenId = Globals.theatreMap.get(map);
		
		if(null!= screenId)
		{
			screenRepository.update(screenId, updatedScreen.getName(), updatedScreen.getNumberOfSeats());
		}
	}
	
	public void deleteScreenById(int screenId)
	{
		screenRepository.deleteById(screenId);
	}
	
	public void deleteScreenByName(int theatreId, String name)
	{
		if( Globals.screenMap.isEmpty())
		{
			populateMapFromDB();
		}
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(theatreId, name);
		
		Integer screenId = Globals.theatreMap.get(map);
		
		if(null!= screenId)
		{
			Globals.theatreMap.remove(name);
			screenRepository.deleteById(screenId);
		}
	}
	
	public ArrayList<Screen> getScreensByTheatreId(int theatreId)
	{
		return screenRepository.getScreensByTheatreId(theatreId);
	}
}
