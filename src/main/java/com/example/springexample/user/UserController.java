package com.example.springexample.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springexample.booking.Booking;
import com.example.springexample.booking.BookingDAO;
import com.example.springexample.booking.History;
import com.example.springexample.movie.MovieDAO;
import com.example.springexample.screen.ScreenDAO;
import com.example.springexample.theatre.TheatreDAO;


@Controller
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private TheatreDAO theatreDAO;
	
	@Autowired
	private ScreenDAO screenDAO;
	
	@Autowired
	private BookingDAO bookingDAO;
	
	
	/*@GetMapping("/test")
	public User test()
	{
		User user = new User(22, "rahul", "rahul123", "rahul111", "rahul@gmail.com", "M", 24);
		return user;
	}*/
	
	@GetMapping("/welcome")
	public String welcome(User user,Model model)
	{  
		
		model.addAttribute("loginForm", new User());
		return "welcome";
	}
	 
	
	@GetMapping("/register")
	public String userForm(Model model)
	{
		model.addAttribute("userForm",new User());
		return "userRegistration";
	}
	
	
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user,Model model)
	{
		int success = userDAO.addUser(user);
		
		if(success == 1)
		{
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		
	} 
	
	@GetMapping("/userList")
	public String userList(Model model)
	{
		model.addAttribute("users",userDAO.getAllUser());
		return "userTable";
	}
	
	@GetMapping("/userLogin")
	public String loginForm(Model model)
	{
		model.addAttribute("loginForm", new User());
		return "login";
	}

	

	@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/userLogin";
    }
	
	
	@PostMapping("/authetication")
	public ResponseEntity<User> authenticate(@RequestBody User user, HttpSession session)
	{
		User savedUser = userDAO.authenticate(user);
		if(null != savedUser)
		{
			
			session.setAttribute("username", savedUser.getUserName());
			session.setAttribute("email", savedUser.getEmail());
			return new ResponseEntity<User>(savedUser, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping("/users")
	public List<User> getAllUser() {
	
		return userDAO.getAllUser();
	}
	
	
	@GetMapping("/user/id/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable(value="userId") int userId)
	{
		User user = userDAO.getUserById(userId);
		
		if(null == user )
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(user);
	}
	
	
	@GetMapping("/user/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable(value="userName") String userName)
	{
		System.out.println(" userDAO= "+userDAO);
		User user = userDAO.getUserByUserName(userName);
		
		if(null == user )
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User updatedUser,  HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		User newUpdatedUser = userDAO.updateUser((String)session.getAttribute("username") , updatedUser);
		
		 /*userDAO.getUserByUserName((String)session.getAttribute("username"));*/
		
		if(newUpdatedUser != null)
		{
			return new ResponseEntity<User>(newUpdatedUser, HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/user/id/{userId}")
	public void delete(@PathVariable(value="userId") int userId)
	{
		userDAO.deleteUserById(userId);
	}
	
	@DeleteMapping("/user/{userName}")
	public ResponseEntity<Integer> delete(@PathVariable(value="userName") String userName)
	{
		int success = userDAO.deleteUserByName(userName);
		
		if(success == 1)
		{
			return new ResponseEntity<Integer>(success, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/profile")
	public String profile(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		if(null == (String)session.getAttribute("username"))
		{
			return "redirect:/userLogin";
		}
		
		User user = userDAO.getUserByUserName((String)session.getAttribute("username"));
		
		if(null == user )
		{
			System.out.println("invalid user...!!!!");
		}
		
		model.addAttribute("user", user);
		
		return "userProfile";
	}
	
	@GetMapping("/history")
	public String history( Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		if(null == (String)session.getAttribute("username"))
		{
			return "redirect:/userLogin";
		}
		
		List<History> historyList = getHistory((String)session.getAttribute("username"));
		model.addAttribute("historyList", historyList);
		
		return "history";
	}
	
	@GetMapping("/generateTicket/guest")
	public String getGuestTicketPage(Model model)
	{
		model.addAttribute("guestTicketForm", new User());
		return "guestTicket";
	}
	
	@GetMapping("/authenticateGuest/{userName}")
	public ResponseEntity<User> authenticateGuest(@PathVariable(value="userName") String userName)
	{
		User savedGuest = userDAO.getUserByUserName(userName);
		if(null == savedGuest)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		else
		{
			return new ResponseEntity<User>(savedGuest, HttpStatus.OK);
		}
	}
	
	@PostMapping("/history/guest")
	public String history( User guest, Model model)
	{
		List<History> historyList = getHistory(guest.getUserName());
		model.addAttribute("historyList", historyList);
		return "history";
	}
	
	public List<History> getHistory(String userName)
	{
		ArrayList<Booking> bookingList =  bookingDAO.history(userName);
		List<History> historyList = new ArrayList<History>();

		for(Booking booked : bookingList)
		{
		
			History history = new History();
			history.setBookingId(booked.getBookingId());
			history.setMovieName(movieDAO.getMovieById(booked.getMovieId()).getName());
			history.setTheatreName(theatreDAO.getTheatreById(booked.getTheatreId()).getName());
			history.setTheatreAddress(theatreDAO.getTheatreById(booked.getTheatreId()).getAddress());
			history.setScreenName(screenDAO.getScreenById(booked.getScreenId()).getName());
			history.setDate(booked.getDate());
			history.setTime(booked.getTime());
			history.setPrice(booked.getPrice());
			history.setDiscount(booked.getDiscount());
			history.setFinalPrice(booked.getFinalPrice());
			String seatNumberList =  booked.getSeatNumberListString();
			history.setSeatNumberListString(seatNumberList);
			
			historyList.add(history);
		}
		
		Collections.sort(historyList);
		
		return historyList;
	}
	
	/*@PostMapping("/update/name")
	public void updateName(User updatedUser)
	{
		userDAO.updateName(updatedUser);
	}
	
	@PostMapping("/update/password")
	public void updatePassword(User updatedUser)
	{
		userDAO.updatePassword(updatedUser);
	}
	
	@PostMapping("/update/email")
	public void updateEmail(User updatedUser)
	{
		userDAO.updateEmail(updatedUser);
	}
	
	@PostMapping("/update/age")
	public void updateAge(User updatedUser)
	{
		userDAO.updateAge(updatedUser);
	}
	
	@PostMapping("/update/gender")
	public void updateGender(User updatedUser)
	{
		userDAO.updateGender(updatedUser);
	}*/
	
}