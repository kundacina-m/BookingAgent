package com.example.bookingagent.data.networking.room.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import java.util.ArrayList

@Root
@Namespace(prefix = "acc", reference = "http://xml/accommodation")
@SoapRequest
data class AddChangeRoomRequest(
	@field:Element(required = false, name = "acc:accommodationId") var accId: Int = 0,
	@field:Element(required = false, name = "acc:id") var roomId: Int = 0,
	@field:Element(required = false, name = "acc:number") var roomNum: Int = 0,
	@field:Element(required = false, name = "acc:floorNum") var floor: Int = 0,
	@field:Element(required = false, name = "acc:bedsNum") var bedsNum: Int = 0,
	@field:Element(required = false, name = "acc:price") var price: Float = 0f,
	@field:Element(required = false, name = "acc:available") var available: Boolean? = null,
	@field:ElementList(
		required = false,
		inline = true,
		type = String::class,
		name = "acc:pictures",
		entry = "acc:pictures"
	)
	var images: ArrayList<String>? = null,
	@field:ElementList(
		required = false,
		inline = true,
		type = String::class,
		name = "acc:appointments",
		entry = "acc:appointments"
	)
	var timePrice: ArrayList<String>? = null,
	@field:ElementList(
		required = false,
		inline = true,
		type = String::class,
		name = "acc:comments",
		entry = "acc:comments"
	)
	var comments: ArrayList<String>? = null

)