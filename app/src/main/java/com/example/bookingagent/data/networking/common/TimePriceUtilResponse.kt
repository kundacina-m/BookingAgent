package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

import java.util.GregorianCalendar

@Root(name = "termin")
data class TimePriceUtilResponse(
    @field:Element(name = "id") var id: Int = 0,
    @field:Element(name = "datumPrijave") var checkIn: String? = null,
    @field:Element(name = "datumOdjave") var checkOut: String? = null,
    @field:Element(name = "Cena") var price: Float = 0.toFloat()
)
