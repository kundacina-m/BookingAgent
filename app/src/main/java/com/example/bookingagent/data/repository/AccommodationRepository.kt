package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.AccommodationDao
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.accommodation.models.AddRoomRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddRoomRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddRoomResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddServiceResponse
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.data.networking.accommodation.models.GetAccommodationRequest
import com.example.bookingagent.data.networking.common.TimePriceNoId
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
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
		accommodationApi.getAccommodations(EnvelopeGetAccommodationRequest(GetAccommodationRequest())).toSealed()

	fun addService(
		envelopeAddServiceRequest: EnvelopeAddServiceRequest
	): Single<WrappedResponse<EnvelopeAddServiceResponse>> =
		accommodationApi.addService(envelopeAddServiceRequest).toSealed()

	fun addAccommodationToDB(accommodation: Accommodation): Single<WrappedResponse<Long>> =
		Single.just(accommodationDao.addAccommodation(accommodation)).toSealed()

	fun addAddressToDB(address: Address): Single<WrappedResponse<Long>> =
		Single.just(accommodationDao.addAddress(address)).toSealed()

	fun addRoom(accId: Int, room: Room): Single<WrappedResponse<EnvelopeAddRoomResponse>> {

		val schedule = arrayListOf<TimePriceNoId>()
		room.schedule?.forEach {
			schedule.add(TimePriceNoId(it.checkIn.toString(),it.checkOut.toString(),it.price))
		}

		return accommodationApi.addRoom(EnvelopeAddRoomRequest(AddRoomRequest(
			accId = accId,
			roomNum = room.roomNum!!,
			floor = room.floor!!,
			price = room.price!!,
			images = room.images,
			bedsNum = room.bedNums!!,
			isAvaiability = true,
			timePrice = schedule
		))).toSealed()
	}

}