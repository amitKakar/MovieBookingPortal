package com.example.springexample.seat;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springexample.show.ShowDAO;

@RestController
public class SeatController {

	
	@Autowired
	private SeatDAO seatDAO;
	
	@Autowired 
	private ShowDAO showDAO;
	
	@PostMapping("/seat")
	public void addSeat(@Valid @RequestBody Seat seat ) 
	{
		//seatDAO.addSeat(seat);
		
	}
	
	@RequestMapping("/seats")
	public List<Seat> getAllSeats()
	{
		return seatDAO.getAllSeats();
	}
	
	@GetMapping("/seat/id/{seatId}")
	public Seat getSeatById(@PathVariable(value="seatId") int seatId)
	{
		return seatDAO.getSeatById(seatId);
	}
	 
	/*@GetMapping("/seat/{screenId}/{seatNumber}")
	public Seat getSeat(@PathVariable(value="screenId") int screenId, @PathVariable(value="seatName") int seatNumber)
	{
		return seatDAO.getSeat(screenId, seatNumber);
	}*/
	
	@PutMapping("/seat/{screenId}/{seatNumber}")
	public void updateSeat(@PathVariable(value = "screenId") int screenId,
			@PathVariable(value = "seatNumber") int seatNumber, @Valid @RequestBody Seat updatedSeat)
	{
		seatDAO.update(screenId, seatNumber, updatedSeat);
	}
	 
	@DeleteMapping("/seat/{screenId}/{seatNumber}")
	public void delete(@PathVariable(value = "screenId") int screenId, @PathVariable(value="seatNumber") int seatNumber)
	{
		seatDAO.deleteSeatBySeatNumber(screenId, seatNumber);
	}
	
	@GetMapping("/seatResult/{showId}")
	public ResponseEntity<ArrayList<Seat>> getSeats(@PathVariable(value = "showId") Integer showId) {
		// int foo = Integer.parseInt(theatreId);
		if(null==showId){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// int showId=show.getShowId();
		int screenId = showDAO.getScreenIdByShowId(showId);
		ArrayList<Seat> seatList = seatDAO.getSeat(screenId,showId);
		if(seatList.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// return ResponseEntity.ok().body(screenList);

		return new ResponseEntity<ArrayList<Seat>>(seatList, HttpStatus.OK);
	}
}
