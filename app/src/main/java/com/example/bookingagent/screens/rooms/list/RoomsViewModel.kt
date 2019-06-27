package com.example.bookingagent.screens.rooms.list

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoomsViewModel @Inject constructor(private val roomRepository: RoomRepository) : BaseViewModel() {

	val rooms = MutableLiveData<WrappedResponse<List<RoomEntity>>>()

	fun getAllRoomsByAccId(accId: Int) =
		disposables.add(roomRepository.getAllRoomsByAccId(accId)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				rooms.postValue(it)
			})

}