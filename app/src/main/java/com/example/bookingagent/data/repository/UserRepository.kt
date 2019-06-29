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


    // region NETWORK

    fun loginUser(username: String, password: String): Single<WrappedResponse<EnvelopeLoginResponse>> =
        userApi.loginUser(EnvelopeLoginRequest(LoginRequest(username, password))).toSealed()

    fun getUserProfile(): Single<WrappedResponse<EnvelopeUserDetailsResponse>> =
        userApi.getUserProfile(EnvelopeUserDetailsRequest(UserDetailsRequest())).toSealed()

    // endregion NETWORK


    // region DB

    fun addUser(userEntity: UserEntity) =
        Single.just(userDao.addUser(userEntity))

    fun loginUserByDB(username: String, password: String) =
        userDao.getUserToken(username,password).subscribeOn(Schedulers.io()).toSealed()

    fun getUserProfileFromDB(token: String) =
        userDao.getUserByToken(token).toSealed()

    // endregion DB

}