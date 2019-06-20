package com.example.bookingagent.screens.rooms.add

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddRoomViewModel @Inject constructor(private val roomRepository: RoomRepository) :
	BaseViewModel() {

	val images = MutableLiveData<List<String>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()
	val roomAddedResponse = MutableLiveData<WrappedResponse<Boolean>>()

	fun addRoom(accId: Int, room: Room) =
		disposables.add(roomRepository.addRoom(accId, room)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> addRoomToDB(accId, room)
					is OnError -> roomAddedResponse.postValue(OnError(it.error))
				}
			})

	private fun addRoomToDB(accId: Int, room: Room) =
		disposables.add(roomRepository.addRoomToDB(room)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> addAccRoomRow(accId, room.id)
					is OnError -> roomAddedResponse.postValue(OnError(it.error))
				}
			})

	private fun addAccRoomRow(accId: Int, roomId: Int) =
		disposables.add(roomRepository.addAccRoomRow(accId, roomId)).also {
			roomAddedResponse.postValue(OnSuccess(true))
		}
}