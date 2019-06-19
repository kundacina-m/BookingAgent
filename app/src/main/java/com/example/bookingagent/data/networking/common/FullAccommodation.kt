package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace

import java.util.ArrayList

@Namespace(prefix = "ns3", reference = "http://accomodation.com")
data class FullAccommodation(

	@field:Element(name = "id")
	var id: Int = 0,
	@field:Element(required = false, name = "tipSmestaja")
	var type: String? = null,
	@field:Element(required = false, name = "naziv")
	var name: String? = null,
	@field:Element(name = "opis")
	var desc: String? = null,
	@field:Element(required = false, name = "adresa", type = AddressUtilResponse::class)
	var address: AddressUtilResponse? = null,
	@field:ElementList(required = false, inline = true, type = String::class, name = "usluga", entry = "usluga")
	var services: ArrayList<String>? = null,
	@field:ElementList(required = false, inline = true, type = RoomUtilResponse::class, name = "soba", entry = "soba")
	var rooms: ArrayList<RoomUtilResponse>? = null,
	@field:Element(required = false, name = "kategorija")
	var category: String? = null,
	@field:Element(name = "ocena", required = false)
	var rating: Float? = null,
	@field:Element(name = "besplatnoOtkazivanje")
	var cancellingFee: Float = 0f

)
