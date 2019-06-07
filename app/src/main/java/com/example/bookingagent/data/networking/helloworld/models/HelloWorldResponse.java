package com.example.bookingagent.data.networking.helloworld.models;

import com.example.soap_annotations.SoapResponse;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(prefix = "ns2", reference = "http://proba.com")
@SoapResponse
public class HelloWorldResponse {
	@Element(name = "hello") private String hello;
	
	public HelloWorldResponse() {
	}
	
	public String getName() {
		return hello;
	}
	
	public void setName(String hello) {
		this.hello = hello;
	}
}
