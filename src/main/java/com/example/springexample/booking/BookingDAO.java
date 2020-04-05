package com.example.springexample.booking;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springexample.seat.SeatDAO;
import com.example.springexample.show.Show;
import com.example.springexample.show.ShowDAO;

@Service
public class BookingDAO {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ShowDAO showDAO;
	
	@Autowired
	private SeatDAO seatDAO;
	
 
	
	public Booking makeEntryinDB(Booking booking)
	{
		Show show = showDAO.getShowById(booking.getShowId());
		
		ArrayList<Integer> bookedSeatIdList = booking.getSeatIdList();
		
		int numberOfTickets = bookedSeatIdList.size();
		String seatIdListString = "";
		/*for(int seatId : bookedSeatIdList)
		{
			
			int temp=seatId;
			seatDAO.updateSeatStatusToBooked(temp);
			
			seatIdListString = seatIdListString + seatId+",";
			System.out.println(seatIdListString);
			seatId=temp;
		}*/
		String seatNumberListString = "";
		
		ArrayList<String> list = seatDAO.updateSeatStatusToBooked(bookedSeatIdList);
		if(null != list)
		{
			seatIdListString = list.get(0);
			seatNumberListString = list.get(1);
			
			booking.setScreenId(show.getScreenId());
			booking.setSeatIdListString(seatIdListString);
			booking.setSeatIdList(null);
			booking.setSeatNumberListString(seatNumberListString);
			booking.setTime(show.getStartTime());
			
			booking.setNumberOfTickets(numberOfTickets);
			booking.setPrice(show.getPrice());
			booking.setTotalPrice(numberOfTickets*show.getPrice());
			booking.setDiscount(booking.getTotalPrice()*show.getDiscount()/100);
			
			double finalPrice = booking.getTotalPrice() - (booking.getTotalPrice()*show.getDiscount()/100); 
			booking.setFinalPrice(finalPrice);
			bookingRepository.save(booking);
			
			return booking;
		}
		return null;
	}
	
	public ArrayList<Booking> history(String userName)
	{
		return bookingRepository.history(userName);
	}

	public Booking getBookingById(int bookingId)
	{
		Optional<Booking> bookingContainer = bookingRepository.findById(bookingId);
		return bookingContainer.get();
	}
	
	
	
	public int cancelTicketByBookingId(int bookingId)
	{
		Booking booked=getBookingById(bookingId);
		
		LocalDate currentDate = LocalDate.now();
		
		
		if(currentDate.compareTo(LocalDate.parse(booked.getDate())) == 0)
		{
			LocalTime currentTime = LocalTime.now();
			
			if(currentTime.compareTo(booked.getTime()) < 0)
			{
				long elapsedMinutes = Duration.between( currentTime, booked.getTime()).toMinutes();
				
				if(elapsedMinutes >= 60)
				{
					String bookedSeatIdListString = booked.getSeatIdListString();
					StringTokenizer tokens = new StringTokenizer(bookedSeatIdListString, ",");
					
					while(tokens.hasMoreTokens())
					{
						int seatId = Integer.parseInt(tokens.nextToken());
						changeSeatStatusToAvailable(seatId);
					}
					
					bookingRepository.deleteById(bookingId);
					
					return 1;
				}
				
				return 0;
			}
			
			return 0;
			
		}
		else if(currentDate.compareTo(LocalDate.parse(booked.getDate())) < 0)
		{
			String bookedSeatIdListString = booked.getSeatIdListString();
			StringTokenizer tokens = new StringTokenizer(bookedSeatIdListString, ",");
			
			while(tokens.hasMoreTokens())
			{
				int seatId = Integer.parseInt(tokens.nextToken());
				changeSeatStatusToAvailable(seatId);
			}
			
			bookingRepository.deleteById(bookingId);
			return 1;
		}
		
		return 0;
	}
	
	
	public void changeSeatStatusToAvailable(int seatId)
	{
		seatDAO.changeSeatStatusToAvailable(seatId);
	}

}
