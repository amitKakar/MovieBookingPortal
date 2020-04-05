package com.example.springexample.screen;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Screens")
@EntityListeners(AuditingEntityListener.class)
public class Screen {
	
	@Id
	private int screenId;
	private int theatreId;
	private String name;
	private int numberOfSeats;

	private String jsonData;
	private static int currentScreenId=0;
	
	public Screen()
	{
		
	}

	public Screen(int screenId, int theatreId, String name, int numberOfSeats) {
		super();
		this.screenId = screenId;
		this.theatreId = theatreId;
		this.name = name;
		this.numberOfSeats = numberOfSeats;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	@Transient
	public static int getCurrentScreenId() {
		return currentScreenId;
	}

	public static void setCurrentScreenId(int currentScreenId) {
		Screen.currentScreenId = currentScreenId;
	}

	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	
}
