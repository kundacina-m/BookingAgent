package com.example.bookingagent.screens.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.LocalUserEntity
import com.example.bookingagent.data.model.helloworld.request.EnvelopeRequest
import com.example.bookingagent.data.model.helloworld.request.HelloWorldEnvelopeRequestBody
import com.example.bookingagent.data.model.helloworld.request.HelloWorldRequest
import com.example.bookingagent.data.networking.HelloWorldApi
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.btLogin
import kotlinx.android.synthetic.main.fragment_login.etPassword
import kotlinx.android.synthetic.main.fragment_login.etUsername
import kotlinx.android.synthetic.main.fragment_login.tvToRegistration
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel, LoginRoutes>() {

	@Inject lateinit var helloWorldApi: HelloWorldApi

	override fun getLayoutId(): Int = R.layout.fragment_login

	override fun initView() {
		setupListeners()
		setObservers()

	}

	private fun setObservers() {
		viewModel.identityVerification.observe(this, Observer {
			when (it) {
				true -> navigation.navigateToHome()
				false -> Log.d(TAG, "setObservers: Wrong information")
			}
		})
	}

	private fun setupListeners() {
		tvToRegistration.setOnClickListener {
			navigation.navigateToRegister()
		}

		btLogin.setOnClickListener {

			val envelope = EnvelopeRequest()
			val body = HelloWorldEnvelopeRequestBody()
			val hello = HelloWorldRequest()
			//			val header = EnvelopeHeader()
			hello.name = "?"
			body.body = hello
			//			envelope.header = header
			envelope.body = body

			helloWorldApi.getHelloWorld(envelope).subscribeOn(Schedulers.io())
				.subscribeBy(
					onError = { Log.e(TAG, it.toString(), it) },
					onSuccess = {
						serverResponse(it.body.body.name)
					}
				)

			//			checkProvidedInformation()
		}
	}

	private fun serverResponse(response: String){
		Log.d(TAG, "serverResponse: $response")

	}

	private fun checkProvidedInformation() {
		val password = etPassword.text.toString()

		if (password.length >= 8) {
			val username = etUsername.text.toString()
			viewModel.checkIfUserExists(LocalUserEntity(username, password))
		}
	}

}