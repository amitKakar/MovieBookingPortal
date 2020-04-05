package com.example.springexample.booking;



import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="Bookings")
@EntityListeners(AuditingEntityListener.class)
public class Booking {
	
	private String userName;
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	private int showId;
	private int movieId;
	private int theatreId;
	private int screenId;
	
	private LocalTime time;
	private String date;
	private int numberOfTickets;
	private double price;
	private double totalPrice;
	private double discount;
	private double finalPrice;
	
	private ArrayList<Integer> seatIdList;
	private String seatIdListString;
	
	//private ArrayList<Integer> seatNumberList;
	private String seatNumberListString;
	
	private String city;
	
	public Booking()
	{
		
	}
	
	public Booking(String userName, String email, int bookingId, int showId, int movieId, int theatreId, int screenId,
			LocalTime time, String date, int numberOfTickets, double price, double totalPrice, double discount,
			double finalPrice, ArrayList<Integer> seatIdList, String seatIdListString, String seatNumberListString, String city) {
		super();
		this.userName = userName;
		this.email = email;
		this.bookingId = bookingId;
		this.showId = showId;
		this.movieId = movieId;
		this.theatreId = theatreId;
		this.screenId = screenId;
		this.time = time;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
		this.price = price;
		this.totalPrice = totalPrice;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.seatIdList = seatIdList;
		this.seatIdListString = seatIdListString;
		this.seatNumberListString = seatNumberListString;
		this.city=city;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Transient
	public ArrayList<Integer> getSeatIdList() {
		return seatIdList;
	}

	public void setSeatIdList(ArrayList<Integer> seatIdList) {
		this.seatIdList = seatIdList;
	}

	
	public String getSeatIdListString() {
		return seatIdListString;
	}

	public void setSeatIdListString(String seatIdListString) {
		this.seatIdListString = seatIdListString;
	}


	public String getSeatNumberListString() {
		return seatNumberListString;
	}



	public void setSeatNumberListString(String seatNumberListString) {
		this.seatNumberListString = seatNumberListString;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
