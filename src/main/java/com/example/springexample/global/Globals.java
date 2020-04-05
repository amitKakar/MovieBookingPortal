package com.example.springexample.global;
import java.util.concurrent.*;
//import java.util.ConcurrentHashMap;

public class Globals {
	public static ConcurrentHashMap<String, Integer> userMap = new ConcurrentHashMap<>(); 
	public static ConcurrentHashMap<String, Integer> movieMap = new ConcurrentHashMap<String, Integer>(); 
	public static ConcurrentHashMap< ConcurrentHashMap<String, String>, Integer> theatreMap = new ConcurrentHashMap<>(); 
	public static ConcurrentHashMap<String, Integer> showMap = new ConcurrentHashMap<>(); 
	public static ConcurrentHashMap< ConcurrentHashMap<Integer, String>, Integer> screenMap = new ConcurrentHashMap<>(); 
	public static ConcurrentHashMap< ConcurrentHashMap<Integer, Integer>, Integer> seatMap = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<String, Integer> cityMap = new ConcurrentHashMap<>(); 
	public static ConcurrentHashMap<String, Integer> operatorMap = new ConcurrentHashMap<>(); 
	public static ConcurrentHashMap<String, Integer> adminMap = new ConcurrentHashMap<>(); 
}
