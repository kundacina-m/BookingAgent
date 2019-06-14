package com.example.bookingagent.data.networking.user.models;

import com.example.soap_annotations.SoapRequest;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(prefix = "register", reference = "http://xml/auth")
@SoapRequest
public class RegisterRequest {
	
	@Element(name = "username") private String username;
	@Element(name = "password") private String password;
	
	public RegisterRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
