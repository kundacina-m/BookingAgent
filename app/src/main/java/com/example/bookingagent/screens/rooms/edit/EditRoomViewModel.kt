package com.example.bookingagent.screens.rooms.edit

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.model.OccupiedTime
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditRoomViewModel @Inject constructor(private val roomRepository: RoomRepository) : BaseViewModel() {

	val editStatus = MutableLiveData<WrappedResponse<Boolean>>()
	val images = MutableLiveData<List<String>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()
	val notAvailable = MutableLiveData<List<OccupiedTime>>()


	fun editRoom(roomEntity: RoomEntity) =
		disposables.add(roomRepository.editRoom(roomEntity)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> editRoomInDB(roomEntity)
					is OnError -> editStatus.postValue(OnError(it.error))
				}
			})

	private fun editRoomInDB(roomEntity: RoomEntity) =
		disposables.add(Single.just(roomRepository)
			.subscribeOn(Schedulers.io())
			.subscribeBy { repository ->
				repository.updateRoomInDB(roomEntity)
					.subscribeOn(Schedulers.io())
					.subscribeBy {
						when (it) {
							is OnSuccess -> editStatus.postValue(OnSuccess(true))
							is OnError -> editStatus.postValue(OnError(it.error))
						}
					}

			})
}