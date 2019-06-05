package com.example.bookingagent.data.networking

import com.example.bookingagent.data.model.helloworld.request.EnvelopeRequest
import com.example.bookingagent.data.model.helloworld.response.HelloWorldEnvelopeResponse
import io.reactivex.Single
import retrofit2.http.*

interface HelloWorldApi {

    @Headers("Content-Type: text/xml;charset=utf-8")
    @POST("hello")
    fun getHelloWorld(@Body envelopeRequest: EnvelopeRequest): Single<HelloWorldEnvelopeResponse>

}