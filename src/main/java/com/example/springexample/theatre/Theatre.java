package com.example.springexample.theatre;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Theatres")
@EntityListeners(AuditingEntityListener.class)

public class Theatre {
	
	@Id
	private int theatreId;
	
	//@Column(unique = true)
	private String name;
	private String address;
	private String city;
	private int numberOfScreens;
	
	private int operatorId;
	
	private static int currentTheatreId=0;
	
	public Theatre()
	{
		
	}
	
	public Theatre(int theatreId, String name, String address, String city, int numberOfScreens, int operatorId) {
		super();
		this.theatreId = theatreId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.numberOfScreens = numberOfScreens;
		this.operatorId = operatorId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getNumberOfScreens() {
		return numberOfScreens;
	}

	public void setNumberOfScreens(int numberOfScreens) {
		this.numberOfScreens = numberOfScreens;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public static int getCurrentTheatreId() {
		return currentTheatreId;
	}

	public static void setCurrentTheatreId(int currentTheatreId) {
		Theatre.currentTheatreId = currentTheatreId;
	}

}