package com.example.bookingagent.data.networking.user.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
@Namespace(prefix = "ns2", reference = "http://xml/auth")
@SoapResponse
data class LoginResponse(
	@field:Element(required = false, name = "jwtToken") var token: String = ""
)
