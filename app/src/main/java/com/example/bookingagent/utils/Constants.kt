package com.example.bookingagent.utils

const val BASE_URL = "http://192.168.0.103:8080/service/"

const val FILE_CHOOSER_IMAGE = 1

enum class accommodation_type {
	SMESTAJ_HOTEL,
	SMESTAJ_BB,
	SMESTAJ_APARTMAN
}

object apiHeaders {
	val map = hashMapOf(
		Pair("Authorization", ""),
		Pair("Content-Type", "text/xml;charset=utf-8")
	)

	fun addToken(token: String) {
		map["Authorization"] = "" + token
	}

	fun removeToken() {
		map["Authorization"] = ""
	}
}

const val BROKEN_IMAGE =
	"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"