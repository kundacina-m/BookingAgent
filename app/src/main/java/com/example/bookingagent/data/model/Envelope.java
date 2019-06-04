package com.example.bookingagent.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soapenv:Envelope")
@NamespaceList({
	@Namespace(prefix = "prob", reference = "http://proba.com"),
	@Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class Envelope {
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

	@Element(name = "soapenv:Body", required = false)
	private EnvelopeBody body;
	
	public EnvelopeBody getBody() {
		return body;
	}
	
	public void setBody(EnvelopeBody body) {
		this.body = body;
	}
}
