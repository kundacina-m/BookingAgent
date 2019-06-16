package com.example.bookingagent.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.User
import com.example.bookingagent.data.networking.helloworld.HelloWorldApi
import com.example.bookingagent.data.networking.helloworld.models.EnvelopeHelloWorldRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginResponse
import com.example.bookingagent.data.networking.user.models.LoginRequest
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: UserRepository, val helloWorldApi: HelloWorldApi) :
	BaseViewModel() {

	val identityVerification = MutableLiveData<Boolean>()
	val serverResponse = MutableLiveData<String>()
	val loginResponse = MutableLiveData<WrappedResponse<EnvelopeLoginResponse>>()

	fun checkIfUserExists(user: User) {
		disposables.add(repository.getUser(user.username)
			.subscribeBy {
				if (it.password == user.password)
					identityVerification.postValue(true)
				else identityVerification.postValue(false)
			})
	}

	fun getHelloWorld(envelope: EnvelopeHelloWorldRequest) {
		disposables.add(helloWorldApi.getHelloWorld(envelope).subscribeOn(Schedulers.io())
			.subscribeBy(
				onError = {
					serverResponse.postValue(it.message)
					Log.d("error", "getHelloWorld: error ")
				},
				onSuccess = {
					serverResponse.postValue(it.body.body.name)
					Log.d("error", "it.body.body.name")
				}
			))
	}

	fun loginUserOnBackend(username: String, password: String) {

		val loginRequest = EnvelopeLoginRequest(LoginRequest(username, password))

		disposables.add(repository.loginUser(loginRequest)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
			loginResponse.postValue(it)
				identityVerification.postValue(true)
		})
	}

}


