package com.example.bookingagent.screens.login

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.LocalUserEntity
import com.example.bookingagent.data.repository.UserRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {

	val identityVerification = MutableLiveData<Boolean>()

	fun checkIfUserExists(localUserEntity: LocalUserEntity) {
		disposables.add(repository.getUser(localUserEntity.username)
			.subscribeBy {
				if (it.password == localUserEntity.password)
					identityVerification.postValue(true)
				else identityVerification.postValue(false)
			})
	}

}


