package com.example.bookingagent.screens.rooms.details

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoomDetailsViewModel @Inject constructor(private val roomRepository: RoomRepository) : BaseViewModel() {

	val deleteStatus = MutableLiveData<WrappedResponse<Boolean>>()
	val images = MutableLiveData<List<String>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()
	val room = MutableLiveData<WrappedResponse<RoomEntity>>()

	fun getRoomById(id: Int) =
		disposables.add(roomRepository.getRoom(id)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				room.postValue(it)
			})

	fun deleteRoom(id: Int) =
		disposables.add(
			roomRepository.deleteRoom(id)
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is OnSuccess -> deleteRoomFromDB(id)
						is OnError -> deleteStatus.postValue(OnError(it.error))
					}
				})

	private fun deleteRoomFromDB(id: Int) =
		disposables.add(Single.just(roomRepository)
			.subscribeOn(Schedulers.io())
			.subscribeBy { repository ->
				repository.deleteRoomFromDB(id)
					.subscribeOn(Schedulers.io())
					.subscribeBy {
						when (it) {
							is OnSuccess -> deleteStatus.postValue(OnSuccess(true))
							is OnError -> deleteStatus.postValue(OnError(it.error))
						}
					}

			})

}