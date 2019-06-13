package com.example.bookingagent.data.networking.accommodation.models;

import com.example.soap_annotations.SoapResponse;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "ns2", reference = "http://xml/accommodation")
@SoapResponse
public class AddAccommodationResponse {
	
	@Element(name = "idAccommodation") private Integer idAccommodation;
	@Element(name = "idAddress") private Integer idAddress;
	
	public AddAccommodationResponse(Integer idAccommodation, Integer idAddress) {
		this.idAccommodation = idAccommodation;
		this.idAddress = idAddress;
	}
	
	public Integer getIdAccommodation() {
		return idAccommodation;
	}
	
	public void setIdAccommodation(Integer idAccommodation) {
		this.idAccommodation = idAccommodation;
	}
	
	public Integer getIdAddress() {
		return idAddress;
	}
	
	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}
}
