package com.example.bookingagent.screens.profile

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.UserEntity
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.WrappedResponse
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

	val userInfo = MutableLiveData<WrappedResponse<UserEntity>>()

	fun getUserInfo() {

	}

}