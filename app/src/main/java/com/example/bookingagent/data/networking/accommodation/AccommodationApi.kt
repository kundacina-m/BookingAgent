package com.example.bookingagent.data.networking.accommodation

import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccommodationApi {

	@Headers("Content-Type: text/xml;charset=utf-8")
	@POST("addaccommodation")
	fun addAccommodation(@Body envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest):
		Single<EnvelopeAddAccommodationResponse>

}