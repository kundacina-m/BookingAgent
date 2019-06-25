package com.example.bookingagent.data.networking.utilresponse

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace

@Namespace(prefix = "ns3", reference = "http://rezervacija.com")
data class ReservationResponse(
	@field:Element(name = "id")
	var id: Int = 0,
	@field:Element(required = false, name = "korisnickoIme")
	var firstName: String? = null,
	@field:Element(required = false, name = "korisnickoPrezime")
	var lastName: String? = null,
	@field:Element(name = "korisnickiEmail")
	var email: String? = null,
	@field:Element(required = false, name = "soba", type = RoomResponse::class)
	var room: RoomResponse? = null,
	@field:Element(required = false, name = "od")
	var from: String? = null,
	@field:Element(required = false, name = "_do")
	var to: String? = null,
	@field:Element(name = "ukupnaCena", required = false)
	var price: Float? = null
)