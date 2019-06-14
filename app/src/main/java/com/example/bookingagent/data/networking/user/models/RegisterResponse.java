package com.example.bookingagent.data.networking.user.models;

import com.example.soap_annotations.SoapResponse;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "register", reference = "http://xml/auth")
@SoapResponse
public class RegisterResponse {
	
	public RegisterResponse() {
	}
}
