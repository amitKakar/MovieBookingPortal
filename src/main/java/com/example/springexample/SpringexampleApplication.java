package com.example.springexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringexampleApplication{

	//private static final Logger logger = LogManager.getLogger(SpringexampleApplication.class);	
	public static void main(String[] args) {
		SpringApplication.run(SpringexampleApplication.class, args);
		
		/*logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
        logger.fatal("Damn! Fatal error. Please fix me.");*/
	}
}

