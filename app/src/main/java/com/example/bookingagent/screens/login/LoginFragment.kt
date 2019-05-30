package com.example.bookingagent.screens.login

import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_login.btLogin
import kotlinx.android.synthetic.main.fragment_login.tvToRegistration
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel>() {

	@Inject lateinit var navigation: LoginRoutes

	override fun getLayoutId(): Int = R.layout.fragment_login
	override fun getClassTypeVM(): Class<LoginViewModel> = LoginViewModel::class.java

	override fun initView() {
		setupListeners()

	}

	private fun setupListeners() {
		tvToRegistration.setOnClickListener {
//			navigation.navigateToRegister()
		}

		btLogin.setOnClickListener {
//			navigation.navigateToHome()
		}
	}

}