package com.example.springexample.show;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ShowRepository extends CrudRepository<Show, Integer>{

	@Modifying
	@Query("UPDATE Show s SET  s.movieId=:movieId, s.theatreId=:theatreId, s.screenId=:screenId, s.price=:price, s.discount=:discount, s.currDate=:currDate, s.startDate=:startDate, s.endDate=:endDate, s.startTime=:startTime, s.endTime=:endTime WHERE s.showId=:showId")
	public void updateShow(@Param("showId") int showId, @Param("movieId") int movieId, @Param("theatreId") int theatreId,
			@Param("screenId") int screenId, @Param("price") double price, @Param("discount") double discount,
			@Param("currDate") Date currDate, @Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	
	@Query("SELECT DISTINCT currDate FROM Show")
	public List<LocalDate> findAllDates();
	
	
	@Query("SELECT DISTINCT s.movieId FROM Show s WHERE s.theatreId = :theatreId")
	public ArrayList<Integer> getMoviesByTheatreId(@Param("theatreId")int theatreId);
	
/*	 @Query("SELECT s.price,s.startTime,s.endTime,s.discount FROM Show s WHERE s.theatreId = :theatreId AND s.currDate=:currDate AND s.movieId=:movieId")
	 public ArrayList<Show> getShows(@Param("theatreId")int theatreId,@Param("currDate")LocalDate currDate,@Param("movieId")int movieId);*/
	
	@Query("SELECT s FROM Show s WHERE s.theatreId = :theatreId AND s.currDate=:currDate AND s.movieId=:movieId")
	 public ArrayList<Show> getShows(@Param("theatreId")int theatreId,@Param("currDate")LocalDate currDate,@Param("movieId")int movieId);
	
	 /*@Query("SELECT s FROM Show s WHERE s.showId = :showId")
	 public Show getShowById(@Param("showId")int showId);*/
	
	
	 @Query("SELECT  s.screenId FROM Show s WHERE s.showId = :showId")
	 public int getScreenIdByShowId(@Param("showId")int showId);
	 
	 @Query("SELECT  s.startTime FROM Show s WHERE s.showId = :showId")
	 public LocalTime getStartTimeByShowId(@Param("showId")int showId);
	
	 @Query("SELECT s FROM Show s WHERE s.currDate = :currDate")
	 public ArrayList<Show> nowShowing(@Param("currDate") LocalDate currDate);
	 
	 
	@Query ("SELECT s FROM Show s WHERE s.operatorId = :operatorId ")
	public ArrayList<Show> getAllShowsByOperator(@Param("operatorId") int operatorId);
	 

	 
	//*********Queries to implement the selection on the basis of date and time
	 
	 @Query("SELECT DISTINCT s.currDate FROM Show s WHERE s.currDate>=:date")
	 public List<LocalDate> getAllDatesAcoordingToSystemTime(@Param("date")LocalDate date);
	 
	 
	 
	 
	 
	 @Query("SELECT DISTINCT s.theatreId FROM Show s WHERE s.currDate=:currentDate AND s.startTime>=:currentTime")
	 public ArrayList<Integer> findAllTheatrIdByDateAndTime(@Param("currentDate") LocalDate currentDate,
			@Param("currentTime") LocalTime currentTime);
	 
	 @Query("SELECT DISTINCT s.theatreId FROM Show s WHERE s.currDate =:currentDate")
	 public ArrayList<Integer> findAllTheatrIdByDate(@Param("currentDate") LocalDate currentDate);
	 
	 
	 
	 
	 
	 @Query("SELECT DISTINCT s.movieId FROM Show s WHERE s.theatreId = :theatreId AND s.currDate=:currentDate AND s.startTime>=:currentTime")
	 public ArrayList<Integer> getMovieIdByTheatreIdDateAndTime(@Param("theatreId") int theatreId,
			@Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalTime currentTime);
	 
	 @Query("SELECT DISTINCT s.movieId FROM Show s WHERE s.theatreId = :theatreId AND s.currDate=:currentDate")
	 public ArrayList<Integer> getMovieIdByTheatreIdDate(@Param("theatreId") int theatreId,
			@Param("currentDate") LocalDate currentDate);
	 
	 
	 
	 @Query("SELECT s FROM Show s WHERE s.movieId=:movieId AND s.theatreId = :theatreId AND s.currDate=:currentDate AND s.startTime>=:currentTime")
	 public ArrayList<Show> getShowsByMovieIdTheatreIdDateAndTime(@Param("movieId") int movieId,
			@Param("theatreId") int theatreId, @Param("currentDate") LocalDate currentDate,
			@Param("currentTime") LocalTime currentTime);

	 @Query("SELECT s FROM Show s WHERE s.movieId=:movieId AND s.theatreId = :theatreId AND s.currDate=:currentDate")
	 public ArrayList<Show> getShowsByMovieIdTheatreIdDate(@Param("movieId") int movieId,
			@Param("theatreId") int theatreId, @Param("currentDate") LocalDate currentDate);

	 /*@Query("SELECT COUNT(*) FROM Show s WHERE s.theatreId = :theatreId AND s.screenId = :screenId AND s.currDate=:currDate AND s.endTime >= :startTime")
	 public int checkAvailabilityOfTheShow(@Param("theatreId") int theatreId, @Param("screenId") int screenId,
			@Param("currDate") LocalDate currDate, @Param("startTime") LocalTime startTime);*/

	 
	 
	 @Query("SELECT s FROM Show s WHERE s.operatorId=:operatorId AND s.theatreId = :theatreId AND s.screenId=:screenId AND s.currDate=:currentDate")
	 public ArrayList<Show> getAllShowsByDateTheatreIdAndOperatorId(@Param("operatorId") int operatorId, @Param("theatreId") int theatreId,
			@Param("screenId") int screenId, @Param("currentDate") LocalDate currentDate);
	 
	 
	 /*@Query("SELECT COUNT(*) FROM Show s  WHERE  ((s.startTime<=:endTimeNeeded AND s.endTime>=:startTimeNeeded) AND s.screenId = :screenId AND currDate=:currentDate)") 
	public int checkAvailableShowSlot( @Param("startTimeNeeded") LocalTime startTimeNeeded,
			@Param("endTimeNeeded") LocalTime endTimeNeeded,  @Param("currentDate") LocalDate currentDate,  @Param("screenId") int screenId);*/
	 
	 @Query("SELECT COUNT(*) FROM Show s  WHERE  ((s.startTime<=:endTimeNeeded AND s.endTime>=:startTimeNeeded) AND s.screenId = :screenId AND currDate=:currentDate)") 
		public int checkAvailableShowSlot( @Param("startTimeNeeded") LocalTime startTimeNeeded,
				@Param("endTimeNeeded") LocalTime endTimeNeeded,  @Param("currentDate") LocalDate currentDate,  @Param("screenId") int screenId);
	 
}
