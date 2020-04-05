package com.example.springexample.screen;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends CrudRepository <Screen, Integer>{

	@Modifying
	@Query("UPDATE Screen s SET s.name = :name, s.numberOfSeats = :numberOfSeats WHERE s.screenId = :screenId")
	public void update(@Param("screenId") int screenId, @Param("name") String name, @Param("numberOfSeats") int numberOfSeats);
	
	@Query("SELECT MAX (screenId) FROM Screen")
	public int currentScreenId();
	
	
	@Query("SELECT COUNT(*) FROM Screen")
	public int tableSize();
	
	@Query("SELECT s FROM Screen s WHERE s.theatreId = :theatreId")
	public ArrayList<Screen> getScreensByTheatreId(@Param("theatreId") int theatreId);
	
}
