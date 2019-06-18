package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
@Namespace(prefix = "ns4", reference = "http://xml/accommodation")
@SoapResponse
data class AddRoomResponse(
	val id: String
)