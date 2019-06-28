package com.example.bookingagent.screens.register

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.checkPasswordMatch
import kotlinx.android.synthetic.main.fragment_register.btRegister
import kotlinx.android.synthetic.main.fragment_register.etPassword
import kotlinx.android.synthetic.main.fragment_register.etPasswordRepeat
import kotlinx.android.synthetic.main.fragment_register.tvToLogin

class RegisterFragment : BaseFragment<RegisterViewModel, RegisterRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_register

	override fun setObservers() {
		viewModel.registrationStatus.observe(this, Observer {
			when (it) {
				true -> {
					Log.d(TAG, "setObservers: Successfully registered!"); navigation.navigateToLogin()
				}
				false -> Log.d(TAG, "setObservers: That username already exists!")
			}
		})
	}

	override fun initView() {
		setupListeners()
	}

	private fun setupListeners() {
		tvToLogin.setOnClickListener {
			navigation.navigateToLogin()
		}

		btRegister.setOnClickListener {

			etPassword.checkPasswordMatch(etPasswordRepeat)?.let {
				//				viewModel.registerUser(UserEntity(etUsername.text.toString(), it))
			} ?: Toast.makeText(activity!!, "Passwords don't match or too short!", Toast.LENGTH_LONG).show()

		}
	}

}