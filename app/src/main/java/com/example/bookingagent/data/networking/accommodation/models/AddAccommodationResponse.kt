package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
@Namespace(prefix = "ns2", reference = "http://xml/accommodation")
@SoapResponse
data class AddAccommodationResponse(
	@field:Element(name = "idAccommodation") var idAccommodation: Int? = null,
	@field:Element(name = "idAddress") var idAddress: Int? = null
)
