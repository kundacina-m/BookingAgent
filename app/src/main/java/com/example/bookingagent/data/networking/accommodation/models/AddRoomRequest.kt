package com.example.bookingagent.data.networking.accommodation.models

import com.example.bookingagent.data.networking.common.TimePriceNoId
import com.example.bookingagent.data.networking.common.TimePriceUtilResponse
import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import java.util.ArrayList

@Root
@Namespace(prefix = "room", reference = "http://xml/room")
@SoapRequest
data class AddRoomRequest(
	@field:Element(required = false, name = "accId") var accId: Int = 0,
	@field:Element(required = false, name = "brojSobe") var roomNum: Int = 0,
	@field:Element(required = false, name = "sprat") var floor: Int = 0,
	@field:Element(required = false, name = "brojKreveta") var bedsNum: Int = 0,
	@field:Element(required = false, name = "cena") var price: Int = 0,
	@field:Element(required = false, name = "dostupnost") var isAvaiability: Boolean? = null,
	@field:ElementList(
		required = false,
		inline = true,
		type = String::class,
		name = "slika",
		entry = "slika"
	)
	var images: ArrayList<String>? = null,
	@field:ElementList(
		required = false,
		inline = true,
		type = TimePriceUtilResponse::class,
		name = "termin",
		entry = "termin"
	)
	var timePrice: ArrayList<TimePriceNoId>? = null

)