package com.example.bookingagent.data.networking.utilresponse

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace

import java.util.ArrayList

@Namespace(prefix = "ns3", reference = "http://accomodation.com")
data class AccommodationResponse(

	@field:Element(name = "id")
	var id: Int = 0,
	@field:Element(required = false, name = "tipSmestaja")
	var type: String? = null,
	@field:Element(required = false, name = "naziv")
	var name: String? = null,
	@field:Element(name = "opis")
	var desc: String? = null,
	@field:Element(required = false, name = "adresa", type = AddressResponse::class)
	var address: AddressResponse? = null,
	@field:ElementList(required = false, inline = true, type = String::class, name = "usluga", entry = "usluga")
	var services: ArrayList<String>? = null,
	@field:ElementList(required = false, inline = true, type = RoomResponse::class, name = "soba", entry = "soba")
	var rooms: ArrayList<RoomResponse>? = null,
	@field:Element(required = false, name = "kategorija")
	var category: String? = null,
	@field:Element(name = "ocena", required = false)
	var rating: Float? = null,
	@field:Element(name = "besplatnoOtkazivanje")
	var cancellingFee: Int = 0,
	@field:ElementList(required = false, inline = true, type = String::class, name = "slika", entry = "slika")
	var images: ArrayList<String>? = arrayListOf()

)
