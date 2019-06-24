package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "acc", reference = "http://xml/accommodation")
@SoapRequest
data class AddChangeAccommodationRequest(
	@field:Element(name = "acc:id") var id: Int?,
	@field:Element(name = "acc:name") var name: String?,
	@field:Element(name = "acc:description") var description: String?,
	@field:Element(name = "acc:type") var type: String?,
	@field:Element(name = "acc:cancellingFee") var cancellingFee: Float?,
	@field:Element(name = "acc:latitude") var latitude: Float?,
	@field:Element(name = "acc:longitude") var longitude: Float?,
	@field:Element(name = "acc:place") var city: String?,
	@field:Element(name = "acc:countyNum") var zipCode: Int?,
	@field:Element(name = "acc:street") var street: String?,
	@field:Element(name = "acc:number") var num: Int?,
	@field:ElementList(name = "acc:services", inline = true, entry = "acc:services") var services: ArrayList<String>?,
	@field:ElementList(name = "acc:pictures", inline = true, entry = "acc:pictures") var pictures: ArrayList<String>?
)
