package com.example.springexample.movie;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Movies")
@EntityListeners(AuditingEntityListener.class)

public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int movieId;
	private String name;
	private String genre;
	private int duration;
	private String language;
	
	private int operatorId;
	
	public Movie ()
	{
		
	}

	public Movie(int movieId, String name, String genre, int duration, String language, int operatorId) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.genre = genre;
		this.duration = duration;
		this.language = language;
		this.operatorId = operatorId;
	}

	public int getMovieId() {
		return movieId;
	}



	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

}
