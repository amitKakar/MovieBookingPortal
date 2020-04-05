package com.example.springexample.booking;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BookingRepository extends CrudRepository <Booking, Integer>{
	
	@Query("SELECT b FROM Booking b WHERE b.userName =:userName")
	public ArrayList<Booking> history(@Param(value="userName") String userName);

}
