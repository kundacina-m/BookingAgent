package com.example.bookingagent.data.networking.user.models

import com.example.bookingagent.data.networking.utilresponse.UserResponse
import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
@Namespace(prefix = "ns4", reference = "http://xml/auth")
@SoapResponse
data class UserDetailsResponse(

	@Namespace(prefix = "ns3", reference = "http://korisnik.com")
	@field:Element(required = false, type = UserResponse::class, name = "Korisnik")
	var userResponse: UserResponse? = null
)