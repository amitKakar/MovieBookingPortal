package com.example.springexample.movie;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	
	@Modifying
	@Query("UPDATE Movie m SET  m.name=:name, m.genre=:genre, m.duration=:duration, m.language=:language WHERE m.movieId=:movieId")
	public void updateMovie(@Param("movieId") int movieId, @Param("name") String name,
			@Param("genre") String genre, @Param("duration") int duration, @Param("language") String language);

	@Query ("SELECT m FROM Movie m WHERE m.operatorId=:operatorId")
	public ArrayList<Movie> getAllMoviesByOperator(@Param("operatorId") int operatorId);
	
	@Query ("SELECT m FROM Movie m WHERE m.name=:name AND m.operatorId=:operatorId")
	public Movie getMovieByNameAndOperatorId(@Param("name") String name, @Param("operatorId") int operatorId);
}
