package com.example.bookingagent.data.networking.accommodation.models;

import com.example.bookingagent.data.networking.common.AddressSOAP;
import com.example.soap_annotations.SoapRequest;
import java.util.ArrayList;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "acc", reference = "http://xml/accommodation")
@SoapRequest
public class AddAccommodationRequest {
	
	@Element(name = "acc:name") private String name;
	@Element(name = "acc:description") private String description;
	@Element(name = "acc:type") private String type;
	@Namespace(prefix = "com", reference = "http://complextypes.com")
	@Element(name = "Adresa", type = AddressSOAP.class) private AddressSOAP address;
	@Element(name = "acc:bedsNum") private Integer bedsNum;
	@Element(name = "acc:cancellingFee") private Long cancelingFee;
	//@Namespace(prefix = "service", reference = "http://xml/service")
	//@ElementList(name = "services", type = ServiceSOAP.class) private List<ServiceSOAP> services;
	@ElementList(inline = true, type = String.class, name = "acc:images", entry = "images") private ArrayList<String>
		images;
	
	public AddAccommodationRequest(String name, String description, String type, AddressSOAP address, Integer bedsNum,
		Long cancelingFee, ArrayList<String> images) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.address = address;
		this.bedsNum = bedsNum;
		this.cancelingFee = cancelingFee;
		this.images = images;
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
	
	public AddressSOAP getAddress() {
		return address;
	}
	
	public void setAddress(AddressSOAP address) {
		this.address = address;
	}
	
	public Integer getBedsNum() {
		return bedsNum;
	}
	
	public void setBedsNum(Integer bedsNum) {
		this.bedsNum = bedsNum;
	}
	
	public Long getCancelingFee() {
		return cancelingFee;
	}
	
	public void setCancelingFee(Long cancelingFee) {
		this.cancelingFee = cancelingFee;
	}
	
	public ArrayList<String> getImages() {
		return images;
	}
	
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
}
