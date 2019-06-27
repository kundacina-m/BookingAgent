package com.example.bookingagent.screens.messages.thread

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MessageThreadViewModel @Inject constructor(private val messagesRepository: MessagesRepository) : BaseViewModel() {

    val addedMessage = MutableLiveData<MessageEntity>()
    val messages = MutableLiveData<WrappedResponse<List<MessageEntity>>>()
    val messageSentStatus = MutableLiveData<WrappedResponse<Int>>()

    fun fetchMessages(resId: Int) =
        disposables.add(messagesRepository.getMessageThread(resId)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                messages.postValue(it)
            })

    fun sendMessage(resId: Int, message: String) {
        disposables.add(messagesRepository.sendMessage(resId, message)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                when (it) {
                    is WrappedResponse.OnSuccess -> {
                        messageSentStatus.postValue(WrappedResponse.OnSuccess(it.item.body.body.messageId))
                        addMessageToDB(resId, message, it.item.body.body.messageId)
                    }
                        is WrappedResponse.OnError -> messageSentStatus.postValue(WrappedResponse.OnError(it.error))
                }
            })
    }

    private fun addMessageToDB(resId: Int, message: String, messageId: Int) {
        val data = (messages.value as WrappedResponse.OnSuccess).item[0]
        val messageEntity = MessageEntity(messageId, data.firstname, data.lastname, message, data.agentEmail, "agent")

        addedMessage.postValue(messageEntity)

        disposables.add(Single.just(messagesRepository).subscribeOn(Schedulers.io())
            .subscribeBy {
                it.addMessage(resId, messageEntity)
            })

    }

    fun deleteMessage(id: Int) =
        disposables.add(messagesRepository.deleteMessage(id)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                when (it) {
                    is WrappedResponse.OnSuccess -> messagesRepository.deleteMessageFromDB(id)
                    is WrappedResponse.OnError -> Log.d("VIEWMODEL", "setObservers: ERROR KOD SLANJA PORUKE")
                }
            })
}