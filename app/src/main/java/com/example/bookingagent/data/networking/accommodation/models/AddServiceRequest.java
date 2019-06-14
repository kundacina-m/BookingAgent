package com.example.bookingagent.data.networking.accommodation.models;

import com.example.soap_annotations.SoapRequest;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "ser", reference = "http://xml/accommodation")
@SoapRequest
public class AddServiceRequest {
	
	@Element(name = "ser:accID") private Long accommodationID;
	@Element(name = "ser:name") private String name;
	@Element(name = "ser:description") private String description;
	@Element(name = "ser:price") private Integer price;
	
	public AddServiceRequest(Long accommodationID, String name, String description, Integer price) {
		this.accommodationID = accommodationID;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Long getAccommodationID() {
		return accommodationID;
	}
	
	public void setAccommodationID(Long accommodationID) {
		this.accommodationID = accommodationID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
}
