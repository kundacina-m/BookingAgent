package com.example.bookingagent.screens.login

import base.BaseFragment
import com.example.bookingagent.Navigation
import com.example.bookingagent.NavigationImpl
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_login.btLogin
import kotlinx.android.synthetic.main.fragment_login.tvToRegistration

class LoginFragment : BaseFragment<LoginViewModel>() {

	val navigation : Navigation = NavigationImpl(activity!!)

	override fun getLayoutId(): Int = R.layout.fragment_login
	override fun getClassTypeVM(): Class<LoginViewModel> = LoginViewModel::class.java

	override fun initView() {
		setupListeners()
	}

	private fun setupListeners() {
		tvToRegistration.setOnClickListener {
			navigation.navigateToRegister()
		}

		btLogin.setOnClickListener {

		}
	}

	private fun navigateToRegistrationForm() {
		Navigation.findNavController(activity!!, R.id.nav_host_fragment)
			.navigate(R.id.action_loginFragment_to_registerFragment)
	}

	private fun navigateToHome()=
		Navigation.findNavController(activity!!, R.id.nav_host_fragment)
			.navigate(R.id.action_registerFragment_to_loginFragment)

}