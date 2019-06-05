package com.example.bookingagent.data.model.helloworld.response;

import com.example.bookingagent.data.model.EnvelopeHeader;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
@NamespaceList({
	@Namespace(prefix = "SOAP-ENV", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class HelloWorldEnvelopeResponse {
	
	public HelloWorldEnvelopeResponse(){}
	
	@Element(name = "Header", required = false)
	private EnvelopeHeader header;

	public EnvelopeHeader getHeader() {
		return header;
	}

	public void setHeader(EnvelopeHeader header) {
		this.header = header;
	}
	
	@Element(name = "Body")
	private HelloWorldEnvelopeResponseBody body;
	
	public HelloWorldEnvelopeResponseBody getBody() {
		return body;
	}
	
	public void setBody(HelloWorldEnvelopeResponseBody body) {
		this.body = body;
	}
}
