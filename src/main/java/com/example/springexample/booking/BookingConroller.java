
package com.example.springexample.booking;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springexample.movie.MovieDAO;
import com.example.springexample.notification.EmailNotification;
import com.example.springexample.screen.ScreenDAO;
import com.example.springexample.show.ShowDAO;
import com.example.springexample.theatre.TheatreDAO;
import com.example.springexample.user.User;
import com.example.springexample.user.UserDAO;

@Controller
public class BookingConroller {
	
	private static final Logger logger = LogManager.getLogger(BookingConroller.class);
	
	@Autowired
	private ShowDAO showDAO;
	
	@Autowired
	private TheatreDAO theatreDAO;
	
	@Autowired
	private BookingDAO bookingDAO;
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private ScreenDAO screenDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private EmailNotification emailNotification; 
	
	/*@GetMapping("/bookingPage")
	public String userForm(Model model)
	{
		model.addAttribute("bookingForm",new Booking());
		
		model.addAttribute("dateList", showDAO.getAllDates());
		model.addAttribute("cityList", theatreDAO.getAllCities());
		
		return "booking";
		
	}*/
	
	
	@GetMapping("/bookingPage")
	public String userForm(Model model)
	{
		model.addAttribute("bookingForm",new Booking());
		model.addAttribute("dateList", showDAO.getAllDatesAcoordingToSystemTime());
		
		return "booking";
		
	}
	
	@GetMapping("/userType")
	public ResponseEntity<Object> getUserType(HttpServletRequest request) 
	{
		
			HttpSession session = request.getSession();
			if(session == null || session.getAttribute("username") == null) 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	
	
	@PostMapping("/makePayment")
	public ResponseEntity<Integer> makePayment(@RequestBody Booking booking, HttpServletRequest request, Model model)
	{
		//boolean registeredUser = false;
		
		if(null==booking)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(null != username) 
		{
			booking.setUserName((String)session.getAttribute("username"));
			booking.setEmail((String)session.getAttribute("email"));
		}
		else
		{
			User guest = new User();
			guest.setUserName(booking.getUserName().toUpperCase());
			guest.setEmail(booking.getEmail());
			userDAO.addUser(guest);
		}
		
		Booking booked = bookingDAO.makeEntryinDB(booking);
		if(null!=booked)
		{
			model.addAttribute("booked", booked);
			model.addAttribute("theatreName", theatreDAO.getTheatreById(booked.getTheatreId()).getName());
			model.addAttribute("movieName", movieDAO.getMovieById(booked.getMovieId()).getName());
			model.addAttribute("screenName", screenDAO.getScreenById(booked.getScreenId()).getName());
			
			session.setAttribute("bookingId", booked.getBookingId());
			
			return new ResponseEntity<Integer>(booked.getBookingId(), HttpStatus.OK); 
			
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	
	@GetMapping("/generateTicket")
	public String generateTicket(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Booking booked=bookingDAO.getBookingById((Integer)session.getAttribute("bookingId"));
		
		String theatreName = theatreDAO.getTheatreById(booked.getTheatreId()).getName();
		String theatreAddress = theatreDAO.getTheatreById(booked.getTheatreId()).getAddress();
		String movieName = movieDAO.getMovieById(booked.getMovieId()).getName();
		String screenName = screenDAO.getScreenById(booked.getScreenId()).getName();
		
		model.addAttribute("booked", booked);
		model.addAttribute("theatreName", theatreName);
		model.addAttribute("theatreAddress", theatreAddress);
		model.addAttribute("movieName", movieName);
		model.addAttribute("screenName", screenName);
		
		String emailBody = "";
		emailBody = emailBody + 
				"Booking Id: " + booked.getBookingId() + "\n" +
				"Theatre Name: " +theatreName + "-" + theatreAddress + "\n" + 
				"Movie Name: " + movieName + "\n" + 
				"Screen Name: " + screenName + "\n" + 
				"Date: " + booked.getDate() + "\n" +
				"Time: " + booked.getTime() + "\n" + 
				"Number of Tickets: " + booked.getNumberOfTickets() + "\n" +
				"Seat Number: " + booked.getSeatNumberListString() + "\n" + 
				"Price: " + booked.getPrice() + "\n" + 
				"Total Price: " + booked.getTotalPrice() + "\n" +
				"Discount: " + booked.getDiscount() + "\n" +
				"Final Price: " + booked.getFinalPrice() + "\n";
		
		try
		{
			emailNotification.sendEmailNotification(booked.getEmail(), emailBody);
		}
		catch(MailException ex)
		{
			logger.error("Error sending mail...", ex.getMessage());
		}
		
		return "generateTicket";
	}
	
	@GetMapping("/generateTicket/{bookingId}")
	public String generateTicketByBookingId(@PathVariable(value="bookingId") int bookingId, Model model)
	{
		Booking booked=bookingDAO.getBookingById(bookingId);
		model.addAttribute("booked", booked);
		model.addAttribute("theatreName", theatreDAO.getTheatreById(booked.getTheatreId()).getName());
		model.addAttribute("movieName", movieDAO.getMovieById(booked.getMovieId()).getName());
		model.addAttribute("screenName", screenDAO.getScreenById(booked.getScreenId()).getName());
		
		return "generateTicket";
	}
	
	@GetMapping("/cancelTicket/{bookingId}")
	public ResponseEntity<Integer> cancelTicketByBookingId(@PathVariable(value="bookingId") int bookingId)
	{
		int success = bookingDAO.cancelTicketByBookingId(bookingId);
		
		if(success == 1)
		{
			return new ResponseEntity<Integer>(bookingId, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	}
	
	/*@PostMapping("/generateTicket/{bookingId}")
	public String generateTicket(@PathVariable(value="bookingId") int bookingId,Model model)
	{
		Booking booked=bookingDAO.getBookingById(bookingId);
		model.addAttribute("booked", booked);
		model.addAttribute("theatreName", theatreDAO.getTheatreById(booked.getTheatreId()).getName());
		model.addAttribute("movieName", movieDAO.getMovieById(booked.getMovieId()).getName());
		model.addAttribute("screenName", screenDAO.getScreenById(booked.getScreenId()).getName());
		
		return "generateTicket";
	}*/

	
	/*@PostMapping("/confirmBooking")
	public String confirmBooking(@ModelAttribute(value="booking") Booking booking, Model model)
	{
		model.addAttribute("booking", booking);
		
		Show show = showDAO.getShowById(booking.getShowId());
		
		
		return "bookingConfirmationPage";
		
	}*/
	
	/*@GetMapping("/cancelTicket")
	public String generateTicket(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Booking booked=bookingDAO.getBookingById((Integer)session.getAttribute("bookingId"));
		model.addAttribute("booked", booked);
		model.addAttribute("theatreName", theatreDAO.getTheatreById(booked.getTheatreId()).getName());
		model.addAttribute("movieName", movieDAO.getMovieById(booked.getMovieId()).getName());
		model.addAttribute("screenName", screenDAO.getScreenById(booked.getScreenId()).getName());
		
		return "generateTicket";
	}*/
	
}
