package com.example.springexample.theatre;

public class MainTheatre {

	private String name;
	private String address;
	private String city;
	private int numberOfScreens;
	private String jsonData;
	
	private int operatorId;

	public MainTheatre()
	{
		
	}

	public MainTheatre(String name, String address, String city, int numberOfScreens, String jsonData, int operatorId) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.numberOfScreens = numberOfScreens;
		this.jsonData = jsonData;
		this.operatorId = operatorId;
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

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	
	
}
