package com.example.bookingagent.screens.login

import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.WrappedResponse.*
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {

    val loginFromDBresponse = MutableLiveData<WrappedResponse<String>>()
    val loginBackendResponse = MutableLiveData<WrappedResponse<String>>()

    fun loginUserByDB(username: String, password: String) {
        disposables.add(repository.loginUserByDB(username,password)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                loginFromDBresponse.postValue(it)
            })
    }

    fun loginUserOnBackend(username: String, password: String) {
        disposables.add(repository.loginUser(username, password)
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                when (it) {
                    is OnSuccess -> loginBackendResponse.postValue(OnSuccess(it.item.body.body.token))
                    is OnError -> loginBackendResponse.postValue(OnError(it.error))
                }
            })
    }

}


