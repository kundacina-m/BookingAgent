package com.example.bookingagent.screens.messages

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.ReservationEntity
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MessagesViewModel @Inject constructor(val messagesRepository: MessagesRepository) : BaseViewModel() {

	val messageThreads = MutableLiveData<WrappedResponse<List<ReservationEntity>>>()

	fun getAllMessages() =
		disposables.add(messagesRepository.getAllMessagedFromDB()
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				messageThreads.postValue(it)
			})

}