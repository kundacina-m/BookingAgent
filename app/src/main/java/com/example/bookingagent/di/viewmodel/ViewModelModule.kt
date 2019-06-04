package com.example.bookingagent.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.home.HomeViewModel
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.login.LoginViewModel
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

	@Binds
	internal abstract fun bindViewModelFactory(factory: ViewModelFactory): IViewModelFactory

	@Binds
	@IntoMap
	@ViewModelKey(LoginFragment::class, LoginViewModel::class)
	internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(RegisterFragment::class, RegisterViewModel::class)
	internal abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(HomeFragment::class, HomeViewModel::class)
	internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

}