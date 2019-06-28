package com.example.bookingagent.data.networking.utilresponse

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import java.util.ArrayList

@Namespace(prefix = "ns3", reference = "http://rezervacija.com")
data class ReservationResponse(

	@field:Element(name = "id")
	var id: Int = 0,
	@field:Element(required = false, name = "korisnickoIme")
	var firstName: String = "",
	@field:Element(required = false, name = "korisnickoPrezime")
	var lastName: String = "",
	@field:Element(name = "korisnickiEmail", required = false)
	var email: String = "",
	@field:Element(required = false, name = "soba", type = RoomResponse::class)
	var room: RoomResponse = RoomResponse(),
	@field:Element(required = false, name = "od")
	var from: String = "",
	@field:Element(required = false, name = "_do")
	var to: String = "",
	@field:Element(name = "ukupnaCena", required = false)
	var price: Float = 0f,
	@field:ElementList(
		required = false,
		inline = true,
		type = MessageResponse::class,
		name = "poruka",
		entry = "poruka"
	)
	var messages: ArrayList<MessageResponse> = arrayListOf(),
	@field:Element(name = "successful", required = false) var reservationUsed: Boolean = false,
	@field:Element(name = "accommodationName") var accommodationName: String = ""

)