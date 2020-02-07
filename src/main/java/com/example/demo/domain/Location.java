package com.example.demo.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {

    private String type;
    private List<Double> coordinates = null;
    private Boolean is_location_exact;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Double> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
	public Boolean getIs_location_exact() {
		return is_location_exact;
	}
	public void setIs_location_exact(Boolean is_location_exact) {
		this.is_location_exact = is_location_exact;
	}

   
}
