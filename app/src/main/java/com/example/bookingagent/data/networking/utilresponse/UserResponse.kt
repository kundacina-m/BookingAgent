package com.example.bookingagent.data.networking.utilresponse

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace

@Namespace(prefix = "ns3", reference = "http://korisnik.com")
data class UserResponse(

	@field:Element(name = "id")
	var id: Int = 0,
	@field:Element(name = "active")
	var active: Boolean = true,
	@field:Element(required = false, name = "email")
	var email: String = "",
	@field:Element(required = false, name = "firstName")
	var firstName: String = "",
	@field:Element(name = "lastName")
	var lastName: String = "",
	@field:Element(required = false, name = "adresa", type = AddressResponse::class)
	var address: AddressResponse = AddressResponse(),
	@field:Element(required = false, name = "password")
	var password: String = "",
	@field:Element(name = "role", required = false)
	var role: String = "",
	@field:Element(name = "userName")
	var userName: String = ""

)