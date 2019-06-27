package com.example.bookingagent.data.networking.message

import com.example.bookingagent.data.networking.message.models.EnvelopeAddMessageRequest
import com.example.bookingagent.data.networking.message.models.EnvelopeAddMessageResponse
import com.example.bookingagent.data.networking.message.models.EnvelopeDeleteMessageRequest
import com.example.bookingagent.data.networking.message.models.EnvelopeDeleteMessageResponse
import com.example.bookingagent.utils.apiHeaders
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface MessageApi {

    @POST("reservation")
    fun addMessage(
        @Body envelopeAddMessageRequest: EnvelopeAddMessageRequest,
        @HeaderMap headers: Map<String, String> = apiHeaders.map
    ): Single<EnvelopeAddMessageResponse>

    @POST("reservation")
    fun deleteMessage(
        @Body envelopeDeleteMessageRequest: EnvelopeDeleteMessageRequest,
        @HeaderMap headers: Map<String, String> = apiHeaders.map
    ): Single<EnvelopeDeleteMessageResponse>

}