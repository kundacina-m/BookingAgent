package com.example.bookingagent.screens.rooms.add

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Image
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.db.entities.ScheduleUnit
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import javax.inject.Inject

class AddRoomViewModel @Inject constructor() : BaseViewModel() {

	val images = MutableLiveData<List<Image>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()
	val roomAddedResponse = MutableLiveData<WrappedResponse<Boolean>>()

	fun addRoom(room: Room) =
		roomAddedResponse.postValue(OnSuccess(true))


}