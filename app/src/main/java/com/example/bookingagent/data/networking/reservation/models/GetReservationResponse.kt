package com.example.bookingagent.data.networking.reservation.models

import com.example.bookingagent.data.networking.utilresponse.ReservationResponse
import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "ns4", reference = "http://xml/reservation")
@SoapResponse
data class GetReservationResponse(
	@Namespace(prefix = "ns3", reference = "http://rezervacija.com")
	@field:ElementList(required = false, inline = true, type = ReservationResponse::class, name = "ns3:Rezervacija",
		entry = "ns3:Rezervacija")
	val reservationsResponse: ArrayList<ReservationResponse>? = arrayListOf()

)