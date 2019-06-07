package com.example.bookingagent.data.networking.helloworld

import com.example.bookingagent.data.networking.helloworld.models.EnvelopeHelloWorldRequest
import com.example.bookingagent.data.networking.helloworld.models.EnvelopeHelloWorldResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface HelloWorldApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("hello")
	fun getHelloWorld(@Body envelopeRequest: EnvelopeHelloWorldRequest): Single<EnvelopeHelloWorldResponse>

}