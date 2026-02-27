package com.spring_prac;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotExist extends RuntimeException{
	 public DataNotExist() {
	        super("Car not found!");
	    }
	    public DataNotExist(int id) {
	        super("Car with id " + id + " not found!");
	    }
}
