package com.example.bookingagent.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.data.networking.utilresponse.AccommodationResponse
import com.example.bookingagent.data.networking.utilresponse.ReservationResponse
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.data.repository.ReservationRepository
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.toAccommodationModel
import com.example.bookingagent.utils.toMessageModel
import com.example.bookingagent.utils.toReservationModel
import com.example.bookingagent.utils.toRoomsModel
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class HomeViewModel @Inject constructor(
	private val accommodationRepository: AccommodationRepository,
	private val roomRepository: RoomRepository,
	private val reservationRepository: ReservationRepository,
	private val messagesRepository: MessagesRepository) :
	BaseViewModel() {

	val syncingStatus = MutableLiveData<Boolean>()

	fun getAccommodations() =
		disposables.add(
			accommodationRepository.getAccommodations()
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> mapResponseToModel(it.item)
						is OnError -> {
							syncingStatus.postValue(false)
							Log.d("GRESA", "getAccommodations: ${it.error}")
						}
					}
				})

	private fun mapResponseToModel(response: EnvelopeGetAccommodationResponse) =
		response.body.body.accommodationResponse?.forEach { accommodationResponse ->
			addAccommodationToDB(accommodationResponse)
		}

	private fun addAccommodationToDB(accommodationResponseResponse: AccommodationResponse) =
		disposables.add(
			accommodationRepository.addAccommodationToDB(accommodationResponseResponse.toAccommodationModel())
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					if ((it as OnSuccess).item > 0)
						addRoomsToDB(accommodationResponseResponse)

				})

	private fun addRoomsToDB(accommodationResponseResponse: AccommodationResponse) =
		accommodationResponseResponse.rooms?.forEach { roomResponse ->
			disposables.add(roomRepository.addRoomToDB(roomResponse.toRoomsModel())
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> roomRepository.addAccRoomRow(accommodationResponseResponse.id, roomResponse.id)
						is OnError -> Log.d("ERROR", "addRoomsToDB: ERROR")
					}
				})
		}

	fun getAllReservations() =
		disposables.add(reservationRepository.getAllReservations()
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> mapToDB(it.item.body.body.reservationsResponse)
					is OnError -> Log.d("ERROR", it.error.toString())
				}
			})

	private fun mapToDB(response: ArrayList<ReservationResponse>?) =
		disposables.add(Single.just(reservationRepository)
			.subscribeOn(Schedulers.io())
			.subscribeBy { repository ->
				response?.forEach { reservationResponse ->
					repository.addReservationToDB(reservationResponse.toReservationModel())
						.subscribeOn(Schedulers.io())
						.subscribeBy {
							when (it) {
								is OnSuccess -> reservationResponse.messages.forEach { messageResponse ->
									messagesRepository.addMessage(
										reservationResponse.id,
										messageResponse.toMessageModel()
									)

								}
							}
						}
				}
			})

}