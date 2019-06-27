package com.example.bookingagent.screens.messages.thread

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MessageThreadViewModel @Inject constructor(private val messagesRepository: MessagesRepository) : BaseViewModel() {

	val messages = MutableLiveData<WrappedResponse<List<MessageEntity>>>()

	fun fetchMessages(resId: Int) =
		disposables.add(messagesRepository.getMessageThread(resId)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				messages.postValue(it)
			})

	fun sendMessage(resId: Int,message: String) =
		disposables.add(messagesRepository.sendMessage(resId,message))

}