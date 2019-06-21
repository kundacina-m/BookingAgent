package com.example.bookingagent.screens.login

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.RequestError.UnknownError
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_login.btLogin
import kotlinx.android.synthetic.main.fragment_login.etPassword
import kotlinx.android.synthetic.main.fragment_login.etUsername
import kotlinx.android.synthetic.main.fragment_login.tvToRegistration

class LoginFragment : BaseFragment<LoginViewModel, LoginRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_login

	override fun setObservers() {
		viewModel.identityVerification.observe(this, Observer {
			when (it) {
				true -> navigateToHome()
				false -> Log.d(TAG, "setObservers: Wrong information")
			}
		})

		viewModel.loginResponse.observe(this, Observer {
			when (it) {
				is OnSuccess -> Log.d(TAG, "setObservers: OnSuccess")
				is OnError -> {
					val error = it.error as UnknownError
					Log.e(TAG, "setObservers: " + error.t)
				}
			}
		})
	}

	private fun navigateToHome() {
		navigation.navigateToHome()
	}

	override fun initView() {

		setupListeners()
	}

	private fun setupListeners() {
		tvToRegistration.setOnClickListener {
			navigation.navigateToRegister()
		}

		btLogin.setOnClickListener {
			checkProvidedInformation()
		}
	}

	private fun checkProvidedInformation() {
		val password = etPassword.text.toString()

		if (password.length >= 0) {
			val username = etUsername.text.toString()
			//			viewModel.checkIfUserExists(User(username, password))
			viewModel.loginUserOnBackend(username, password)
		}
	}

}