package com.example.bookingagent.screens.rooms.details

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.ScheduleUnit
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomResponse
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoomDetailsViewModel @Inject constructor(private val roomRepository: RoomRepository) : BaseViewModel() {

	val deleteStatus = MutableLiveData<WrappedResponse<EnvelopeDeleteRoomResponse>>()
	val images = MutableLiveData<List<String>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()



	fun deleteRoom(id: Long) {
		disposables.add(
			roomRepository.deleteRoom(id)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					deleteStatus.postValue(it)
				})
	}

}