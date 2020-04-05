package com.example.springexample.show;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springexample.movie.Movie;
import com.example.springexample.movie.MovieDAO;
import com.example.springexample.operator.Operator;
import com.example.springexample.operator.OperatorDAO;
import com.example.springexample.screen.ScreenDAO;
import com.example.springexample.theatre.TheatreDAO;


@Controller
public class ShowController {

	@Autowired
	private ShowDAO showDAO;
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private TheatreDAO theatreDAO;
	
	@Autowired
	private OperatorDAO operatorDAO;
	
	@Autowired
	private ScreenDAO screenDAO;
	
	@PostMapping("/addShow")
	public String addShow(@ModelAttribute(value="show") Show show, Model model, HttpServletRequest request )
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
	
		show.setOperatorId(operator.getOperatorId());
		
		showDAO.addShow(show);
		 
		model.addAttribute("show", show);
		model.addAttribute("movieName", movieDAO.getMovieById(show.getMovieId()).getName());
		model.addAttribute("theatreName", theatreDAO.getTheatreById(show.getTheatreId()).getName());
		model.addAttribute("theatreAddress", theatreDAO.getTheatreById(show.getTheatreId()).getAddress());
		model.addAttribute("screenName", screenDAO.getScreenById(show.getScreenId()).getName());
		
		
		return "showSuccess";
	}
	
	
	@GetMapping("/registerShow")
	public String userForm(Model model, HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		model.addAttribute("showForm",new Show());
		model.addAttribute("cityList", theatreDAO.getAllCitiesByOperator(operator.getOperatorId()));
		model.addAttribute("movieList", movieDAO.getAllMoviesByOperator(operator.getOperatorId()));
		return "registerShow";
	}
	
	@GetMapping("/shows")
	public List<Show> getAllShows()
	{
		return showDAO.getAllShows();
	}
	
	@GetMapping("/show/{showId}")
	public Show getShowById(@PathVariable(value="showId") int showId)
	{
		return showDAO.getShowById(showId);
	}
	
	/*@PutMapping("/show/{showId}")
	public void updateShow(@PathVariable(value="showId") int showId, @Valid @RequestBody Show updatedShow)
	{
		showDAO.updateShow(showId, updatedShow);
	}
	*/
	@DeleteMapping("/show/{showId}")
	public void deleteShow(@PathVariable(value="showId") int showId)
	{
		showDAO.deleteShow(showId);
	}
	
	@GetMapping("/showDate")
	public List<LocalDate> getAllDates()
	{
		return showDAO.getAllDates();
	}
	
	@GetMapping("/showsMovies/{theatreId}")
	public ResponseEntity<ArrayList<Movie>> getMoviesByTheatreId(@PathVariable("theatreId") int theatreId) {
		// int foo = Integer.parseInt(theatreId);

		ArrayList<Movie> movieList = showDAO.getMoviesByTheatreId(theatreId);

		// return ResponseEntity.ok().body(screenList);

		return new ResponseEntity<ArrayList<Movie>>(movieList, HttpStatus.OK);
	}
	
	
	@PostMapping("/showResult")
	public ResponseEntity<ArrayList<Show>> getShows(@Valid @RequestBody Show show) {
		// int foo = Integer.parseInt(theatreId);

		int theatreId = show.getTheatreId();
		LocalDate date = show.getCurrDate();
		int movieId = show.getMovieId();
		ArrayList<Show> showList = showDAO.getShows(theatreId, date, movieId);

		
		return new ResponseEntity<ArrayList<Show>>(showList, HttpStatus.OK);
	}
	
	/*@GetMapping("/nowShowing")
	public String nowShowing(Model model)
	{
		model.addAttribute("movieList", showDAO.nowShowing()); 
		//showDAO.nowShowing();
		
		return "index";
	}*/
	
	/*@PostMapping("/showAvailability")
	public ResponseEntity<Integer> checkAvailabilityOfTheShow(@RequestBody Show show)
	{
		LocalDate currDate = LocalDate.parse(show.getCurrDateString());
		LocalTime startTime = LocalTime.parse(show.getStartTimeString());
		
		show.setCurrDate(currDate);
		show.setStartTime(startTime);
		
		Integer result = showDAO.checkAvailabilityOfTheShow(show);
		
		if(result == 0)
		{
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	@PostMapping("/showAvailability")
	public ResponseEntity<Integer> checkAvailableShowSlot(@RequestBody Show show)
	{
		LocalDate currDate = LocalDate.parse(show.getCurrDateString());
		LocalTime startTime = LocalTime.parse(show.getStartTimeString());
		LocalTime endTime = LocalTime.parse(show.getEndTimeString());
		
		show.setCurrDate(currDate);
		show.setStartTime(startTime);
		show.setEndTime(endTime);
		
		Integer result = showDAO.checkAvailableShowSlot(show.getStartTime(), show.getEndTime(), show.getCurrDate(), show.getScreenId());
		
		if(result == 0)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
			
		}
	}
	
	
	
	//*********Implementing the selection on the basis of date and time
	@GetMapping("/getDate")
	public List<LocalDate> getAllDatesAcoordingToSystemTime()
	{
		return showDAO.getAllDatesAcoordingToSystemTime();
	}
	
	
	@GetMapping("/movies/{theatreId}/{date}")
	public ResponseEntity<ArrayList<Movie>> getMoviesByTheatreIdDateAndTime(@PathVariable("theatreId") Integer theatreId,
			@PathVariable(value = "date") String date) 
	{
		
		
		/*String currentTimeString = "21:00:00";
		LocalTime currentTime = LocalTime.parse(currentTimeString);*/
		if(null==theatreId){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		LocalDate currentDate = LocalDate.parse(date);
		System.out.println(currentDate);
		
		LocalDate localDate = LocalDate.now();
		
		if (currentDate.compareTo(localDate) == 0)
		{
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime time = LocalTime.now();
			System.out.println(dtf.format(time));
			String currentTimeString = dtf.format(time);
			LocalTime currentTime = LocalTime.parse(currentTimeString);
			
			System.out.println(dtf.format(currentTime));
			
			ArrayList<Movie> movieList = showDAO.getMoviesByTheatreIdDateAndTime(theatreId, currentDate, currentTime);
			if(movieList.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ArrayList<Movie>>(movieList, HttpStatus.OK);
		}
		else
		{
			ArrayList<Movie> movieList = showDAO.getMoviesByTheatreIdDate(theatreId, currentDate);
			if(movieList.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ArrayList<Movie>>(movieList, HttpStatus.OK);
		}

		
	}
	
	
	
	@PostMapping("/getShows")
	public ResponseEntity<ArrayList<Show>> getShowsByMovieIdTheatreIdDateAndTime(@Valid @RequestBody Show show) 
	{
		if(null==show){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Date date = new Date();

	      // display time and date using toString()
	      System.out.println(date.toString());

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime time = LocalTime.now();
		System.out.println(dtf.format(time));
		String currentTimeString = dtf.format(time);
		LocalTime currentTime = LocalTime.parse(currentTimeString);
		
		/*String currentTimeString = "21:00:00";
		LocalTime currentTime = LocalTime.parse(currentTimeString);*/
		
		System.out.println(dtf.format(currentTime));
		
		
		int theatreId = show.getTheatreId();
		
		LocalDate currentDate = show.getCurrDate();
		int movieId = show.getMovieId();
		
		LocalDate localDate = LocalDate.now();
		
		if (currentDate.compareTo(localDate) == 0)
		{
			
			ArrayList<Show> showList = showDAO.getShowsByMovieIdTheatreIdDateAndTime(movieId, theatreId, currentDate, currentTime );
			if(showList.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ArrayList<Show>>(showList, HttpStatus.OK);
		}
		else
		{
			ArrayList<Show> showList = showDAO.getShowsByMovieIdTheatreIdDate(movieId, theatreId, currentDate);
			if(showList.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ArrayList<Show>>(showList, HttpStatus.OK);
		}

		
	}
	
	//get currently running shows by operatorId, theatreId, screenId and date
	@PostMapping("/getCurrentlyRunningShow")
	public ResponseEntity<List<ShowDetail>> getAllShowsByDateTheatreIdAndOperatorID(@RequestBody Show show, HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		LocalDate currentDate = LocalDate.parse(show.getCurrDateString());
		
		ArrayList<Show> showList = showDAO.getAllShowsByDateTheatreIdAndOperatorId(operator.getOperatorId(),
				show.getTheatreId(), show.getScreenId(), currentDate);
		List<ShowDetail> showDetailList = new ArrayList<ShowDetail>();
		
		for(Show showObject : showList)
		{
			ShowDetail showDetail = new ShowDetail();
			showDetail.setShowId(showObject.getShowId());
			showDetail.setMovieName(movieDAO.getMovieById(showObject.getMovieId()).getName());
			showDetail.setMovieId(showObject.getMovieId());
			showDetail.setTheatreName(theatreDAO.getTheatreById(showObject.getTheatreId()).getName());
			showDetail.setTheatreId(showObject.getTheatreId());
			showDetail.setTheatreAddress(theatreDAO.getTheatreById(showObject.getTheatreId()).getAddress());
			showDetail.setScreenName(screenDAO.getScreenById(showObject.getScreenId()).getName());
			showDetail.setScreenId(showObject.getScreenId());
			
			showDetail.setPrice(showObject.getPrice());
			showDetail.setDiscount(showObject.getDiscount());
			showDetail.setCurrDate(showObject.getCurrDate());
			showDetail.setStartDate(showObject.getStartDate());
			showDetail.setEndDate(showObject.getEndDate());
			showDetail.setStartTime(showObject.getStartTime());
			showDetail.setEndTime(showObject.getEndTime());
			
			showDetailList.add(showDetail);
			
		}
		
		return new ResponseEntity<List<ShowDetail>>(showDetailList, HttpStatus.OK);
	}
	
	
	@GetMapping("/registered/shows")
	public String getAllRegisteredShows(Model model, HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		List<ShowDetail> showDetailList = new ArrayList<ShowDetail>();
		List<Show> showList = showDAO.getAllShowsByOperator(operator.getOperatorId());
		
		
		
		for(Show show : showList)
		{
			ShowDetail showDetail = new ShowDetail();
			showDetail.setShowId(show.getShowId());
			showDetail.setMovieName(movieDAO.getMovieById(show.getMovieId()).getName());
			showDetail.setMovieId(show.getMovieId());
			showDetail.setTheatreName(theatreDAO.getTheatreById(show.getTheatreId()).getName());
			showDetail.setTheatreId(show.getTheatreId());
			showDetail.setTheatreAddress(theatreDAO.getTheatreById(show.getTheatreId()).getAddress());
			showDetail.setScreenName(screenDAO.getScreenById(show.getScreenId()).getName());
			showDetail.setScreenId(show.getScreenId());
			
			showDetail.setPrice(show.getPrice());
			showDetail.setDiscount(show.getDiscount());
			showDetail.setCurrDate(show.getCurrDate());
			showDetail.setStartDate(show.getStartDate());
			showDetail.setEndDate(show.getEndDate());
			showDetail.setStartTime(show.getStartTime());
			showDetail.setEndTime(show.getEndTime());
			
			showDetailList.add(showDetail);
			
		}
		model.addAttribute("showDetailList", showDetailList);
		
		return "registeredShows";
	}
	
}
