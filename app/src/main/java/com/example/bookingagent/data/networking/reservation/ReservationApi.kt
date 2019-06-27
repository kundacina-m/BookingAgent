package com.example.bookingagent.data.networking.reservation

import com.example.bookingagent.data.networking.reservation.models.EnvelopeGetReservationRequest
import com.example.bookingagent.data.networking.reservation.models.EnvelopeGetReservationResponse
import com.example.bookingagent.utils.apiHeaders
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ReservationApi {

	@POST("reservation")
	fun getAllReservations(@Body envelopeGetReservationRequest: EnvelopeGetReservationRequest,
		@HeaderMap headers: Map<String, String> = apiHeaders.map):
		Single<EnvelopeGetReservationResponse>
}