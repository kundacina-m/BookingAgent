package com.example.bookingagent.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeGetAccommodationResponse
import com.example.bookingagent.data.networking.common.FullAccommodation
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.toAccommodationModel
import com.example.bookingagent.utils.toRoomsModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
	private val accommodationRepository: AccommodationRepository,
	private val roomRepository: RoomRepository) :
	BaseViewModel() {

	val syncingStatus = MutableLiveData<Boolean>()

	fun getAccommodations() {
		disposables.add(
			accommodationRepository.getAccommodations()
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> mapResponseToModel(it.item)
						is OnError -> syncingStatus.postValue(false)
					}
				})
	}

	private fun mapResponseToModel(response: EnvelopeGetAccommodationResponse) {
		response.body.body.accommodation?.forEach { accommodationResponse ->
			addAccommodationToDB(accommodationResponse)
		}

	}

	private fun addAccommodationToDB(accommodationResponse: FullAccommodation) =
		disposables.add(
			accommodationRepository.addAccommodationToDB(accommodationResponse.toAccommodationModel())
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					if ((it as OnSuccess).item > 0)
						addRoomsToDB(accommodationResponse)

				})

	private fun addRoomsToDB(accommodationResponse: FullAccommodation) =
		accommodationResponse.rooms?.forEach { roomResponse ->
			disposables.add(roomRepository.addRoomToDB(roomResponse.toRoomsModel())
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> roomRepository.addAccRoomRow(accommodationResponse.id, roomResponse.id)
						is OnError -> Log.d("ERROR", "addRoomsToDB: ERROR")
					}
				})
		}

}