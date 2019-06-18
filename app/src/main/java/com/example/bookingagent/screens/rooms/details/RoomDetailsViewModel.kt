package com.example.bookingagent.screens.rooms.details

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.ScheduleUnit
import javax.inject.Inject

class RoomDetailsViewModel @Inject constructor() : BaseViewModel() {

	val images = MutableLiveData<List<String>>()
	val schedule = MutableLiveData<List<ScheduleUnit>>()
}