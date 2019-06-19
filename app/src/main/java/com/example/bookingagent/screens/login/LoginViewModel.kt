package com.example.bookingagent.screens.login

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.User
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginResponse
import com.example.bookingagent.data.networking.user.models.LoginRequest
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {

	val identityVerification = MutableLiveData<Boolean>()
	val loginResponse = MutableLiveData<WrappedResponse<EnvelopeLoginResponse>>()

	fun checkIfUserExists(user: User) {
		disposables.add(repository.getUser(user.username)
			.subscribeBy {
				if (it.password == user.password)
					identityVerification.postValue(true)
				else identityVerification.postValue(false)
			})
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


