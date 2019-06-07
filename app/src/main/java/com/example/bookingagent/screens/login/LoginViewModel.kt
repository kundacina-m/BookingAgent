package com.example.bookingagent.screens.login

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.LocalUserEntity
import com.example.bookingagent.data.networking.helloworld.HelloWorldApi
import com.example.bookingagent.data.networking.helloworld.models.EnvelopeHelloWorldRequest
import com.example.bookingagent.data.repository.UserRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: UserRepository, val helloWorldApi: HelloWorldApi) :
	BaseViewModel() {

	val identityVerification = MutableLiveData<Boolean>()
	val serverResponse = MutableLiveData<String>()

	fun checkIfUserExists(localUserEntity: LocalUserEntity) {
		disposables.add(repository.getUser(localUserEntity.username)
			.subscribeBy {
				if (it.password == localUserEntity.password)
					identityVerification.postValue(true)
				else identityVerification.postValue(false)
			})
	}

	fun getHelloWorld(envelope: EnvelopeHelloWorldRequest) {
		disposables.add(helloWorldApi.getHelloWorld(envelope).subscribeOn(Schedulers.io())
			.subscribeBy(
				onError = { },
				onSuccess = {
					serverResponse.postValue(it.body.body.name)
				}
			))
	}

}


