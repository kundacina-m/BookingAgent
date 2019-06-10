package com.example.bookingagent.data.networking.user.models;

import com.example.soap_annotations.SoapResponse;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(prefix = "login", reference = "http://xml/login")
@SoapResponse
public class LoginResponse {
	
	@Element(name = "token") private String token;
	
	public LoginResponse() {
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
