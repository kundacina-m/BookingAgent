package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.AccommodationDao
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.GetAccommodationRequest
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import javax.inject.Inject

class AccommodationRepository @Inject constructor(
	private val accommodationDao: AccommodationDao,
	private val accommodationApi: AccommodationApi
) {

	fun addAccommodation(envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest):
		Single<WrappedResponse<EnvelopeAddAccommodationResponse>> =
		accommodationApi.addAccommodation(envelopeAddAccommodationRequest).toSealed()

	fun getAccommodations(): Single<WrappedResponse<EnvelopeGetAccommodationResponse>> =
	//        accommodationApi.test(
	//            EnvelopeGetAccommodationResponse(
	//                GetAccommodationResponse(
	//                    arrayListOf(
	//                        FullAccommodation(
	//                            1, "1", "1", "1", AddressUtilResponse(1, 2, 3, "33", 4, "412", 3), arrayListOf(
	//                                ServiceUtilResponse(
	//                                    1, "@3", "#2", 3
	//                                )
	//                            ),
	//                            arrayListOf(
	//                                RoomUtilResponse(
	//                                    1, 2, 3, 4, 5, "DA", arrayListOf("sd"), arrayListOf(ImagesUtilResponse("a", 2)),
	//                                    arrayListOf(TimePriceUtilResponse(1, GregorianCalendar(), GregorianCalendar(), 2))
	//                                )
	//                            ), "kategorija", 2.0f, 2
	//                        )
	//                    )
	//                )
	//            )
		//        ).toSealed()
		accommodationApi.getAccommodations(EnvelopeGetAccommodationRequest(GetAccommodationRequest())).toSealed()
	//    }

	fun addService(
		envelopeAddServiceRequest: EnvelopeAddServiceRequest
	): Single<WrappedResponse<EnvelopeAddServiceResponse>> =
		accommodationApi.addService(envelopeAddServiceRequest).toSealed()

	fun addAccommodationToDB(accommodation: Accommodation): Single<WrappedResponse<Long>> =
		Single.just(accommodationDao.addAccommodation(accommodation)).toSealed()

	fun addAddressToDB(address: Address): Single<WrappedResponse<Long>> =
		Single.just(accommodationDao.addAddress(address)).toSealed()

}