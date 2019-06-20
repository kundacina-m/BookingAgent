package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "acc", reference = "http://xml/accommodation")
@SoapRequest
data class DeleteAccommodationRequest(
	@field:Element(name = "acc:id") val id: Int?
)