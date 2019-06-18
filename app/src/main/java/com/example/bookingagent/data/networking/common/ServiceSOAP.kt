package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "service")
class ServiceSOAP(
	@field:Element(name = "name") var name: String?,
	@field:Element(name = "description") var description: String?,
	@field:Element(name = "price") var price: Int?
)
