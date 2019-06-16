package com.example.bookingagent.data.networking.accommodation

import com.example.bookingagent.data.networking.accommodation.models.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccommodationApi {

    @Headers("Content-Type: text/xml;charset=utf-8")
    @POST("accommodation")
    fun addAccommodation(@Body envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest):
            Single<EnvelopeAddAccommodationResponse>

    @Headers("Content-Type: text/xml;charset=utf-8")
    @POST("getaccommodation")
    fun getAccommodations(@Body envelopeGetAccommodationsRequest: EnvelopeGetAccommodationRequest):
            Single<EnvelopeGetAccommodationResponse>

    @Headers("Content-Type: text/xml;charset=utf-8")
    @POST("accommodation")
    fun addService(@Body envelopeAddServiceRequest: EnvelopeAddServiceRequest):
            Single<EnvelopeAddServiceResponse>

//    @Headers("Content-Type: text/xml;charset=utf-8")
//    @POST("getaccommodation")
//    fun test(@Body envelopeGetAccommodationResponse: EnvelopeGetAccommodationResponse):
//            Single<EnvelopeGetAccommodationResponse>

}