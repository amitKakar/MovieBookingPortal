package com.example.springexample.theatre;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springexample.operator.Operator;
import com.example.springexample.operator.OperatorDAO;
import com.example.springexample.show.ShowDAO;


@Controller
public class TheatreController {
	
	@Autowired
	private TheatreDAO theatreDAO;
	
	@Autowired
	private ShowDAO showDAO;
	
	@Autowired 
	private OperatorDAO operatorDAO;
	

	@PostMapping("/theatre")
	public void addTheatre(@Valid @RequestBody Theatre theatre)
	{
		theatreDAO.addTheatre(theatre);
		
		
	}

	/*public void addTheatre( Theatre theatre )
	{
		theatreDAO.addTheatre(theatre);
	}*/
	
	@RequestMapping("/theatres")
	public List<Theatre> getAllTheatres()
	{
		return theatreDAO.getAllTheatres();
	}
	
	@GetMapping("/theatre/id/{theatreId}")
	public Theatre getTheatreById(@PathVariable(value="theatreId") int theatreId)
	{
		return theatreDAO.getTheatreById(theatreId);
	}
	 
	//get theatre details by name and address
	@PostMapping("/getTheatre")
	public ResponseEntity<Theatre> getTheatreByNameAndAddress( @RequestBody Theatre theatre)
	{
		Theatre getTheatreByNameAndAddress = theatreDAO.getTheatreByNameAndAddress(theatre);
		
		if(null != getTheatreByNameAndAddress)
		{
			return new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/theatre/{name}")
	public void updateTheatre(@PathVariable(value="name") String name, @Valid @RequestBody Theatre updatedTheatre)
	{
		theatreDAO.updateTheatre(name, updatedTheatre);
	}	 
	
	@DeleteMapping("/theatre")
	public void delete(@Valid @RequestBody Theatre theatre)
	{
		theatreDAO.deleteTheatreByName(theatre);
	}
	
	
	@GetMapping("getAllCities")
	public ArrayList<String> getAllCities()
	{
		return theatreDAO.getAllCities();
	}
	
	
	@GetMapping("getCities/operator")
	public ArrayList<String> getAllCitiesByOperator(HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		return theatreDAO.getAllCitiesByOperator(operator.getOperatorId());
	}
	 
	@GetMapping("theatres/{city}")
	public ResponseEntity<ArrayList<Theatre>> getAllTheatresByCityName(@PathVariable(value="city") String city)
	{
		
		ArrayList<Theatre> theatreList = theatreDAO.getAllTheatresByCityName(city);
		return new ResponseEntity<ArrayList<Theatre>>(theatreList, HttpStatus.OK);
	}
	
	
	@GetMapping("theatres/operator/{city}")
	public ResponseEntity<ArrayList<Theatre>> getAllTheatresByCityName(@PathVariable(value="city") String city, HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		ArrayList<Theatre> theatreList = theatreDAO.getAllTheatresByCityNameAndOperatorId(city, operator.getOperatorId());
		return new ResponseEntity<ArrayList<Theatre>>(theatreList, HttpStatus.OK);
	}
	
	//*********Implementing the selection on the basis of date and time
	
	
	@GetMapping("/cities/{date}")
	public ResponseEntity<Set<String>> getAllCitiesByDateAndTime(@PathVariable(value = "date") String date)
	{
		if(date==null || date==""){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		LocalDate currentDate = LocalDate.parse(date);
		//System.out.println(currentDate);
		
		//DateTimeFormatter dateTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		
		Set<String> cityList = new HashSet<String>();
		if (currentDate.compareTo(localDate) == 0) 
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime time = LocalTime.now();
			//System.out.println(dtf.format(time));
			String currentTimeString = dtf.format(time);
			LocalTime currentTime = LocalTime.parse(currentTimeString);
			
			ArrayList<Integer> theatreIdListFromShowTable = showDAO.getTheatreIdListByDateAndTime(currentDate, currentTime);
			int count = 0;
			for(Integer theatreId: theatreIdListFromShowTable)
			{
				Theatre theatre = theatreDAO.getTheatreById(theatreId);
				if(null != theatre)
				{
					cityList.add(theatre.getCity());
					count++;
				}
				
			}

			if(count!=0){
				return new ResponseEntity<Set<String>>(cityList, HttpStatus.OK);		
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
            
        }
		
		else 
		{
			ArrayList<Integer> theatreIdListFromShowTable = showDAO.getTheatreIdListByDate(currentDate);
			int count=0;
			for (Integer theatreId : theatreIdListFromShowTable) 
			{
				Theatre theatre = theatreDAO.getTheatreById(theatreId);
				if(null != theatre)
				{
					cityList.add(theatre.getCity());
					count++;
				}
				
			}
			if(count!=0){
				return new ResponseEntity<Set<String>>(cityList, HttpStatus.OK);		
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("theatres/{city}/{date}")
	public ResponseEntity<ArrayList<Theatre>> getAllTheatresByCityNameDateAndTime(@PathVariable(value = "city") String city,
			@PathVariable(value = "date") String date)
	{
		
		
		/*String currentTimeString = "21:00:00";
		LocalTime currentTime = LocalTime.parse(currentTimeString);*/
		if(city==null || city=="" || date==null || date==""){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		LocalDate currentDate = LocalDate.parse(date);
		//System.out.println(currentDate);
		
		//DateTimeFormatter dateTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		
		ArrayList<Theatre> theatreList = new ArrayList<Theatre>();
		if (currentDate.compareTo(localDate) == 0) 
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime time = LocalTime.now();
			//System.out.println(dtf.format(time));
			String currentTimeString = dtf.format(time);
			LocalTime currentTime = LocalTime.parse(currentTimeString);
			
			ArrayList<Integer> theatreIdListFromShowTable = showDAO.getTheatreIdListByDateAndTime(currentDate, currentTime);
			int count = 0;
			for(Integer theatreId: theatreIdListFromShowTable)
			{
				Theatre theatre = theatreDAO.getTheatreByCityNameAndId(city, theatreId);
				if(null != theatre)
				{
					theatreList.add(theatre);
					count++;
				}
				
			}

			if(count!=0){
				return new ResponseEntity<ArrayList<Theatre>>(theatreList, HttpStatus.OK);		
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
            
        }
		
		else 
		{
			ArrayList<Integer> theatreIdListFromShowTable = showDAO.getTheatreIdListByDate(currentDate);
			int count=0;
			for (Integer theatreId : theatreIdListFromShowTable) 
			{
				Theatre theatre = theatreDAO.getTheatreByCityNameAndId(city, theatreId);
				if(null!=theatre)
				{
					theatreList.add(theatre);
					count++;
				}
				
			}
			if(count!=0){
				return new ResponseEntity<ArrayList<Theatre>>(theatreList, HttpStatus.OK);		
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	
	@GetMapping("/registered/theatres")
	public String getAllRegisteredTheatres(Model model, HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		model.addAttribute("theatreList", theatreDAO.getAllTheatresByOperator(operator.getOperatorId()));
		
		return "registeredTheatres";
	}
	
	//get currently registered theatres by operatorId
	@GetMapping("/registered/theatres/operator")
	public ResponseEntity<List<Theatre>> getAllRegisteredTheatresByOperator(Model model, HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		List<Theatre> theatreList =  theatreDAO.getAllTheatresByOperator(operator.getOperatorId());
		
		return new ResponseEntity<List<Theatre>>(theatreList, HttpStatus.OK);
	}
	
}

