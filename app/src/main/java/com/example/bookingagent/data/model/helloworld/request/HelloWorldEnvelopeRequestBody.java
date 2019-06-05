package com.example.bookingagent.data.model.helloworld.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "SOAP-ENV", reference = "http://schemas.xmlsoap.org/soap/envelope/")
public class HelloWorldEnvelopeRequestBody {
	
	@Element(name = "HelloWorldRequest", required = false) private HelloWorldRequest data;
	
	public HelloWorldRequest getBody() {
		return data;
	}
	
	public void setBody(HelloWorldRequest data) {
		this.data = data;
	}
}
