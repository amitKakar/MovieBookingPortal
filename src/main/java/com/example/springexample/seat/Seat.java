package com.example.springexample.seat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Seats")
@EntityListeners(AuditingEntityListener.class)
public class Seat {
	
	@Id
	private int seatId;
	private int screenId;
	private int showId;
	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	private int seatNumber;
	private String status;
	private String seatClass;
	
	private static int currentSeatId;
	
	public Seat()
	{
		
	}

	public Seat(int screenId, int seatId, int seatNumber, String status, String seatClass) {
		super();
		this.screenId = screenId;
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.status = status;
		this.seatClass = seatClass;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public static int getCurrentSeatId() {
		return currentSeatId;
	}

	public static void setCurrentSeatId(int currentSeatId) {
		Seat.currentSeatId = currentSeatId;
	}
	
	

}
