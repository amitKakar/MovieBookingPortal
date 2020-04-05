package com.example.springexample.movie;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springexample.operator.Operator;
import com.example.springexample.operator.OperatorDAO;



@Controller

public class MovieController {
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private OperatorDAO operatorDAO;
	 
	@GetMapping("/registerMovie")
	public String userForm(Model model)
	{
		model.addAttribute("movieForm",new Movie());
		return "movieRegister";
	}
	
	@PostMapping("/movie")
	public String addMovie(Movie movie, HttpServletRequest request, Model model)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		movie.setOperatorId(operator.getOperatorId());
		
		int movieId = movieDAO.addMovie(movie);
		 
		model.addAttribute("movieId", movieId);
		model.addAttribute("movie", movie);
		
		return "movieSuccess";
		 
		
	}
	
	@RequestMapping("/movies")
	public List<Movie> getAllMovies()
	{
		return movieDAO.getAllMovies();
	}
	
	@GetMapping("/movie/id/{movieId}")
	public Movie getMovieById(@PathVariable(value="movieId") int movieId)
	{
		return movieDAO.getMovieById(movieId);
	}
	
	
	
	 
	@GetMapping("/movie/{movieName}")
	public ResponseEntity<Movie> getMovieByName(@PathVariable(value = "movieName") String movieName,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		String operatorName = (String)session.getAttribute("operatorname");
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		
		Movie movie = movieDAO.getMovieByNameAndOperatorId(movieName, operator.getOperatorId());
		if(null != movie)
		{
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@PutMapping("/movie/{movieName}")
	public void updateMovie(@PathVariable(value="movieName") String movieName, @Valid @RequestBody Movie updatedMovie)
	{
		movieDAO.updateMovie(movieName, updatedMovie);
	}
	 
	@DeleteMapping("/movie/{movieName}")
	public void delete(@PathVariable(value="movieName") String movieName)
	{
		System.out.println("testing......");
		movieDAO.deleteMovieByName(movieName);
	}
	
	
	@GetMapping("movies/operator")
	public ResponseEntity<ArrayList<Movie>> getAllMoviesByOperator(HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		ArrayList<Movie> movieList = movieDAO.getAllMoviesByOperator(operator.getOperatorId());
		return new ResponseEntity<ArrayList<Movie>>(movieList, HttpStatus.OK);
	}
	
	@GetMapping("registered/movies")
	public String getAllRegisteredMovies(Model model, HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		String operatorName = (String)httpSession.getAttribute("operatorname");
		
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		model.addAttribute("movieList", movieDAO.getAllMoviesByOperator(operator.getOperatorId()));
		
		return "registeredMovies";
	}
	 
}
