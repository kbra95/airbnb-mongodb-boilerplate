package com.example.demo.json;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component
public class DetailAttribute {

	private String home_id;
	private String image;
	private int accomodates;
	private int bedrooms;
	private int beds;
	private int bathooms;
	private String summary;
	
	public DetailAttribute () {
		
	}
	
	public DetailAttribute(String home_id, String image, int accomodates, int bedrooms, int beds, int bathooms,
			String summary) {
		super();
		this.home_id = home_id;
		this.image = image;
		this.accomodates = accomodates;
		this.bedrooms = bedrooms;
		this.beds = beds;
		this.bathooms = bathooms;
		this.summary = summary;
	}
	public String getHome_id() {
		return home_id;
	}
	public void setHome_id(String home_id) {
		this.home_id = home_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAccomodates() {
		return accomodates;
	}
	public void setAccomodates(int accomodates) {
		this.accomodates = accomodates;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getBathooms() {
		return bathooms;
	}
	public void setBathooms(int bathooms) {
		this.bathooms = bathooms;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
