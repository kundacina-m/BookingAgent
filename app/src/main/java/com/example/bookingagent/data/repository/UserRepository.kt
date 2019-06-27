package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.db.entities.UserEntity
import com.example.bookingagent.data.networking.user.UserApi
import com.example.bookingagent.data.networking.user.models.*
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao, private val userApi: UserApi) {

	fun addUser(userEntity: UserEntity) =
		Single.just(userDao.addUser(userEntity))

	fun getUser(username: String) =
		userDao.getUser(username).subscribeOn(Schedulers.io())

	fun removeUser(userEntity: UserEntity) {
		userDao.deleteUser(userEntity)
	}

	fun loginUser(loginRequest: EnvelopeLoginRequest): Single<WrappedResponse<EnvelopeLoginResponse>> =
		userApi.loginUser(loginRequest).toSealed()

	fun getUserProfile() : Single<WrappedResponse<EnvelopeUserDetailsResponse>> =
		userApi.getUserProfile(EnvelopeUserDetailsRequest(UserDetailsRequest())).toSealed()


}