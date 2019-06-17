package com.example.bookingagent.data.networking.common

import org.simpleframework.xml.Element

data class ServiceUtilResponse(

	@field:Element(name = "id")
	var id: Int = 0,
	@field:Element(name = "naziv")
	var name: String? = null,
	@field:Element(name = "opis")
	var desc: String? = null,
	@field:Element(name = "cena")
	var cena: Int = 0
)