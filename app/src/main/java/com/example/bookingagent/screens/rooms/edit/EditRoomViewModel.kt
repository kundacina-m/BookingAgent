package com.example.bookingagent.screens.rooms.edit

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.db.entities.ScheduleUnit
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomResponse
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditRoomViewModel @Inject constructor(private val roomRepository: RoomRepository) : BaseViewModel() {

	val editStatus = MutableLiveData<WrappedResponse<EnvelopeAddChangeRoomResponse>>()
	val images = MutableLiveData<List<String>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()

	fun editRoom(room: Room) {
		disposables.add(roomRepository.editRoom(room)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				editStatus.postValue(it)
			})
	}
}