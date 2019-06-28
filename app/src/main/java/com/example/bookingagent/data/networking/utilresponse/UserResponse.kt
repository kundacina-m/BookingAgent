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
	var email: String? = null,
	@field:Element(required = false, name = "firstName")
	var firstName: String? = null,
	@field:Element(name = "lastName")
	var lastName: String? = null,
	@field:Element(required = false, name = "adresa", type = AddressResponse::class)
	var address: AddressResponse? = null,
	@field:Element(required = false, name = "password")
	var password: String? = null,
	@field:Element(name = "role", required = false)
	var role: String? = null,
	@field:Element(name = "userName")
	var userName: String? = null

)