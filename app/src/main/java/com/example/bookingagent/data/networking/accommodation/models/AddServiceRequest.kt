package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "ser", reference = "http://xml/accommodation")
@SoapRequest
data class AddServiceRequest(
	@field:Element(name = "ser:accID") var accommodationID: Long?,
	@field:Element(name = "ser:name") var name: String?,
	@field:Element(name = "ser:description") var description: String?,
	@field:Element(name = "ser:price") var price: Int?
)
