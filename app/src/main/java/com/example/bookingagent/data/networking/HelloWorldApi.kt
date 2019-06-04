package com.example.bookingagent.data.networking

import com.example.bookingagent.data.model.Envelope
import com.example.bookingagent.data.model.HelloWorldRequest
import io.reactivex.Single
import retrofit2.http.*

interface HelloWorldApi {

    @Headers("Content-Type: text/xml;charset=utf-8")
    @POST("hello")
    fun getHelloWorld(@Body envelope: Envelope): Single<Any>

}