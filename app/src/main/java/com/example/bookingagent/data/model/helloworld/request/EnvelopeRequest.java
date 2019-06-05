package com.example.bookingagent.data.model.helloworld.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
@Namespace(prefix = "SOAP-ENV", reference = "http://schemas.xmlsoap.org/soap/envelope/")
public class EnvelopeRequest {
	//	@Element(name = "soapenv:Header")
	//	private EnvelopeHeader header;
	//
	//	public EnvelopeHeader getHeader() {
	//		return header;
	//	}
	//
	//	public void setHeader(EnvelopeHeader header) {
	//		this.header = header;
	//	}
	
	@Element(name = "Body", required = false) private HelloWorldEnvelopeRequestBody body;
	
	public HelloWorldEnvelopeRequestBody getBody() {
		return body;
	}
	
	public void setBody(HelloWorldEnvelopeRequestBody body) {
		this.body = body;
	}
}
