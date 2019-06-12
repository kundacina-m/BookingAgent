package com.example.bookingagent.data.networking.accommodation.models;

import com.example.bookingagent.data.networking.common.AddressRequest;
import com.example.soap_annotations.SoapRequest;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "addaccommodation", reference = "http://xml/addaccommodation")
@SoapRequest
public class AddAccommodationRequest {

	@Element(name = "name") private String name;
	@Element(name = "description") private String description;
	@Element(name = "type") private String type;
	@Namespace(prefix = "address", reference = "http://xml/address")
	@Element(name = "address", type = AddressRequest.class) private AddressRequest address;
	//@Namespace(prefix = "room", reference = "http://xml/room")
	//@ElementList(name = "room" , type = AddRoomRequest.class) private List<AddRoomRequest> room;
	
	public AddAccommodationRequest(String name, String description, String type, AddressRequest address) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.address = address;
		//this.room = room;
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public AddressRequest getAddress() {
		return address;
	}
	
	public void setAddress(AddressRequest address) {
		this.address = address;
	}
	
	//public List<AddRoomRequest> getRoom() {
	//	return room;
	//}
	//
	//public void setRoom(List<AddRoomRequest> room) {
	//	this.room = room;
	//}
}
