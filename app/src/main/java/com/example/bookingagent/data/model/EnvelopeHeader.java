package com.example.bookingagent.data.model;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(prefix = "SOAP-ENV", reference = "http://schemas.xmlsoap.org/soap/envelope/")
public class EnvelopeHeader {
	
	public EnvelopeHeader() {
	}
}
