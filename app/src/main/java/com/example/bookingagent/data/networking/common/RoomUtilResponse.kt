package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

import java.util.ArrayList

data class RoomUtilResponse(

    @field:Element(required = false, name = "id") var id: Int = 0,
    @field:Element(required = false, name = "brojSobe") var roomNum: Int = 0,
    @field:Element(required = false, name = "sprat") var floor: Int = 0,
    @field:Element(required = false, name = "brojKreveta") var bedsNum: Int = 0,
    @field:Element(required = false, name = "cena") var price: Int = 0,
    @field:Element(required = false, name = "dostupnost") var isAvaiability: Boolean? = null,
    @field:ElementList(
        required = false,
        inline = true,
        type = String::class,
        name = "komentar",
        entry = "komentar"
    ) var comments: ArrayList<String>? = null,
    @field:ElementList(
        required = false,
        inline = true,
        type = ImagesUtilResponse::class,
        name = "slika",
        entry = "slika"
    )
    var images: ArrayList<ImagesUtilResponse>? = null,
    @field:ElementList(
        required = false,
        inline = true,
        type = TimePriceUtilResponse::class,
        name = "termin",
        entry = "termin"
    )
    var timePrice: ArrayList<TimePriceUtilResponse>? = null

)
