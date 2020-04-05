package com.example.springexample.seat;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {

	@Modifying
	@Query("UPDATE Seat s SET s.status = :status, s.seatClass = :seatClass WHERE s.seatId = :seatId")
	public void update(@Param("seatId") int seatId, @Param("status") String status, @Param("seatClass") String seatClass);
	
	
	@Query("SELECT MAX (seatId) FROM Seat")
	public int currentSeatId();
	
	
	@Query("SELECT COUNT(*) FROM Seat")
	public int tableSize();
	
/*	@Query("SELECT  s.seatNumber FROM Seat s WHERE s.screenId = :screenId AND s.status='A'")
	public ArrayList<Seat> getSeatsByScreenId(@Param("screenId")int screenId);*/
	
	@Query("SELECT  s FROM Seat s WHERE s.screenId = :screenId AND s.showId=:showId AND s.status='A'")
	public ArrayList<Seat> getSeatsByScreenId(@Param("screenId")int screenId,@Param("showId")int showId);
	
	
	@Modifying
	@Query("UPDATE Seat s SET s.status='B' WHERE s.seatId = :seatId AND s.status='A'")
	public int updateSeatStatusToBooked(@Param("seatId") int seatId);
	
	
	@Query("SELECT  s.status FROM Seat s WHERE s.seatId = :seatId")
	public String getSeatStatus(@Param("seatId")int seatId);
	
	@Query("SELECT  s.seatNumber FROM Seat s WHERE s.seatId = :seatId")
	public int getSeatNumber(@Param("seatId")int seatId);

	
	@Modifying
	@Query("UPDATE Seat s SET s.status='A' WHERE s.seatId = :seatId")
	public void changeSeatStatusToAvailable(@Param("seatId") int seatId);
	
}
