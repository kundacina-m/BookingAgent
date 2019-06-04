package com.example.bookingagent.data.networking

import com.example.bookingagent.data.model.Envelope
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TestApi {

	@Headers( "Content-Type: text/xml",
		"Accept-Charset: utf-8", "Content-Length: 420" )
	@POST("webservices/dilbert.asmx")
	fun getResponse(@Body dilbert: Envelope): Single<Any>


}