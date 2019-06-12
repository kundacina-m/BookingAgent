package com.example.bookingagent.screens.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.LocalUserEntity
import com.example.bookingagent.data.networking.helloworld.models.EnvelopeHelloWorldRequest
import com.example.bookingagent.data.networking.helloworld.models.HelloWorldRequest
import com.example.bookingagent.utils.RequestError.UnknownError
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_login.btLogin
import kotlinx.android.synthetic.main.fragment_login.etPassword
import kotlinx.android.synthetic.main.fragment_login.etUsername
import kotlinx.android.synthetic.main.fragment_login.tvToRegistration

class LoginFragment : BaseFragment<LoginViewModel, LoginRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_login

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setObservers()
	}

	override fun initView() {

		setupListeners()


	}

	private fun setObservers() {
		viewModel.identityVerification.observe(this, Observer {
			when (it) {
				true -> navigation.navigateToHome()
				false -> Log.d(TAG, "setObservers: Wrong information")
			}
		})

		viewModel.serverResponse.observe(this, Observer {
			Toast.makeText(activity, "Server says:  $it", Toast.LENGTH_LONG).show()
		})
		
		viewModel.loginResponse.observe(this, Observer { 
			when (it) {
				is OnSuccess -> Log.d(TAG, "setObservers: OnSuccess")
				is OnError -> {
					val error = it.error as UnknownError
					Log.e(TAG, "setObservers: " + error.error )
				}
			}
		})
	}

	private fun setupListeners() {
		tvToRegistration.setOnClickListener {
			navigation.navigateToRegister()
		}

		btLogin.setOnClickListener {

			val envelope = EnvelopeHelloWorldRequest(HelloWorldRequest("?"))
			viewModel.getHelloWorld(envelope)
			checkProvidedInformation()
		}
	}

	private fun checkProvidedInformation() {
		val password = etPassword.text.toString()

		if (password.length >= 8) {
			val username = etUsername.text.toString()
			viewModel.checkIfUserExists(LocalUserEntity(username, password))
			viewModel.loginUserOnBackend(username,password)
		}
	}

}