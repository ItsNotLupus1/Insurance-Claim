package com.spring.insurance.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EnableAutoConfiguration
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hosp_id;
	
	@ManyToMany(mappedBy = "hospital")
	private List<Insurance> insurance;

	private String hname;
	private String city;
	private String state;
	private String country;
	private String address;
	private Integer pincode;
	private Integer ratings;
	private String contact;
	private String businesshours;
	private String businessdays;
	private String description;
	private Double latitude;
	private Double longitude;
	private String facilities;
	
	public Long getHosp_id() {
		return hosp_id;
	}
	public void setHosp_id(Long hosp_id) {
		this.hosp_id = hosp_id;
	}
	public List<Insurance> getInsurance() {
		return insurance;
	}
	public void setInsurance(List<Insurance> insurance) {
		this.insurance = insurance;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public Integer getRatings() {
		return ratings;
	}
	public void setRatings(Integer ratings) {
		this.ratings = ratings;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getBusinesshours() {
		return businesshours;
	}
	public void setBusinesshours(String businesshours) {
		this.businesshours = businesshours;
	}
	public String getBusinessdays() {
		return businessdays;
	}
	public void setBusinessdays(String businessdays) {
		this.businessdays = businessdays;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getFacilities() {
		return facilities;
	}
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
	
	
}
