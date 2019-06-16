package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.db.entities.User
import com.example.bookingagent.data.networking.user.UserApi
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeLoginResponse
import com.example.bookingagent.data.networking.user.models.EnvelopeRegisterRequest
import com.example.bookingagent.data.networking.user.models.EnvelopeRegisterResponse
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao, private val userApi: UserApi) {

	fun addUser(user: User) =
		Single.just(userDao.addUser(user))

	fun getUser(username: String) =
		userDao.getUser(username).subscribeOn(Schedulers.io())

	fun removeUser(user: User) {
		userDao.deleteUser(user)
	}

	fun registerUser(registerRequest: EnvelopeRegisterRequest): Single<WrappedResponse<EnvelopeRegisterResponse>> =
		userApi.registerUser(registerRequest).toSealed()

	fun loginUser(loginRequest: EnvelopeLoginRequest): Single<WrappedResponse<EnvelopeLoginResponse>> =
		userApi.loginUser(loginRequest).toSealed()



}