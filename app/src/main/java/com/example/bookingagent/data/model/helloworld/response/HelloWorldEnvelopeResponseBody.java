package com.example.bookingagent.data.model.helloworld.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Body", strict = false)
public class HelloWorldEnvelopeResponseBody {
	
	public HelloWorldEnvelopeResponseBody(){}
	
	@Element(name = "HelloWorldResponse", required = false) private HelloWorldResponse data;
	
	public HelloWorldResponse getBody() {
		return data;
	}
	
	public void setBody(HelloWorldResponse data) {
		this.data = data;
	}
}
