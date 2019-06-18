package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "acc", reference = "http://xml/accommodation")
@SoapRequest
data class AddAccommodationRequest(
	@field:Element(name = "acc:name") var name: String?,
	@field:Element(name = "acc:description") var description: String?,
	@field:Element(name = "acc:type") var type: String?,
	@field:Element(name = "acc:bedsNum") var bedsNum: Int?,
	@field:Element(name = "acc:cancellingFee") var cancelingFee: Long?,
	@field:Element(name = "acc:latitude") var latitude: Int,
	@field:Element(name = "acc:longitude") var longitude: Int,
	@field:Element(name = "acc:place") var city: String?,
	@field:Element(name = "acc:countyNum") var zipCode: Int,
	@field:Element(name = "acc:street") var street: String?,
	@field:Element(name = "acc:number") var num: Int
)
