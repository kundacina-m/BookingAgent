package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
@Namespace(prefix = "ns2", reference = "http://xml/accommodation")
@SoapResponse
data class AddChangeAccommodationResponse(
	@field:Element(name = "idAccommodation", required = false) var idAccommodation: Int? = null,
	@field:Element(name = "idAddress", required = false) var idAddress: Int? = null
)
