package com.example.bookingagent.data.networking.user.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "auth", reference = "http://xml/auth")
@SoapRequest
data class LoginRequest(
	@field:Element(name = "auth:username") var username: String? = null,
	@field:Element(name = "auth:password") var password: String? = null
)
