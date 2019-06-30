package com.example.bookingagent.screens.login

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.RequestError.*
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.ApiHeaders
import com.example.bookingagent.utils.Cache
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.fragment_login.btLogin
import kotlinx.android.synthetic.main.fragment_login.etPassword
import kotlinx.android.synthetic.main.fragment_login.etUsername

class LoginFragment : BaseFragment<LoginViewModel, LoginRoutes>() {

    private lateinit var password:String

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun setObservers() {
        viewModel.loginFromDBresponse.observe(this, Observer {
            when (it) {
                is OnSuccess -> onLoginSuccessfully(it.item)
                is OnError -> Log.d("ERROR","${it.error}")
            }
        })

        viewModel.loginBackendResponse.observe(this, Observer {
            when (it) {
                is OnSuccess -> onLoginSuccessfully(it.item)
                is OnError -> handleError(it)
            }
        })
    }

    private fun onLoginSuccessfully(token: String) {
        Cache.cachePass(password)
        ApiHeaders.addToken(token)
        navigateToHome()
    }

    private fun navigateToHome() =
        navigation.navigateToHome()


    override fun initView() {
        setupListeners()
    }

    private fun setupListeners() =
        btLogin.setOnClickListener {
            login()
        }

    private fun login(loginOnBackend: Boolean = true) =
        validateLoginInfo().run {
            first?.let {
                if (loginOnBackend) viewModel.loginUserOnBackend(first!!, second!!)
                else viewModel.loginUserByDB(first!!, second!!)
            }
        }

    private fun validateLoginInfo() : Pair<String?,String?>{
        val password = etPassword.asString()
        val username = etUsername.asString()

        if (username.length < 4) { showToast("Username too short!") ; return Pair(null,null) }
        if (password.length < 4) { showToast("Password too short!") ; return Pair(null,null) }

        this.password = password

        return Pair(username,password)
    }

    private fun handleError(error: OnError<String>) {
        when (error.error) {
            is NoInternetError -> login(loginOnBackend = false)
            is HttpError -> showToast("You have entered wrong credentials!")
            is UnknownError -> {
                login(loginOnBackend = false)
                showToast("There is issue with server, request was not processed")
            }
        }
    }

}