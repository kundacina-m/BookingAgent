package com.example.bookingagent.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.UserEntity
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginResponse
import com.example.bookingagent.data.networking.user.models.LoginRequest
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.apiHeaders
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {

	val identityVerification = MutableLiveData<Boolean>()
	val loginResponse = MutableLiveData<WrappedResponse<EnvelopeLoginResponse>>()

	fun checkIfUserExists(userEntity: UserEntity) {
		disposables.add(repository.getUser(userEntity.username!!)
			.subscribeBy {
				if (it.password == userEntity.password)
					identityVerification.postValue(true)
				else identityVerification.postValue(false)
			})
	}

	fun loginUserOnBackend(username: String, password: String) {

		val loginRequest = EnvelopeLoginRequest(LoginRequest(username, password))

		disposables.add(repository.loginUser(loginRequest)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is OnSuccess -> {
						Log.d("ERROR","NIJE DOBIO LOGIN ${it.item.body.body.token}")
						loginResponse.postValue(it)
						apiHeaders.addToken(it.item.body.body.token!!)
						identityVerification.postValue(true)
					}
					is WrappedResponse.OnError -> Log.d("ERROR","NIJE DOBIO LOGIN ${it.error}")
				}
			})
	}

}


