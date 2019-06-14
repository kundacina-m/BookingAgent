package com.example.bookingagent.data.networking.user

import com.example.bookingagent.data.networking.user.models.EnvelopeLoginRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginResponse
import com.example.bookingagent.data.networking.user.models.EnvelopeRegisterRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeRegisterResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("login")
	fun loginUser(@Body envelopeLoginRequest: EnvelopeLoginRequest): Single<EnvelopeLoginResponse>

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("register")
	fun registerUser(@Body envelopeRegisterRequest: EnvelopeRegisterRequest): Single<EnvelopeRegisterResponse>

}