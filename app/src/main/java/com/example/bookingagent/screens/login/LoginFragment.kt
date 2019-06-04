package com.example.bookingagent.screens.login

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.LocalUserEntity
import com.example.bookingagent.data.model.Envelope
import com.example.bookingagent.data.model.EnvelopeBody
import com.example.bookingagent.data.model.TodayFuckingGilbert
import com.example.bookingagent.data.networking.TestApi
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.btLogin
import kotlinx.android.synthetic.main.fragment_login.etPassword
import kotlinx.android.synthetic.main.fragment_login.etUsername
import kotlinx.android.synthetic.main.fragment_login.tvToRegistration
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel, LoginRoutes>() {

	@Inject lateinit var testApi: TestApi

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

			val envelope = Envelope()
			val body = EnvelopeBody()
			body.gilbert = TodayFuckingGilbert()
			envelope.body = body

			testApi.getResponse(envelope).subscribeOn(Schedulers.io())
				.subscribeBy(
					onError ={ Log.e(TAG,it.toString(),it) },
					onSuccess = {Log.d(TAG, "setupListeners: ${it}")}
				)







//			checkProvidedInformation()
		}
	}

	private fun checkProvidedInformation(){
		val password = etPassword.text.toString()

		if (password.length >= 8){
			val username = etUsername.text.toString()
			viewModel.checkIfUserExists(LocalUserEntity(username,password))
		}
	}

}