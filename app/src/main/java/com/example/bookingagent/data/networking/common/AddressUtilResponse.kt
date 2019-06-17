package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Element

data class AddressUtilResponse(

	@field:Element(name = "id")
	var id: Int = 0,
	@field:Element(name = "latitude")
	var latitude: Int = 0,
	@field:Element(name = "longitude")
	var longitude: Int = 0,
	@field:Element(name = "mesto")
	var city: String? = null,
	@field:Element(name = "posBroj")
	var zipCode: Int = 0,
	@field:Element(name = "ulica")
	var street: String? = null,
	@field:Element(name = "broj")
	var num: Int = 0
)
