package com.example.springexample.movie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springexample.global.Globals;


@Service
public class MovieDAO {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public int addMovie(Movie movie)
	{
		if(Globals.movieMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		String upperCaseMovieName = movie.getName().toUpperCase(); 
		movie.setName(upperCaseMovieName);
		
		movieRepository.save(movie);
		Globals.movieMap.put(movie.getName(), movie.getMovieId());
		
		return movie.getMovieId();
	}
	
	public void populateMapFromDatabase()
	{
		List<Movie> allMoviesList = getAllMovies();
		if(null != allMoviesList)
		{
			for(Movie movie: allMoviesList)
			{
				Globals.movieMap.put(movie.getName(), movie.getMovieId());
			}
		}
		
	}
	
	public List<Movie> getAllMovies()
	{
		List<Movie> movies = new ArrayList<>();
		Iterable<Movie> iterableElements = movieRepository.findAll();
		Iterator<Movie> it =  iterableElements.iterator();
		while(it.hasNext())
		{
			movies.add(it.next());
		}

		return movies;
	}
	
	public Movie getMovieByName(String name)
	{
		if(Globals.movieMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		Integer movieId = Globals.movieMap.get(name);
		if(null != movieId)
		{
			return getMovieById(movieId);
		}
		return null;
	}
	
	public Movie getMovieByNameAndOperatorId(String name, int operatorId)
	{
		return movieRepository.getMovieByNameAndOperatorId(name, operatorId);
	}
	
	public Movie getMovieById(int movieId)
	{
		Optional<Movie> movieContainer = movieRepository.findById(movieId);
		return movieContainer.get();
	}
	 
	@Transactional
	public void updateMovie(String movieName, Movie updatedMovie) 
	{
		if(Globals.movieMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		Integer movieId=Globals.movieMap.get(movieName);
		if (null != movieId) {
			movieRepository.updateMovie(movieId, updatedMovie.getName(), updatedMovie.getGenre(),
					updatedMovie.getDuration(), updatedMovie.getLanguage());
		}
	}
	
	public void deleteByMovieId(int movieId)
	{
		movieRepository.deleteById(movieId);
	}
	 
	public void deleteMovieByName(String name)
	{
		if(Globals.movieMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		Integer movieId=Globals.movieMap.get(name);
		if(null != movieId )
		{
			Globals.movieMap.remove(name);
			movieRepository.deleteById(movieId);
		}
	}

	public ArrayList<Movie> getAllMoviesByOperator(int operatorId) {
		
		return movieRepository.getAllMoviesByOperator(operatorId);
	}

	

}
