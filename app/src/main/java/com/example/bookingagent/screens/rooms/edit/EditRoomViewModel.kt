package com.example.bookingagent.screens.rooms.edit

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.db.entities.ScheduleUnit
import javax.inject.Inject

class EditRoomViewModel @Inject constructor() : BaseViewModel() {
	fun editRoom(room: Room) {

	}

	val images = MutableLiveData<List<String>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()
}