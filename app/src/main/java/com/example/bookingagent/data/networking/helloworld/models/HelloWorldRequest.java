package com.example.bookingagent.data.networking.helloworld.models;

import com.example.soap_annotations.SoapRequest;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@SoapRequest
@Root
@Namespace(prefix = "prob", reference = "http://proba.com")
public class HelloWorldRequest {
	
	@Element(name = "prob:name") private String name;
	
	public HelloWorldRequest(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
