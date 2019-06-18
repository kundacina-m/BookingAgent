package com.example.bookingagent.screens.register

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.User
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {

	val registrationStatus = MutableLiveData<Boolean>()

	fun registerUser(user: User) {

		disposables.add(Single.just(repository)
			.subscribeOn(Schedulers.io())
			.subscribeBy { repo ->
				repo.addUser(user)
					.subscribeOn(Schedulers.io())
					.subscribeBy {
						registrationStatus.postValue(it > 0)
					}
			})

	}
}