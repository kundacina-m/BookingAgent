package com.example.bookingagent.data.networking.user

import com.example.bookingagent.data.networking.user.models.EnvelopeLoginRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginResponse
import com.example.bookingagent.data.networking.user.models.EnvelopeUserDetailsRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeUserDetailsResponse
import com.example.bookingagent.utils.apiHeaders
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("login")
	fun loginUser(@Body envelopeLoginRequest: EnvelopeLoginRequest): Single<EnvelopeLoginResponse>

	@POST("profile")
	fun getUserProfile(
		@Body envelopeUserDetailsRequest: EnvelopeUserDetailsRequest,
		@HeaderMap headers: Map<String, String> = apiHeaders.map
	): Single<EnvelopeUserDetailsResponse>

}
