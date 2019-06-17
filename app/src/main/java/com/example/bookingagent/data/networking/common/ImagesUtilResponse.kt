package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "slika")
data class ImagesUtilResponse(

	@field:Attribute(required = false) var src: String? = null,
	@field:Element(name = "id", required = false) var id: Int = 0
)
