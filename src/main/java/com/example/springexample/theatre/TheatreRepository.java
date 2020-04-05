package com.example.springexample.theatre;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TheatreRepository extends CrudRepository<Theatre, Integer> {

	@Modifying
	@Query("UPDATE Theatre t SET name = :name, numberOfScreens = :numberOfScreens WHERE theatreId = :theatreId")
	public void update(@Param("theatreId") int theatreId, @Param("name") String name,
			@Param("numberOfScreen") int numberOfScreens);

	@Query("SELECT MAX(theatreId) FROM Theatre")
	public int currentTheatreId();

	@Query("SELECT COUNT(*) FROM Theatre")
	public int tableSize();
	
	@Query ("SELECT DISTINCT(t.city) FROM Theatre t")
	public ArrayList<String> getAllCities();
	
	@Query ("SELECT t FROM Theatre t WHERE t.city = :city ")
	public ArrayList<Theatre> getAllTheatresByCityName(@Param("city") String city);
	
	@Query ("SELECT t FROM Theatre t WHERE t.city = :city AND t.operatorId=:operatorId")
	public ArrayList<Theatre> getTheatreByCityNameAndOperatorId(@Param("city") String city, @Param("operatorId") int operatorId);
	
	
	@Query ("SELECT DISTINCT(t.city) FROM Theatre t WHERE t.operatorId=:operatorId")
	public ArrayList<String> getAllCitiesByOperator(@Param("operatorId") int operatorId);
	
	
	@Query ("SELECT t FROM Theatre t WHERE t.operatorId = :operatorId ")
	public ArrayList<Theatre> getAllTheatresByOperator(@Param("operatorId") int operatorId);
	
	//*********Queries to implement the selection on the basis of date and time
	@Query ("SELECT t FROM Theatre t WHERE t.city = :city AND t.theatreId=:theatreId")
	public Theatre getTheatreByCityNameAndId(@Param("city") String city, @Param("theatreId") int theatreId);

	

}
