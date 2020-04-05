package com.example.springexample.show;

public class ShowDetail extends Show{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movieName;
	private String theatreName;
	private String theatreAddress;
	private String screenName;
	
	public ShowDetail()
	{
		
	}

	public ShowDetail(String movieName, String theatreName, String theatreAddress, String screenName) {
		super();
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.theatreAddress = theatreAddress;
		this.screenName = screenName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getTheatreAddress() {
		return theatreAddress;
	}

	public void setTheatreAddress(String theatreAddress) {
		this.theatreAddress = theatreAddress;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	
	
}
