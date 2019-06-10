package com.example.bookingagent.data.networking.accommodation.models;

import com.example.soap_annotations.SoapRequest;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "addaccommodation", reference = "http://xml/addaccommodation")
@SoapRequest
public class AddAccommodationRequest {

	@Element(name = "name") private String name;
	@Element(name = "description") private String description;
	@Element(name = "address") private String address;
	@Element(name = "type") private String type;
	@Namespace(prefix = "room", reference = "http://xml/room")
	@Element(name = "room") private AddRoom room;
	
	public AddAccommodationRequest(String name, String description, String address, String type, AddRoom room) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.type = type;
		this.room = room;
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public AddRoom getRoom() {
		return room;
	}
	
	public void setRoom(AddRoom room) {
		this.room = room;
	}
}
