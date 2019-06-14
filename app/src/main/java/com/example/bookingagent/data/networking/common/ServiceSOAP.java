package com.example.bookingagent.data.networking.common;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "service")
public class ServiceSOAP {
	
	@Element(name = "name") private String name;
	@Element(name = "description") private String description;
	@Element(name = "price") private Integer price;
	
	public ServiceSOAP(String name, String description, Integer price) {
		this.name = name;
		this.description = description;
		this.price = price;
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
