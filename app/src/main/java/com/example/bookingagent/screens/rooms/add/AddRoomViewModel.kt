package com.example.bookingagent.screens.rooms.add

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.model.OccupiedTime
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
	val notAvailable = MutableLiveData<List<OccupiedTime>>()
	val roomAddedResponse = MutableLiveData<WrappedResponse<Boolean>>()

	fun addRoom(accId: Int, roomEntity: RoomEntity) =
		disposables.add(roomRepository.addRoom(accId, roomEntity)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> addRoomToDB(accId, roomEntity)
					is OnError -> roomAddedResponse.postValue(OnError(it.error))
				}
			})

	private fun addRoomToDB(accId: Int, roomEntity: RoomEntity) =
		disposables.add(roomRepository.addRoomToDB(roomEntity)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> addAccRoomRow(accId, roomEntity.id)
					is OnError -> roomAddedResponse.postValue(OnError(it.error))
				}
			})

	private fun addAccRoomRow(accId: Int, roomId: Int) =
		disposables.add(roomRepository.addAccRoomRow(accId, roomId)).also {
			roomAddedResponse.postValue(OnSuccess(true))
		}
}