package com.example.bookingagent.data.networking.accommodation.models;

import com.example.bookingagent.data.networking.common.FullAccommodation;
import com.example.soap_annotations.SoapResponse;
import java.util.ArrayList;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(prefix = "ns4", reference = "http://xml/accommodation")
@SoapResponse
public class GetAccommodationResponse {
	
	@Namespace(prefix = "ns3", reference = "http://accomodation.com")
	@ElementList(inline = true, type = FullAccommodation.class, name = "ns3:Smestaj", entry = "Smestaj")
	private ArrayList<FullAccommodation> accommodation;
	
	public GetAccommodationResponse() {
	}
	
	public GetAccommodationResponse(ArrayList<FullAccommodation> accommodation) {
		this.accommodation = accommodation;
	}
	
	public ArrayList<FullAccommodation> getAccommodation() {
		return accommodation;
	}
	
	public void setAccommodation(ArrayList<FullAccommodation> accommodation) {
		this.accommodation = accommodation;
	}
}
