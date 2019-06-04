package com.example.bookingagent.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "soapenv:Body", strict = false)
public class EnvelopeBody {
	
	@Element(name = "prob:HelloWorldRequest",required = false)
	private HelloWorldRequest data;
	
	public HelloWorldRequest getBody() {
		return data;
	}
	
	public void setBody(HelloWorldRequest data) {
		this.data = data;
	}
}
