package com.example.bookingagent.data.model.helloworld.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(prefix = "ns2", reference = "http://proba.com")
public class HelloWorldResponse {
	@Element(name = "hello")
	private String hello;
	
	public HelloWorldResponse(){}
	
	public String getName() {
		return hello;
	}
	
	public void setName(String hello) {
		this.hello = hello;
	}
}
