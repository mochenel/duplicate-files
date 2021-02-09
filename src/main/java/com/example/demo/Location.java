package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Location {

	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
