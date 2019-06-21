package com.example.bookingagent.data.networking.accommodation.models

import com.example.bookingagent.data.networking.utilresponse.AccommodationResponse
import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import java.util.ArrayList

@Root(strict = false)
@Namespace(prefix = "ns4", reference = "http://xml/accommodationResponse")
@SoapResponse
data class GetAccommodationResponse(

	@Namespace(prefix = "ns3", reference = "http://accomodation.com")
	@field:ElementList(required = false, inline = true, type = AccommodationResponse::class, name = "ns3:Smestaj", entry =
	"Smestaj")
	var accommodationResponse: ArrayList<AccommodationResponse>? = null
)
