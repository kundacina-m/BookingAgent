package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "termin")
data class TimePriceNoId(
	@field:Element(name = "datumPrijave") var checkIn: String? = null,
	@field:Element(name = "datumOdjave") var checkOut: String? = null,
	@field:Element(name = "cena") var price: Float = 0.toFloat()
)