package com.example.springexample.show;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springexample.movie.Movie;
import com.example.springexample.movie.MovieDAO;
import com.example.springexample.screen.ScreenDAO;
import com.example.springexample.seat.Seat;
import com.example.springexample.seat.SeatDAO;


@Service
public class ShowDAO {
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private ScreenDAO screenDAO;
	
	@Autowired
	private SeatDAO seatDAO;
	
	public void addShow(Show show)
	{
		LocalDate currDate = LocalDate.parse(show.getCurrDateString());
		LocalDate startDate = LocalDate.parse(show.getStartDateString());
		LocalDate endDate = LocalDate.parse(show.getEndDateString());
		LocalTime startTime = LocalTime.parse(show.getStartTimeString());
		LocalTime endTime = LocalTime.parse(show.getEndTimeString());
		
		show.setCurrDate(currDate);
		show.setStartDate(startDate);
		show.setEndDate(endDate);
		show.setStartTime(startTime);
		show.setEndTime(endTime);
		
		showRepository.save(show);
		
		Seat seat = new Seat();
		seat.setScreenId(show.getScreenId());
		seat.setSeatClass("gold");
		seat.setStatus("A");
		seat.setShowId(show.getShowId());	
		seatDAO.addSeat(seat, screenDAO.getScreenById(show.getScreenId()).getNumberOfSeats());
	}
	
	public List<Show> getAllShows()
	{
		List<Show> showList=new ArrayList<>();
		showRepository.findAll().forEach(showList::add);
		return showList;
	}
	
	public Show getShowById(int showId)
	{
		Optional<Show> showContainer = showRepository.findById(showId);
		if(null!=showContainer)
		{
			return showContainer.get();
		}
		return null;
	}
	
	
/*	public Show getShowbyName(String name)
	{
		Integer showId = Globals.showMap.get(name);
		if(null != showId)
		{
			return getShowById(showId);
		}
		return null;
	}
	*/
/*	@Transactional
	public void updateShow(int showId, Show updatedShow)
	{
		showRepository.updateShow(showId, updatedShow.getMovieId(), updatedShow.getTheatreId(),
				updatedShow.getScreenId(), updatedShow.getPrice(), updatedShow.getDiscount(), updatedShow.getCurrDate(),
				updatedShow.getStartDate(), updatedShow.getEndDate(), updatedShow.getStartTime(),
				updatedShow.getEndTime());
	}
	*/
	
	public void deleteShow(int showId)
	{
		showRepository.deleteById(showId);
	}

	public List<LocalDate> getAllDates() {
		
		List<LocalDate> dates=new ArrayList<>();
		dates=showRepository.findAllDates();
		return dates;
		
	}
	
	public ArrayList<Movie> getMoviesByTheatreId(int theatreId) {
		
		ArrayList<Integer> movieIdList = showRepository.getMoviesByTheatreId(theatreId);
		
		ArrayList<Movie> movieList = new ArrayList<Movie> ();
		
		for(Integer movieId: movieIdList)
		{
			movieList.add(movieDAO.getMovieById(movieId));
		}
		
		return movieList;
	}
	
	public ArrayList<Show> getShows(int theatreId, LocalDate date, int movieId) {

		return showRepository.getShows(theatreId, date, movieId);
	}
	
	public int getScreenIdByShowId(int showId) {
		return showRepository.getScreenIdByShowId(showId);

	}
	
	public LocalTime getStartTimeByShowId(int showId)
	{
		return showRepository.getStartTimeByShowId(showId);
	}

	/*public ArrayList<Movie> nowShowing() {
		
		 Calendar cal = Calendar.getInstance();
	     SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
	     DateFormat currDate = new SimpleDateFormat("yyyy-MM-dd");
	     
	     System.out.println(currentTime+"****"+currDate);
	     return null;
	     //ArrayList<Show> showList = showRepository.nowShowing(currDate)
	}*/

	public List<LocalDate> getAllDatesAcoordingToSystemTime() {
		
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");*/
		LocalDate localDate = LocalDate.now();
		/*System.out.println(dtf.format(localDate));
		String dateString = dtf.format(localDate);*/
		
		//String dateString = "2018-04-08";
		/*LocalDate date = LocalDate.parse(dateString);
		System.out.println(dtf.format(date));
		*/
		List<LocalDate> dateList = showRepository.getAllDatesAcoordingToSystemTime(localDate);
		
		Collections.sort(dateList);
		
		return dateList;
	}

	/*public Show getShowById(int showId)
	{
		return showRepository.getShowById(showId);
	}*/
	
	
	
	
	//*********Implementing the selection on the basis of date and time
	
	public ArrayList<Integer> getTheatreIdListByDateAndTime(LocalDate currentDate, LocalTime currentTime)
	{
		return showRepository.findAllTheatrIdByDateAndTime(currentDate, currentTime);
	}
	
	public ArrayList<Integer> getTheatreIdListByDate(LocalDate currentDate)
	{
		return showRepository.findAllTheatrIdByDate(currentDate);
	}
	
	public ArrayList<Movie> getMoviesByTheatreIdDateAndTime(int theatreId, LocalDate currentDate, LocalTime currentTime) {

		ArrayList<Integer> movieIdList = showRepository.getMovieIdByTheatreIdDateAndTime(theatreId, currentDate, currentTime);

		ArrayList<Movie> movieList = new ArrayList<Movie>();

		for (Integer movieId : movieIdList) {
			movieList.add(movieDAO.getMovieById(movieId));
		}

		return movieList;
	}
	
	public ArrayList<Movie> getMoviesByTheatreIdDate(int theatreId, LocalDate currentDate) {
		
		ArrayList<Integer> movieIdList = showRepository.getMovieIdByTheatreIdDate(theatreId, currentDate);

		ArrayList<Movie> movieList = new ArrayList<Movie>();

		for (Integer movieId : movieIdList) {
			movieList.add(movieDAO.getMovieById(movieId));
		}

		return movieList;
	}
	
	public ArrayList<Show> getShowsByMovieIdTheatreIdDateAndTime( int movieId, int theatreId, LocalDate currentDate,LocalTime currentTime ) {
		return showRepository.getShowsByMovieIdTheatreIdDateAndTime(movieId, theatreId, currentDate, currentTime );
	}

	public ArrayList<Show> getShowsByMovieIdTheatreIdDate(int movieId, int theatreId, LocalDate currentDate) {
		return showRepository.getShowsByMovieIdTheatreIdDate(movieId, theatreId, currentDate);
	}

	/*public int checkAvailabilityOfTheShow(Show show) {
		
		return showRepository.checkAvailabilityOfTheShow(show.getTheatreId(), show.getScreenId(), show.getCurrDate(), show.getStartTime());
	}
*/
	public ArrayList<Show> getAllShowsByDateTheatreIdAndOperatorId(int operatorId, int theatreId, int screenId, LocalDate currentDate)
	{
		
		return showRepository.getAllShowsByDateTheatreIdAndOperatorId(operatorId, theatreId, screenId, currentDate);
	}
	
	
	public int checkAvailableShowSlot(LocalTime startTimeNeeded,LocalTime endTimeNeeded,LocalDate currentDate, int screenId){
		
		return showRepository.checkAvailableShowSlot(startTimeNeeded, endTimeNeeded, currentDate, screenId);
	}
	
	
	public ArrayList<Show> getAllShowsByOperator(int operatorId)
	{
		return showRepository.getAllShowsByOperator(operatorId);
	}
	
}
