package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.db.entities.LocalUserEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

	fun addUser(user: LocalUserEntity) =
		Single.just(userDao.addUser(user))


	fun getUser(username: String) =
		userDao.getUser(username).subscribeOn(Schedulers.io())


	fun removeUser(user: LocalUserEntity) {
		userDao.deleteUser(user)
	}
}