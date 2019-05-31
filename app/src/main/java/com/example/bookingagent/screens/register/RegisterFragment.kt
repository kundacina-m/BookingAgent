package com.example.bookingagent.screens.register

import android.widget.Toast
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.utils.checkPasswordMatch
import kotlinx.android.synthetic.main.fragment_register.btRegister
import kotlinx.android.synthetic.main.fragment_register.etPassword
import kotlinx.android.synthetic.main.fragment_register.etPasswordRepeat
import kotlinx.android.synthetic.main.fragment_register.tvToLogin

class RegisterFragment : BaseFragment<RegisterViewModel, RegisterRoutes>() {

	override fun getClassTypeVM(): Class<RegisterViewModel> = RegisterViewModel::class.java
	override fun getLayoutId(): Int = R.layout.fragment_register

	override fun initView() {
		setupListeners()

	}

	private fun setupListeners() {
		tvToLogin.setOnClickListener {
			navigation.navigateToLogin()
		}

		btRegister.setOnClickListener {

			etPassword.checkPasswordMatch(etPasswordRepeat)?.let {

			} ?: Toast.makeText(activity!!, "Passwords don't match or too short!", Toast.LENGTH_LONG).show()

		}
	}

}