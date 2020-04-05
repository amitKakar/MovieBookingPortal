package com.example.springexample.screen;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ScreenController {
	
	@Autowired
	private ScreenDAO screenDAO;
	
	@PostMapping("/screen")
	public void addScreen(@Valid @RequestBody Screen screen ) throws JSONException
	{
		screenDAO.addScreen(screen);
	}
	
	@RequestMapping("/screens")
	public List<Screen> getAllScreen()
	{
		return screenDAO.getAllScreens();
	}
	
	@GetMapping("/screen/id/{screenId}")
	public Screen getScreenById(@PathVariable(value="screenId") int screenId)
	{
		return screenDAO.getScreenById(screenId);
	}
	 
	@GetMapping("/screen/{theatreId}/{screenName}")
	public Screen getScreen(@PathVariable(value="theatreId") int theatreId, @PathVariable(value="screenName") String screenName)
	{
		return screenDAO.getScreen(theatreId, screenName);
	}
	
	@PutMapping("/screen/{theatreId}/{screenName}")
	public void updateScreen(@PathVariable(value = "theatreId") int theatreId,
			@PathVariable(value = "screenName") String screenName, @Valid @RequestBody Screen updatedScreen)
	{
		screenDAO.update(theatreId, screenName, updatedScreen);
	}
	 
	@DeleteMapping("/screen/{theatreId}/{screenName}")
	public void delete(@PathVariable(value = "theatreId") int theatreId, @PathVariable(value="screenName") String screenName)
	{
		screenDAO.deleteScreenByName(theatreId, screenName);
	}
	
	@GetMapping("/screens/{theatreId}")
	public ResponseEntity<ArrayList<Screen>> getScreensByTheatreId(@PathVariable("theatreId") int theatreId)
	{
		//int foo = Integer.parseInt(theatreId);
		
		ArrayList<Screen> screenList = screenDAO.getScreensByTheatreId(theatreId);
		
		//return ResponseEntity.ok().body(screenList);
		
		return new ResponseEntity<ArrayList<Screen>>(screenList, HttpStatus.OK);
	}
	
	/*@PostMapping("/screens")
	public ResponseEntity<ArrayList<Screen>> getScreensByTheatreId(@Valid @RequestBody JSONObject theatreDetail) throws JSONException
	{
		int theatreId = (int)theatreDetail.get("Id");
		ArrayList<Screen> screenList = screenDAO.getScreensByTheatreId(theatreId);
		
		//return ResponseEntity.ok().body(screenList);
		
		return new ResponseEntity<ArrayList<Screen>>(screenList, HttpStatus.OK);
	}*/
}

