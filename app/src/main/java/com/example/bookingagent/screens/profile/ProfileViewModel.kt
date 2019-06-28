package com.example.bookingagent.screens.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.UserEntity
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toUserModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

	val userInfo = MutableLiveData<WrappedResponse<UserEntity>>()

	fun getUserInfo() {
		disposables.add(
			userRepository.getUserProfile()
				.subscribeOn(Schedulers.io())
				.subscribeBy {
					when (it) {
						is WrappedResponse.OnSuccess -> userInfo.postValue(
							WrappedResponse.OnSuccess(it.item.body.body.userResponse?.toUserModel()!!))
						is WrappedResponse.OnError -> Log.d("bad mapping bro", "ERROR")
					}
				})
	}

}