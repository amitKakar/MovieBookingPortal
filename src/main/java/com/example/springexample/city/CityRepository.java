package com.example.springexample.city;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer>
{
/*	@Query("SELECT c.cityName FROM City c WHERE c.theatreId = :theatreId")
	public ArrayList<String> getScreensByTheatreId(@Param("theatreId") int theatreId);*/
	
	@Query("SELECT COUNT(*) FROM City")
	public int tableSize();
}
