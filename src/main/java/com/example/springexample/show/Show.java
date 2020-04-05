package com.example.springexample.show;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Shows")
@EntityListeners(AuditingEntityListener.class)
public class Show  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int showId;
	private int movieId;
	private int theatreId;
	private int screenId;
	
	private double price;
	private double discount;
	
	/*@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "curr_date", columnDefinition="DATE")
	@Temporal(TemporalType.DATE)
	private Date currDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date", columnDefinition="DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date", columnDefinition="DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "start_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp startTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "end_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp endTime;*/
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate currDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime endTime;
	
	
	private String currDateString;
	private String startDateString;
	private String endDateString;
	private String startTimeString;
	private String endTimeString;
	
	private int operatorId;
	
	
	public Show()
	{
		
	}

	public Show(int showId, int movieId, int theatreId, int screenId, double price, double discount, LocalDate currDate,
			LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int operatorId) {
		super();
		this.showId = showId;
		this.movieId = movieId;
		this.theatreId = theatreId;
		this.screenId = screenId;
		this.price = price;
		this.discount = discount;
		this.currDate = currDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.operatorId = operatorId;
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

	public LocalDate getCurrDate() {
		return currDate;
	}

	public void setCurrDate(LocalDate currDate) {
		this.currDate = currDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	//********Transient*******
	@Transient
	public String getCurrDateString() {
		return currDateString;
	}

	public void setCurrDateString(String currDateString) {
		this.currDateString = currDateString;
	}

	@Transient
	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}
	@Transient

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	@Transient
	public String getStartTimeString() {
		return startTimeString;
	}

	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}
	
	@Transient
	public String getEndTimeString() {
		return endTimeString;
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

}
