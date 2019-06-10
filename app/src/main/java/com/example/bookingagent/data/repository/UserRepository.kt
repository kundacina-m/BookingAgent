package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.db.entities.LocalUserEntity
import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationRequest
import com.example.bookingagent.data.networking.accommodation.models.EnvelopeAddAccommodationResponse
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

class UserRepository @Inject constructor(private val userDao: UserDao, private val userApi: UserApi,
	private val accommodationApi: AccommodationApi) {

	fun addUser(user: LocalUserEntity) =
		Single.just(userDao.addUser(user))

	fun getUser(username: String) =
		userDao.getUser(username).subscribeOn(Schedulers.io())

	fun removeUser(user: LocalUserEntity) {
		userDao.deleteUser(user)
	}

	fun registerUser(registerRequest: EnvelopeRegisterRequest): Single<WrappedResponse<EnvelopeRegisterResponse>> =
		userApi.registerUser(registerRequest).toSealed()

	fun loginUser(loginRequest: EnvelopeLoginRequest): Single<WrappedResponse<EnvelopeLoginResponse>> =
		userApi.loginUser(loginRequest).toSealed()

	fun addAccommodation(envelopeAddAccommodationRequest: EnvelopeAddAccommodationRequest):
		Single<WrappedResponse<EnvelopeAddAccommodationResponse>> =
		accommodationApi.addAccommodation(envelopeAddAccommodationRequest).toSealed()

}